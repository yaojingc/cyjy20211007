package nc.utils.transfer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.cy.classituation.ClassituationDetailVO;
import nc.vo.cy.classituation.ClassituationHVO;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.cy.clsschedule.ClsscheduleBVO;
import nc.vo.cy.clsschedule.ClsscheduleHVO;
import nc.vo.cy.studentfile.pojo.StudentClassMsg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

/**
 * 课堂情况vo类转换
 * 
 * @author rongleia
 *
 */
public class ClsscheduleToSituationVO {

	public AggClassituationHVO[] getSituation(AggClsscheduleHVO aggvo) throws BusinessException {
		try {
			List<AggClassituationHVO> aggcslistvo = new ArrayList<>();
			String startstr = aggvo.getParentVO().getStartdate().toString();// 开始时间
			String enddstr = aggvo.getParentVO().getEnddate().toString();// 结束时间

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			Date parse = sdf.parse(startstr.substring(0, 10));
			cd.setTime(parse);// 初始日期

			List<String> mondays = new ArrayList<>();
			List<String> tuesdays = new ArrayList<>();
			List<String> wednesdays = new ArrayList<>();
			List<String> thursdays = new ArrayList<>();
			List<String> fridays = new ArrayList<>();
			List<String> saturdays = new ArrayList<>();
			List<String> sundays = new ArrayList<>();

			LocalDate sdate = LocalDate.of(Integer.parseInt(startstr.substring(0, 4)),
					Integer.parseInt(startstr.substring(5, 7)), Integer.parseInt(startstr.substring(8, 10)));
			LocalDate edate = LocalDate.of(Integer.parseInt(enddstr.substring(0, 4)),
					Integer.parseInt(enddstr.substring(5, 7)), Integer.parseInt(enddstr.substring(8, 10)));
			int num = (int) ChronoUnit.DAYS.between(sdate, edate);
			for (int i = 1; i <= num; i++) {
				int j = cd.get(Calendar.DAY_OF_WEEK);
				String date = sdf.format(cd.getTime());
				if (j == 1) {
					mondays.add(date);
				} else if (j == 2) {
					tuesdays.add(date);
				} else if (j == 3) {
					wednesdays.add(date);
				} else if (j == 4) {
					thursdays.add(date);
				} else if (j == 5) {
					fridays.add(date);
				} else if (j == 6) {
					saturdays.add(date);
				} else if (j == 7) {
					sundays.add(date);
				}
				cd.add(Calendar.DATE, 1);
			}
			ClsscheduleHVO parentVO = aggvo.getParentVO();
			ClsscheduleBVO[] bvos = (ClsscheduleBVO[]) aggvo.getChildrenVO();
			if (CyCommonUtils.isNotEmpty(bvos)) {
				for (ClsscheduleBVO bvo : bvos) {
					String days = bvo.getDef1();
					if (days.equals("1001L1100000000048SF")) {
						for (String monday : mondays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, monday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					} else if (days.equals("1001L1100000000048SG")) {
						for (String tuesday : tuesdays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, tuesday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					} else if (days.equals("1001L1100000000048SH")) {
						for (String wednesday : wednesdays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, wednesday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					} else if (days.equals("1001L1100000000048SI")) {
						for (String thursday : thursdays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, thursday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					} else if (days.equals("1001L1100000000048SJ")) {
						for (String friday : fridays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, friday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					} else if (days.equals("1001L1100000000048SK")) {
						for (String saturday : saturdays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, saturday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					} else if (days.equals("1001L1100000000048SL")) {
						for (String sunday : sundays) {
							AggClassituationHVO aggcsvo = this.setAggvo(parentVO, bvo, sunday);
							if (CyCommonUtils.isNotEmpty(aggcsvo)) {
								aggcslistvo.add(aggcsvo);
							}
						}
					}

				}
				if (CyCommonUtils.isNotEmpty(aggcslistvo)) {
					AggClassituationHVO[] array = aggcslistvo.toArray(new AggClassituationHVO[aggcslistvo.size()]);
					return array;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	private AggClassituationHVO setAggvo(ClsscheduleHVO cchvo, ClsscheduleBVO ccbvo, String day) {
		/*
		 * 拼装班级情况数据
		 */
		AggClassituationHVO aggvo = new AggClassituationHVO();
		ClassituationHVO hvo = new ClassituationHVO();
		List<ClassituationDetailVO> bvolist = new ArrayList<ClassituationDetailVO>();
		/*
		 * 表头数据Start
		 */
		try {
			hvo.setBillstatus(-1);
			hvo.setApprovestatus(-1);
			hvo.setDbilldate(new UFDate(day));
			hvo.setPk_group(AppletsParams.Pk_Group);
			hvo.setPk_org(AppletsParams.Pk_Org);
			hvo.setDef1(cchvo.getDef1());// 所属学校
			hvo.setCyteacher(cchvo.getTeacher());// 授课老师
			String[] classtime = ccbvo.getClasstime().split("-");
			hvo.setClassbegintime(classtime[0]);// 上课时间
			hvo.setClassendtime(classtime[1]);// 下课时间
			/*
			 * 表体数据Start
			 */
			String studentstr = ccbvo.getStudents();
			if (CyCommonUtils.isNotEmpty(studentstr) && studentstr.indexOf("&") != -1) {
				String[] students = ccbvo.getStudents().split("&");
				for (String student : students) {
					StudentClassMsg msgvo;

					msgvo = CyCommonUtils.getInstance(IStudenthvoMaintain.class).queryMsgByCode(student);

					if (CyCommonUtils.isNotEmpty(msgvo) && CyCommonUtils.isNotEmpty(msgvo.getPk_student())) {
						ClassituationDetailVO bvo = new ClassituationDetailVO();
						// 根据学生主键查询学生档案中的所属班级
						bvo.setDef1(CyCommonUtils.isNotEmpty(msgvo.getPk_student()) ? msgvo.getPk_student() : "~");
						bvo.setDef2(CyCommonUtils.isNotEmpty(msgvo.getCyclass()) ? msgvo.getPk_student() : "~");
						bvo.setDef3(CyCommonUtils.isNotEmpty(msgvo.getXzclass()) ? msgvo.getPk_student() : "~");
						bvo.setDef4(AppletsParams.Cqqk);// 出勤情况
						bvo.setDef5(AppletsParams.Zywcqk);// 作业完成情况
						bvo.setDef6(AppletsParams.Ktbxqk);// 课堂表现情况
						bvolist.add(bvo);
					}
				}
			}
			aggvo.setParentVO(hvo);
			aggvo.setChildrenVO(bvolist.toArray(new ClassituationDetailVO[bvolist.size()]));
			if (bvolist.size() > 0) {
				return aggvo;
			} else {
				return null;
			}
		} catch (BusinessException e) {
			return null;
		}
	}
}
