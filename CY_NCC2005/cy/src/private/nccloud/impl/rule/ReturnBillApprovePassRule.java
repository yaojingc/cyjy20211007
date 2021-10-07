package nccloud.impl.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ufsoft.script.base.UfoDouble;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.busibean.SysinitAccessorForNcc;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.crm.CrmUtil;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.bd.bankaccount.BankAccSubVO;
import nc.vo.bd.bankaccount.BankAccbasVO;
import nc.vo.bd.cust.CustbankVO;
import nc.vo.ct.ar.entity.CtArTermVO;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.cy.returncost.ReturncostHVO;
import nc.vo.fct.ar.entity.AggCtArVO;
import nc.vo.fct.ar.entity.CtArBVO;
import nc.vo.fct.ar.entity.CtArVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;

/**
  * 退费申请单，审批通过后的，生成付款单和应收单：
	<p>业务规则							    	付款单	应收单	应收单(红字)		合同退费金额(更新) </p>
	<p>*退学不退款业务*																			 </p>
	<p>当累计原币应收金额<累计原币收款金额	=》	×		√		×				×				 </p>
	<p>当累计原币应收金额=累计原币收款金额	=》	×		×		×				×				 </p>
	<p>当累计原币应收金额>累计原币收款金额	=》	×		×		√				×				 </p>
	<p>*退学退款业务*																			 </p>
	<p>当累计原币应收金额<累计原币收款金额	=》	√		√		×				√				 </p>
	<p>当累计原币应收金额=累计原币收款金额	=》	√		×		×				√				 </p>
	<p>当累计原币应收金额>累计原币收款金额	=》	√		×		√				√				 </p>
 * @author csh
 * @param <T>
 */
public class ReturnBillApprovePassRule<T extends AbstractBill> implements IRule<T>  {
	
	private static IPFBusiAction pfBusiAction = null;
	
	private static BaseDAO baseDao;
	
	/**
	 * 动作脚本接口
	 * 
	 * @return
	 */
	private static IPFBusiAction getPFBusiAction() {
		if (pfBusiAction == null)
			pfBusiAction = NCLocator.getInstance().lookup(IPFBusiAction.class);
		return pfBusiAction;
	}
	
	private static BaseDAO getBaseDao() {
		if (baseDao == null) {
			baseDao = new BaseDAO();
		}
		return baseDao;
	}
	
	/**
	 * 付款银行账户
	 */
	private String gl_payaccount = null;
			
	/**
	 * 收款银行账户
	 */
	private String gl_recaccount = null;
	
	/**
	 * 税率
	 */
	private UFDouble gl_taxrate = null;
	
	/**
	 * 税码
	 */
	private String gl_pk_taxcode = null;
	
	/**
	 * 业务日期
	 */
	private String gl_busiDateStr = null;

	@Override
	public void process(T[] aggvos) {
		for(T obj : aggvos) {
			try {
				AggReturncostHVO aggvo = (AggReturncostHVO) obj;
				ReturncostHVO headvo = aggvo.getParentVO();
				
				if(headvo.getApprovestatus() == 1) {
					String def1 = headvo.getDef1();//退学类型（退学不退款、退学退款）
					String def2 = headvo.getDef2();//学生(客户)
					UFDouble receivable = headvo.getReceivable();//累计原币应收金额
					UFDouble collection = headvo.getCollection();//累计原币收款金额
					
					String txtk = AppletsParams.Txtk;//退学类型自定义档案-退学退款pk
					String txbtk = AppletsParams.Txbtk;//退学类型自定义档案-退学不退款pk
					
					IReturncosthvoMaintain service = NCLocator.getInstance().lookup(IReturncosthvoMaintain.class);
					int countentNum = service.countCtByStudent(def2);
					if(countentNum > 1) {
						//一个学生只能存在一个合同，否则当前退款申请单找不到到底是依据那个合同进行退款的
						throw new BusinessException("单据号"+aggvo.getParentVO().getBill_no()+"的退费申请单所关联的客户信息存在多个收款合同，请检查！");
					}
					
					if(def1.equals(txbtk)) {
						//退学不退款
						//当累计原币应收金额<累计原币收款金额  =》  1、生成付款单
						if(receivable.compareTo(collection)<0) {
							payBillGenerate(aggvo, def2, false);
						}
						
						//当累计原币应收金额>累计原币收款金额  =》  1、生成红字应收单
						if(receivable.compareTo(collection)>0) {
							receivableBillGenerate(aggvo, def2, false, true);
						}
					}
					
					if(def1.equals(txtk)) {
						Map<String, Object> mapData = new HashMap<String, Object>();
						//退学退款
						//当累计原币应收金额<累计原币收款金额  =》  1、生成付款单   2、更新合同金额 3、生成应收单 4、更新合同金额
						if(receivable.compareTo(collection)<0) {
							mapData = payBillGenerate(aggvo, def2, true);
							Map<String, Object> map = receivableBillGenerate(aggvo, def2, true, false);
							mapData.putAll(map);
						}
						
						//当累计原币应收金额=累计原币收款金额  =》  1、生成付款单  2、更新合同金额
						if(receivable.compareTo(collection)==0) {
							mapData = payBillGenerate(aggvo, def2, true);
						}
						
						//当累计原币应收金额>累计原币收款金额  =》  1、生成付款单 2、更新合同金额 3、生成红字应收单 4、更新合同金额
						if(receivable.compareTo(collection)>0) {
							mapData = payBillGenerate(aggvo, def2, true);
							Map<String, Object> map = receivableBillGenerate(aggvo, def2, true, true);
							mapData.putAll(map);
						}
						
						//反写付款单和应收单的金额至退费申请单上
						endWriteUpdateBill(aggvo, mapData);
					}
				}
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	
	}
	
	/**
	  * 退学退款业务模式下，业务单据生成后的数据反写操作
	  * <p>付款单和应收单的金额，反写到退费申请单上，用作记录，便于在取消审批时，可用此处的金额进行合同金额进行还原</p>
	 * @param aggvo
	 * @param mapData
	 */
	private void endWriteUpdateBill(AggReturncostHVO aggvo, Map<String, Object> mapData) throws BusinessException {
		UFDouble paybillMoney = (UFDouble) mapData.get("paybillMoney");
		UFDouble receivableBillMoney = (UFDouble) mapData.get("receivableBillMoney");
		if(paybillMoney!=null || receivableBillMoney!=null) {
			aggvo.getParentVO().setDef3(paybillMoney==null?null:paybillMoney.toString());//付款单的金额
			aggvo.getParentVO().setDef4(receivableBillMoney==null?null:receivableBillMoney.toString());//应收单的金额
			aggvo.getParentVO().setStatus(VOStatus.UPDATED);
			Object[] objs = getPFBusiAction().processBatch("SAVEBASE", "TFSQ", new AggReturncostHVO[] { aggvo }, null, null, new HashMap());
			if(objs==null || objs.length==0) {
				throw new BusinessException("付款单和应收单金额反写至退费申请单失败！");
			}
		}
	}
	
	/**
	  * 生成付款单数据，并更新合同金额
	 * @param aggReturncostHVO 退费申请单数据
	 * @param pk_customer 客户主键
	 * @param isTxtk 是否退学退款模式（如果为退学退款模式则需要更新合同金额数据:累计原币预收款金额、累计原币收款金额、累计原币预收款金额、累计原币收款金额）
	 * @throws BusinessException
	 */
	private Map<String,Object> payBillGenerate(AggReturncostHVO aggReturncostHVO, String pk_customer, boolean isTxtk) throws BusinessException {
		AggCtArVO aggCtArVO = getCtArVO(aggReturncostHVO.getParentVO(), pk_customer);
		AggPayBillVO aggvo = assemblePayBillData(aggCtArVO, aggReturncostHVO);
		//调用动作脚本生成付款单数据，并进行审核
		HashMap<String,Object> saveHashMap = new HashMap<String, Object>();
		saveHashMap.put("notechecked", "notechecked");
		saveHashMap.put("NCCLOUD_ENTRY", true);
		saveHashMap.put("IS_RELOADBILL", true);
		saveHashMap.put("nolockandconsist", "Y");
		Object[] objs = getPFBusiAction().processBatch("SAVE", "D3", new AggPayBillVO[] { aggvo }, null, null, saveHashMap);
		if(objs!=null && objs.length>0) {
			AggPayBillVO aggPayBillVO = (AggPayBillVO) objs[0];
			HashMap<String,Object> commitHashMap = new HashMap<String, Object>();
			commitHashMap.put("batch", true);
			commitHashMap.put("CommitNoFlowBatch", true);
			commitHashMap.put("NCCLOUD_ENTRY", true);
			//objs = getPFBusiAction().processBatch("COMMIT", "D3", new AggPayBillVO[] { aggPayBillVO }, null, null, commitHashMap);
			objs = (Object[]) getPFBusiAction().processAction("COMMIT", "D3", null, aggPayBillVO, null, commitHashMap);
			if(objs==null || objs.length==0) {
				throw new  BusinessException("付款单提交失败！");
			}
			aggPayBillVO = (AggPayBillVO) objs[0];
			Object obj = getPFBusiAction().processAction("APPROVE", "D3", null, aggPayBillVO, null, commitHashMap);
			if(obj==null) {
				throw new  BusinessException("付款单审核失败！");
			}
		}else {
			throw new  BusinessException("付款单生成失败！");
		}
		
		if(isTxtk) {
			//更新合同上的金额
			ReturncostHVO returncostHVO = aggReturncostHVO.getParentVO();
			
			CtArVO ctArvo = aggCtArVO.getParentVO();
			CtArBVO[] ctArBVOs = (CtArBVO[]) aggCtArVO.getChildren(CtArBVO.class);
			UFDouble norigplanamount = ctArvo.getNorigplanamount().sub(returncostHVO.getRetuencost());//累计原币预收款金额
			UFDouble norigpshamount = ctArvo.getNorigpshamount().sub(returncostHVO.getRetuencost());//累计原币收款金额
			UFDouble noriplangpmny = ctArBVOs[0].getNoriplangpmny().sub(returncostHVO.getRetuencost());//累计原币预收款金额
			UFDouble noritotalgpmny = ctArBVOs[0].getNoritotalgpmny().sub(returncostHVO.getRetuencost());//累计原币收款金额
	
			ctArvo.setNorigplanamount(norigplanamount);
			ctArvo.setNorigpshamount(norigpshamount);
			ctArvo.setStatus(VOStatus.UPDATED);
			ctArBVOs[0].setNoriplangpmny(noriplangpmny);
			ctArBVOs[0].setNoritotalgpmny(noritotalgpmny);
			ctArBVOs[0].setStatus(VOStatus.UPDATED);
	
			Object[] aggCtArVOObjs = getPFBusiAction().processBatch("SAVEBASE", "FCT2", new AggCtArVO[] { aggCtArVO }, null, null, new HashMap());
			if(aggCtArVOObjs==null || aggCtArVOObjs.length==0) {
				throw new  BusinessException("付款单生成后，合同更新失败！");
			}
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("paybillMoney", aggvo.getHeadVO().getMoney());
		
		return map;
	}
	
	/**
	 * 组装付款单VO
	 * @param aggCtArVO 收款合同数据
	 * @param aggReturncostHVO 退费申请单数据
	 * @return
	 * @throws BusinessException 
	 */
	private AggPayBillVO assemblePayBillData(AggCtArVO aggCtArVO, AggReturncostHVO aggReturncostHVO) throws BusinessException {
		//退费申请单数据
		ReturncostHVO returncostHVO = aggReturncostHVO.getParentVO();
		
		//收款合同数据
		CtArVO ctArVO = aggCtArVO.getParentVO();
		CtArBVO[] ctArTermVOs = (CtArBVO[]) aggCtArVO.getChildren(CtArBVO.class);
		
		//付款银行账户
		String payaccount = getPayaccount(ctArVO.getPk_org());
		this.gl_payaccount = payaccount;
		
		//收款银行账户
		String recaccount = getCustomerBank(returncostHVO.getBankaccount(), returncostHVO.getDef2());
		this.gl_recaccount = recaccount;
		
		//物料的税码税率
		Map<String, Object> map = getTaxByMaterial(ctArTermVOs[0].getPk_material());
		UFDouble taxrate = null;//税率
		String pk_taxcode = null;//税码
		if(map!=null && !map.isEmpty()) {
			taxrate = map.get("taxrate")==null?new UFDouble(0):new UFDouble(map.get("taxrate").toString());//税率
			this.gl_taxrate = taxrate;
			pk_taxcode = map.get("pk_taxcode")==null?null:map.get("pk_taxcode").toString();//税码
			this.gl_pk_taxcode = pk_taxcode;
		}
		
		String pk_org = ctArVO.getPk_org();
		String pk_org_v = ctArVO.getPk_org_v();
		String pk_group = ctArVO.getPk_group();
		
		//业务日期
		String busiDateStr = getBusiDate(pk_org);
		UFDate busiDate = new UFDate(busiDateStr);
		this.gl_busiDateStr = busiDateStr;

		//退款金额
		UFDouble retuencost = returncostHVO.getRetuencost();
		//业务流程（默认付款结算）
		String pk_busitype = "0001L1100000000011QY";
		//币种（默认人民币）
		String pk_currtype = "1002Z0100000000001K1";
		//付款类型（默认付款单）
		String pk_tradetypeid = "0001L110000000001CQM";
		//国家（默认中国）
		String pk_country = "0001Z010000000079UJJ";
		//付款业务类型（默认货款）
		String pk_recpaytype = "GLOBZ300000000000001";
		
		UFDouble notax_de = returncostHVO.getRetuencost().div(new UFDouble(1).add(taxrate));//借方原币无税金额/组织本币无税金额
		UFDouble local_tax_de = returncostHVO.getRetuencost().sub(notax_de);//税额
		
		//进行组装付款单数据
		AggPayBillVO aggvo = new AggPayBillVO();
		//表头数据组装
		PayBillVO parentVO = new PayBillVO();
		parentVO.setApprovestatus(-1);//审批状态
		parentVO.setBillclass("fk");//单据大类
		parentVO.setBilldate(busiDate);//单据日期
		parentVO.setBillmaker(ctArVO.getBillmaker());//制单人
		parentVO.setBillperiod(busiDateStr.substring(5,7));//单据会计期间
		parentVO.setBillstatus(-1);//单据状态
		parentVO.setBillyear(busiDateStr.substring(0,4));//单据会计年度
		parentVO.setCreationtime(new UFDateTime());//创建时间
		parentVO.setCreator(ctArVO.getCreator());//创建人
		parentVO.setDr(0);
		parentVO.setEffectstatus(0);//生效状态
		parentVO.setGloballocal(new UFDouble(0));//全局本币金额
		parentVO.setGrouplocal(new UFDouble(0));//集团本币金额
		parentVO.setIsflowbill(UFBoolean.FALSE);//是否流程单据
		parentVO.setIsforce(UFBoolean.FALSE);//承付
		parentVO.setIsfromindependent(UFBoolean.FALSE);//是否独立结算信息
		parentVO.setIsinit(UFBoolean.FALSE);//期初标志
		parentVO.setIsmandatepay(UFBoolean.FALSE);//提交资金组织结算
		parentVO.setIsnetpayready(UFBoolean.FALSE);//是否已经补录
		parentVO.setIsonlinepay(UFBoolean.FALSE);//是否网上支付
		parentVO.setIsreded(UFBoolean.FALSE);//是否红冲过
		parentVO.setIsrefund(UFBoolean.FALSE);//是否退款
		parentVO.setIsurgent(UFBoolean.FALSE);//紧急
		parentVO.setLocal_money(retuencost);//组织本币金额
		parentVO.setMoney(retuencost);//原币金额
		parentVO.setPk_billtype("F3");//单据类型编码
		parentVO.setPk_busitype(pk_busitype);//业务流程
		parentVO.setPk_currtype(pk_currtype);//币种
		parentVO.setPk_fiorg(pk_org);//财务组织
		parentVO.setPk_fiorg_v(pk_org_v);//财务组织版本主键
		parentVO.setPk_group(pk_group);//所属集团
		parentVO.setPk_org(pk_org);//付款财务组织
		parentVO.setPk_org_v(pk_org_v);//付款财务组织版本主键
		parentVO.setPk_tradetype("D3");//付款类型code
		parentVO.setPk_tradetypeid(pk_tradetypeid);//付款类型
		parentVO.setRececountryid(pk_country);//收货国
		parentVO.setSaga_frozen(0);//冻结状态
		parentVO.setSaga_status(0);//事务状态
		parentVO.setSddreversalflag(UFBoolean.FALSE);//直接借记退回标记
		parentVO.setSett_org(pk_org);//结算财务组织
		parentVO.setSett_org_v(pk_org_v);//结算财务组织版本主键
		parentVO.setSrc_syscode(1);//单据来源系统
		parentVO.setSyscode(1);//单据所属系统
		parentVO.setTaxcountryid(pk_country);//报税国
		parentVO.setDef1(ctArVO.getVdef1());//大区
		parentVO.setDef2(ctArVO.getVdef2());//省份
		parentVO.setDef3(ctArVO.getVdef3());//学校
		parentVO.setStatus(VOStatus.NEW);
		aggvo.setParentVO(parentVO);
		
		//表体数据组装
		PayBillItemVO itemVO = new PayBillItemVO();
		itemVO.setAgentreceivelocal(new UFDouble(0));//托收组织本币
		itemVO.setAgentreceiveprimal(new UFDouble(0));//托收原币金额
		//itemVO.setBankrelated_code(bankrelated_code);//对账标识码
		itemVO.setBillclass("fk");//单据大类
		itemVO.setBilldate(busiDate);//单据日期
		itemVO.setBusidate(busiDate);//起算日期
		itemVO.setBuysellflag(2);//购销类型
		itemVO.setCustomer(ctArVO.getPk_customer());//客户
		itemVO.setDirect_checkno(UFBoolean.FALSE);//直联电票
		itemVO.setDirection(1);//方向
		itemVO.setDr(0);
		itemVO.setForcemoney(new UFDouble(0));//
		itemVO.setGlobalagentreceivelocal(new UFDouble(0));//托收全局本币
		itemVO.setGlobalbalance(new UFDouble(0));//全局本币余额
		itemVO.setGlobaldebit(new UFDouble(0));//全局本币金额(借方)
		itemVO.setGlobalnotax_de(new UFDouble(0));//全局本币无税金额(借方)
		itemVO.setGlobalrate(new UFDouble(0));//全局本币汇率
		itemVO.setGlobaltax_de(new UFDouble(0));//
		itemVO.setGroupagentreceivelocal(new UFDouble(0));//托收集团本币
		itemVO.setGroupbalance(new UFDouble(0));//集团本币余额
		itemVO.setGroupdebit(new UFDouble(0));//集团本币金额(借方)
		itemVO.setGroupnotax_de(new UFDouble(0));//集团本币无税金额(借方)
		itemVO.setGrouprate(new UFDouble(0));//集团本币汇率
		itemVO.setGrouptax_de(new UFDouble(0));//
		itemVO.setIsdiscount(UFBoolean.FALSE);//是否抵扣金额
		itemVO.setIsrefused(UFBoolean.FALSE);//是否被拒付
		itemVO.setIsurgent(UFBoolean.FALSE);//紧急
		itemVO.setLocal_money_bal(retuencost);//组织本币余额
		itemVO.setLocal_money_de(retuencost);//组织本币金额(借方)
		itemVO.setLocal_notax_de(notax_de);//组织本币无税金额(借方)
		itemVO.setLocal_price(new UFDouble(0));//本币单价
		itemVO.setLocal_tax_de(local_tax_de);//税额
		itemVO.setLocal_taxprice(new UFDouble(0));//本币含税单价
		itemVO.setMaterial(ctArTermVOs[0].getPk_material());//物料
		itemVO.setMaterial_src(ctArTermVOs[0].getPk_material());//原始物料
		itemVO.setMoney_bal(retuencost);//原币余额
		itemVO.setMoney_de(retuencost);//借方原币金额
		itemVO.setNosubtax(new UFDouble(0));//不可抵扣税额
		itemVO.setNosubtaxrate(new UFDouble(0));//不可抵扣税率
		itemVO.setNotax_de(notax_de);//借方原币无税金额
		itemVO.setObjtype(0);//往来对象
		itemVO.setOccupationmny(retuencost);//预占用核销原币余额
		//itemVO.setOrdercubasdoc(ordercubasdoc);//订单供应商
		itemVO.setPausetransact(UFBoolean.FALSE);//挂起标志
		itemVO.setPayaccount(payaccount);//付款银行账户
		itemVO.setPk_billtype("F3");//单据类型编码
		itemVO.setPk_currtype(pk_currtype);//	币种
		itemVO.setPk_fiorg(pk_org);//财务组织
		itemVO.setPk_fiorg_v(pk_org_v);//财务组织版本主键
		itemVO.setPk_group(pk_group);//所属集团
		itemVO.setPk_org(pk_org_v);//付款财务组织
		itemVO.setPk_org_v(pk_org_v);//付款财务组织版本主键
		itemVO.setPk_recpaytype(pk_recpaytype);//付款业务类型
		itemVO.setPk_tradetype("D3");//付款类型code
		itemVO.setPk_tradetypeid(pk_tradetypeid);//付款类型
		itemVO.setPrepay(0);//付款性质
		itemVO.setPrice(new UFDouble(0));//单价
		itemVO.setQuantity_bal(new UFDouble(0));//数量余额
		itemVO.setRate(new UFDouble(1));//组织本币汇率
		itemVO.setRecaccount(recaccount);//收款银行账户
		itemVO.setRowno(10);//单据分录号
		itemVO.setSendcountryid(pk_country);//发货国
		itemVO.setSett_org(pk_org);//结算财务组织
		itemVO.setSett_org_v(pk_org_v);//结算财务组织版本主键
		itemVO.setSettlemoney(new UFDouble(0));//应付金额
		//itemVO.settax_de//
		itemVO.setTaxcodeid(pk_taxcode);//税码
		itemVO.setTaxprice(new UFDouble(0));//含税单价
		itemVO.setTaxrate(taxrate);//税率
		itemVO.setTaxtype(1);//扣税类别
		
		itemVO.setTop_billid(returncostHVO.getPrimaryKey());//上层单据主键	
		//itemVO.setTop_itemid(top_itemid);//上层单据行主键	
		itemVO.setTop_billtype("TFSQ");//上层单据类型
		//itemVO.setTop_tradetype(top_tradetype);//上层交易类型		
		//itemVO.setSrc_tradetype(src_tradetype);//源头交易类型
		itemVO.setSrc_billtype("TFSQ");//源头单据类型
		itemVO.setSrc_billid(returncostHVO.getPrimaryKey());//源头单据主键	
		//itemVO.setSrc_itemid(src_itemid);//源头单据行主键
		itemVO.setStatus(VOStatus.NEW);
		
		aggvo.setChildren(PayBillItemVO.class, new PayBillItemVO[] {itemVO});
		return aggvo;
	}
	
	/**
	  * 生成应收单数据，并更新合同金额
	 * @param aggReturncostHVO 退费申请单数据
	 * @param pk_customer 客户主键
	 * @param isTxtk 是否退学退款模式（如果为退学退款模式则需要更新合同金额数据:累计原币应收金额、累计原币应收金额）
	 * @param isRedBill 是否红字应收单
	 * @throws BusinessException
	 */
	private Map<String,Object> receivableBillGenerate(AggReturncostHVO aggReturncostHVO, String pk_customer, boolean isTxtk, boolean isRedBill) throws BusinessException {
		AggCtArVO aggCtArVO = getCtArVO(aggReturncostHVO.getParentVO(), pk_customer);
		AggReceivableBillVO aggReceivableBillVO = assembleReceivableBillData(aggCtArVO, aggReturncostHVO);
		
		//调用动作脚本生成付款单数据，并进行审核
		HashMap<String,Object> saveHashMap = new HashMap<String, Object>();
		saveHashMap.put("notechecked", "notechecked");
		saveHashMap.put("NCCLOUD_ENTRY", true);
		saveHashMap.put("IS_RELOADBILL", true);
		saveHashMap.put("nolockandconsist", "Y");
		Object[] objs = getPFBusiAction().processBatch("SAVE", "D0", new AggReceivableBillVO[] { aggReceivableBillVO }, null, null, saveHashMap);
		if(objs==null || objs.length==0) {
			throw new  BusinessException("应收单生成失败！");
		}
		
		if(isTxtk) {
			CtArVO ctArVO = aggCtArVO.getParentVO();
			UFDouble money = ctArVO.getNorigpshamount().sub(ctArVO.getNorigcopamount());
			
			//更新合同上的金额
			CtArVO ctArvo = aggCtArVO.getParentVO();
			CtArBVO[] ctArBVOs = (CtArBVO[]) aggCtArVO.getChildren(CtArBVO.class);

			UFDouble norigcopamount = ctArvo.getNorigcopamount().add(money);//累计原币应收金额
			UFDouble noricopegpmny = ctArBVOs[0].getNoricopegpmny().add(money);//累计原币应收金额

			ctArvo.setNorigcopamount(norigcopamount);
			ctArvo.setStatus(VOStatus.UPDATED);
			ctArBVOs[0].setNoricopegpmny(noricopegpmny);
			ctArBVOs[0].setStatus(VOStatus.UPDATED);

			Object[] aggCtArVOObjs = getPFBusiAction().processBatch("SAVEBASE", "FCT2", new AggCtArVO[] { aggCtArVO }, null, null, new HashMap());
			if(aggCtArVOObjs==null || aggCtArVOObjs.length==0) {
				throw new  BusinessException("应收单生成后，合同更新失败！");
			}
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("receivableBillMoney", aggReceivableBillVO.getHeadVO().getMoney());
		
		return map;
	}
	
	/**
	  * 生成应收单数据
	 * @param aggCtArVO 收款合同数据
	 * @param aggReturncostHVO 退费申请单数据
	 * @return
	 * @throws BusinessException
	 */
	private AggReceivableBillVO assembleReceivableBillData(AggCtArVO aggCtArVO, AggReturncostHVO aggReturncostHVO) throws BusinessException {
		//退费申请单数据
		ReturncostHVO returncostHVO = aggReturncostHVO.getParentVO();
		
		//收款合同数据
		CtArVO ctArVO = aggCtArVO.getParentVO();
		CtArBVO[] ctArTermVOs = (CtArBVO[]) aggCtArVO.getChildren(CtArBVO.class);
		
		//付款银行账户
		//String payaccount = this.gl_payaccount==null?getPayaccount(ctArVO.getPk_org()):this.gl_payaccount;
		
		//收款银行账户
		//String recaccount = this.gl_recaccount==null?getCustomerBank(returncostHVO.getBankaccount(), returncostHVO.getDef2()):this.gl_recaccount;
		
		//物料的税码税率
		UFDouble taxrate = this.gl_taxrate;//税率
		String pk_taxcode = this.gl_pk_taxcode;//税码
		if(taxrate==null || pk_taxcode==null) {
			Map<String, Object> map = getTaxByMaterial(ctArTermVOs[0].getPk_material());
			if(map!=null && !map.isEmpty()) {
				taxrate = map.get("taxrate")==null?new UFDouble(0):new UFDouble(map.get("taxrate").toString());//税率
				pk_taxcode = map.get("pk_taxcode")==null?null:map.get("pk_taxcode").toString();//税码
			}else {
				taxrate = new UFDouble(0);
			}
		}
		
		String pk_org = ctArVO.getPk_org();
		String pk_org_v = ctArVO.getPk_org_v();
		String pk_group = ctArVO.getPk_group();
		
		//业务日期
		String busiDateStr = this.gl_busiDateStr==null?getBusiDate(pk_org):this.gl_busiDateStr;
		UFDate busiDate = new UFDate(busiDateStr);

		//借方原币金额\组织本币金额
		UFDouble money = ctArVO.getNorigpshamount().sub(ctArVO.getNorigcopamount());
		
		//组织本币无税金额
		UFDouble local_notax_de = money.div(new UFDouble(1).add(taxrate));
		
		//税额
		UFDouble local_tax_de = money.sub(local_notax_de);
		
		//业务流程（默认收款）
		String pk_busitype = "0001L1100000000011QR";
		//币种（默认人民币）
		String pk_currtype = "1002Z0100000000001K1";
		//应收类型（默认应收单）
		String pk_tradetypeid = "0001L110000000001CQJ";
		//国家（默认中国）
		String pk_country = "0001Z010000000079UJJ";
		
		AggReceivableBillVO aggReceivableBillVO = new AggReceivableBillVO();
		ReceivableBillVO receivableBillVO = new ReceivableBillVO();
		receivableBillVO.setApprovestatus(-1);//审批状态
		receivableBillVO.setBillclass("ys");//单据大类
		receivableBillVO.setBilldate(busiDate);//单据日期
		receivableBillVO.setBillmaker(ctArVO.getBillmaker());//制单人
		receivableBillVO.setBillperiod(busiDateStr.substring(5,7));//单据会计期间
		receivableBillVO.setBillstatus(-1);//单据状态
		receivableBillVO.setBillyear(busiDateStr.substring(0,4));//单据会计年度
		receivableBillVO.setCreationtime(new UFDateTime());//创建时间
		receivableBillVO.setCreator(ctArVO.getCreator());//创建人
		receivableBillVO.setDef1(ctArVO.getVdef1());//大区
		receivableBillVO.setDef2(ctArVO.getVdef2());//省份
		receivableBillVO.setDef3(ctArVO.getVdef3());//学校
		receivableBillVO.setDr(0);//
		receivableBillVO.setEffectstatus(0);//生效状态
		receivableBillVO.setGloballocal(new UFDouble(0));//全局本币金额
		receivableBillVO.setGrouplocal(new UFDouble(0));//集团本币金额
		receivableBillVO.setIsflowbill(UFBoolean.FALSE);//是否流程单据
		receivableBillVO.setIsinit(UFBoolean.FALSE);//期初标志
		receivableBillVO.setIsreded(UFBoolean.FALSE);//是否红冲过
		receivableBillVO.setIsrefund(UFBoolean.FALSE);//是否退款
		receivableBillVO.setLocal_money(money);//组织本币金额(收款合同累计收款金额-累计应收金额)
		receivableBillVO.setMoney(money);//原币金额(收款合同累计收款金额-累计应收金额)
		receivableBillVO.setPk_billtype("F0");//单据类型编码
		receivableBillVO.setPk_busitype(pk_busitype);//业务流程
		receivableBillVO.setPk_currtype(pk_currtype);//币种
		receivableBillVO.setPk_fiorg(pk_org);//废弃财务组织
		receivableBillVO.setPk_fiorg_v(pk_org_v);//废弃财务组织版本
		receivableBillVO.setPk_group(pk_group);//所属集团
		receivableBillVO.setPk_org(pk_org);//应收财务组织
		receivableBillVO.setPk_org_v(pk_org_v);//应收财务组织版本
		receivableBillVO.setPk_tradetype("D0");//应收类型code
		receivableBillVO.setPk_tradetypeid(pk_tradetypeid);//应收类型
		receivableBillVO.setSaga_frozen(0);//冻结状态
		receivableBillVO.setSaga_status(0);//事务状态
		receivableBillVO.setSendcountryid(pk_country);//发货国
		receivableBillVO.setSett_org(pk_org);//结算财务组织
		receivableBillVO.setSett_org_v(pk_org_v);//结算财务组织版本
		receivableBillVO.setSrc_syscode(20);//单据来源系统
		receivableBillVO.setSyscode(0);//单据所属系统
		receivableBillVO.setTaxcountryid(pk_country);//报税国
		receivableBillVO.setStatus(VOStatus.NEW);
		aggReceivableBillVO.setParentVO(receivableBillVO);
		
		ReceivableBillItemVO receivableBillItemVO = new ReceivableBillItemVO();
		receivableBillItemVO.setBillclass("ys");//单据大类
		receivableBillItemVO.setBilldate(busiDate);//单据日期
		receivableBillItemVO.setBusidate(busiDate);//起算日期
		receivableBillItemVO.setBuysellflag(1);//购销类型
		receivableBillItemVO.setCaltaxmny(local_notax_de);//计税金额
		receivableBillItemVO.setContractno(ctArVO.getVbillcode());//合同号
		receivableBillItemVO.setCustomer(ctArVO.getPk_customer());//客户
		receivableBillItemVO.setDirection(1);//方向
		receivableBillItemVO.setDr(0);//
		receivableBillItemVO.setGlobalbalance(new UFDouble(0));//全局本币余额
		receivableBillItemVO.setGlobaldebit(new UFDouble(0));//全局本币金额(借方)
		receivableBillItemVO.setGlobalnotax_de(new UFDouble(0));//全局本币无税金额(借方)
		receivableBillItemVO.setGlobalrate(new UFDouble(0));//全局本币汇率
		receivableBillItemVO.setGlobaltax_de(new UFDouble(0));//
		receivableBillItemVO.setGroupbalance(new UFDouble(0));//集团本币余额
		receivableBillItemVO.setGroupdebit(new UFDouble(0));//集团本币金额(借方)
		receivableBillItemVO.setGroupnotax_de(new UFDouble(0));//集团本币无税金额(借方)
		receivableBillItemVO.setGrouprate(new UFDouble(1));//集团本币汇率
		receivableBillItemVO.setGrouptax_de(new UFDouble(0));//
		receivableBillItemVO.setLocal_money_bal(new UFDouble(0));//组织本币余额
		receivableBillItemVO.setLocal_money_de(money);//组织本币金额(收款合同累计收款金额-累计应收金额)
		receivableBillItemVO.setLocal_notax_de(local_notax_de);//组织本币无税金额(（收款合同累计收款金额-累计应收金额）/（1+税率）)
		receivableBillItemVO.setLocal_price(local_notax_de);//本币单价
		receivableBillItemVO.setLocal_tax_de(local_tax_de);//税额(（收款合同累计收款金额-累计应收金额）-（收款合同累计收款金额-累计应收金额）/（1+税率）)
		receivableBillItemVO.setLocal_taxprice(money);//本币含税单价
		receivableBillItemVO.setMaterial(ctArTermVOs[0].getPk_material());//物料
		receivableBillItemVO.setMaterial_src(ctArTermVOs[0].getPk_material());//原始物料
		receivableBillItemVO.setMoney_bal(new UFDouble(0));//原币余额
		receivableBillItemVO.setMoney_de(money);//借方原币金额(收款合同累计收款金额-累计应收金额)
		receivableBillItemVO.setNotax_de(local_notax_de);//借方原币无税金额(（收款合同累计收款金额-累计应收金额）/（1+税率）)
		receivableBillItemVO.setObjtype(0);//往来对象
		receivableBillItemVO.setOccupationmny(new UFDouble(0));//预占用原币余额
		receivableBillItemVO.setOrdercubasdoc(ctArVO.getPk_customer());//订单客户
		receivableBillItemVO.setPausetransact(UFBoolean.FALSE);//挂起标志
		receivableBillItemVO.setPk_billtype("F0");//单据类型编码
		receivableBillItemVO.setPk_currtype(pk_currtype);//币种
		//receivableBillItemVO.setPk_deptid(pk_deptid);//部门
		//receivableBillItemVO.setPk_deptid_v(pkDeptidV);//部门
		receivableBillItemVO.setPk_fiorg(pk_org);//废弃财务组织
		receivableBillItemVO.setPk_fiorg_v(pk_org_v);//废弃财务组织版本
		receivableBillItemVO.setPk_group(pk_group);//所属集团
		receivableBillItemVO.setPk_org(pk_org_v);//应收财务组织
		receivableBillItemVO.setPk_org_v(pk_org_v);//应收财务组织版本
		//receivableBillItemVO.setPk_psndoc(pk_psndoc);//业务员
		receivableBillItemVO.setPk_tradetype("D0");//应收类型code
		receivableBillItemVO.setPk_tradetypeid(pk_tradetypeid);//应收类型
		receivableBillItemVO.setPostprice(new UFDouble(0));//报价单位含税单价
		receivableBillItemVO.setPostpricenotax(new UFDouble(0));//报价单位无税单价
		receivableBillItemVO.setPostquantity(new UFDouble(0));//报价单位数量
		receivableBillItemVO.setPrice(local_notax_de);//单价
		receivableBillItemVO.setQuantity_bal(new UFDouble(0));//数量余额
		receivableBillItemVO.setQuantity_de(new UFDouble(1));//借方数量
		receivableBillItemVO.setRate(new UFDouble(1));//组织本币汇率
		receivableBillItemVO.setRececountryid(pk_country);//收货国
		receivableBillItemVO.setRowno(0);//单据分录号
		receivableBillItemVO.setRowtype(0);//行类型
		receivableBillItemVO.setSett_org(pk_org);//结算财务组织
		receivableBillItemVO.setSett_org_v(pk_org_v);//结算财务组织版本
		receivableBillItemVO.setSettlemoney(new UFDouble(0));//收款金额
		receivableBillItemVO.setTaxcodeid(pk_taxcode);//税码
		receivableBillItemVO.setTaxprice(money);//含税单价(收款合同累计收款金额-累计应收金额)
		receivableBillItemVO.setTaxrate(taxrate);//税率
		receivableBillItemVO.setTaxtype(1);//扣税类别
		receivableBillItemVO.setTriatradeflag(UFBoolean.FALSE);//三角贸易

		receivableBillItemVO.setTop_billid(returncostHVO.getPrimaryKey());//上层单据主键
		//receivableBillItemVO.setTop_itemid(top_itemid);//上层单据行主键
		receivableBillItemVO.setTop_billtype("TFSQ");//上层单据类型
		//receivableBillItemVO.setTop_tradetype(top_tradetype);//上层交易类型	
		//receivableBillItemVO.setSrc_tradetype(src_tradetype);//源头交易类型
		receivableBillItemVO.setSrc_billtype("TFSQ");//源头单据类型
		receivableBillItemVO.setSrc_billid(returncostHVO.getPrimaryKey());//源头单据主键
		//receivableBillItemVO.setSrc_itemid(src_itemid);//源头单据行主键
		receivableBillItemVO.setStatus(VOStatus.NEW);
		aggReceivableBillVO.setChildren(ReceivableBillItemVO.class, new ReceivableBillItemVO[] {receivableBillItemVO});
		
		return aggReceivableBillVO;
	}
	
	/**
	  * 根据客户主键 查询合同收款信息
	 * @param headvo 退费申请单数据
	 * @param pk_customer 客户主键
	 * @return
	 * @throws BusinessException
	 */
	private AggCtArVO getCtArVO(ReturncostHVO headvo, String pk_customer) throws BusinessException {
		IReturncosthvoMaintain service = NCLocator.getInstance().lookup(IReturncosthvoMaintain.class);
		AggCtArVO aggvo = service.getAggCtArVOByStudent(pk_customer);
		
		if(aggvo == null) {
			throw new BusinessException("单据号"+headvo.getBill_no()+"的退费申请单，未找到学生的合同收款信息！");
		}
		
		return aggvo;
	}
	
	/**
	 * 根据物料主键查询物料所属增值税码税率
	 * @param pk_material 物料主键
	 * @return
	 * @throws DAOException 
	 */
	private Map<String,Object> getTaxByMaterial(String pk_material) throws BusinessException{
		try {
			String querySql = "select bd_material.name,\n" +
					"       bd_material.code,\n" + 
					"       bd_taxrate.taxrate,\n" + 
					"       bd_taxrate.pk_taxcode\n" + 
					"  from bd_material bd_material\n" + 
					"  left join bd_taxcode bd_taxcode\n" + 
					"    on bd_taxcode.mattaxes = bd_material.pk_mattaxes\n" + 
					"  left join bd_taxrate bd_taxrate\n" + 
					"    on bd_taxrate.pk_taxcode = bd_taxcode.pk_taxcode\n" + 
					" where bd_material.pk_material = '"+pk_material+"'\n" + 
					"   and nvl(bd_taxrate.dr, 0) = 0\n" + 
					"   and nvl(bd_taxcode.dr, 0) = 0";

			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>)getBaseDao().executeQuery(querySql, new MapProcessor());
			
			return map;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	/**
	  * 根据当前财务组织获取该财务组织下的退款银行账户
	 * @param pk_org
	 * @return
	 * @throws DAOException 
	 */
	private String getPayaccount(String pk_org) throws BusinessException {
		try {
			//查询银行账户
			String sql = "select * from bd_bankaccbas where pk_org='"+pk_org+"' and def1 = 'Y'";
			BankAccbasVO backvo = (BankAccbasVO) getBaseDao().executeQuery(sql, new BeanProcessor(BankAccbasVO.class));
			//查询银行账户关联的子户
			if(backvo!=null) {
				sql = "select * from bd_bankaccsub where pk_bankaccbas = '"+backvo.getPk_bankaccbas()+"'";
				
				@SuppressWarnings("unchecked")
				List<BankAccSubVO> list = (List<BankAccSubVO>) getBaseDao().executeQuery(sql, new BeanListProcessor(BankAccSubVO.class));
				
				if(list!=null && list.size()>0) {
					return list.get(0).getPrimaryKey();
				}else {
					throw new BusinessException("当前财务组织下的退款银行账户，未维护子户信息，请检查！");
				}
			}else {
				throw new BusinessException("未找到当前财务组织下的退款银行账户，请检查！");
			}
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}
	
	/**
	 * 查询得到客户的银行账号信息
	 * @param bankNum 银行账号
	 * @param pk_customer 客户主键
	 * @return
	 * @throws DAOException 
	 */
	private String getCustomerBank(String bankNum,String pk_customer) throws BusinessException{
		try {
			String sql = "select *\n" +
					"  from bd_custbank\n" + 
					"  left join bd_bankaccbas\n" + 
					"    on bd_custbank.pk_bankaccbas = bd_bankaccbas.pk_bankaccbas\n" + 
					"  left join bd_bankaccsub\n" + 
					"    on bd_custbank.pk_bankaccbas = bd_bankaccsub.pk_bankaccbas\n" + 
					" where bd_custbank.pk_cust = '"+pk_customer+"'\n" + 
					"   and bd_bankaccsub.accnum = '"+bankNum+"'";
			CustbankVO custbankVO = (CustbankVO) getBaseDao().executeQuery(sql, new BeanProcessor(CustbankVO.class));
			
			if(custbankVO != null) {
				if(StringUtils.isBlank(custbankVO.getPk_bankaccsub())) {
					throw new BusinessException("客户所绑定的客户银行账户数据未维护子户信息，请检查！");
				}else {
					return custbankVO.getPk_bankaccsub();
				}
			}else {
				throw new BusinessException("根据退款申请单中的银行账号，未找到当前客户所绑定的银行账户数据，请检查！");
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	/**
	  * 获得业务日期：根据关账时间进行划分
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	private String getBusiDate(String pk_org) throws BusinessException {
		String closeDateStr = SysinitAccessorForNcc.getNccInstance().getParaString(pk_org, "cwgz");//关账日期
		UFDate busiDate = AppContext.getInstance().getBusiDate();
		if(StringUtils.isBlank(closeDateStr)) {
			return busiDate.toString();
		}
		UFDate closeDate = new UFDate(closeDateStr);
		if(busiDate.equals(closeDate) || busiDate.before(closeDate)) {
			//关账日期与业务日期相等或业务日期在关账日期之前 返回业务日期
			return busiDate.toString();
		}else {
			//业务日期在关账日期之后
			//当前业务日期所处年月是否和关账日期的年月一致，默认取下个月的一号，否则则返会当前业务日期
			if(closeDateStr.substring(0, 7).equals(busiDate.toString().substring(0, 7))) {
				String firstDayOfNextMonth = CrmUtil.getFirstDayOfNextMonth(busiDate.toString());
				return firstDayOfNextMonth;
			}else {
				return busiDate.toString();
			}
		}
	}
	
	

}
