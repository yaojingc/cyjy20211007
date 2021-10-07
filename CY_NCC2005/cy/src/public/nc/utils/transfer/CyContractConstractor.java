package nc.utils.transfer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.creditcontract.CreditcontractDetailVO;
import nc.vo.cy.creditcontract.CreditcontractHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

public class CyContractConstractor {

	public static AggCreditcontractHVO ccBillManager(int htnx, ClasscyHVO clvo, StudentHVO stu,
			List<ClasscyDetailVO> listClassDVO) throws BusinessException {
		String contractowner = clvo.getContractowner();
		UFDouble amount = clvo.getPaystand();
		AggCreditcontractHVO aggCCHVO = new AggCreditcontractHVO();
		CreditcontractHVO cchvo = new CreditcontractHVO();

		String stupk = stu.getPk_student();
		/*
		 * 根据学生主键查询是否存在学费收款合同
		 */
		Boolean flag = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryContractByStudent(stupk);
		if (flag) {
			cchvo.setAmount(amount);// 缴费标准
			cchvo.setSchool(stu.getDef2());
			cchvo.setClasses(clvo.getPk_classcy());
			cchvo.setStudent(stupk);
			cchvo.setDef1(clvo.getPk_classcy());
			cchvo.setDef2(stupk);
			cchvo.setDef3("否");
			cchvo.setDef4("未生效");
			cchvo.setDef5(stu.getDef2());
			cchvo.setDef6("1");// 虚拟合同版本
			cchvo.setDef9(amount.toString());// 初始化学费减免金额为合同总金额
//			cchvo.setPk_material(AppletsParams.pk_material);
			cchvo.setPk_material("1001L110000000004QZW");
			cchvo.setStartdate(new UFDateTime());

			Calendar cend = Calendar.getInstance();
			cend.add(Calendar.YEAR, htnx);
			UFDateTime endUFDateTime = new UFDateTime(cend.getTimeInMillis());
			String endYear = endUFDateTime.toString().substring(0, 4);
			UFDateTime enddate = new UFDateTime(endYear + "-06-30 00:00:00");
			cchvo.setEnddate(enddate);
			cchvo.setPk_group(AppletsParams.Pk_Group);
			cchvo.setPk_org(contractowner);
			cchvo.setDbilldate(new UFDate());
			cchvo.setBillstatus(-1);
			cchvo.setStatus(VOStatus.NEW);

			// 合同子表数据处理
			List<CreditcontractDetailVO> ctdArrVO = detailDataManager(clvo,
					listClassDVO.toArray(new ClasscyDetailVO[0]));

			aggCCHVO.setChildrenVO(ctdArrVO.toArray(new CreditcontractDetailVO[0]));
			aggCCHVO.setParentVO(cchvo);
			return aggCCHVO;
		} else {
			cchvo.setPk_group(AppletsParams.Pk_Group);
			cchvo.setPk_org(contractowner);
			cchvo.setDbilldate(new UFDate());
			cchvo.setBillstatus(-1);
			cchvo.setStatus(VOStatus.NEW);
			cchvo.setVnote("1");
			aggCCHVO.setParentVO(cchvo);
			return aggCCHVO;
		}
	}

	private static List<CreditcontractDetailVO> detailDataManager(ClasscyHVO clvo, ClasscyDetailVO[] cDetailVOs)
			throws BusinessException {
		List<CreditcontractDetailVO> ctdArrVO = new ArrayList<CreditcontractDetailVO>();
		if (MMValueCheck.isEmpty(cDetailVOs)) {
			return ctdArrVO;
		}
		int proplanNotlast = 0;
		for (int i = 0; i < cDetailVOs.length; i++) {
			CreditcontractDetailVO cdvo = new CreditcontractDetailVO();
			UFDate startdateOrigin = cDetailVOs[i].getStartdate();
			UFDate enddateOrigin = cDetailVOs[i].getEnddate();
			if (MMValueCheck.isEmpty(startdateOrigin) || MMValueCheck.isEmpty(enddateOrigin)) {
				throw new BusinessException("开始日期或结束日期为空");
			}
			if (!startdateOrigin.before(enddateOrigin)) {
				throw new BusinessException("结束日期小于开始日期");
			}
			UFDateTime startDate = new UFDateTime(startdateOrigin.toString().substring(0, 10) + " 00:00:00");
			UFDateTime endDate = new UFDateTime(enddateOrigin.toString().substring(0, 10) + " 00:00:00");
			long between_days = (endDate.getMillis() - startDate.getMillis()) / (1000 * 3600 * 24);

			cdvo.setStartdate(startDate);
			cdvo.setEnddate(endDate);
			cdvo.setPaydays(Integer.parseInt(String.valueOf(between_days)));
			UFDouble planamount = cDetailVOs[i].getPlanamount();
			if (null == planamount || (planamount != null && planamount.doubleValue() <= 0)) {
				throw new BusinessException("子表计划金额为空或小于0");
			}
			cdvo.setPlanamount(cDetailVOs[i].getPlanamount());
			UFDouble amount = clvo.getPaystand();

			int proplan = planamount.div(amount).multiply(100).intValue();
			if (i == cDetailVOs.length - 1) {
				cdvo.setProplan(100 - proplanNotlast);
			} else {
				cdvo.setProplan(proplan);
				proplanNotlast += proplan;
			}
			cdvo.setPayno(i + 1 + "");
			cdvo.setRowno(i + 1 + "");
			cdvo.setStatus(VOStatus.NEW);

			ctdArrVO.add(cdvo);
		}
		return ctdArrVO;
	}

}
