package nccloud.impl.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskallocation.TaskallocationHVO;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.cy.taskfeedback.TaskfeedbackHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 
  * 任务分配操作时，生成对应的任务反馈信息
 * 
 * @author huangcong
 *
 * @param <T>
 */
public class TaskallocationRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] paramArrayOfE) {
		if (CyCommonUtils.isNotEmpty(paramArrayOfE)) {
			try {
				updateTaskfeedback(paramArrayOfE);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}

	}

	private void updateTaskfeedback(T[] paramArrayOfE) throws BusinessException {
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggTaskallocationHVO) {
				AggTaskallocationHVO agg = (AggTaskallocationHVO) paramArrayOfE[i];
				TaskallocationHVO parentVO = agg.getParentVO();
				AggTaskfeedbackHVO aggvo=new AggTaskfeedbackHVO();
				TaskfeedbackHVO feedvo=new TaskfeedbackHVO();
				feedvo.setAttributeValue("pk_group", parentVO.getPk_group());// 集团
				feedvo.setAttributeValue("pk_org", parentVO.getPk_org());// 组织
				feedvo.setAttributeValue("pk_org_v", parentVO.getPk_org_v());// 组织多版本
				feedvo.setAttributeValue("dbilldate", new UFDate());// 单据日期
				feedvo.setAttributeValue("billstatus", -1);// 单据状态（默认自由）
				feedvo.setAttributeValue("approvestatus", -1);// 审批状态（默认自由）
				feedvo.setAttributeValue("billmaker", parentVO.getBillmaker());// 制单人（用户表主键）
				feedvo.setAttributeValue("creator", parentVO.getCreator());// 创建人（用户表主键）
				feedvo.setAttributeValue("creationtime", new UFDateTime());// 创建时间
				feedvo.setStatus(VOStatus.NEW);
				if(CyCommonUtils.isNotEmpty(parentVO)) {		
					SqlBuilder sql=new SqlBuilder();
					sql.append("and cuserid",parentVO.getBillmaker());
					UserVO uservo=(UserVO) CyCommonUtils.getInstance(ICyCommonSqlUtil.class).getVO("sm_user", sql.toString(),UserVO.class);
					if(CyCommonUtils.isNotEmpty(uservo)) {
						feedvo.setAppointman(uservo.getPk_psndoc());
					}					
					feedvo.setAppointedman(parentVO.getSalesman());
					feedvo.setTaskinfo(parentVO.getTask());
					feedvo.setPlanstartdate(parentVO.getAssgindate());
					feedvo.setPlanenddate(parentVO.getEnddate());
					feedvo.setDef1(parentVO.getDef1());
				}
				aggvo.setParentVO(feedvo);
				CrmUtil.setBillType(aggvo);
				CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggvo.getParentVO().getBilltype(), aggvo,
						false);
				}				
			}
		}
	

}
