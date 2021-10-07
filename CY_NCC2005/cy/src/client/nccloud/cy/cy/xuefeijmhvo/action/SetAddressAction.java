package nccloud.cy.cy.xuefeijmhvo.action;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.mx.thread.ThreadEntry;
import nc.bs.framework.mx.thread.ThreadTracer;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.workplan.WorkplanHVO;
import nc.vo.cy.workplan.WorkplanbVO;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.cy.xuefeiapply.XuefeijmHVO;
import nc.vo.sm.UserVO;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.impl.dao.StudentBaseDao;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nccloud.itf.cy.cy.IYejiapplyhvoMaintain;

public class SetAddressAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		try {
			BillCardOperator billCardOperator = new BillCardOperator();
			// 1、获取AGGVO （request转换主子VO）
			AggXuefeijmHVO vo = billCardOperator.toBill(paramIRequest);
			// 获取工作计划信息并赋值		
			if(CyCommonUtils.isNotEmpty(vo.getParentVO().getDef1())) {		 
				AggSchoolBasicsHVO aggvo = (AggSchoolBasicsHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(vo.getParentVO().getDef1(),
						AggSchoolBasicsHVO.class);
				if(CyCommonUtils.isNotEmpty(aggvo.getParentVO())){
					vo.getParentVO().setSaddress(aggvo.getParentVO().getSaddress());
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
