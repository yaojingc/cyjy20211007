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
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskallocation.TaskallocationHVO;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.cy.taskfeedback.TaskfeedbackHVO;
import nc.vo.cy.workplan.AggWorkplanHVO;
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
 * 任务分配反馈保存时，将学校带出的大区
 * 
 * @author huangcong
 *
 * @param <T>
 */
public class TaskfeedbackRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] paramArrayOfE) {
		if (CyCommonUtils.isNotEmpty(paramArrayOfE)) {
			try {
				// 任务反馈保存前增加学校带出大区赋值逻辑
				updateArea(paramArrayOfE);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}

	}

	private void updateArea(T[] paramArrayOfE) throws BusinessException {
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggTaskfeedbackHVO) {
				AggTaskfeedbackHVO agg = (AggTaskfeedbackHVO) paramArrayOfE[i];
				TaskfeedbackHVO feedvo = agg.getParentVO();
				if(CyCommonUtils.isNotEmpty(feedvo.getDef1())) {
					AggSchoolBasicsHVO aggvo = (AggSchoolBasicsHVO) CyCommonUtils.getInstance(ICrmService.class)
							.queryDeatil(feedvo.getDef1(), AggSchoolBasicsHVO.class);
					if (CyCommonUtils.isNotEmpty(aggvo)) {
						feedvo.setDef3(aggvo.getParentVO().getDef11());
					}
					
				}
				String doc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk( "未完成","cy22");
				feedvo.setDef5(doc);
				if(CyCommonUtils.isEmpty(feedvo.getEnddate())&&CyCommonUtils.isNotEmpty(feedvo.getFeedback())) {
					if(feedvo.getEnddate().beforeDate(feedvo.getPlanenddate())) {
						String doc1 = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk( "完成","cy22");
						if(CyCommonUtils.isNotEmpty(doc1)) {
							feedvo.setDef5(doc1);
						}

					}
				}

			}
		}

	}
}
