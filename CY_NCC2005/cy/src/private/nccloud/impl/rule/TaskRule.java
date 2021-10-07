package nccloud.impl.rule;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.ibm.icu.text.SimpleDateFormat;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanSave;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanBvoPOJO;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskallocation.TaskallocationDetailVO;
import nc.vo.cy.taskallocation.TaskallocationHVO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.cy.workplan.WorkplanHVO;
import nc.vo.cy.workplan.WorkplanbVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;

/*@author huangcong
 * 任务分配新增后处理逻辑，工作计划覆盖和新增
*/
public class TaskRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] arg0) {
		for (T aggvo : arg0) {
			AggTaskallocationHVO tvo = (AggTaskallocationHVO) aggvo;
			try {
				updateWork(tvo);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

	// 校验该日期的工作计划是否存在
	public String Check(TaskallocationDetailVO vo, String salesman) {
		SqlBuilder sql = new SqlBuilder();
		sql.append("and salesman", salesman);
		if (CyCommonUtils.isNotEmpty(vo.getDef11()) && CyCommonUtils.isNotEmpty(vo.getDef17())) {
			// 添加条件
			UFDate d1 = new UFDate(vo.getDef11() + " 00:00:00");
			UFDate d2 = new UFDate(vo.getDef17() + " 00:00:00");
			sql.append("and startdate", d1.toString());
			sql.append("and enddate", d2.toString());
		}
		try {
			WorkplanHVO hvo = (WorkplanHVO) CyCommonUtils.getInstance(ICyCommonSqlUtil.class).getVO("cy_workplan",
					sql.toString(), WorkplanHVO.class);
			if (CyCommonUtils.isNotEmpty(hvo)) {
				return hvo.getPk_workplan();
			}

		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return null;
	}

	// 工作计划更新业务
	public void updateWork(AggTaskallocationHVO aggVO) {
		List<TaskallocationDetailVO> list = new ArrayList<>();
		for (int j = 0; j < aggVO.getChildren(TaskallocationDetailVO.class).length; j++) {
			TaskallocationDetailVO vo = getMonday(
					(TaskallocationDetailVO) aggVO.getChildren(TaskallocationDetailVO.class)[j]);
			list.add(vo);
		}
		Map<String, List<TaskallocationDetailVO>> collect = list.stream()
				.collect(Collectors.groupingBy(TaskallocationDetailVO::getDef11));
		for (String key : collect.keySet()) {
			List<TaskallocationDetailVO> dvos = collect.get(key);
			try {
				updateWorkplan(dvos, aggVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 工作计划更新方法
	public void updateWorkplan(List<TaskallocationDetailVO> dvos, AggTaskallocationHVO aggVO) throws Exception {
		String pk_workplan = null;
		AggWorkplanHVO aggvo = null;
		if (CyCommonUtils.isNotEmpty(dvos)) {
			pk_workplan = Check(dvos.get(0), aggVO.getParentVO().getSalesman());
		}

		if (CyCommonUtils.isNotEmpty(pk_workplan)) {
			aggvo = (AggWorkplanHVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk_workplan,
					AggWorkplanHVO.class);
			// 工作计划覆盖
			if (aggvo != null) {
				for (int j = 0; j < aggvo.getChildren(WorkplanbVO.class).length; j++) {
					WorkplanbVO vo = (WorkplanbVO) aggvo.getChildren(WorkplanbVO.class)[j];
					for (TaskallocationDetailVO dvo : dvos) {
						if (dvo.getAssgindate().toString().equals(vo.getVisitdate().toString())) {
							vo.setDestination(dvo.getTask());
							vo.setDef1(dvo.getDef1());
							vo.setStatus(1);
						}
					}
				}
				aggvo.getParentVO().setStatus(VOStatus.UPDATED);
			}
		}

		// 工作计划新增
		else {
			aggvo = new AggWorkplanHVO();
			WorkplanHVO hvo = new WorkplanHVO();
			hvo.setStartdate(new UFDate(dvos.get(0).getDef11() + " 00:00:00"));
			hvo.setEnddate(new UFDate(dvos.get(0).getDef17() + " 00:00:00"));
			hvo.setSalesman(aggVO.getParentVO().getSalesman());
			aggvo.setParentVO(hvo);
			aggvo.getParentVO().setAttributeValue("pk_group", aggVO.getParentVO().getPk_group());// 集团
			aggvo.getParentVO().setAttributeValue("pk_org", aggVO.getParentVO().getPk_org());// 组织
			aggvo.getParentVO().setAttributeValue("pk_org_v", aggVO.getParentVO().getPk_org_v());// 组织多版本
			aggvo.getParentVO().setAttributeValue("dbilldate", new UFDate());// 单据日期
			aggvo.getParentVO().setAttributeValue("billstatus", -1);// 单据状态（默认自由）
			aggvo.getParentVO().setAttributeValue("approvestatus", -1);// 审批状态（默认自由）
			aggvo.getParentVO().setAttributeValue("billmaker", aggVO.getParentVO().getBillmaker());// 制单人（用户表主键）
			aggvo.getParentVO().setAttributeValue("creator", aggVO.getParentVO().getCreator());// 创建人（用户表主键）
			aggvo.getParentVO().setAttributeValue("creationtime", new UFDateTime());// 创建时间
			aggvo.getParentVO().setStatus(VOStatus.NEW);
			CrmUtil.setBillType(aggvo);
			ArrayList<WorkplanbVO> bvos = new ArrayList<>();
			List<String> dates = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date week1 = sdf.parse(dvos.get(0).getDef11());
			dates = CrmUtil.addData(week1, true, "星期一");
			for (TaskallocationDetailVO dvo : dvos) {
				WorkplanbVO vo = new WorkplanbVO();
				vo.setDestination(dvo.getTask());
				vo.setDef1(dvo.getDef1());
				vo.setVisitdate(dvo.getAssgindate());
				dates.remove(dvo.getAssgindate().toString().substring(0, 10));
				bvos.add(vo);
			}
			for (String d : dates) {
				WorkplanbVO vo = new WorkplanbVO();
				vo.setVisitdate(new UFDate(d + " 00:00:00"));
				bvos.add(vo);
			}
			WorkplanbVO[] array = bvos.toArray(new WorkplanbVO[bvos.size()]);
			aggvo.setChildren(WorkplanbVO.class, array);

		}
		// 调用动作脚本
		if (CyCommonUtils.isNotEmpty(aggvo)) {
			CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggvo.getParentVO().getBilltype(), aggvo,
					false);
		}

	}

	// 通过日期获取当周周一和周日的日期
	public TaskallocationDetailVO getMonday(TaskallocationDetailVO vo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		Date time = null;
		try {
			time = sdf.parse(vo.getAssgindate().toString().substring(0, 10));
			cal.setTime(time);
			System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			System.out.println("所在周星期一的日期：" + sdf.format(cal.getTime()));
			vo.setDef11(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 6);
			System.out.println("所在周星期日的日期：" + sdf.format(cal.getTime()));
			vo.setDef17(sdf.format(cal.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;

	}

}
