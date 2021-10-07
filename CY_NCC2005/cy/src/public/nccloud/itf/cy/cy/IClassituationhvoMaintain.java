
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.pub.BusinessException;

public interface IClassituationhvoMaintain {

        public void delete(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;

        public AggClassituationHVO[] insert(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;

        public AggClassituationHVO[] update(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;

        public AggClassituationHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggClassituationHVO[] save(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;

        public AggClassituationHVO[] unsave(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;

        public AggClassituationHVO[] approve(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;

        public AggClassituationHVO[] unapprove(AggClassituationHVO[] clientFullVOs,
                        AggClassituationHVO[] originBills) throws BusinessException;
}
