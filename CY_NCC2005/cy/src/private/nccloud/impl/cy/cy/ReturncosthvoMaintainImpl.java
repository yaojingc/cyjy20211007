
package nccloud.impl.cy.cy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pf.pub.PfHistoryMsgUtil;
import nc.bs.pub.workflownote.WorknoteManager;
import nc.itf.bd.cust.baseinfo.ICustBaseInfoQueryService;
import nc.itf.pub.workflowqry.IFlowInstanceQuery;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.bd.bankaccount.BankAccbasVO;
import nc.vo.bd.banktype.BankTypeVO;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.cy.returncost.MiniReturnCostInsertPojo;
import nc.vo.cy.returncost.ReturncostHVO;
import nc.vo.fct.ar.entity.AggCtArVO;
import nc.vo.fct.ar.entity.CtArBVO;
import nc.vo.fct.ar.entity.CtArVO;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.wfengine.core.activity.Activity;
import nc.vo.wfengine.core.workflow.BasicWorkflowProcess;
import nc.vo.wfengine.definition.WorkflowTypeEnum;
import nccloud.framework.service.ServiceLocator;
import nccloud.impl.pub.ace.AceAggbusiReturncostHVOPubServiceImpl;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;
import nccloud.vo.pf.flowinstance.NCCFlowInstanceVO;

public class ReturncosthvoMaintainImpl extends AceAggbusiReturncostHVOPubServiceImpl implements IReturncosthvoMaintain {

	@Override
	public void delete(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggReturncostHVO[] insert(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggReturncostHVO[] update(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggReturncostHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggReturncostHVO[] save(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggReturncostHVO[] unsave(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggReturncostHVO[] approve(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	/**
	 * ??????????????????????????????
	 * add by csh 20210928 ??????????????????????????????????????????????????????
	 */
	@Override
	public AggReturncostHVO[] unapprove(AggReturncostHVO[] clientFullVOs, AggReturncostHVO[] originBills)
			throws BusinessException {
//		return super.pubunapprovebills(clientFullVOs, originBills);
		AggReturncostHVO[] aggvos = super.pubunapprovebills(clientFullVOs, originBills);
		List<AggReturncostHVO> listAggReturncostHVO = new ArrayList<AggReturncostHVO>();
		for(AggReturncostHVO aggvo : originBills) {
			//?????????????????????????????????????????????????????????????????????????????????,?????????????????????????????????????????????
			if(aggvo.getParentVO().getApprovestatus() == 1 && aggvo.getParentVO().getDef1().equals(AppletsParams.Txtk)) {
				for(AggReturncostHVO aggReturncostHVO : aggvos) {
					if(aggvo.getPrimaryKey().equals(aggReturncostHVO.getPrimaryKey())) {
						listAggReturncostHVO.add(aggReturncostHVO);
						break;
					}
				}
			}
		}
		if(listAggReturncostHVO.size()>0) {
			restoreContentMoney(listAggReturncostHVO);
		}
		return aggvos;
	}

	
	/**
	 * ??????????????????????????????????????????????????????
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public AggReturncostHVO[] insertByMiniPro(MiniReturnCostInsertPojo miniReturnCostInsertPojo)
			throws BusinessException {

		// ??????????????????
		String stuidno = miniReturnCostInsertPojo.getStuId();
		// ????????????????????????????????????
		String bankName = miniReturnCostInsertPojo.getBankName();
		// ????????????
		String bankNo = miniReturnCostInsertPojo.getBankNo();
		// ??????????????????
		String branchBankName = miniReturnCostInsertPojo.getBranchBankName();
		
		
		// ??????????????????????????????
		BankTypeVO[] bankTypeVOs = CyCommonUtils.getInstance(CyCommonSqlUtilImpl.class).queryAllBankType("name", bankName);
		if(CyCommonUtils.isNotEmpty(bankTypeVOs)) {
			BankTypeVO bankTypeVO = bankTypeVOs[0];
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		// ??????????????????????????????????????????
		String[] pk_cust = NCLocator.getInstance().lookup(ICustBaseInfoQueryService.class)
				.queryCustomerPKByCondition(new String[] { AppletsParams.Pk_Org }, "def1='"+stuidno+"'");

		
		
		
		/**
		 * 
		 */

		// 1.??????????????????????????????????????????????????????????????????????????????????????????????????????

		AggReturncostHVO aggReturncostHVO = new AggReturncostHVO();
		ReturncostHVO hvo = new ReturncostHVO();

		aggReturncostHVO.setParentVO(hvo);
		this.insert(new AggReturncostHVO[] { aggReturncostHVO }, null);

		return null;
	}

	private static BaseDAO baseDao;

	private static IPFBusiAction pfBusiAction = null;

	private static IMDPersistenceQueryService aggvoQueryService;

	private static BaseDAO getBaseDao() {
		if (baseDao == null) {
			baseDao = new BaseDAO();
		}
		return baseDao;
	}

	/**
	 * ??????????????????
	 * 
	 * @return
	 */
	private static IPFBusiAction getPFBusiAction() {
		if (pfBusiAction == null)
			pfBusiAction = NCLocator.getInstance().lookup(IPFBusiAction.class);
		return pfBusiAction;
	}

	/**
	 * aggvo????????????
	 * 
	 * @return
	 */
	private static IMDPersistenceQueryService getMDPersistenceQueryService() {
		if (aggvoQueryService == null)
			aggvoQueryService = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class);
		return aggvoQueryService;
	}

	@Override
	public boolean checkManIsEditReturnMoney(String pk_bill, String checkman) throws BusinessException {
//			String sql = "select * from pub_workflownote where pk_billtype = 'TFSQ' and workflow_type = 2 and ischeck = 'N' and billid='"+pk_bill+"' and checkman = '"+checkman+"'";
//			WorkflownoteVO workFlowNoteVOs = executeQueryVO(sql, WorkflownoteVO.class);
//			if(workFlowNoteVOs==null ) {
//				return false;
//			}
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
//			FlowHistoryQryResult result = ((IPFWorkflowQry)ServiceLocator.find(IPFWorkflowQry.class)).queryFlowHistoryQryResult(billType, billId, workflow_type);
//			ProcessRouteRes processRoute = result.getProcessRoute();	
//			result.getHistorymsgVOs();
		WorkflownoteVO[] noteVOs = queryWorkitems(billId, billType, workflow_type, 0);
		boolean tag = false;
		WorkflownoteVO nowWorkflownoteVO = null;// ????????????????????????
		for (WorkflownoteVO workflownoteVO : noteVOs) {
			if (workflownoteVO.getSenderman().equals(checkman)) {
				// ?????????????????????????????????????????????
				tag = true;
			}
			if (workflownoteVO.getIscheck().equals("N")) {
				nowWorkflownoteVO = workflownoteVO;
			}
		}

		if (nowWorkflownoteVO != null) {
			// ?????????????????????????????????????????????????????????
			BasicWorkflowProcess p = PfHistoryMsgUtil.getBWFProcess(noteVOs);
			Activity act = PfHistoryMsgUtil.getActivity(nowWorkflownoteVO, p);
			if (act.getDescription().equals("??????????????????")) {
				tag = true;
			} else {
				tag = false;
			}
		}else {
			//?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			tag = false;
		}
		return tag;
	}

	@Override
	public AggReturncostHVO setReturnMoney(String pk_bill, String returnMoney) throws BusinessException {
//			Collection aggVOs = getMDPersistenceQueryService().queryBillOfVOByCond(AggReturncostHVO.class, " pk_returncost = '"+pk_bill+"'", true, false);
//			if (aggVOs.isEmpty() || aggVOs.size() == 0) {
//				return null;
//			}
//			AggReturncostHVO aggvo = (AggReturncostHVO) aggVOs.toArray(new AggReturncostHVO[0])[0];
//			aggvo.getParentVO().setRetuencost(new UFDouble(returnMoney));
//			aggvo.getParentVO().setStatus(VOStatus.UPDATED);
//			Object[] objs = getPFBusiAction().processBatch("SAVEBASE", "TFSQ", new AbstractBill[] { aggvo }, null, null, new HashMap());
//			
//			if(objs!=null && objs.length>0) {
//				return (AggReturncostHVO) objs[0];
//			}

		String sql = "update cy_returncost set retuencost = '" + returnMoney + "' where pk_returncost = '" + pk_bill
				+ "'";
		int count = getBaseDao().executeUpdate(sql);
		if (count > 0) {
			@SuppressWarnings("rawtypes")
			Collection aggVOs = getMDPersistenceQueryService().queryBillOfVOByCond(AggReturncostHVO.class,
					" pk_returncost = '" + pk_bill + "'", true, false);

			@SuppressWarnings("unchecked")
			AggReturncostHVO aggvo = (AggReturncostHVO) aggVOs.toArray(new AggReturncostHVO[0])[0];
			return aggvo;
		}
		return null;
	}

	private WorkflownoteVO[] queryWorkitems(String billId, String billType, int iWfType, int allOrFinished)
			throws BusinessException {
		//WorknoteManager noteMgr = new WorknoteManager();
		try {
			return queryAllByBillId(billId, billType, allOrFinished, iWfType);
			//return noteMgr.queryAllByBillId(billId, billType, allOrFinished, iWfType);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
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
	
	@Override
	public int countCtByStudent(String pk_customer) throws BusinessException {
		String querySql = "select count(0) from fct_ar where dr=0 and pk_customer = '" + pk_customer + "'"; 
		return (int) getBaseDao().executeQuery(querySql, new ColumnProcessor());
	}

	@Override
	public AggCtArVO getAggCtArVOByStudent(String pk_customer) throws BusinessException {
		@SuppressWarnings("rawtypes")
		Collection aggVOs = getMDPersistenceQueryService().queryBillOfVOByCond(AggCtArVO.class,
				" dr = 0 and pk_customer = '" + pk_customer + "'", true, false);

		if (aggVOs == null || aggVOs.size() == 0) {
			return null;
		}

		@SuppressWarnings("unchecked")
		AggCtArVO aggvo = (AggCtArVO) aggVOs.toArray(new AggCtArVO[0])[0];

		return aggvo;
	}

	@Override
	public BankAccbasVO queryReturnBankData(BankAccbasVO bankaccVO) throws BusinessException {
		String sql = "select * from bd_bankaccbas where pk_org='" + bankaccVO.getPk_org() + "' and def1 = 'Y'";
		BankAccbasVO[] bankAccbasVOs = executeQueryVOs(sql, BankAccbasVO.class);

		if (bankAccbasVOs != null && bankAccbasVOs.length > 1) {
			StringBuilder strs = new StringBuilder();
			for (BankAccbasVO bankAccbasVO : bankAccbasVOs) {
				strs.append("???").append(bankAccbasVO.getCode()).append("???");
			}

			throw new BusinessException("?????????????????????????????????" + strs.toString() + "??????????????????????????????????????????????????????");
		}

		if (bankAccbasVOs == null || bankAccbasVOs.length == 0) {
			return null;
		}

		return bankAccbasVOs[0];
	}

	/**
	 * ????????????(??????)
	 * 
	 * @param <T>
	 * @param sql     SQL??????
	 * @param voClass ????????????
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({ "all" })
	private static <T> T executeQueryVO(String sql, Class<T> voClass) throws DAOException {
		return (T) getBaseDao().executeQuery(sql, new BeanProcessor(voClass));
	}

	/**
	 * ????????????(??????)
	 * 
	 * @param <T>
	 * @param sql
	 * @param voClass
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({ "all" })
	private <T> T[] executeQueryVOs(String sql, Class<T> voClass) throws DAOException {
		List<T> list = (List) getBaseDao().executeQuery(sql, new BeanListProcessor(voClass));
		if ((list == null) || (list.size() == 0)) {
			return (T[]) Array.newInstance(voClass, 0);
		}

		return (T[]) list.toArray((Object[]) Array.newInstance(voClass, list.size()));
	}

	/**
	  * ??????????????????
	 * @param listAggReturncostHVO
	 * @throws BusinessException
	 */
	private void restoreContentMoney(List<AggReturncostHVO> listAggReturncostHVO) throws BusinessException  {
		List<AggCtArVO> listCtAgg = new ArrayList<AggCtArVO>();
		List<AggReturncostHVO> listReAgg = new ArrayList<AggReturncostHVO>();
		for(AggReturncostHVO aggvo : listAggReturncostHVO) {
			ReturncostHVO returncostHVO = aggvo.getParentVO();
			AggCtArVO aggCtArVO = getAggCtArVOByStudent(aggvo.getParentVO().getDef2());
			
			if(aggCtArVO==null) {
				continue;
			}
			
			CtArVO ctArvo = aggCtArVO.getParentVO();
			CtArBVO[] ctArBVOs = (CtArBVO[]) aggCtArVO.getChildren(CtArBVO.class);
			//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			UFDouble norigplanamount = ctArvo.getNorigplanamount().add(new UFDouble(returncostHVO.getDef3()));//???????????????????????????
			UFDouble norigpshamount = ctArvo.getNorigpshamount().add(new UFDouble(returncostHVO.getDef3()));//????????????????????????
			UFDouble noriplangpmny = ctArBVOs[0].getNoriplangpmny().add(new UFDouble(returncostHVO.getDef3()));//???????????????????????????
			UFDouble noritotalgpmny = ctArBVOs[0].getNoritotalgpmny().add(new UFDouble(returncostHVO.getDef3()));//????????????????????????
			//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			UFDouble norigcopamount = ctArvo.getNorigcopamount().sub(new UFDouble(returncostHVO.getDef4()));//????????????????????????
			UFDouble noricopegpmny = ctArBVOs[0].getNoricopegpmny().sub(new UFDouble(returncostHVO.getDef4()));//????????????????????????
			//????????????????????????
			ctArvo.setNorigplanamount(norigplanamount);
			ctArvo.setNorigpshamount(norigpshamount);
			ctArvo.setNorigcopamount(norigcopamount);
			ctArvo.setStatus(VOStatus.UPDATED);
			ctArBVOs[0].setNoriplangpmny(noriplangpmny);
			ctArBVOs[0].setNoritotalgpmny(noritotalgpmny);
			ctArBVOs[0].setNoricopegpmny(noricopegpmny);
			ctArBVOs[0].setStatus(VOStatus.UPDATED);
			listCtAgg.add(aggCtArVO);
			
			//????????????????????????????????????????????????????????????
			returncostHVO.setDef3(null);
			returncostHVO.setDef4(null);
			returncostHVO.setStatus(VOStatus.UPDATED);
			listReAgg.add(aggvo);
		}
		
		if(listCtAgg.size()>0) {
			Object[] aggCtArVOObjs = getPFBusiAction().processBatch("SAVEBASE", "FCT2", listCtAgg.toArray(new AggCtArVO[listCtAgg.size()]), null, null, new HashMap());
			if(aggCtArVOObjs==null || aggCtArVOObjs.length==0) {
				throw new  BusinessException("???????????????????????????????????????????????????????????????");
			}
		}
		if(listReAgg.size()>0) {
			Object[] aggReturncostObjs = getPFBusiAction().processBatch("SAVEBASE", "TFSQ", listReAgg.toArray(new AggReturncostHVO[listReAgg.size()]), null, null, new HashMap());
			if(aggReturncostObjs==null || aggReturncostObjs.length==0) {
				throw new  BusinessException("???????????????????????????????????????????????????");
			}
		}
	}
}
