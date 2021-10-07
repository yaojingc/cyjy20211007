
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiAggPOJO;
import nc.api.cy.rest.entity.crm.jiedaiapply.RestJiedaiQuery;
import nc.api.cy.rest.entity.crm.jiedaiapply.RestJiedaiSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.pub.BusinessException;

public interface IJiedaiapplyhvoMaintain {

        public void delete(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public AggJiedaiapplyHVO[] insert(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public AggJiedaiapplyHVO[] update(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public AggJiedaiapplyHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggJiedaiapplyHVO[] save(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public AggJiedaiapplyHVO[] unsave(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public AggJiedaiapplyHVO[] approve(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public AggJiedaiapplyHVO[] unapprove(AggJiedaiapplyHVO[] clientFullVOs,
                        AggJiedaiapplyHVO[] originBills) throws BusinessException;

        public ResponseList queryListPage(RestJiedaiQuery restWorkplan) throws BusinessException;

        public JiedaiAggPOJO queryDeatil(String pk) throws BusinessException;

        public void billSave(RestJiedaiSave restWorkplan) throws BusinessException;

        public void commit(RestJiedaiSave restSave) throws BusinessException;
}
