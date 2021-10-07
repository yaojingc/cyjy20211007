package nccloud.impl.sync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.itf.fct.ar.IArMaintain;
import nc.vo.fct.ar.entity.AggCtArVO;
import nc.vo.fct.ar.entity.CtArVO;
import nc.vo.fct.ar.entity.CtArBVO;
import nc.vo.fct.ar.entity.CtArPlanVO;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.creditcontract.CreditcontractDetailVO;
import nc.vo.cy.creditcontract.CreditcontractHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 学费收款合同定时生成正式合同
 * 
 * @author tanrg
 *
 */
public class ToFormalContractSync implements IBackgroundWorkPlugin {
	private BaseDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0) throws BusinessException {
		// 查询学生收款合同数据
		SqlBuilder builder = new SqlBuilder();
//		sql.append(" SELECT * FROM cy_contract where nvl(dr,0)=0 and nvl(def3,'否')='否' and nvl(def4,'未生效')='已生效' ");

		/**
		 * 查询学费收款合同的逻辑修改 Edit By yaojing
		 */
		builder.append(" select * ");
		builder.append(" from (select row_number() over(partition by def2 order by to_number(def6) desc) rn,con.* ");
		builder.append(" from cy_contract con ");
		builder.append(" WHERE con.def2 is not null ");
		builder.append(" and nvl(dr, 0) = 0 ");
		builder.append(" and nvl(def3, '否') = '否' ");
		builder.append(" and nvl(def4, '未生效') = '已生效') t ");
		builder.append("  where t.rn <= 1 ");

		BaseDAO dao = new BaseDAO();
		List<CreditcontractHVO> listCCH = (List<CreditcontractHVO>) getBaseDAO().executeQuery(builder.toString(),
				new BeanListProcessor(CreditcontractHVO.class));
		if (MMValueCheck.isEmpty(listCCH)) {
			return null;
		}

		Set<String> cchKs = new HashSet<String>();
		Set<String> stuPks = new HashSet<String>();
		Set<String> materPks = new HashSet<String>();
		Set<String> customerPks = new HashSet<String>();// 学生和学校主键
		Set<String> classcyPks = new HashSet<String>();
		Set<String> schPks = new HashSet<String>();
		for (CreditcontractHVO cthvo : listCCH) {
			cchKs.add(cthvo.getPk_contract());
			String pk_material = cthvo.getPk_material();
			String def1 = cthvo.getDef1();// 班级
			String def2 = cthvo.getDef2();// 学生
			String def5 = cthvo.getDef5();// 学校
			if (MMValueCheck.isNotEmpty(pk_material)) {
				materPks.add(pk_material);
			}
			if (MMValueCheck.isNotEmpty(def1)) {
				classcyPks.add(def1);
			}
			if (MMValueCheck.isNotEmpty(def2)) {
				stuPks.add(def2);
				customerPks.add(def2);
			}
			if (MMValueCheck.isNotEmpty(def5)) {
				schPks.add(def5);
				customerPks.add(def5);
			}
		}

		Map<String, List<CreditcontractDetailVO>> ccdMap = contractDetailData(cchKs);// 查询获取学费收款合同子表信息
		Map<String, StudentHVO> studentMap = studentData(stuPks);// 查询获取学生信息
		Map<String, SchoolBasicsHVO> schoolMap = schoolData(schPks);// 查询获取学校信息
		Map<String, ClasscyHVO> classMap = classData(classcyPks);// 查询获取班级信息
		Map<String, CustomerVO> customerMap = customerData(customerPks);// 查询获取客户
		Map<String, UFDouble> materialMap = materialData(materPks);// 根据物料查询税率赋值

		List<String> listccpk = new ArrayList<String>();// 回写主键
		List<AggCtArVO> listAgg = new ArrayList<AggCtArVO>();
		for (CreditcontractHVO cthvo : listCCH) {
			AggCtArVO agg = new AggCtArVO();
			String pk_contract = cthvo.getPk_contract();
			listccpk.add(pk_contract);
			String def1 = cthvo.getDef1();// 班级
			String def2 = cthvo.getDef2();// 学生
			String def5 = cthvo.getDef5();// 学校

			CtArVO ctarvo = new CtArVO();
			String pk_org = cthvo.getPk_org();
			String pk_org_v = cthvo.getPk_org_v();
			ctarvo.setPk_group(cthvo.getPk_group());
			ctarvo.setPk_org(pk_org);
			ctarvo.setOrganizer(pk_org);
			ctarvo.setSignorg(pk_org);
			ctarvo.setOrganizer_v(pk_org_v);
			ctarvo.setSignorg_v(pk_org_v);
			ctarvo.setDbilldate(new UFDate());
			String claName = "";
			if (MMValueCheck.isNotEmpty(classMap) && MMValueCheck.isNotEmpty(classMap.get(def1))) {
				ClasscyHVO classcyHVO = classMap.get(def1);
				ctarvo.setVdef5(classcyHVO.getDef12());// 年级
				claName = classcyHVO.getClasname();
			}
			String stuName = "";
			if (MMValueCheck.isNotEmpty(studentMap) && MMValueCheck.isNotEmpty(studentMap.get(def2))) {
				StudentHVO stu = studentMap.get(def2);
				stuName = stu.getSname();
				ctarvo.setVdef4(stu.getIdcard());// 身份证号
			}
			String schName = "";
			if (MMValueCheck.isNotEmpty(schoolMap) && MMValueCheck.isNotEmpty(schoolMap.get(def5))) {
				SchoolBasicsHVO sch = schoolMap.get(def5);
				schName = sch.getSname();
				ctarvo.setVdef1(sch.getDef11());// 大区 取班级对应信息
				ctarvo.setVdef2(sch.getProvince());// 省份 取班级对应信息

			}
			if (MMValueCheck.isNotEmpty(customerMap)) {
				CustomerVO custClasscy = customerMap.get(def2);
				if (MMValueCheck.isNotEmpty(custClasscy)) {
					ctarvo.setPk_customer(custClasscy.getPk_customer());// 学生 客户
				}
				CustomerVO custSchool = customerMap.get(def5);
				if (MMValueCheck.isNotEmpty(custSchool)) {
					ctarvo.setVdef3(custSchool.getPk_customer());// 学校 客户
				}
			}

			String ctname = schName + claName + stuName;
			if (MMValueCheck.isEmpty(ctname)) {
				ctname = cthvo.getSchool() + cthvo.getClasses() + cthvo.getStudent();
			}
			ctarvo.setCtname(ctname);// 合同名称
			UFDate startDate = new UFDate(cthvo.getStartdate().getMillis());
			UFDate endDate = new UFDate(cthvo.getEnddate().getMillis());
			ctarvo.setSubscribedate(startDate);// 签字盖章日期
			ctarvo.setValdate(startDate);// 计划生效日期
			ctarvo.setInvallidate(endDate);// 计划终止日期
			ctarvo.setFstatusflag(1);// 合同状态
			ctarvo.setCorigcurrencyid(AppletsParams.pk_currtype);// 币种 pk_currtype
			ctarvo.setCcurrencyid(AppletsParams.pk_currtype);// 本位币
			String jmhje = cthvo.getDef9();
			if (CyCommonUtils.isEmpty(jmhje)) {
				ctarvo.setNtotalorigmny(cthvo.getAmount());
				ctarvo.setVdef7(cthvo.getAmount().toString());// 原合同金额
			} else {
				ctarvo.setNtotalorigmny(new UFDouble(jmhje));
				ctarvo.setVdef7(cthvo.getAmount().toString());// 原合同金额
			}
			ctarvo.setCbilltypecode("FCT2");
			ctarvo.setCtrantypeid(AppletsParams.pk_billtype);// 合同类型 pk_billtype
			ctarvo.setVtrantypecode("FCT2-01");
			ctarvo.setCsendcountryid(AppletsParams.pk_countryzone);// 国家pk_countryzone
			ctarvo.setCrececountryid(AppletsParams.pk_countryzone);
			ctarvo.setCtaxcountryid(AppletsParams.pk_countryzone);
			ctarvo.setNexchangerate(new UFDouble(1.0));
			ctarvo.setEarlysign(new UFBoolean(false));
			ctarvo.setBlatest(new UFBoolean(false));
			ctarvo.setFbuysellflag(1);
			ctarvo.setIprintcount(0);
			ctarvo.setVersion(new UFDouble(1.0));
			ctarvo.setMountcalculation(1);
			ctarvo.setStatus(VOStatus.NEW);
			agg.setParentVO(ctarvo);

			if (MMValueCheck.isNotEmpty(ccdMap) && MMValueCheck.isNotEmpty(ccdMap.get(pk_contract))) {
				// 合同基本
				List<CtArBVO> listctb = new ArrayList<CtArBVO>();
				CtArBVO cb = new CtArBVO();
				UFDouble amount = cthvo.getAmount().setScale(2, 5);
				cb.setCrowno(1 + "");
				String pk_material = cthvo.getPk_material();
				UFDouble ntaxrate = materialMap.get(pk_material);
				if (MMValueCheck.isEmpty(pk_material)) {
					pk_material = AppletsParams.pk_material;
					ntaxrate = new UFDouble(3);
				}
				UFDouble divTax = ntaxrate.div(100).add(new UFDouble(1.0));
				cb.setPk_material(pk_material);
				cb.setNtaxrate(ntaxrate);// 根据物料查询税率赋值
				if (CyCommonUtils.isEmpty(jmhje)) {
					cb.setNorigtaxmny(amount);// 原币价税合计
					cb.setNtaxmny(amount);// 本币价税合计
					UFDouble bhsje = amount.div(divTax).setScale(2, 5);
					cb.setNorigmny(bhsje);// 无税金额
					cb.setNmny(bhsje);// 本币无税金额
					cb.setNtax(amount.sub(bhsje));// 税额
				} else {
					cb.setNorigtaxmny(new UFDouble(jmhje));// 原币价税合计
					cb.setNtaxmny(new UFDouble(jmhje));// 本币价税合计
					UFDouble bhsje = new UFDouble(jmhje).div(divTax).setScale(2, 5);
					cb.setNorigmny(bhsje);// 无税金额
					cb.setNmny(bhsje);// 本币无税金额
					cb.setNtax(new UFDouble(jmhje).sub(bhsje));// 税额
				}
				cb.setStatus(VOStatus.NEW);
				listctb.add(cb);

				// 合同计划
				List<CtArPlanVO> listctp = new ArrayList<CtArPlanVO>();
				for (CreditcontractDetailVO ctd : ccdMap.get(pk_contract)) {
					CtArPlanVO cp = new CtArPlanVO();
					cp.setAccountnum(Integer.parseInt(ctd.getRowno()));//
					cp.setPlanrate(new UFDouble(ctd.getProplan()));// 计划比例
					cp.setBegindate(new UFDate(ctd.getStartdate().getMillis()));
					cp.setAccountdate(ctd.getPaydays());// 账期天数
					cp.setEnddate(new UFDate(ctd.getEnddate().getMillis()));
					cp.setPlanmoney(ctd.getPlanamount().setScale(2, 5));// 计划金额
					cp.setOrgmoney(ctd.getPlanamount().setScale(2, 5));
					cp.setGroupmoney(ctd.getPlanamount().setScale(2, 5));
					cp.setGlomoney(ctd.getPlanamount().setScale(2, 5));
					cp.setReymontype(AppletsParams.reymontype);// 收款类型 pk_recpaytype
					cp.setReceivetype(new UFBoolean(true));
					cp.setVdef1(ctd.getPk_detail());// 学费收款合同表体主键
					cp.setStatus(VOStatus.NEW);

					listctp.add(cp);
				}
				agg.setCtArBVO(listctb.toArray(new CtArBVO[0]));
				agg.setCtArPlanVO(listctp.toArray(new CtArPlanVO[0]));
			}

			listAgg.add(agg);
		}

		IArMaintain iarMaintain = NCLocator.getInstance().lookup(IArMaintain.class);
		AggCtArVO[] aggArr = iarMaintain.save(listAgg.toArray(new AggCtArVO[0]));

		// 回写学生收款合同
		if (MMValueCheck.isNotEmpty(listccpk)) {
			builder.reset();
			builder.append(" update cy_contract set def3='是' where dr=0 ");
			builder.append(" and pk_contract ", listccpk.toArray(new String[0]));
			dao.executeUpdate(builder.toString());
		}

		return getNothingObject();
	}

	@SuppressWarnings("unchecked")
	/**
	 * 查询合同子表数据
	 * 
	 * @param dao
	 * @param cchks
	 * @return
	 * @throws DAOException
	 */
	private Map<String, List<CreditcontractDetailVO>> contractDetailData(Set<String> cchks) throws DAOException {
		SqlBuilder sqlStu = new SqlBuilder();
		sqlStu.append(" SELECT * FROM cy_creditcontract_detail WHERE nvl(dr,0)=0 and pk_contract ",
				cchks.toArray(new String[0]));
		sqlStu.append(" order by startdate ");

		List<CreditcontractDetailVO> list = (List<CreditcontractDetailVO>) getBaseDAO().executeQuery(sqlStu.toString(),
				new BeanListProcessor(CreditcontractDetailVO.class));

		Map<String, List<CreditcontractDetailVO>> map = list.stream()
				.collect(Collectors.groupingBy(CreditcontractDetailVO::getPk_contract));
		return map;
	}

	private Map<String, CustomerVO> customerData(Set<String> customerPks) throws DAOException {
		Map<String, CustomerVO> map = new HashMap<String, CustomerVO>();
		if (MMValueCheck.isEmpty(customerPks)) {
			return map;
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT * FROM bd_customer WHERE nvl(dr,0)=0 and enablestate =2 ");
		sql.append(" and def9 is not null and def9 !='~' and def9", customerPks.toArray(new String[0]));
		sql.append(" and pk_custclass in (SELECT pk_custclass FROM bd_custclass WHERE dr=0 and ");
		sql.append(" enablestate =2 and (name like '%学生%' or name like '%学校%'))");

		List<CustomerVO> list = (List<CustomerVO>) getBaseDAO().executeQuery(sql.toString(),
				new BeanListProcessor(CustomerVO.class));

		for (CustomerVO vo : list) {
			map.put(vo.getDef9(), vo);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private Map<String, ClasscyHVO> classData(Set<String> classcyPks) throws DAOException {
		Map<String, ClasscyHVO> map = new HashMap<String, ClasscyHVO>();
		if (MMValueCheck.isEmpty(classcyPks)) {
			return map;
		}
		SqlBuilder sqlStu = new SqlBuilder();
		sqlStu.append(" SELECT * FROM cy_classcy WHERE nvl(dr,0)=0 ");
		sqlStu.append(" and pk_classcy ", classcyPks.toArray(new String[0]));
		List<ClasscyHVO> list = (List<ClasscyHVO>) getBaseDAO().executeQuery(sqlStu.toString(),
				new BeanListProcessor(ClasscyHVO.class));

		for (ClasscyHVO clacy : list) {
			map.put(clacy.getPk_classcy(), clacy);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private Map<String, SchoolBasicsHVO> schoolData(Set<String> schPks) throws DAOException {
		Map<String, SchoolBasicsHVO> map = new HashMap<String, SchoolBasicsHVO>();
		if (MMValueCheck.isEmpty(schPks)) {
			return map;
		}
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT * FROM cy_schoolbasics WHERE nvl(dr,0)=0 ");
		sql.append(" and pk_school ", schPks.toArray(new String[0]));

		List<SchoolBasicsHVO> list = (List<SchoolBasicsHVO>) getBaseDAO().executeQuery(sql.toString(),
				new BeanListProcessor(SchoolBasicsHVO.class));

		for (SchoolBasicsHVO sch : list) {
			map.put(sch.getPk_school(), sch);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private Map<String, StudentHVO> studentData(Set<String> stupks) throws DAOException {
		Map<String, StudentHVO> mapStu = new HashMap<String, StudentHVO>();
		if (MMValueCheck.isEmpty(stupks)) {
			return mapStu;
		}

		SqlBuilder sqlStu = new SqlBuilder();
		sqlStu.append(" SELECT * FROM cy_student WHERE nvl(dr,0)=0 and pk_student ", stupks.toArray(new String[0]));

		List<StudentHVO> listStu = (List<StudentHVO>) getBaseDAO().executeQuery(sqlStu.toString(),
				new BeanListProcessor(StudentHVO.class));

		for (StudentHVO studentHVO : listStu) {
			mapStu.put(studentHVO.getPk_student(), studentHVO);
		}
		return mapStu;
	}

	@SuppressWarnings("unchecked")
	private Map<String, UFDouble> materialData(Set<String> materPks) throws DAOException {
		Map<String, UFDouble> mapMaterial = new HashMap<String, UFDouble>();
		if (MMValueCheck.isEmpty(materPks)) {
			return mapMaterial;
		}
		SqlBuilder sqlStu = new SqlBuilder();
		sqlStu.append(" SELECT mtia.pk_material,taxr.taxrate intolerance FROM bd_material mtia ");
		sqlStu.append(" join bd_taxcode taxc on taxc.mattaxes = mtia.pk_mattaxes ");
		sqlStu.append(" join bd_taxrate taxr on taxr.pk_taxcode=taxc.pk_taxcode ");
		sqlStu.append(" where mtia.dr=0 and mtia.enablestate=2 and taxc.dr=0  ");
		sqlStu.append(" and taxc.enablestate=2 and taxc.dr=0 and taxc.enablestate=2 ");
		sqlStu.append(" and mtia.pk_material ", materPks.toArray(new String[0]));

		List<MaterialVO> listStu = (List<MaterialVO>) getBaseDAO().executeQuery(sqlStu.toString(),
				new BeanListProcessor(MaterialVO.class));
		if (MMValueCheck.isNotEmpty(listStu)) {
			for (MaterialVO materialVO : listStu) {
				mapMaterial.put(materialVO.getPk_material(), materialVO.getIntolerance());
			}
		}

		return mapMaterial;
	}

	private BaseDAO getBaseDAO() {
		if (null == dao) {
			dao = new BaseDAO();
		}
		return dao;
	}

	private PreAlertObject getNothingObject() {
		PreAlertObject retObj = new PreAlertObject();
		retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
		return retObj;
	}
}
