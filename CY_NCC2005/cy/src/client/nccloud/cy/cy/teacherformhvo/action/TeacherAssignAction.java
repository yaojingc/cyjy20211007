
package nccloud.cy.cy.teacherformhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.generator.IdGenerator;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.pojo.GlbdefVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.teacherform.TeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.cy.cy.studenthvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nccloud.itf.cy.cy.ITeacherformhvoMaintain;

/**
 * 老师讲师申请单审批后分配
 * 
 * @author tanrg
 *
 */
public class TeacherAssignAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		// 解析request
		String str = paramIRequest.read();
		// 获取待分配的人员
		String pk_psndoc = (String) JSONTool.getJsonValueByKey(str, "pk_psndoc");
		String pk_teacherform = (String) JSONTool.getJsonValueByKey(str, "pk_teacherform");

		try {
			if (MMValueCheck.isEmpty(pk_psndoc)) {
				throw new BusinessException("未选中待分配的人员信息");
			}
			if (MMValueCheck.isEmpty(pk_teacherform)) {
				throw new BusinessException("未选中要分配的单据");
			}
			// 根据前端传递的pks查询数据,获取单据
			TeacherformHVO thvo = this.queryStuBills(pk_teacherform);

			if (MMValueCheck.isEmpty(thvo)) {
				throw new BusinessException("未匹配到对应的单据");
			}

			String def6 = thvo.getDef6();// 是否分配 Y/N
			String bill_no = thvo.getBill_no();
			Integer approvestatus = thvo.getApprovestatus();
			UFDateTime startdate = thvo.getStartdate();
			UFDateTime enddate = thvo.getEnddate();
			if (MMValueCheck.isNotEmpty(def6) && "Y".equals(def6)) {
				throw new BusinessException("单据：" + bill_no + " 已分配，请取消分配后再操作");
			}
//			-
			if (MMValueCheck.isEmpty(approvestatus)
					|| (MMValueCheck.isNotEmpty(approvestatus) && approvestatus.intValue() != 1)) {
				throw new BusinessException("单据：" + bill_no + " 不为审批态，不能分配");
			}
			// 查询获取学校信息
			String def1 = thvo.getDef1();// 学校
			// 根据学校主键查询客户主键
			String pk_customer_school = "";
			pk_customer_school = CyCommonUtils.getInstance(ISchoolbasicshvoMaintain.class)
					.queryCustomerIdBySchoolId(def1);
			if (MMValueCheck.isEmpty(def1)) {
				throw new BusinessException("选择单据中学校信息为空");
			}
			String def2 = thvo.getDef2();// 班级
			if (MMValueCheck.isEmpty(def2)) {
				throw new BusinessException("选择单据中班级信息为空");
			}

			Set<String> schpks = new HashSet<String>();
			schpks.add(def1);
			Map<String, SchoolBasicsHVO> schoolMap = null;
			if (MMValueCheck.isNotEmpty(schpks)) {
				schoolMap = querySchoolBills(schpks);
			}
			if (MMValueCheck.isEmpty(schoolMap)) {
				throw new BusinessException("选择单据中学校信息未匹配到对应的学校档案");
			}
			SchoolBasicsHVO schoolhvo = schoolMap.get(def1);
			if (MMValueCheck.isEmpty(schoolhvo)) {
				throw new BusinessException("选择单据中学校信息未匹配到对应的学校档案");
			}

			// 2. 查询获取班级信息
			Set<String> clapks = new HashSet<String>();
			clapks.add(def2);
			Map<String, ClasscyHVO> clmap = null;
			if (MMValueCheck.isNotEmpty(clapks)) {
				clmap = this.queryClasscyBills(clapks);
			}
			if (MMValueCheck.isEmpty(clmap)) {
				throw new BusinessException("选择单据中班级信息未匹配到对应的班级档案");
			}
			ClasscyHVO clHVO = clmap.get(def2);
			if (MMValueCheck.isEmpty(clHVO)) {
				throw new BusinessException("选择单据中班级信息未匹配到对应的班级档案");
			}
			String userId = InvocationInfoProxy.getInstance().getUserId();
			String sname = schoolhvo.getSname();
			String clasname = clHVO.getClasname();
			// 人员档案子表数据处理
			// 查询人员页签数据，判断是否已生成，生成则修改，否则新增; 人员主键+学校+班级+开始时间
			List<GlbdefVO> glbdefVOs = queryPsndocGlbdef(pk_teacherform, pk_psndoc, sname, clasname, startdate);
			ITeacherformhvoMaintain itService = NCLocator.getInstance().lookup(ITeacherformhvoMaintain.class);
			if (MMValueCheck.isNotEmpty(glbdefVOs)) {
				for (GlbdefVO glbdefVO : glbdefVOs) {
					glbdefVO.setGlbdef1(schoolhvo.getDef11());// 大区
					glbdefVO.setGlbdef2(schoolhvo.getProvince());// 省份
					glbdefVO.setGlbdef3(schoolhvo.getCity());// 地区
					glbdefVO.setGlbdef4(pk_customer_school); // 学校
					glbdefVO.setGlbdef5(thvo.getDef4());// 年级
					// glbdefVO.setGlbdef6(glbdef1);// 班级
					glbdefVO.setGlbdef7(thvo.getClassnum() + "");// 班级人数
					glbdefVO.setBegindate(new UFLiteralDate(startdate.toString().substring(0, 10)));
					glbdefVO.setEnddate(new UFLiteralDate(enddate.toString().substring(0, 10)));
					glbdefVO.setModifiedtime(new UFDateTime());
					glbdefVO.setModifier(userId);
					glbdefVO.setStatus(VOStatus.UPDATED);
				}
				itService.glbdefUpdate(glbdefVOs.toArray(new GlbdefVO[0]), new String[] { "glbdef1", "glbdef2",
						"glbdef3", "glbdef5", "glbdef7", "modifiedtime", "modifier" });
			} else {
				GlbdefVO glbdefVO = new GlbdefVO();
				glbdefVO.setCreationtime(new UFDateTime());
				glbdefVO.setCreator(userId);
				glbdefVO.setPk_psndoc_sub(getNewPK());
				glbdefVO.setPk_psndoc(pk_psndoc);
				glbdefVO.setGlbdef1(schoolhvo.getDef11());// 大区
				glbdefVO.setGlbdef2(schoolhvo.getProvince());// 省份
				glbdefVO.setGlbdef3(schoolhvo.getSaddress());// 地区
				glbdefVO.setGlbdef4(pk_customer_school); // 学校
				glbdefVO.setGlbdef5(thvo.getDef4());// 年级
				glbdefVO.setGlbdef6(clasname);// 班级
				glbdefVO.setGlbdef7(thvo.getClassnum() + "");// 班级人数
				glbdefVO.setGlbdef8(pk_teacherform);// 老师申请单主键
				glbdefVO.setBegindate(new UFLiteralDate(startdate.toString().substring(0, 10)));
				glbdefVO.setEnddate(new UFLiteralDate(enddate.toString().substring(0, 10)));
				glbdefVO.setStatus(VOStatus.NEW);

				itService.glbdefInsert(glbdefVO);

			}

			// 3. 回写班级信息
			clHVO.setTeacher(pk_psndoc);
			clHVO.setStatus(VOStatus.UPDATED);
			VOUpdate<ClasscyHVO> classUpdate = new VOUpdate<ClasscyHVO>();
			classUpdate.update(new ClasscyHVO[] { clHVO }, new String[] { "teacher" });

			// 4. 回写老师/讲师申请单
			thvo.setTeacher(pk_psndoc);
			thvo.setDef6("Y");// 已分配
			thvo.setStatus(VOStatus.UPDATED);
			VOUpdate<TeacherformHVO> teacherUpdate = new VOUpdate<TeacherformHVO>();
			teacherUpdate.update(new TeacherformHVO[] { thvo }, new String[] { "teacher", "def6" });
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<GlbdefVO> queryPsndocGlbdef(String pk_teacherfrom, String pk_psndoc, String xxdef1, String bjdef2,
			UFDateTime startdate) {
		// pk+人员主键+学校+班级+开始时间
		IUAPQueryBS iuapSevice = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT * FROM hi_psndoc_glbdef1 WHERE nvl(dr,0)=0 ");
		sql.append(" and glbdef8 ", pk_teacherfrom);
		sql.append(" and pk_psndoc ", pk_psndoc);
		sql.append(" and glbdef4 ", xxdef1);
		sql.append(" and glbdef6 ", bjdef2);
		sql.append(" and begindate ", startdate.toString().substring(0, 10));

		List<GlbdefVO> listVo = new ArrayList<GlbdefVO>();
		try {
			listVo = (List<GlbdefVO>) iuapSevice.executeQuery(sql.toString(), new BeanListProcessor(GlbdefVO.class));
		} catch (BusinessException e) {
			Logger.error("查询人员数据时异常：" + e);
		}

		return listVo;
	}

	/**
	 * 查询业务数据
	 *
	 * @param queryParam
	 * @return
	 * @throws MetaDataException
	 */
	private TeacherformHVO queryStuBills(String pk) throws MetaDataException {
		// 1、根据参数查询结果
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		List<TeacherformHVO> billvo = (List<TeacherformHVO>) service.queryBillOfVOByPKs(TeacherformHVO.class,
				new String[] { pk }, false);
		if ((billvo == null) || (billvo.size() == 0)) {
			return null;
		}
		return billvo.get(0);
	}

	@SuppressWarnings("unchecked")
	private Map<String, ClasscyHVO> queryClasscyBills(Set<String> queryParam) throws MetaDataException {
		// 1、根据参数查询结果
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		Map<String, ClasscyHVO> mapcl = new HashMap<String, ClasscyHVO>();

		Collection<ClasscyHVO> billvo = service.queryBillOfVOByPKs(ClasscyHVO.class, queryParam.toArray(new String[0]),
				false);
		if ((billvo == null) || (billvo.size() == 0)) {
			return null;
		}

		for (ClasscyHVO hvo : billvo) {
			mapcl.put(hvo.getPk_classcy(), hvo);
		}
		return mapcl;
	}

	@SuppressWarnings("unchecked")
	private Map<String, SchoolBasicsHVO> querySchoolBills(Set<String> queryParam) throws MetaDataException {
		// 1、根据参数查询结果
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		Map<String, SchoolBasicsHVO> map = new HashMap<String, SchoolBasicsHVO>();

		Collection<SchoolBasicsHVO> billhvo = service.queryBillOfVOByPKs(SchoolBasicsHVO.class,
				queryParam.toArray(new String[0]), false);
		if ((billhvo == null) || (billhvo.size() == 0)) {
			return null;
		}

		for (SchoolBasicsHVO hvo : billhvo) {
			map.put(hvo.getPk_school(), hvo);
		}
		return map;
	}

	// pk生成器
	private String getNewPK() {
		IdGenerator idGenerator = new SequenceGenerator();
		return idGenerator.generate();
	}
}
