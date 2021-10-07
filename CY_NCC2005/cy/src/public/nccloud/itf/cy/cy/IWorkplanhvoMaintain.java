
package nccloud.itf.cy.cy;

import java.text.ParseException;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.workplan.RestQueryDetail;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanQuery;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanSave;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanAggPOJO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.daily.DailyHVO;
import nc.vo.cy.daily.DailyTaskVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.cy.workplan.WorkplanbVO;
import nc.vo.cy.workplan.pojo.WorkplanPOJO;
import nc.vo.cy.workplan.pojo.WorkplanQueryPOJO;
import nc.vo.cy.workplan.pojo.Workplanb;
import nc.vo.cy.workplan.pojo.WorkplanbPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public interface IWorkplanhvoMaintain {

        public void delete(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;

        public AggWorkplanHVO[] insert(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;

        public AggWorkplanHVO[] update(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;

        public AggWorkplanHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggWorkplanHVO[] save(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;

        public AggWorkplanHVO[] unsave(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;

        public AggWorkplanHVO[] approve(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;

        public AggWorkplanHVO[] unapprove(AggWorkplanHVO[] clientFullVOs,
                        AggWorkplanHVO[] originBills) throws BusinessException;
        
		
		//huangcong,通过日期和业务员查询当天和次日工作计划
		public DailyTaskVO queryDesByPk(RestQueryDetail restDailyQuery);

		public ResponseList queryListPage(RestWorkPlanQuery restWorkplan) throws BusinessException;

		public WorkPlanAggPOJO queryDeatil(String pk) throws BusinessException;

		public void billSave(RestWorkPlanSave restWorkplan) throws BusinessException;

		public	void commit(RestWorkPlanSave restWorkplan) throws BusinessException;

}
