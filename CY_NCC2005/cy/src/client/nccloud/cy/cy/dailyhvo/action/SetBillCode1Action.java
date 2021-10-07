package nccloud.cy.cy.dailyhvo.action;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.mx.thread.ThreadEntry;
import nc.bs.framework.mx.thread.ThreadTracer;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.utils.crm.CrmUtil;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.workplan.WorkplanHVO;
import nc.vo.cy.workplan.WorkplanbVO;
import nc.vo.sm.UserVO;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.impl.dao.StudentBaseDao;

public class SetBillCode1Action implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		try {
			BillCardOperator billCardOperator = new BillCardOperator();
			// 1、获取AGGVO （request转换主子VO）
			AggDailyHVO vo = billCardOperator.toBill(paramIRequest);
			// 获取工作计划信息并赋值
			WorkplanHVO wvo=new WorkplanHVO();
			if(vo.getParentVO().getReportdate()!=null) {
				 ThreadEntry cte = ThreadTracer.getInstance().getCurThreadEntry();
				
				 UserVO uvo=(UserVO) new BaseDAO().executeQuery("select * from sm_user where cuserid =  ='"+cte.getRemoteUser()+"')", new BeanProcessor(UserVO.class));
				 WorkplanbVO wbvo=StudentBaseDao.queryDesByPk(uvo.getPk_psndoc(),vo.getParentVO().getReportdate());
				 if(wbvo.getDestination()!=null) {
					 vo.getParentVO().setDef8(wbvo.getDestination()); 
				 }		
			}
			
			Object card = billCardOperator.toCard(vo);
			return card;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
