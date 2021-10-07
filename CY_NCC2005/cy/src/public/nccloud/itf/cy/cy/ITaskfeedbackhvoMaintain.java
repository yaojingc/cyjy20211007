
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.taskfeedback.RestTaskfeedbackQuery;
import nc.api.cy.rest.entity.crm.taskfeedback.RestTaskfeedbackSave;
import nc.api.cy.rest.entity.crm.taskfeedback.TaskfeedbackAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.pub.BusinessException;

public interface ITaskfeedbackhvoMaintain {

        public void delete(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public AggTaskfeedbackHVO[] insert(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public AggTaskfeedbackHVO[] update(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public AggTaskfeedbackHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggTaskfeedbackHVO[] save(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public AggTaskfeedbackHVO[] unsave(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public AggTaskfeedbackHVO[] approve(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public AggTaskfeedbackHVO[] unapprove(AggTaskfeedbackHVO[] clientFullVOs,
                        AggTaskfeedbackHVO[] originBills) throws BusinessException;

        public ResponseList queryListPage(RestTaskfeedbackQuery restQuery) throws BusinessException;

        public TaskfeedbackAggPOJO queryDeatil(String pk) throws BusinessException;

        public void billSave(RestTaskfeedbackSave restSave) throws BusinessException;
}
