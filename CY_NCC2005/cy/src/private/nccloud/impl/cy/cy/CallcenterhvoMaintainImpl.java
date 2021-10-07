
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiCallcenterHVOPubServiceImpl;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain ;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.pub.BusinessException;

public class CallcenterhvoMaintainImpl extends AceAggbusiCallcenterHVOPubServiceImpl implements ICallcenterhvoMaintain  {

        @Override
        public void delete(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggCallcenterHVO[] insert(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggCallcenterHVO[] update(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggCallcenterHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggCallcenterHVO[] save(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggCallcenterHVO[] unsave(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggCallcenterHVO[] approve(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggCallcenterHVO[] unapprove(AggCallcenterHVO[] clientFullVOs,
                        AggCallcenterHVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }

}
