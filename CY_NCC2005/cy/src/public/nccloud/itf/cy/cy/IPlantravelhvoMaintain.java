
package nccloud.itf.cy.cy;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.plantravel.PlantravelAggPOJO;
import nc.api.cy.rest.entity.crm.plantravel.RestPlantravelQuery;
import nc.api.cy.rest.entity.crm.plantravel.RestPlantravelSave;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.pub.BusinessException;

public interface IPlantravelhvoMaintain {

        public void delete(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public AggPlantravelHVO[] insert(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public AggPlantravelHVO[] update(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public AggPlantravelHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggPlantravelHVO[] save(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public AggPlantravelHVO[] unsave(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public AggPlantravelHVO[] approve(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public AggPlantravelHVO[] unapprove(AggPlantravelHVO[] clientFullVOs,
                        AggPlantravelHVO[] originBills) throws BusinessException;

        public ResponseList queryListPage(RestPlantravelQuery restQuery) throws BusinessException;

        public PlantravelAggPOJO queryDeatil(String pk) throws BusinessException;

        public void billSave(RestPlantravelSave restSave) throws BusinessException;
}
