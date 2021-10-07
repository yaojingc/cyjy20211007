
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.daily.DailyAggPOJO;
import nc.api.cy.rest.entity.crm.daily.RestDailyQuery;
import nc.api.cy.rest.entity.crm.daily.RestDailySave;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiQuery;
import nc.api.cy.rest.entity.crm.yejiapply.RestYejiSave;
import nc.api.cy.rest.entity.crm.yejiapply.YejiAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.pub.BusinessException;

public interface IDailyhvoMaintain {

        public void delete(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public AggDailyHVO[] insert(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public AggDailyHVO[] update(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public AggDailyHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggDailyHVO[] save(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public AggDailyHVO[] unsave(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public AggDailyHVO[] approve(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public AggDailyHVO[] unapprove(AggDailyHVO[] clientFullVOs,
                        AggDailyHVO[] originBills) throws BusinessException;

        public ResponseList queryListPage(RestDailyQuery restSave) throws BusinessException;

        public DailyAggPOJO queryDeatil(String pk) throws BusinessException;

        public void billSave(RestDailySave restSave) throws BusinessException;
}
