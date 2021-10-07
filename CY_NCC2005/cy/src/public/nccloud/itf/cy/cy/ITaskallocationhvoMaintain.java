
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.taskallocation.RestTaskallocationQuery;
import nc.api.cy.rest.entity.crm.taskallocation.RestTaskallocationSave;
import nc.api.cy.rest.entity.crm.taskallocation.TaskallocationAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;

public interface ITaskallocationhvoMaintain {

        public void delete(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public AggTaskallocationHVO[] insert(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public AggTaskallocationHVO[] update(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public AggTaskallocationHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggTaskallocationHVO[] save(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public AggTaskallocationHVO[] unsave(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public AggTaskallocationHVO[] approve(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public AggTaskallocationHVO[] unapprove(AggTaskallocationHVO[] clientFullVOs,
                        AggTaskallocationHVO[] originBills) throws BusinessException;

        public void billSave(RestTaskallocationSave restTask) throws BusinessException;

        public ResponseList queryListPage(RestTaskallocationQuery restTask) throws BusinessException;

        public TaskallocationAggPOJO queryDeatil(String pk) throws BusinessException;
}
