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
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.cy.xuefeiapply.XuefeijmHVO;
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
 * 学费减免保存时，将学校带出的大区
 * 
 * @author huangcong
 *
 * @param <T>
 */
public class XuefeiRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] paramArrayOfE) {
		if (CyCommonUtils.isNotEmpty(paramArrayOfE)) {
			try {
				// 任务反馈保存前增加学校带出大区赋值逻辑
				updateConsume(paramArrayOfE);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}

	}

	private void updateConsume(T[] paramArrayOfE) throws BusinessException {
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggXuefeijmHVO) {
				AggXuefeijmHVO agg = (AggXuefeijmHVO) paramArrayOfE[i];
				XuefeijmHVO xfvo = agg.getParentVO();
				if (CyCommonUtils.isEmpty(xfvo.getDef5())) {
					String doc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk( "否","cy21");
					if(CyCommonUtils.isNotEmpty(doc)) {
						xfvo.setDef5(doc);
					}
				} 
			}
		}

	}
}
