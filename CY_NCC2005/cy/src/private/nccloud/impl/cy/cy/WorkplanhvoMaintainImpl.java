package nccloud.impl.cy.cy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.workplan.RestQueryDetail;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanQuery;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanSave;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanAggPOJO;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanInfoPOJO;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.generator.IdGenerator;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.daily.DailyHVO;
import nc.vo.cy.daily.DailyTaskVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.pojo.SchoolPOJO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.teacherform.pojo.BaseRef;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.cy.workplan.WorkplanHVO;
import nc.vo.cy.workplan.WorkplanbVO;
import nc.vo.cy.workplan.pojo.WorkplanPOJO;
import nc.vo.cy.workplan.pojo.WorkplanQueryPOJO;
import nc.vo.cy.workplan.pojo.Workplanb;
import nc.vo.cy.workplan.pojo.WorkplanbPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nccloud.impl.dao.StudentBaseDao;
import nccloud.impl.pub.ace.AceAggbusiWorkplanHVOPubServiceImpl;
import nccloud.itf.cy.cy.IWorkplanhvoMaintain;

public class WorkplanhvoMaintainImpl extends AceAggbusiWorkplanHVOPubServiceImpl implements IWorkplanhvoMaintain {

	@Override
	public void delete(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggWorkplanHVO[] insert(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggWorkplanHVO[] update(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
			throws BusinessException {

		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggWorkplanHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggWorkplanHVO[] save(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggWorkplanHVO[] unsave(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggWorkplanHVO[] approve(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggWorkplanHVO[] unapprove(AggWorkplanHVO[] clientFullVOs, AggWorkplanHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}


	// huangcong 查询当天计划和明日计划
	@Override
	public DailyTaskVO queryDesByPk(RestQueryDetail restDailyQuery) {
		UserInfoByDiworkPOJO userinfo = restDailyQuery.getUserinfo();
		try {
			UserVO vo = CyCommonUtils.getInstance(ICrmService.class).getUserInfoByUserMobile(userinfo.getUserMobile());
			if (vo == null) {
				return null;
			}
			String newdate = "";
			UFDate date = new UFDate();
			UFDate datenew = new UFDate();
			Date dtn = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = format.parse(restDailyQuery.getReportdate());
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dt);
			calendar.add(Calendar.DATE, 1);
			dtn = calendar.getTime();
			newdate = format.format(dtn);
			// 字符串补0转UFDate
			if (CyCommonUtils.isNotEmpty(restDailyQuery.getReportdate())
					&& restDailyQuery.getReportdate().length() == 10) {
				date = new UFDate(restDailyQuery.getReportdate() + " 00:00:00");
				datenew = new UFDate(newdate + " 00:00:00");
			}
			if (CyCommonUtils.isNotEmpty(restDailyQuery.getReportdate())
					&& restDailyQuery.getReportdate().length() == 19) {
				date = new UFDate(restDailyQuery.getReportdate());
				datenew = new UFDate(newdate);
			}
			Workplanb bvo = queryDesByPkd(vo.getPk_psndoc(), date);
			Workplanb bvonew = queryDesByPkd(vo.getPk_psndoc(), datenew);
			DailyTaskVO hvo = new DailyTaskVO();
			if (CyCommonUtils.isNotEmpty(bvonew) && CyCommonUtils.isNotEmpty(bvonew.getDestination())) {
				hvo.setTomwork(bvonew.getDestination());
			}
			if (CyCommonUtils.isNotEmpty(bvo)) {
				hvo.setDef8(bvo.getDestination());
			}

			return hvo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Workplanb queryDesByPkd(String creator, UFDate date) {
		// TODO 自动生成的方法存根
		try {
			SqlBuilder sql = new SqlBuilder();
			if (CyCommonUtils.isNotEmpty(creator)) {
				sql.append(
						"select cb.* from cy_workplanb cb  inner join cy_workplan cw on cb.pk_workplan=cw.pk_workplan where cw.salesman",
						creator);
				sql.append("and cb.visitdate ='");
				sql.append(date);
				sql.append("' and cb.dr", 0);
				// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
			}
			BaseDAO dao = new BaseDAO();
			List<Workplanb> rvos = new ArrayList<Workplanb>();

			rvos = (List<Workplanb>) dao.executeQuery(sql.toString(), new BeanListProcessor(Workplanb.class));

			if (CyCommonUtils.isNotEmpty(rvos)) {
				return rvos.get(0);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;

	}

  	/**
	 * 根据条件分页查询数据
	 * 
	 * @param restWorkplan 查询VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public ResponseList queryListPage(RestWorkPlanQuery restWorkplan) throws BusinessException {
		// 1、查询条件拼装
		SqlBuilder sql = new SqlBuilder();
		if(CyCommonUtils.isNotEmpty(restWorkplan.getSalesman())) {
			sql.append("and salesman in (select pk_psndoc from bd_psndoc where name like '%" + restWorkplan.getSalesman()
			+ "%') ");
		}if(CyCommonUtils.isNotEmpty(restWorkplan.getBillmaker())) {
			sql.append("and creator", restWorkplan.getBillmaker());			
		}
		// 2、进行分页查询
		@SuppressWarnings("rawtypes")
		ResponseList queryListData =CyCommonUtils.getInstance(ICrmService.class).queryListPage(sql.toString(),
				restWorkplan, AggWorkplanHVO.class, WorkPlanInfoPOJO.class);
		return queryListData;

	}
	
	/**
	 * 根据主键查询详情
	 * 
	 * @param pk 查询主键
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public WorkPlanAggPOJO queryDeatil(String pk) throws BusinessException {

		if (CyCommonUtils.isEmpty(pk)) {
			throw new BusinessException("主键不能为空");
		}
		// 1、查询NccAggvo
		AggWorkplanHVO aggvo = (AggWorkplanHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk,
				AggWorkplanHVO.class);

		// 2、将当前NccAggvo数据转换成接口实体数据
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, WorkPlanAggPOJO.class, false);
		WorkPlanAggPOJO workpojo = (WorkPlanAggPOJO) changeEntitySoPowerful.getRestObj();
		return workpojo;

	}

	/**
	 * 学费减免更新
	 * 
	 * @param restWorkplan更新VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void billSave(RestWorkPlanSave restWorkplan) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restWorkplan, AggWorkplanHVO.class,
				true);
		AggWorkplanHVO transformAggVO = (AggWorkplanHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();
		AggWorkplanHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggWorkplanHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggWorkplanHVO.class);
			CrmUtil.editBillReplenishData(aggVO, transformAggVO, WorkPlanAggPOJO.class, userinfo.getUserMobile());
		} else {
			// 新增
			aggVO = transformAggVO;
			CrmUtil.addBillInitData(transformAggVO, userinfo.getUserMobile());
			UserVO user = CyCommonUtils.getInstance(ICrmService.class)
					.getUserInfoByUserMobile(userinfo.getUserMobile());
			aggVO.getParentVO().setCreator(user.getCuserid());
			aggVO.getParentVO().setCreationtime(new UFDateTime());
		}
		UserVO vo = CyCommonUtils.getInstance(ICrmService.class).getUserInfoByUserMobile(userinfo.getUserMobile());
		aggVO.getParentVO().setSalesman(vo.getPk_psndoc());

		// 2、调用接口进行保存
		CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO, false);
	}
	
	/**
	 *工作计划提交
	 * 
	 * @param restWorkplan更新VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestWorkPlanSave restWorkplan) throws BusinessException {
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restWorkplan, AggWorkplanHVO.class,
				true);
		AggWorkplanHVO transformAggVO = (AggWorkplanHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
		AggWorkplanHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 通过主键查询详情
			aggVO = (AggWorkplanHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggWorkplanHVO.class);
			if(CyCommonUtils.isNotEmpty(aggVO)) {
				CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE", aggVO);	
			}
			else {
				throw new  BusinessException("提交失败");
			}
			
		} else {
			throw new  BusinessException("提交失败");
		}	
	}
}
