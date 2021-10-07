
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.itf.bd.cust.baseinfo.ICustBaseInfoService;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.custclass.CustClassVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 标准单据审核的BP
 */
public class AceSchoolBasicsHVOApproveBP {
	private IUAPQueryBS iQueryBS;
	private IVOPersistence iPersis;

	/**
	 * 审核动作huangcong
	 * 
	 * @param vos
	 * @param script
	 * @return
	 * @throws BusinessException
	 */
	public AggSchoolBasicsHVO[] approve(AggSchoolBasicsHVO[] clientBills, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		for (AggSchoolBasicsHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSchoolBasicsHVO> update = new BillUpdate<AggSchoolBasicsHVO>();
		AggSchoolBasicsHVO[] returnVos = update.update(clientBills, originBills);

		SchoolBasicsHVO tvo = returnVos[0].getParentVO();
		String status = "";
		String doc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk("已开发", "cy05");
		if (CyCommonUtils.isNotEmpty(doc)) {
			status = doc;
		}
		// 判断学校的审批状态和学校是否已开发
		if (tvo.getApprovestatus() == 1 && CyCommonUtils.isNotEmpty(tvo.getDef12()) && status.equals(tvo.getDef12())) {
			try {
				// 拼接查询条件
				SqlBuilder sb = new SqlBuilder();
				sb.append("and def8", tvo.getBill_no());
				// 查询客户档案是否存在
				CustomerVO cvo = (CustomerVO) CyCommonUtils.getInstance(ICyCommonSqlUtil.class).getVO("bd_customer",
						sb.toString(), CustomerVO.class);

				String def2 = CyCommonUtils.isEmpty(tvo.getDef11()) ? "" : tvo.getDef11();// 大区
				String def3 = CyCommonUtils.isEmpty(tvo.getProvince()) ? "" : tvo.getProvince();// 省份
				String def4 = CyCommonUtils.isEmpty(tvo.getSname()) ? "" : tvo.getSname();// 学校名称
				String def9 = CyCommonUtils.isEmpty(tvo.getPk_school()) ? "" : tvo.getPk_school();// 学校主键
				// 查询客户基本分类编码为G02的客户分类
				SqlBuilder sb1 = new SqlBuilder();
				sb1.append("and code", "0202");// 正式
//				sb1.append("and code", "G02");// 测试
				CustClassVO cls = (CustClassVO) CyCommonUtils.getInstance(ICyCommonSqlUtil.class).getVO("bd_custclass",
						sb1.toString(), CustClassVO.class);
				String pk_custclass = "";
				if (CyCommonUtils.isNotEmpty(cls)) {
					pk_custclass = cls.getPk_custclass();
				}
				// 客户基本分类 02学校 G02
				Integer custprop = 0;
				// 判断客户档案是否存在
				if (cvo == null) {
					cvo = new CustomerVO();
					cvo.setPk_customer(new SequenceGenerator().generate());
					cvo.setName(CyCommonUtils.isEmpty(tvo.getSname()) ? "" : tvo.getSname());
					cvo.setDef2(def2);
					cvo.setDef3(def3);
					cvo.setDef4(def4);
					cvo.setDef8(CyCommonUtils.isEmpty(tvo.getBill_no()) ? "" : tvo.getBill_no());
					cvo.setDef9(def9);
//					TableCountQueryVO vo = new TableCountQueryVO("bd_customer", null);
//					String newcode = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryNewCode("0202");
//					if (CyCommonUtils.isNotEmpty(newcode)) {
//						Integer lsh = r.getTotal() + 1;
//						String s = String.valueOf(lsh);
//						String str = "0000000";
//						s = "XS" + str.substring(0, 7 - s.length()) + s;
//						cvo.setCode(s);
//					}else {
//						cvo.setCode("02020000001");
//					}
					cvo.setPk_group(tvo.getPk_group());
					cvo.setPk_org(tvo.getPk_group());
					cvo.setPk_custclass(pk_custclass);
					cvo.setCustprop(custprop);
					cvo.setEnablestate(2);// 启用状态2
					cvo.setCuststate(1);// 客户状态 1核准
					cvo.setFrozenflag(new UFBoolean(false));// frozenflag 冻结标志
					cvo.setIssupplier(new UFBoolean(false));// 是否供应商
					cvo.setIsfreecust(new UFBoolean(true));// 是否散户
					cvo.setIsretailstore(new UFBoolean(false));// 是否零售门店
					cvo.setIsvat(new UFBoolean(false));// 是否VAT
					cvo.setDataoriginflag(0);// 分布式 本级产生
					cvo.setPk_format("FMT0Z000000000000000");
					cvo.setPk_country("0001Z010000000079UJJ");
					cvo.setPk_timezone("0001Z010000000079U2P");
					cvo.setTs(new UFDateTime());
					cvo.setCreationtime(new UFDateTime());
					cvo.setDr(0);
					NCLocator.getInstance().lookup(ICustBaseInfoService.class).insertCustomerVO(cvo, true);
				} else {
					cvo.setDef2(def2);
					cvo.setDef3(def3);
					cvo.setDef4(def4);
					cvo.setDef8(CyCommonUtils.isEmpty(tvo.getBill_no()) ? "" : tvo.getBill_no());
					cvo.setDef9(def9);
					cvo.setModifiedtime(new UFDateTime());
					NCLocator.getInstance().lookup(ICustBaseInfoService.class).updateCustomerVO(cvo, true);
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnVos;
	}

}
