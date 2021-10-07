
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiClassituationHVOPubServiceImpl;
import nccloud.itf.cy.cy.IClassituationhvoMaintain ;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.pub.BusinessException;

public class ClassituationhvoMaintainImpl extends AceAggbusiClassituationHVOPubServiceImpl implements IClassituationhvoMaintain  {

        @Override
        public void delete(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggClassituationHVO[] insert(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggClassituationHVO[] update(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggClassituationHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggClassituationHVO[] save(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggClassituationHVO[] unsave(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggClassituationHVO[] approve(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggClassituationHVO[] unapprove(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }

}
