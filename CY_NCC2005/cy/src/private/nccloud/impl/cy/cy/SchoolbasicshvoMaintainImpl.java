
package nccloud.impl.cy.cy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.entity.crm.schoolarchives.RestSchoolSave;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.busibean.SysinitAccessorForNcc;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonConstant.EnumConstant;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.RefPOJO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.schoolarchives.pojo.AggSchoolBasicsPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsClassPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsLinkPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsTopPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolBasicsVisitPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolCardRetuenPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolListDetailPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolQueryPOJO;
import nc.vo.org.DeptVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.impl.dao.SchoolBaseDao;
import nccloud.impl.pub.ace.AceAggbusiSchoolBasicsHVOPubServiceImpl;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;

public class SchoolbasicshvoMaintainImpl extends AceAggbusiSchoolBasicsHVOPubServiceImpl
		implements ISchoolbasicshvoMaintain {

	private static BaseDAO basedao = CyCommonUtils.getInstance(BaseDAO.class);

	private static SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	@Override
	public void delete(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolBasicsHVO[] insert(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolBasicsHVO[] update(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolBasicsHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSchoolBasicsHVO[] save(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolBasicsHVO[] unsave(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolBasicsHVO[] approve(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSchoolBasicsHVO[] unapprove(AggSchoolBasicsHVO[] clientFullVOs, AggSchoolBasicsHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public PagingPOJO querySchoolList(SchoolQueryPOJO queryvo) throws BusinessException {
		PagingPOJO revo = new PagingPOJO();
		CyCommonUtils.login();
		List<SchoolListDetailPOJO> schoollist = SchoolBaseDao.querySchoolList(queryvo);
		if (CyCommonUtils.isEmpty(schoollist)) {
			return null;
		}
		for (SchoolListDetailPOJO schoolListDetailPOJO : schoollist) {
			String isuse = schoolListDetailPOJO.getIsuse();
			if (CyCommonUtils.isNotEmpty(isuse)) {
				RefPOJO pojo = new RefPOJO();
				if ("Y".equals(isuse)) {
					pojo.setLabel("是");
					pojo.setValue("Y");
				} else {
					pojo.setLabel("否");
					pojo.setValue("N");
				}
				schoolListDetailPOJO.setIsuseing(pojo);
				schoolListDetailPOJO.setIsuse(null);
			}
		}
		String sname = queryvo.getSname();
		String queryTotal = SchoolBaseDao.queryTotal(sname);
		int total = Integer.parseInt(queryTotal);
		int pagesize = queryvo.getSize().intValue();
		revo.setTotal(total);
		UFDouble totalPages;
		int split = new UFDouble(total).div(new UFDouble(pagesize)).intValue();
		UFDouble mod = new UFDouble(total).mod(new UFDouble(pagesize));
		if (mod.sub(new UFDouble(0)).doubleValue() > 0) {
			totalPages = new UFDouble(split).add(new UFDouble(1));
		} else {
			totalPages = new UFDouble(split);
		}
		revo.setTotalPages(totalPages.intValue());
		revo.setPageSize(pagesize);
		revo.setCurrentPage(queryvo.getIndex().intValue());
		revo.setContent(schoollist);
		return revo;
	}

	@Override
	public SchoolCardRetuenPOJO querySchoolCard(String pk_school) throws BusinessException {
		/*
		 * 根据学校主键查询明细信息
		 */
		SchoolCardRetuenPOJO aggpojo = new SchoolCardRetuenPOJO();
		SchoolBasicsPOJO basicpojo = SchoolBaseDao.querySchoolBasicByPk(pk_school);
		String approvestatus = basicpojo.getApprovestatus();
		if (CyCommonUtils.isNotEmpty(approvestatus)) {
			RefPOJO rpojo = new RefPOJO();
			rpojo.setValue(approvestatus);
			rpojo.setLabel(EnumConstant.statusMap.get(approvestatus));
			basicpojo.setBillstatus(rpojo);
			basicpojo.setApprovestatus(null);
		}
		List<SchoolBasicsClassPOJO> classlist = SchoolBaseDao.querySchoolBasicClassByPk(pk_school);
		if (CyCommonUtils.isNotEmpty(classlist)) {
			for (SchoolBasicsClassPOJO schoolBasicsClassPOJO : classlist) {
				String ufwhetherclas = schoolBasicsClassPOJO.getUfwhetherclas();
				if (CyCommonUtils.isNotEmpty(ufwhetherclas)) {
					RefPOJO pojo = new RefPOJO();
					if ("Y".equals(ufwhetherclas)) {
						pojo.setLabel("是");
						pojo.setValue("Y");
					} else {
						pojo.setLabel("否");
						pojo.setValue("N");
					}
					schoolBasicsClassPOJO.setWhetherclas(pojo);
					schoolBasicsClassPOJO.setUfwhetherclas(null);
				}
			}
		}
		List<SchoolBasicsLinkPOJO> linklist = SchoolBaseDao.querySchoolBasicLinkByPk(pk_school);
		List<SchoolBasicsTopPOJO> toplist = SchoolBaseDao.querySchoolBasicTopByPk(pk_school);
		if (CyCommonUtils.isNotEmpty(toplist)) {
			for (SchoolBasicsTopPOJO schoolBasicsTopPOJO : toplist) {
				String ufstablelevel = schoolBasicsTopPOJO.getUfstablelevel();
				if (CyCommonUtils.isNotEmpty(ufstablelevel)) {
					RefPOJO refPOJO = new RefPOJO();
					refPOJO.setLabel(EnumConstant.stableMap.get(ufstablelevel));
					refPOJO.setValue(ufstablelevel);
					schoolBasicsTopPOJO.setStablelevel(refPOJO);
					schoolBasicsTopPOJO.setUfstablelevel(null);
				}
				String ufisexistside = schoolBasicsTopPOJO.getUfisexistside();
				if (CyCommonUtils.isNotEmpty(ufisexistside)) {
					RefPOJO pojo = new RefPOJO();
					if ("Y".equals(ufisexistside)) {
						pojo.setLabel("是");
						pojo.setValue("Y");
					} else {
						pojo.setLabel("否");
						pojo.setValue("N");
					}
					schoolBasicsTopPOJO.setIsexistside(pojo);
					schoolBasicsTopPOJO.setUfisexistside(null);
				}
				String ufredornot = schoolBasicsTopPOJO.getUfredornot();
				if (CyCommonUtils.isNotEmpty(ufredornot)) {
					RefPOJO pojo2 = new RefPOJO();
					if ("Y".equals(ufredornot)) {
						pojo2.setLabel("是");
						pojo2.setValue("Y");
					} else {
						pojo2.setLabel("否");
						pojo2.setValue("N");
					}
					schoolBasicsTopPOJO.setRedornot(pojo2);
					schoolBasicsTopPOJO.setUfredornot(null);
				}
			}
		}
		List<SchoolBasicsVisitPOJO> visitlist = SchoolBaseDao.querySchoolBasicVisitByPk(pk_school);
		if (CyCommonUtils.isNotEmpty(visitlist)) {
			for (SchoolBasicsVisitPOJO schoolBasicsVisitPOJO : visitlist) {
				String ufdevelsituation = schoolBasicsVisitPOJO.getUfdevelsituation();
				if (CyCommonUtils.isNotEmpty(ufdevelsituation)) {
					RefPOJO pojo = new RefPOJO();
					if ("Y".equals(ufdevelsituation)) {
						pojo.setLabel("是");
						pojo.setValue("Y");
					} else {
						pojo.setLabel("否");
						pojo.setValue("N");
					}
					schoolBasicsVisitPOJO.setDevelsituation(pojo);
					schoolBasicsVisitPOJO.setUfdevelsituation(null);
				}
				String ufbusilevel = schoolBasicsVisitPOJO.getUfbusilevel();
				if (CyCommonUtils.isNotEmpty(ufbusilevel)) {
					RefPOJO refPOJO = new RefPOJO();
					refPOJO.setValue(ufbusilevel);
					refPOJO.setLabel(EnumConstant.busiMap.get(ufbusilevel));
					schoolBasicsVisitPOJO.setBusilevel(refPOJO);
					schoolBasicsVisitPOJO.setUfbusilevel(null);
				}
				String ufintedegree = schoolBasicsVisitPOJO.getUfintedegree();
				if (CyCommonUtils.isNotEmpty(ufintedegree)) {
					RefPOJO refPOJO2 = new RefPOJO();
					refPOJO2.setValue(ufintedegree);
					refPOJO2.setLabel(EnumConstant.intedegreeMap.get(ufintedegree));
					schoolBasicsVisitPOJO.setIntedegree(refPOJO2);
					schoolBasicsVisitPOJO.setUfintedegree(null);
				}
			}
		}
		aggpojo.setSchoolbaseinfo(basicpojo);
		aggpojo.setId_schoolbasicsclassvo(classlist);
		aggpojo.setId_schoolbasicslinkvo(linklist);
		aggpojo.setId_schoolbasicstopvo(toplist);
		aggpojo.setId_schoolbasicsvisitvo(visitlist);
		return aggpojo;
	}

	@Override
	public String UpdateSchoolList(AggSchoolBasicsPOJO aggpojo) throws BusinessException {

		SchoolBasicsPOJO schoolbaseinfo = aggpojo.getSchoolbaseinfo();// 学校基础信息
		String pk_school = schoolbaseinfo.getPk_school();// 学校主键
		try {
			String update = "";
			List<SchoolBasicsLinkPOJO> linkpojolist = aggpojo.getId_schoolbasicslinkvo();// 联系人信息
			if (CyCommonUtils.isNotEmpty(linkpojolist)) {
				for (SchoolBasicsLinkPOJO linkpojo : linkpojolist) {
					update = SchoolBaseDao.updateLink(linkpojo, pk_school);
				}
			}
			List<SchoolBasicsClassPOJO> clspojolist = aggpojo.getId_schoolbasicsclassvo();// 年纪情况信息
			if (CyCommonUtils.isNotEmpty(clspojolist)) {
				for (SchoolBasicsClassPOJO classpojo : clspojolist) {
					update = SchoolBaseDao.updateClass(classpojo, pk_school);
				}
			}
			List<SchoolBasicsVisitPOJO> visitpojolist = aggpojo.getId_schoolbasicsvisitvo();// 拜访记录信息
			if (CyCommonUtils.isNotEmpty(visitpojolist)) {
				for (SchoolBasicsVisitPOJO visitpojo : visitpojolist) {
					update = SchoolBaseDao.updateVisit(visitpojo, pk_school);
				}
			}
			List<SchoolBasicsTopPOJO> toppojolist = aggpojo.getId_schoolbasicstopvo();// 高管维护信息
			if (CyCommonUtils.isNotEmpty(toppojolist)) {
				for (SchoolBasicsTopPOJO toppojo : toppojolist) {
					update = SchoolBaseDao.updateTop(toppojo, pk_school);
				}
			}
			if ("ok".equals(update)) {
				SchoolBaseDao.updateTs(pk_school);
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "ok";
	}

	/**
	 * 学校信息查询
	 * 
	 * @param pk
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> schoolDataQuery(Set<String> pks) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (MMValueCheck.isEmpty(pks)) {
			return retMap;
		}
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			SqlBuilder sql = new SqlBuilder();
			sql.append(" SELECT distinct pk_school pk,sname name  FROM cy_schoolbasics WHERE nvl(dr,0)=0 ");
			sql.append(" and pk_school ", pks.toArray(new String[0]));
			List<Map<String, String>> queryListMap = (List<Map<String, String>>) queryBS.executeQuery(sql.toString(),
					new MapListProcessor());
			if (MMValueCheck.isNotEmpty(queryListMap)) {
				for (Map<String, String> map : queryListMap) {
					retMap.put(map.get("pk"), map.get("name"));
				}
			}
		} catch (Exception e) {
			return retMap;
		}
		return retMap;
	}

	/**
	 * 学校分配，并记录变动信息
	 */
	public int schoolAllot(String pk_psndoc, String pk_psndoc2, String pk_psndoc3, String pk_psndoc4,
			SchoolBasicsHVO[] schoolVOS) throws BusinessException {
		// 判断待分配的人员是普通业务员还是高管....
		DeptVO deptInfoVO = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDeptByPsnInfo(pk_psndoc);
		if (CyCommonUtils.isNotEmpty(deptInfoVO)) {
			String dept_code = deptInfoVO.getCode();
			Arrays.asList(schoolVOS).forEach(item -> {
				try {
					this.batchUpdate(pk_psndoc, pk_psndoc2, pk_psndoc3, pk_psndoc4, item, dept_code);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		return 0;
	}

	private void batchUpdate(String pk_psndoc, String pk_psndoc2, String pk_psndoc3, String pk_psndoc4,
			SchoolBasicsHVO schoolVO, String dept_code) throws BusinessException {
//		SchoolBasicsBusinVO schoolBasicsBusinVO = new SchoolBasicsBusinVO();
		builder.reset();
		builder.append(" UPDATE cy_schoolbasics SET ");

		try {
			/**
			 * 010301 市场部-开发(saleman 业务员) 010302 市场部-维护(senior 高管)
			 */
//		if ("010301".equals(dept_code)) {
//			builder.append(" saleman ", pk_psndoc);
//			schoolBasicsBusinVO.setDef1(schoolVO.getSaleman());
//			schoolBasicsBusinVO.setDef2(pk_psndoc);
//		} else if ("010302".equals(dept_code)) {
//			builder.append(" senior ", pk_psndoc);
//			schoolBasicsBusinVO.setDef3(schoolVO.getSenior());
//			schoolBasicsBusinVO.setDef4(pk_psndoc);
//		}
			if (CyCommonUtils.isNotEmpty(pk_psndoc)) {
				builder.append(" saleman ", pk_psndoc);
				String date = getdate();
				builder.append(",def18", date);
			} else {
				builder.append(" saleman ", "~");
			}
			if (!"Y".equals(schoolVO.getDef17())) {
				String doc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk("开发中", "cy05");
				builder.append(",def17", "Y");
				builder.append(",def12", doc);
			}
			if (CyCommonUtils.isNotEmpty(pk_psndoc2)) {
				builder.append(" ,senior ", pk_psndoc2);
			}
			if (CyCommonUtils.isNotEmpty(pk_psndoc3)) {
				builder.append(" ,def13 ", pk_psndoc3);
			}
			if (CyCommonUtils.isNotEmpty(pk_psndoc4)) {
				builder.append(" ,def14 ", pk_psndoc4);
			}

			builder.append(" WHERE ");
			builder.append(" pk_school ", schoolVO.getPk_school());

			int i = CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(builder.toString());
			/**
			 * 开始插入变更记录 def1： 变动前业务员 def2： 变动后业务员 def3: 变动前高管 def4: 变动后高管 def5： 变动时间
			 */
//			schoolBasicsBusinVO.setPk_school(schoolVO.getPk_school());
//			schoolBasicsBusinVO.setDef5(new UFDateTime().toString());
//			schoolBasicsBusinVO.setTs(new UFDateTime());
//			SchoolBaseDao.insertBusin(schoolBasicsBusinVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String querySchoolIdByCode(String code) throws BusinessException {
		builder.reset();
		builder.append(" select pk_school from cy_schoolbasic where bill_no = '" + code + "' and dr = '0'");
		Object executeQuery = CyCommonUtils.getInstance(BaseDAO.class).executeQuery(builder.toString(),
				new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(executeQuery)) {
			return executeQuery.toString();
		} else {
			return "";
		}
	}

	/**
	 * 处理得到学校截止日期
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */

	public String getdate() throws BusinessException {
		Integer days = SysinitAccessorForNcc.getNccInstance().getParaInt(AppletsParams.Pk_Group, "cy-date");
		String hjks = SysinitAccessorForNcc.getNccInstance().getParaString(AppletsParams.Pk_Group, "cy-hjks");
		String hjjs = SysinitAccessorForNcc.getNccInstance().getParaString(AppletsParams.Pk_Group, "cy-hjjs");
		String sjks = SysinitAccessorForNcc.getNccInstance().getParaString(AppletsParams.Pk_Group, "cy-sjks");
		String sjjs = SysinitAccessorForNcc.getNccInstance().getParaString(AppletsParams.Pk_Group, "cy-sjjs");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date hjksdate = sdf.parse(hjks);// 寒假开始日期
			Date hjjsdate = sdf.parse(hjjs); // 寒假结束日期
			int hj = (int) (hjksdate.getTime() - hjjsdate.getTime()) / (24 * 3600 * 1000);
			Date sjksdate = sdf.parse(sjks); // 暑假开始日期
			Date sjjsdate = sdf.parse(sjjs); // 暑假结束日期
			int sj = (int) (sjksdate.getTime() - sjjsdate.getTime()) / (24 * 3600 * 1000);

			// 获取当前系统日期
			Date now = new Date();
			Calendar after = Calendar.getInstance();
			after.setTime(now);
			after.set(Calendar.DATE, after.get(Calendar.DATE) + days);
			if ((now.getTime() >= hjksdate.getTime() && now.getTime() <= hjjsdate.getTime())
					|| (after.getTime().getTime() >= hjksdate.getTime()
							&& after.getTime().getTime() <= hjjsdate.getTime())) {
				Calendar afterDate = Calendar.getInstance();
				afterDate.setTime(after.getTime());
				afterDate.set(Calendar.DATE, afterDate.get(Calendar.DATE) + hj);
				return sdf.format(afterDate.getTime());
			} else if ((now.getTime() >= sjksdate.getTime() && now.getTime() <= sjjsdate.getTime())
					|| (after.getTime().getTime() >= sjksdate.getTime()
							&& after.getTime().getTime() <= sjjsdate.getTime())) {
				Calendar afterDate = Calendar.getInstance();
				afterDate.setTime(after.getTime());
				afterDate.set(Calendar.DATE, afterDate.get(Calendar.DATE) + sj);
				return sdf.format(afterDate.getTime());
			} else {
				return sdf.format(after.getTime());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * /** 学校档案提交
	 * 
	 * @param restSave提交VO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void commit(RestSchoolSave restSave) throws BusinessException {
		// 1、将接口实体Vo转换成Aggvo
		ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restSave, AggSchoolBasicsHVO.class,
				true);
		AggSchoolBasicsHVO transformAggVO = (AggSchoolBasicsHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo

		AggSchoolBasicsHVO aggVO = null;
		if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
			// 修改
			aggVO = (AggSchoolBasicsHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(transformAggVO.getPrimaryKey(), AggSchoolBasicsHVO.class);
			// 2、调用接口进行提交
			CyCommonUtils.getInstance(ICrmService.class).pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVE", aggVO);
		} else {
			throw new BusinessException("提交失败");
		}
	}

	@Override
	public String queryCustomerIdBySchoolId(String schoolid) throws BusinessException {
		builder.reset();
		builder.append(" select pk_customer from bd_customer where def9 = '" + schoolid + "' and dr = '0'");
		Object executeQuery = CyCommonUtils.getInstance(BaseDAO.class).executeQuery(builder.toString(),
				new ColumnProcessor());
		if (CyCommonUtils.isNotEmpty(executeQuery)) {
			return executeQuery.toString();
		} else {
			return "";
		}
	}
}
