
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;

public interface ICallcenterhvoMaintain {

        public void delete(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;

        public AggCallcenterHVO[] insert(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;

        public AggCallcenterHVO[] update(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;

        public AggCallcenterHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggCallcenterHVO[] save(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;

        public AggCallcenterHVO[] unsave(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;

        public AggCallcenterHVO[] approve(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;

        public AggCallcenterHVO[] unapprove(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException;
}
