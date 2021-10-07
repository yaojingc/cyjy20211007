
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.cy.jiedaiapply.JiedaiapplyHVO;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.cy.plantravel.PlantravelBVO;
import nc.vo.cy.plantravel.PlantravelHVO;
import nc.vo.cy.plantravel.PlantravelkcVO;

/**
 * 标准单据审核的BP
 */
public class AceJiedaiapplyHVOApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggJiedaiapplyHVO[] approve(AggJiedaiapplyHVO[] clientBills, AggJiedaiapplyHVO[] originBills) {
		for (AggJiedaiapplyHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggJiedaiapplyHVO> update = new BillUpdate<AggJiedaiapplyHVO>();
		AggJiedaiapplyHVO[] returnVos = update.update(clientBills, originBills);
		JiedaiapplyHVO tvo = returnVos[0].getParentVO();
		try {
			// 审批时更新学校档案
			if (tvo.getApprovestatus() == 1) {
				updatePlantravel(returnVos[0]);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnVos;
	}

	/**
	 * 
	 * 客情接待审批完成生成关联的行程安排单据
	 * 
	 * @author huangcong
	 * @param aggvo
	 * @return
	 */
	public void updatePlantravel(AggJiedaiapplyHVO aggVO) throws BusinessException {
		try {
			JiedaiapplyHVO tvo = aggVO.getParentVO();
			AggPlantravelHVO aggvo = new AggPlantravelHVO();
			PlantravelHVO svo = new PlantravelHVO();
			if (CyCommonUtils.isNotEmpty(tvo.getDef1())) {
				svo.setDef1(tvo.getDef1());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getSalesman())) {
				svo.setSalesman(tvo.getSalesman());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getEnddate())) {
				svo.setEnddate(tvo.getEnddate());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getStartdate())) {
				svo.setStartdate(tvo.getStartdate());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getVisitnum())) {
				svo.setVisitnum(tvo.getVisitnum());
			}
			svo.setIstatus(new UFBoolean(false));
			svo.setDef2(tvo.getPk_jiedaiapply());
			aggvo.setParentVO(svo);
			aggvo.getParentVO().setAttributeValue("pk_group", aggVO.getParentVO().getPk_group());// 集团
			aggvo.getParentVO().setAttributeValue("pk_org", aggVO.getParentVO().getPk_org());// 组织
			aggvo.getParentVO().setAttributeValue("pk_org_v", aggVO.getParentVO().getPk_org_v());// 组织多版本
			aggvo.getParentVO().setAttributeValue("dbilldate", new UFDate());// 单据日期
			aggvo.getParentVO().setAttributeValue("billstatus", -1);// 单据状态（默认自由）
			aggvo.getParentVO().setAttributeValue("approvestatus", -1);// 审批状态（默认自由）
			aggvo.getParentVO().setAttributeValue("billmaker", aggVO.getParentVO().getBillmaker());// 制单人（用户表主键）
			aggvo.getParentVO().setAttributeValue("creator", aggVO.getParentVO().getCreator());// 创建人（用户表主键）
			aggvo.getParentVO().setAttributeValue("creationtime", new UFDateTime());// 创建时间
			aggvo.getParentVO().setStatus(VOStatus.NEW);
			PlantravelBVO[] arrb = new PlantravelBVO[0];
			PlantravelkcVO[] arrkc = new PlantravelkcVO[0];
			aggvo.setChildren(PlantravelBVO.class, arrb);
			aggvo.setChildren(PlantravelkcVO.class, arrkc);
			CrmUtil.setBillType(aggvo);
			CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggvo.getParentVO().getBilltype(), aggvo,
					false);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
