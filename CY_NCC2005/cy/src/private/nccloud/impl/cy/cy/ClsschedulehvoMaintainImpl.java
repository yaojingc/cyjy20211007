
package nccloud.impl.cy.cy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.CyCommonUtils.GroupBy;
import nc.utils.commonplugin.returnparam.TwoTuple;
import nc.utils.transfer.ClsscheduleToSituationVO;
import nc.vo.cy.classfilexz.ClassxzHVO;
import nc.vo.cy.classituation.AggClassituationHVO;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.cy.clsschedule.ClsscheduleBVO;
import nc.vo.cy.clsschedule.ClsscheduleHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.impl.pub.ace.AceAggbusiClsscheduleHVOPubServiceImpl;
import nccloud.itf.cy.cy.IClassituationhvoMaintain;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;

public class ClsschedulehvoMaintainImpl extends AceAggbusiClsscheduleHVOPubServiceImpl
		implements IClsschedulehvoMaintain {

	private SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	@Override
	public void delete(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggClsscheduleHVO[] insert(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggClsscheduleHVO[] update(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggClsscheduleHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggClsscheduleHVO[] save(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClsscheduleHVO[] unsave(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClsscheduleHVO[] approve(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggClsscheduleHVO[] unapprove(AggClsscheduleHVO[] clientFullVOs, AggClsscheduleHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * ??????????????????????????????????????????????????????????????????????????????
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TwoTuple<ClsscheduleBVO, Map<ClassxzHVO, List<StudentHVO>>> queryStuByXzcls(String pk_clsschedule,
			String def2) throws BusinessException {
		/**
		 * ??????????????????VO
		 */
		ClsscheduleBVO bvo = (ClsscheduleBVO) CyCommonUtils.getInstance(BaseDAO.class)
				.retrieveByPK(ClsscheduleBVO.class, pk_clsschedule);

		if (CyCommonUtils.isNotEmpty(bvo)) {
			builder.reset();
			builder.append(" SELECT ");
			builder.append(" stu.* ");
			builder.append(" FROM cy_student stu ");
			builder.append(" WHERE ");
			String[] cyclass = def2.split(",");
			builder.append("stu.def1", cyclass);
			List<StudentHVO> stuList = (List<StudentHVO>) CyCommonUtils.getInstance(BaseDAO.class)
					.executeQuery(builder.toString(), new BeanListProcessor(StudentHVO.class));

			if (CyCommonUtils.isEmpty(stuList)) {
				return null;
			}

			/**
			 * ????????????????????????????????????????????????????????????????????????????????????
			 */
			Map<String, List<StudentHVO>> map = CyCommonUtils.group(stuList, new GroupBy<String>() {
				@Override
				public String groupby(Object obj) {
					StudentHVO stuHvo = (StudentHVO) obj;
					// ????????????????????????
					return stuHvo.getDef4();
				}
			});

			Iterator<Map.Entry<String, List<StudentHVO>>> it1 = map.entrySet().iterator();

			Map<ClassxzHVO, List<StudentHVO>> mapreturn = new HashMap<ClassxzHVO, List<StudentHVO>>();
			while (it1.hasNext()) {
				Entry<String, List<StudentHVO>> entry = it1.next();
				String pk_xzclass = entry.getKey();
				List<StudentHVO> stulist = entry.getValue();
				ClassxzHVO classxzhvo = (ClassxzHVO) CyCommonUtils.getInstance(BaseDAO.class)
						.retrieveByPK(ClassxzHVO.class, pk_xzclass);
				mapreturn.put(classxzhvo, stulist);
			}

			return new TwoTuple(bvo, mapreturn);
		}
		return null;
	}

	/**
	 * ???????????????????????????????????????????????????????????????
	 */
	@SuppressWarnings("unchecked")
	public Map<ClassxzHVO, List<StudentHVO>> constractStuTreeByPKStu(String[] pk_stu) throws BusinessException {
		builder.reset();
		builder.append(" SELECT * FROM ");
		builder.append(" cy_student ");
		builder.append(" WHERE ");
		builder.append("bill_no", pk_stu);

		List<StudentHVO> stuList = (List<StudentHVO>) CyCommonUtils.getInstance(BaseDAO.class)
				.executeQuery(builder.toString(), new BeanListProcessor(StudentHVO.class));

		if (CyCommonUtils.isEmpty(stuList)) {
			return null;
		}
		/**
		 * ????????????????????????????????????????????????????????????????????????????????????
		 */
		Map<String, List<StudentHVO>> map = CyCommonUtils.group(stuList, new GroupBy<String>() {
			@Override
			public String groupby(Object obj) {
				StudentHVO stuHvo = (StudentHVO) obj;
				// ????????????????????????
				return stuHvo.getDef4();
			}
		});

		Iterator<Map.Entry<String, List<StudentHVO>>> it1 = map.entrySet().iterator();

		Map<ClassxzHVO, List<StudentHVO>> mapreturn = new HashMap<ClassxzHVO, List<StudentHVO>>();
		while (it1.hasNext()) {
			Entry<String, List<StudentHVO>> entry = it1.next();
			String pk_xzclass = entry.getKey();
			List<StudentHVO> stulist = entry.getValue();
			ClassxzHVO classxzhvo = (ClassxzHVO) CyCommonUtils.getInstance(BaseDAO.class).retrieveByPK(ClassxzHVO.class,
					pk_xzclass);
			mapreturn.put(classxzhvo, stulist);
		}

		return mapreturn;
	}

	public void updateBodyLineByPK(String pk_body, String stustr) throws BusinessException {
		builder.reset();
		builder.append(" UPDATE ");
		builder.append(" cy_clsschedule_detail ");
		builder.append(" SET ");
		builder.append("students", stustr);
		builder.append(" WHERE ");
		builder.append("pk_clsscheduled", pk_body);

		CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(builder.toString());

	}

	@Override
	public Boolean creatSituation(AggClsscheduleHVO aggvo) throws BusinessException {
		/*
		 * ????????????????????????
		 */
		AggClassituationHVO[] situations = new ClsscheduleToSituationVO().getSituation(aggvo);
		IClassituationhvoMaintain maintain = NCLocator.getInstance().lookup(IClassituationhvoMaintain.class);
		AggClassituationHVO[] insert = maintain.insert(situations, null);
		if (CyCommonUtils.isNotEmpty(insert)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public List<AggClsscheduleHVO> buildVOForInsert(Sheet sheet) throws BusinessException {
		List<AggClsscheduleHVO> agglist = new ArrayList<AggClsscheduleHVO>();
		AggClsscheduleHVO aggvo = new AggClsscheduleHVO();
		ClsscheduleHVO hvo = new ClsscheduleHVO();
		List<ClsscheduleBVO> bvolist = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist1 = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist2 = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist3 = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist4 = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist5 = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist6 = new ArrayList<ClsscheduleBVO>();
		List<ClsscheduleBVO> bvolist7 = new ArrayList<ClsscheduleBVO>();
		try {
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				// ???????????????????????????????????????????????????????????????
				Row row = sheet.getRow(i);
				List<String> cellvaluelist = new ArrayList<String>();
				String cellValue = null;
				String section = null;// ??????
				String classtime = null;// ????????????
				if (i < 9) {
					for (int j = 0; j < 2; j++) {
						Cell cell = row.getCell(j);
						if (MMValueCheck.isNotEmpty(cell)) {
							switch (cell.getCellType()) {
							case NUMERIC:
								cellValue = String.valueOf((int) cell.getNumericCellValue());
								break;
							case STRING:
								cellValue = cell.getStringCellValue();
								break;
							case FORMULA:
								cellValue = cell.getCellFormula() + "";
								break;
							case BLANK:
								cellValue = "";
								break;
							case BOOLEAN:
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case ERROR:
								cellValue = String.valueOf(cell.getErrorCellValue());
								break;
							}
						} else {
							cellValue = "";
						}
						if (MMValueCheck.isNotEmpty(cellValue)) {
							cellValue = cellValue.trim();
						}
						cellvaluelist.add(cellValue);
					}
					String strname = cellvaluelist.get(0);
					if ("????????????".equals(strname)) {
						// ??????????????????????????????
						String psncode = cellvaluelist.get(1);
						if (CyCommonUtils.isNotEmpty(psncode)) {
							String pk_psndoc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class)
									.queryPsnIdByCode(psncode);
							hvo.setTeacher(pk_psndoc);
						} else {
							hvo.setVnote("????????????????????????????????????");
							aggvo.setParentVO(hvo);
							agglist.add(aggvo);
							return agglist;
						}
					} else if ("????????????".equals(strname)) {
						String string = cellvaluelist.get(1);
						if (CyCommonUtils.isNotEmpty(string)) {
							String opentimestr = string.replaceAll("???", "-").replaceAll("???", "-").replaceAll("???", "");
							hvo.setOpentime(new UFDate(opentimestr));
						} else {
							hvo.setVnote("????????????????????????????????????");
							aggvo.setParentVO(hvo);
							agglist.add(aggvo);
							return agglist;
						}
					} else if ("??????????????????".equals(strname)) {
						Map<String, UFDate> mapdate = this.getStartAndEndDate(cellvaluelist.get(1));
						if (CyCommonUtils.isNotEmpty(mapdate)) {
							hvo.setStartdate(mapdate.get("StartDate"));// ????????????
							hvo.setEnddate(mapdate.get("EndDate"));// ????????????
						} else {
							hvo.setVnote("????????????????????????????????????yyyy???xx??????yyyy???xx??????");
							aggvo.setParentVO(hvo);
							agglist.add(aggvo);
							return agglist;
						}
					} else if ("????????????".equals(strname)) {
						String classcycode = cellvaluelist.get(1);
						String classcyid = CyCommonUtils.getInstance(ICyCommonSqlUtil.class)
								.queryClasscyIdByCycode(classcycode);
						hvo.setDef2(classcyid);
					} else if ("????????????".equals(strname)) {
						hvo.setNum(cellvaluelist.get(1));
					} else if ("????????????".equals(strname)) {
						// ????????????????????????????????????
						String schoolcode = cellvaluelist.get(1);
						if (CyCommonUtils.isNotEmpty(schoolcode)) {
							String pk_school = CyCommonUtils.getInstance(ISchoolbasicshvoMaintain.class)
									.querySchoolIdByCode(schoolcode);
							hvo.setDef1(pk_school);
						} else {
							hvo.setVnote("????????????????????????????????????");
							aggvo.setParentVO(hvo);
							agglist.add(aggvo);
							return agglist;
						}
					}
				} else if (!(i == 9 || i == 10 || i == 11 || i == 16 || i == 21) && i < 26) {
					for (int j = 0; j < 9; j++) {
						Cell cell = row.getCell(j);
						if (MMValueCheck.isNotEmpty(cell)) {
							switch (cell.getCellType()) {
							case NUMERIC:
								cellValue = String.valueOf((int) cell.getNumericCellValue());
								break;
							case STRING:
								cellValue = cell.getStringCellValue();
								break;
							case FORMULA:
								cellValue = cell.getCellFormula() + "";
								break;
							case BLANK:
								cellValue = "";
								break;
							case BOOLEAN:
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case ERROR:
								cellValue = String.valueOf(cell.getErrorCellValue());
								break;
							}
						} else {
							cellValue = "~";
						}
						if (MMValueCheck.isNotEmpty(cellValue)) {
							cellValue = cellValue.trim();
							if (j == 0) {
								section = cellValue;// ??????
							} else if (j == 1) {
								classtime = cellValue;// ????????????
							} else {
								ClsscheduleBVO bvo = new ClsscheduleBVO();
								bvo.setSection(section);
								bvo.setClasstime(classtime);
								bvo.setClassinfo(cellValue);
								if (j == 2) {// ??????
									bvo.setDef1("1001L11000000000AXOB");
									bvolist1.add(bvo);
								} else if (j == 3) {// ??????
									bvo.setDef1("1001L11000000000AXOC");
									bvolist2.add(bvo);
								} else if (j == 4) {// ??????
									bvo.setDef1("1001L11000000000AXOD");
									bvolist3.add(bvo);
								} else if (j == 5) {// ??????
									bvo.setDef1("1001L11000000000AXOE");
									bvolist4.add(bvo);
								} else if (j == 6) {// ??????
									bvo.setDef1("1001L11000000000AXOF");
									bvolist5.add(bvo);
								} else if (j == 7) {// ??????
									bvo.setDef1("1001L11000000000AXOG");
									bvolist6.add(bvo);
								} else if (j == 8) {// ??????
									bvo.setDef1("1001L11000000000AXOH");
									bvolist7.add(bvo);
								}
							}
						}
					}
				}
			}
			bvolist.addAll(bvolist1);
			bvolist.addAll(bvolist2);
			bvolist.addAll(bvolist3);
			bvolist.addAll(bvolist4);
			bvolist.addAll(bvolist5);
			bvolist.addAll(bvolist6);
			bvolist.addAll(bvolist7);
			hvo.setBillstatus(-1);
			hvo.setApprovestatus(-1);
			hvo.setPk_group(AppletsParams.Pk_Group);
			hvo.setPk_org(AppletsParams.Pk_Org);
			hvo.setDbilldate(new UFDate());
			aggvo.setParentVO(hvo);
			aggvo.setChildrenVO(bvolist.toArray(new ClsscheduleBVO[bvolist.size()]));
			agglist.add(aggvo);
			return agglist;
		} catch (Exception e) {
			return agglist;
		}
	}

	public Map<String, UFDate> getStartAndEndDate(String strval) {
		try {
			Map<String, UFDate> mapdate = new HashMap<>();
			String trim = strval.trim().replaceAll("???", "-").replaceAll("???", "");
			String[] split = trim.split("???");
			String startdate = split[0];
			String enddate = split[1];
			mapdate.put("StartDate", new UFDate(startdate + "-01"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			Date parse = sdf.parse(enddate + "-01");
			cd.setTime(parse);
			cd.add(Calendar.MONTH, 1);
			cd.add(Calendar.DATE, -1);
			String enddatestr = sdf.format(cd.getTime());// ????????????
			mapdate.put("EndDate", new UFDate(enddatestr));
			return mapdate;
		} catch (Exception e) {
			return null;
		}
	}
}
