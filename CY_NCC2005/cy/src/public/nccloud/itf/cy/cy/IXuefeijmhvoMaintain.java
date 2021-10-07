
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiQuery;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiSave;
import nc.api.cy.rest.entity.crm.xuefeiapply.XuefeiAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.pub.BusinessException;

public interface IXuefeijmhvoMaintain {

        public void delete(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;

        public AggXuefeijmHVO[] insert(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;

        public AggXuefeijmHVO[] update(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;

        public AggXuefeijmHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggXuefeijmHVO[] save(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;

        public AggXuefeijmHVO[] unsave(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;

        public AggXuefeijmHVO[] approve(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;

        public AggXuefeijmHVO[] unapprove(AggXuefeijmHVO[] clientFullVOs,
                        AggXuefeijmHVO[] originBills) throws BusinessException;
        
        public ResponseList queryListPage(RestXuefeiQuery restXuefei) throws BusinessException;

        public XuefeiAggPOJO queryDeatil(String pk) throws BusinessException;

        public void billSave(RestXuefeiSave restYeji) throws BusinessException;

        public void commit(RestXuefeiSave restXuefei) throws BusinessException;
        
        public String feeAmountQuery(String pk) throws BusinessException;
}
