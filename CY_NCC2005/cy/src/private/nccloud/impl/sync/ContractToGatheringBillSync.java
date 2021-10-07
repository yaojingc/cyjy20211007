package nccloud.impl.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.pf.IplatFormEntry;
import nc.jdbc.framework.generator.IdGenerator;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.arap.gathering.IArapGatheringBillPubService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.IdCardUtil;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.cy.bankcollflow.BankcollflowHVO;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.fct.ar.entity.CtArBVO;
import nc.vo.fct.ar.entity.CtArPlanVO;
import nc.vo.fct.ar.entity.CtArVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 银行收款流水与收款合同的计划金额进行匹配。匹配条件为身份证号+计划金额，能匹配上的自动生成收款单
 * 
 * @author tanrg
 *
 */
public class ContractToGatheringBillSync implements IBackgroundWorkPlugin {

	@SuppressWarnings("unchecked")
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0) throws BusinessException {
		BaseDAO dao = new BaseDAO();
		// 1. 查询银行收款流水
		SqlBuilder sql = new SqlBuilder();
		// matchstate 未匹配，匹配成功，匹配失败
		sql.append(
				" select CONCAT(idcard,CONCAT('￥',to_char(round(Payamount,2),'fm9999999999999990.00'))) strkey,cyflow.* ");
		sql.append(" from cy_bankcollflow cyflow where nvl(dr,0)=0  and nvl(matchstate,'未匹配')!='匹配成功' ");
		List<BankcollflowHVO> listBFHVO = (List<BankcollflowHVO>) dao.executeQuery(sql.toString(),
				new BeanListProcessor(BankcollflowHVO.class));
		if (MMValueCheck.isEmpty(listBFHVO)) {
			return getNothingObject();
		}
		Set<String> idcardSet = new HashSet<String>();
		Set<UFDouble> amountSet = new HashSet<UFDouble>();
		Map<String, BankcollflowHVO> flowMap = new HashMap<String, BankcollflowHVO>();// key:身份证号+金额，value:银行流水主表vo
		for (BankcollflowHVO bcfhvo : listBFHVO) {
			String idcard = bcfhvo.getIdcard();
			String paymountStr = bcfhvo.getPayamount();
			StringBuilder errMsgDef1 = new StringBuilder();
			if (IdCardUtil.isValidatedAllIdcard(idcard)) {
				idcardSet.add(idcard);
			} else {
				bcfhvo.setMatchstate("匹配失败");
				errMsgDef1.append("身份证号：" + idcard + "异常");
			}

			// String paym = "";
			try {
				UFDouble ufDouble = new UFDouble(paymountStr).setScale(2, 5);
				amountSet.add(new UFDouble(ufDouble));
				// paym = ufDouble.toString();
				if (ufDouble.doubleValue() < 0) {
					throw new BusinessException("银行收款流水:" + bcfhvo.getBill_no() + "金额异常" + paymountStr);
				}
			} catch (Exception e) {
				bcfhvo.setMatchstate("匹配失败");
				errMsgDef1.append("金额异常：" + paymountStr);
			}

			bcfhvo.setDef1(errMsgDef1.toString());
			bcfhvo.setStatus(VOStatus.UPDATED);
			flowMap.put(bcfhvo.getStrkey(), bcfhvo);
		}

		System.out.println(flowMap.keySet());
		if (MMValueCheck.isEmpty(flowMap) || MMValueCheck.isEmpty(flowMap.keySet())) {
			bankFlowUpdate(listBFHVO);
		}
		// 2. 查询收款合同子数据
		sql.reset();
		sql.append(" select distinct * from ( ");
		sql.append(
				" select concat(fcta.vdef4,concat('￥',to_char(round(fctp.planmoney,2),'fm9999999999999990.00'))) def30, ");
		sql.append(
				" fctp.vdef1,fctp.pk_fct_ar,fctp.pk_fct_ar_plan,fctp.balancemoney,fctp.balancemoneyorg,fctp.planmoney,fctp.begindate,fctp.vdef4 ");
		sql.append(" from fct_ar_plan fctp ");
		sql.append(" join fct_ar fcta ");
		sql.append(" on fcta.pk_fct_ar = fctp.pk_fct_ar ");
		sql.append(" join fct_ar_b fctb  ");
		sql.append(" on fctb.pk_fct_ar = fctp.pk_fct_ar ");
		sql.append(" where nvl(fcta.dr, 0) = 0 ");
		sql.append(" and nvl(fctp.dr, 0) = 0 ");
		sql.append(" and fcta.fstatusflag = 1 ");
		sql.append(" and fcta.vdef4 is not null and fcta.vdef4 !='~' ");
		sql.append(" and fctp.begindate is not null and substr(fctp.begindate,0,4)=to_char(sysdate,'yyyy')");
//		sql.append(" and (fctp.begindate is not null and substr(fctp.begindate,0,4)=to_char(sysdate,'yyyy')) ");
		sql.append(" and fctp.planmoney != 0 and fctp.planmoney > 0 ");
		sql.append(" and nvl(fctb.norigtaxmny,0) >  nvl(fctb.noritotalgpmny,0)) fctdata ");
//		sql.append(" where fctdata.def30", flowMap.keySet().toArray(new String[0]));
		sql.append(
				" where fctdata.def30 in (select CONCAT(idcard,CONCAT('￥',to_char(round(Payamount,2),'fm9999999999999990.00'))) strkey from cy_bankcollflow cyflow where nvl(dr,0)=0  and nvl(matchstate,'未匹配')!='匹配成功')");
		sql.append(
				" and fctdata.vdef1 in (select cyflow.def30 from cy_bankcollflow cyflow where nvl(dr,0)=0  and nvl(matchstate,'未匹配')!='匹配成功')");
		List<CtArPlanVO> listCPVO = (List<CtArPlanVO>) dao.executeQuery(sql.toString(),
				new BeanListProcessor(CtArPlanVO.class));
		if (MMValueCheck.isEmpty(listCPVO)) {
			// 学生不存在收款合同 ，计划金额和收款金额不一致
			// 未匹配到 对应的收款合同，判断是合同不存在还是金额不对
			for (BankcollflowHVO bf : listBFHVO) {// flowMap.values()
				bf.setMatchstate("匹配失败");
				bf.setDef1("身份证号和金额未匹配到对应的收款合同");
				bf.setStatus(VOStatus.UPDATED);
			}

			bankFlowUpdate(listBFHVO);
			return getNothingObject();

		}

		// 查询收款合同主表数据
		Set<String> caPks = new HashSet<String>();
		for (CtArPlanVO cp : listCPVO) {
			caPks.add(cp.getPk_fct_ar());
		}

		sql.reset();
		sql.append(" select distinct * from fct_ar where nvl(dr,0)=0 ");
		sql.append(" and fstatusflag=1 and pk_fct_ar ", caPks.toArray(new String[0]));
		List<CtArVO> listCTA = (List<CtArVO>) dao.executeQuery(sql.toString(), new BeanListProcessor(CtArVO.class));
		if (MMValueCheck.isEmpty(listCTA)) {
			return getNothingObject();
		}
		Map<String, CtArVO> mapCAVO = new HashMap<String, CtArVO>();
		for (CtArVO ctArVO : listCTA) {
			mapCAVO.put(ctArVO.getPk_fct_ar(), ctArVO);
		}

		sql.reset();

		sql.append(" select * from fct_ar_b where nvl(dr,0)=0 ");
		sql.append(" and pk_fct_ar ", caPks.toArray(new String[0]));
		List<CtArBVO> listCTAB = (List<CtArBVO>) dao.executeQuery(sql.toString(), new BeanListProcessor(CtArBVO.class));
		Map<String, CtArBVO> mapCtArBVO = new HashMap<String, CtArBVO>();
		if (MMValueCheck.isNotEmpty(listCTAB)) {
			for (CtArBVO ctArBVO : listCTAB) {
				mapCtArBVO.put(ctArBVO.getPk_fct_ar(), ctArBVO);
			}
		}

		// 3. 组装单据，保存审批
		List<AggGatheringBillVO> listAgg = dataManager(listCPVO, mapCAVO, mapCtArBVO, flowMap);
		IArapGatheringBillPubService gbPubService = NCLocator.getInstance().lookup(IArapGatheringBillPubService.class);
		AggGatheringBillVO[] retbill = gbPubService.save(listAgg.toArray(new AggGatheringBillVO[0]));
		AggGatheringBillVO[] approveBills = gbPubService.approve(retbill);

		// 合同计划回写信息处理
//		List<BankcollflowHVO> listSuccess = new ArrayList<BankcollflowHVO>();
		for (AggGatheringBillVO aggGTVO : approveBills) {
			GatheringBillVO parentVO = (GatheringBillVO) aggGTVO.getParentVO();
			GatheringBillItemVO[] billitems = (GatheringBillItemVO[]) aggGTVO.getChildrenVO();
			String def4 = parentVO.getDef4();// idcard
			String moneyStr = (parentVO.getMoney().setScale(2, 5)).toString();// 金额
			String billno = parentVO.getBillno();
			String strKey = def4 + "￥" + moneyStr;
			BankcollflowHVO bcfhvo = flowMap.get(strKey);
			if (MMValueCheck.isNotEmpty(bcfhvo)) {
				String pk_gatherbill = parentVO.getPk_gatherbill();
				String pk_gatheritem = billitems[0].getPk_gatheritem();
				bcfhvo.setMatchstate("匹配成功");
				bcfhvo.setDef1(null);
				bcfhvo.setSrcbillid(pk_gatherbill);
				bcfhvo.setSrcid(pk_gatherbill);
				bcfhvo.setSrcbid(pk_gatheritem);
				bcfhvo.setSrccode(billno);
				bcfhvo.setVfirstid(pk_gatherbill);
				bcfhvo.setVfirstbid(pk_gatheritem);
				bcfhvo.setVfirstcode(billno);
				bcfhvo.setStatus(VOStatus.UPDATED);

				flowMap.remove(strKey);
			}

		}
//		}
		if (MMValueCheck.isNotEmpty(flowMap)) {
			for (BankcollflowHVO bfhvo : flowMap.values()) {
				bfhvo.setMatchstate("匹配失败");
				bfhvo.setDef1("身份证号和金额未匹配到对应的收款合同");
				bfhvo.setStatus(VOStatus.UPDATED);
			}
		}

		// 4. 回写银行收款流水
		if (MMValueCheck.isNotEmpty(listBFHVO)) {
			bankFlowUpdate(listBFHVO);
		}

		// TODO 5.6.7 可优化成一个修改接口，最初版需求只回写收款计划
		// 5. 回写收款合同 收款计划
		if (MMValueCheck.isNotEmpty(listCPVO)) {
			contractPlanUpdate(listCPVO);
		}
		// 6.回写收款合同 合同基本
		if (MMValueCheck.isNotEmpty(mapCtArBVO) && MMValueCheck.isNotEmpty(mapCtArBVO.values())) {
			contractBaseUpdate(mapCtArBVO.values());
		}
		// 7.收款合同主表回写
		if (MMValueCheck.isNotEmpty(mapCAVO) && MMValueCheck.isNotEmpty(mapCAVO.values())) {
			List<CtArVO> listMa = new ArrayList<CtArVO>();
			Collection<CtArVO> values = mapCAVO.values();
			for (CtArVO ctArVO : values) {
				if (MMValueCheck.isNotEmpty(ctArVO.getStatus()) && ctArVO.getStatus() == VOStatus.UPDATED) {
					listMa.add(ctArVO);
				}
			}
			if (MMValueCheck.isNotEmpty(listMa)) {
				contractHeadUpdate(listMa);
			}

		}
		return getNothingObject();
	}

	private void contractHeadUpdate(List<CtArVO> listMa) {
		VOUpdate<CtArVO> updateOnhand = new VOUpdate<CtArVO>();
		updateOnhand.update(listMa.toArray(new CtArVO[0]), new String[] { "norigplanamount", "norigpshamount" });
	}

	private void bankFlowUpdate(List<BankcollflowHVO> listBFHVO) {
		VOUpdate<BankcollflowHVO> updateOnhand = new VOUpdate<BankcollflowHVO>();
		updateOnhand.update(listBFHVO.toArray(new BankcollflowHVO[0]), new String[] { "def1", "matchstate", "srcbillid",
				"srcid", "srcbid", "srccode", "vfirstid", "vfirstbid", "vfirstcode" });
	}

	private void contractPlanUpdate(List<CtArPlanVO> listBFHVO) {
		VOUpdate<CtArPlanVO> updateOnhand = new VOUpdate<CtArPlanVO>();
		updateOnhand.update(listBFHVO.toArray(new CtArPlanVO[0]), new String[] { "balancemoney", "balancemoneyorg" });
	}

	private void contractBaseUpdate(Collection<CtArBVO> collection) {
		VOUpdate<CtArBVO> updateOnhand = new VOUpdate<CtArBVO>();
		updateOnhand.update(collection.toArray(new CtArBVO[0]),
				new String[] { "noritotalgpmny", "ntotalgpmny", "noriplangpmny", "nplangpmny" });
	}

	/**
	 * 数据组装
	 * 
	 * @param listCPVO
	 * @param mapCAVO
	 * @return
	 */
	private List<AggGatheringBillVO> dataManager(List<CtArPlanVO> listCPVO, Map<String, CtArVO> mapCAVO,
			Map<String, CtArBVO> mapCtArBVO, Map<String, BankcollflowHVO> flowMap) {
		List<AggGatheringBillVO> listAgg = new ArrayList<AggGatheringBillVO>();
		for (CtArPlanVO cp : listCPVO) {
			// 单据组装
			AggGatheringBillVO bill = new AggGatheringBillVO();
			String hid = getNewPK();
			String bid = getNewPK();
			String pk_fct_ar = cp.getPk_fct_ar();
			CtArVO ctArVO = mapCAVO.get(pk_fct_ar);
			GatheringBillVO g = new GatheringBillVO();
			UFBoolean ufBoolean = new UFBoolean(false);
			String pk_group = ctArVO.getPk_group();
			String pk_org = ctArVO.getPk_org();
			String userid = InvocationInfoProxy.getInstance().getUserId();
			g.setPk_gatherbill(hid);
			g.setCreator(userid);
			g.setBillmaker(userid);
			g.setCreationtime(new UFDateTime());
			g.setPk_group(ctArVO.getPk_group());
			g.setPk_org(pk_org);
			g.setPk_org_v(ctArVO.getPk_org_v());
			g.setPk_fiorg(pk_org);
			g.setSett_org(pk_org);
			g.setIsreded(ufBoolean);
			g.setIsonlinepay(ufBoolean);
			g.setPk_billtype("F2");
			g.setIsmandatepay(ufBoolean);
			g.setPk_tradetype("D2");
			g.setPk_tradetypeid(AppletsParams.pk_tradetypeid);// 收款类型 Pk_tradetypeid
			g.setBillclass("sk");
			g.setIsflowbill(ufBoolean);
			g.setIsinit(ufBoolean);
			g.setBilldate(new UFDate());
			g.setSyscode(0);
			g.setSrc_syscode(0);
			g.setBillstatus(-1);
			g.setApprovestatus(-1);
			g.setIsnetpayready(ufBoolean);
			g.setPk_busitype(AppletsParams.pk_busitype);// 业务流程
			UFDouble planmoney = cp.getPlanmoney().setScale(2, 5);
			g.setMoney(planmoney);
			g.setLocal_money(planmoney);
			g.setBillyear(cp.getBegindate().getYear() + "");
			g.setBillperiod(cp.getBegindate().getMonth() + "");
			g.setScomment(ctArVO.getCtname());
			g.setAccessorynum(0);
			g.setSettleflag(1);
			g.setSettletype(5);
			g.setGrouplocal(new UFDouble());
			g.setGloballocal(new UFDouble());
			g.setPk_currtype(AppletsParams.pk_currtype);// 币种
			g.setIsforce(ufBoolean);
			g.setSddreversalflag(ufBoolean);
			g.setDef1(ctArVO.getVdef1());// 大区
			g.setDef2(ctArVO.getVdef2());// 省份
			g.setDef3(ctArVO.getVdef3());// 学校
			String cadef4 = ctArVO.getVdef4();// idcard
			g.setDef4(cadef4);

			g.setStatus(VOStatus.NEW);

			bill.setParentVO(g);

			// 合同子表回写信息
			cp.setBalancemoney(cp.getBalancemoney().add(planmoney));
			cp.setBalancemoneyorg(cp.getBalancemoneyorg().add(planmoney));
			cp.setStatus(VOStatus.UPDATED);
			// 合同主表回写信息
			ctArVO.setNorigplanamount(ctArVO.getNorigplanamount().add(planmoney));// 累计原币预收款金额
			ctArVO.setNorigpshamount(ctArVO.getNorigpshamount().add(planmoney));// 累计原币收款金额
			ctArVO.setStatus(VOStatus.UPDATED);

			GatheringBillItemVO gi = new GatheringBillItemVO();
			gi.setPk_gatheritem(bid);
			gi.setPk_group(pk_group);
			gi.setPk_org(pk_org);
			gi.setPk_org_v(ctArVO.getPk_org_v());
			gi.setPk_fiorg(pk_org);
			gi.setSett_org(pk_org);
			gi.setPrepay(1);
			gi.setPausetransact(ufBoolean);
			gi.setBilldate(g.getBilldate());
			gi.setPk_billtype(g.getPk_billtype());
			gi.setBillclass(g.getBillclass());
			gi.setPk_tradetype(g.getPk_tradetype());
			gi.setPk_tradetypeid(g.getPk_tradetypeid());
			gi.setBusidate(g.getBilldate());
			gi.setObjtype(0);
			gi.setRowno(1);
			gi.setDirection(-1);
			// gi.setSubjcode("");
			// gi.setPk_subjcode("");
			gi.setPk_currtype(g.getPk_currtype());
			gi.setRate(new UFDouble(1.0));
			// gi.setPk_deptid(pk_deptid);
			// gi.setPk_psndoc(pk_psndoc);
			gi.setLocal_money_cr(planmoney);
			gi.setMoney_cr(planmoney);
			gi.setMoney_bal(planmoney);
			gi.setLocal_money_bal(planmoney);
			gi.setNotax_cr(planmoney);
			gi.setTaxprice(new UFDouble(0));
			gi.setPk_balatype(AppletsParams.pk_balatype);// 结算方式
			gi.setCustomer(ctArVO.getPk_customer());
			gi.setOrdercubasdoc(ctArVO.getPk_customer());
			gi.setOccupationmny(planmoney);
			gi.setScomment(ctArVO.getCtname());

			BankcollflowHVO bcfhvo = flowMap.get(cadef4 + "￥" + planmoney);
			if (MMValueCheck.isNotEmpty(bcfhvo)) {
				gi.setRecaccount(bcfhvo.getBankaccount());// 收款银行账户
			}
			if (MMValueCheck.isNotEmpty(mapCtArBVO)) {
				CtArBVO ctArBVO = mapCtArBVO.get(cp.getPk_fct_ar());
				String pk_material = ctArBVO.getPk_material();
				UFDouble ntaxrate = ctArBVO.getNtaxrate();
				if (MMValueCheck.isEmpty(pk_material)) {
					pk_material = AppletsParams.pk_material;
					ntaxrate = new UFDouble(3.0);
				}
				if (MMValueCheck.isEmpty(ntaxrate) || ntaxrate.doubleValue() == 0) {
					pk_material = AppletsParams.pk_material;
					ntaxrate = new UFDouble(3.0);
				}
				UFDouble divTax = ntaxrate.div(100).add(new UFDouble(1.0));

				gi.setMaterial(pk_material);// 物料
				gi.setMaterial_src(pk_material);// 原始物料
				gi.setTaxrate(ntaxrate);
				UFDouble bhsje = planmoney.div(divTax).setScale(2, 5);
				gi.setLocal_notax_cr(bhsje);// 组织本币无税金额(贷方) 不含税金额=缴费金额/（1+税率）*税率
				gi.setLocal_tax_cr(planmoney.sub(bhsje));// 税额

				// 合同基本回写信息处理
				ctArBVO.setNoritotalgpmny(ctArBVO.getNoritotalgpmny().add(planmoney));// 累计原币收款金额
				ctArBVO.setNtotalgpmny(ctArBVO.getNtotalgpmny().add(planmoney));
				ctArBVO.setNoriplangpmny(ctArBVO.getNoriplangpmny().add(planmoney));// 累计原币预收款金额
				ctArBVO.setNplangpmny(ctArBVO.getNplangpmny().add(planmoney));
				ctArBVO.setStatus(VOStatus.UPDATED);
			}
			gi.setSrc_billid(cp.getPk_fct_ar()); // 源头单据主键
			gi.setSrc_itemid(cp.getPk_fct_ar_plan());// 源头单据行主键

			gi.setStatus(VOStatus.NEW);

			bill.setChildrenVO(new GatheringBillItemVO[] { gi });
			listAgg.add(bill);

		}
		return listAgg;
	}

	private PreAlertObject getNothingObject() {
		PreAlertObject retObj = new PreAlertObject();
		retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
		return retObj;
	}

	private String getNewPK() {
		IdGenerator idGenerator = new SequenceGenerator();
		return idGenerator.generate();
	}
}
