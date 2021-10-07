
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiQuery;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiSave;
import nc.api.cy.rest.entity.crm.yejiapply.YejiAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.pub.BusinessException;


public interface IYejiapplyhvoMaintain {

        public void delete(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;

        public AggYejiapplyHVO[] insert(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;

        public AggYejiapplyHVO[] update(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;

        public AggYejiapplyHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggYejiapplyHVO[] save(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;

        public AggYejiapplyHVO[] unsave(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;

        public AggYejiapplyHVO[] approve(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;

        public AggYejiapplyHVO[] unapprove(AggYejiapplyHVO[] clientFullVOs,
                        AggYejiapplyHVO[] originBills) throws BusinessException;
        
        /**
     	 *  根据条件分页查询数据
     	 * @param restYeji 查询VO
     	 * @return
     	 * @throws BusinessException
     	 */
     	public ResponseList queryListPage(RestYejiQuery restYeji)throws BusinessException;
     	
    	public YejiAggPOJO queryDeatil(String  pk) throws BusinessException;
    	
    	public void billSave(RestYejiSave restWorkPlan) throws BusinessException;

    	public void commit(RestYejiSave restSave) throws BusinessException;
}
