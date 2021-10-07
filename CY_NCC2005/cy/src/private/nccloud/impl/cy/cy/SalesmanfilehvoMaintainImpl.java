
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiSalesmanfileHVOPubServiceImpl;
import nccloud.itf.cy.cy.ISalesmanfilehvoMaintain ;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;

public class SalesmanfilehvoMaintainImpl extends AceAggbusiSalesmanfileHVOPubServiceImpl implements ISalesmanfilehvoMaintain  {

        @Override
        public void delete(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggSalesmanfileHVO[] insert(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggSalesmanfileHVO[] update(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggSalesmanfileHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggSalesmanfileHVO[] save(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggSalesmanfileHVO[] unsave(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggSalesmanfileHVO[] approve(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggSalesmanfileHVO[] unapprove(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }

}
