package nccloud.impl.rule;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pf.pub.PfHistoryMsgUtil;
import nc.bs.pub.workflownote.WorknoteManager;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pub.workflowqry.IFlowInstanceQuery;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.cy.returncost.ReturncostHVO;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.wfengine.core.activity.Activity;
import nc.vo.wfengine.core.workflow.BasicWorkflowProcess;
import nc.vo.wfengine.definition.WorkflowTypeEnum;
import nccloud.framework.service.ServiceLocator;
import nccloud.vo.pf.flowinstance.NCCFlowInstanceVO;

/**
 * 校验退费申请单审批流在市场助理环节能否进行审批，如果退费金额没有维护的话则不允许进行审批
 * 
 * @author csh
 *
 */
public class CheckReturnMoneyIsSetRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] aggvos) {
		for(T obj : aggvos) {
			AggReturncostHVO aggvo = (AggReturncostHVO) obj;
			ReturncostHVO headvo = aggvo.getParentVO();
			try {
				boolean IsMarketAssistantApprove = checkManIsEditReturnMoney(headvo.getPrimaryKey());
				if(IsMarketAssistantApprove == true && headvo.getRetuencost() == null) {
					throw new BusinessException("单据号"+headvo.getBill_no()+"退费申请单未设置退款金额");
				}
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

	/**
	 * 检查当前单据审批流程环节，是否处理市场助理审批环节
	 * 
	 * @param pk_bill 单据主键
	 * @return
	 * @throws BusinessException
	 */
	private boolean checkManIsEditReturnMoney(String pk_bill) throws BusinessException {
		int workflow_type = 0;
		String billId = pk_bill;
		String billType = "TFSQ";
//		NCCFlowInstanceVO[] resultInstance = ((IFlowInstanceQuery) ServiceLocator.find(IFlowInstanceQuery.class))
//				.findFlowInstancesByBillIdAndBillTypeAppendOrderByCondition(billId, billType);
		NCCFlowInstanceVO[] resultInstance = NCLocator.getInstance().lookup(IFlowInstanceQuery.class).findFlowInstancesByBillIdAndBillTypeAppendOrderByCondition(billId, billType);
		if ((resultInstance == null) || (resultInstance.length == 0)) {
			return false;
		}
		billId = resultInstance[0].getBillversionpk();
		workflow_type = Integer.valueOf(resultInstance[0].getWorkflow_type()).intValue();
		WorkflownoteVO[] noteVOs = queryWorkitems(billId, billType, workflow_type, 0);
		boolean tag = false;
		WorkflownoteVO nowWorkflownoteVO = null;// 当前所处操作流程
		for (WorkflownoteVO workflownoteVO : noteVOs) {
			if (workflownoteVO.getIscheck().equals("N")) {
				nowWorkflownoteVO = workflownoteVO;
				break;
			}
		}
		if (nowWorkflownoteVO!=null) {
			// 判断当前审批流程环节是否为市场助理审批
			BasicWorkflowProcess p = PfHistoryMsgUtil.getBWFProcess(noteVOs);
			Activity act = PfHistoryMsgUtil.getActivity(nowWorkflownoteVO, p);
			if (act.getDescription().equals("市场助理审批")) {
				tag = true;
			} else {
				tag = false;
			}
		}
		return tag;
	}

	/**
	 * 查询单据的工作流程
	 * 
	 * @param billId        单据主键
	 * @param billType      单据类型
	 * @param iWfType       流程类型 （审批流）
	 * @param allOrFinished 0
	 * @return
	 * @throws BusinessException
	 */
	private WorkflownoteVO[] queryWorkitems(String billId, String billType, int iWfType, int allOrFinished)
			throws BusinessException {
		//WorknoteManager noteMgr = new WorknoteManager();
		try {
			return queryAllByBillId(billId, billType, allOrFinished, iWfType);
		    //return noteMgr.queryAllByBillId(billId, billType, allOrFinished, iWfType);
		}catch (DbException e) {
			//Logger.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}
	
	private WorkflownoteVO[] queryAllByBillId(String billId, String billType, int iStatus, int iWorkflowOrApproveflow)
			/*      */     throws DbException
			/*      */   {
			/*  199 */     String suffix = MultiLangUtil.getCurrentLangSeqSuffix();
			/*  200 */     String mlFiledName = "user_name" + suffix;
			/*  201 */     String sql = "select pk_checkflow,pk_wf_task,pk_billtype,billno,actiontype,senderman,checkman,ischeck,checknote,senddate,dealdate,messagenote,receivedeleteflag,observer,c.ts,c.pk_org,billid,billversionpk,a.user_name as sendername,a." + mlFiledName + " as sendernameml,b.user_name as checkname,b." + mlFiledName + " as checknameml,approvestatus,approveresult,c.pk_group,c.workflow_type from pub_workflownote c,sm_user a,sm_user b where c.senderman=a.cuserid and c.checkman=b.cuserid and c.approvestatus !=5 and (billversionpk=? or billid =? ) and actiontype<>'" + "BIZ" + "' " + (billType == null ? "" : "and pk_billtype=?");
			/*      */     
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*  216 */     if (iWorkflowOrApproveflow != 0)
			/*      */     {
			/*      */ 
			/*  219 */       sql = sql + getWorkflowTypeAndWhere(iWorkflowOrApproveflow);
			/*      */     }
			/*      */     
			/*      */ 
			/*  223 */     String tempSql = "";
			/*  224 */     if (iStatus == 1) {
			/*  225 */       tempSql = " and approveresult is not null";
			/*  226 */     } else if (iStatus == 2) {
			/*  227 */       tempSql = " and approveresult is null and  approvestatus = 0 ";
			/*      */     }
			/*      */     
			/*  230 */     sql = sql + tempSql + " order by c.senddate, c.dealdate asc,c.dealtimemillis asc";
			/*      */     
			/*  232 */     PersistenceManager persist = null;
			/*      */     try {
			/*  234 */       persist = PersistenceManager.getInstance();
			/*  235 */       JdbcSession jdbc = persist.getJdbcSession();
			/*  236 */       SQLParameter para = new SQLParameter();
			/*  237 */       para.addParam(billId);
			/*  238 */       para.addParam(billId);
			/*  239 */       if (billType != null) {
			/*  240 */         para.addParam(billType);
			/*      */       }
			/*  242 */       List<WorkflownoteVO> noteList = (List)jdbc.executeQuery(sql, para, new BeanListProcessor(WorkflownoteVO.class));
			/*      */       
			/*  244 */       return 
			/*  245 */         (WorkflownoteVO[])noteList.toArray(new WorkflownoteVO[noteList.size()]);
			/*      */     } finally {
			/*  247 */       if (persist != null) {
			/*  248 */         persist.release();
			/*      */       }
			/*      */     }
			/*      */   }
	
	 private String getWorkflowTypeAndWhere(int iWfType)
	 /*      */   {
	 /*  175 */     String strWfTypeSql = " and workflow_type in" + WorkflowTypeEnum.getInstanceTypeInSql(iWfType);
	 /*      */     
	 /*  177 */     return strWfTypeSql;
	 /*      */   }

}
