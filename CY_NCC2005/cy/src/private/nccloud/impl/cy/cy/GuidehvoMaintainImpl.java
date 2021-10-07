
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiGuideHVOPubServiceImpl;
import nccloud.itf.cy.cy.IGuidehvoMaintain ;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;

public class GuidehvoMaintainImpl extends AceAggbusiGuideHVOPubServiceImpl implements IGuidehvoMaintain  {

        @Override
        public void delete(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggGuideHVO[] insert(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggGuideHVO[] update(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggGuideHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggGuideHVO[] save(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggGuideHVO[] unsave(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggGuideHVO[] approve(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggGuideHVO[] unapprove(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }

}
