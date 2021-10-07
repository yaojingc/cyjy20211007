
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.guide.AggGuideHVO;
import nc.vo.pub.BusinessException;

public interface IGuidehvoMaintain {

        public void delete(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;

        public AggGuideHVO[] insert(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;

        public AggGuideHVO[] update(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;

        public AggGuideHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggGuideHVO[] save(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;

        public AggGuideHVO[] unsave(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;

        public AggGuideHVO[] approve(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;

        public AggGuideHVO[] unapprove(AggGuideHVO[] clientFullVOs,
                        AggGuideHVO[] originBills) throws BusinessException;
}
