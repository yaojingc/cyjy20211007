package nc.api.cy.rest.itf;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.classfilecy.ClassAggPOJO;
import nc.api.cy.rest.entity.crm.classfilecy.ClassInfoPOJO;
import nc.api.cy.rest.entity.crm.classfilecy.RestClassQuery;
import nc.api.cy.rest.entity.crm.classfilecy.RestClassSave;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.uap.IUAPQueryBS;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.utils.transfer.ObjTransformHvo;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.classfilecy.pojo.AggClassCyPOJO;
import nc.vo.cy.classfilecy.pojo.ClassCyCardReturnPOJO;
import nc.vo.cy.classfilecy.pojo.ClassCyQueryPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.sm.UserVO;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/***
 * 词源班级营销端接口
 * 
 * @author RL
 *
 */
@Path("cyclass")
public class CyClassResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 班级基础档案更新（词源）
	 */
	@POST
	@Path("classcyupdate")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString ClassCyUpdate(@RequestBody AggClassCyPOJO aggpojo) {
		try {
			ArrayList<String> liststr = new ArrayList<String>();
			AggClasscyHVO aggvo = new AggClasscyHVO();
			String pk_classcy = aggpojo.getPk_classcy();
			ClasscyHVO hvo = new ClasscyHVO();
			hvo = (ClasscyHVO) ObjTransformHvo.dataTransform(aggpojo, hvo);
			if (StringUtils.isBlank(pk_classcy)) {
				// 新增
				hvo.setDef1(hvo.getSchool());
				hvo.setStatus(VOStatus.NEW);
				hvo.setBillstatus(-1);
				hvo.setPk_group(AppletsParams.Pk_Group);
				hvo.setPk_org(AppletsParams.Pk_Org);
				hvo.setDbilldate(new UFDate());
				aggvo.setParentVO(hvo);
				getService().insert(new AggClasscyHVO[] { aggvo }, null);
				return ResponsePOJO.toJSON(null, "200", "成功");
			} else {
				// 修改
				liststr.add(pk_classcy);
				BillQuery<AggClasscyHVO> query = new BillQuery<AggClasscyHVO>(AggClasscyHVO.class);
				AggClasscyHVO[] aggvos = query.query(liststr.toArray(new String[liststr.size()]));
				ClasscyHVO parentVO = aggvos[0].getParentVO();
				ClasscyHVO clone = SerializationUtils.clone(parentVO);
				// 将参数数据赋值到查询到的VO中
				Class basicclass = hvo.getClass();
				Field[] uCfields = basicclass.getDeclaredFields();
				for (Field uf : uCfields) {
					uf.setAccessible(true); // 私有属性必须设置访问权限
					Object objVal = uf.get(hvo);
					if (null != objVal) {
						ObjTransformHvo.reflexToSetValue(clone, uf.getName(), objVal);
					}
				}
				clone.setDef1(hvo.getSchool());
				clone.setStatus(VOStatus.UPDATED);
				aggvo.setParentVO(clone);
				getService().update(new AggClasscyHVO[] { aggvo }, aggvos);
				return ResponsePOJO.toJSON(null, "200", "成功");
			}

		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 班级档案列表查询接口（词源）
	 */
	@POST
	@Path("classcylistquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString ClassCyListQuery(@RequestBody RestClassQuery restClassQuery) {
		try {
			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			if (!StringUtils.isBlank(restClassQuery.getSname())) {

			}
			if (!StringUtils.isBlank(restClassQuery.getBill_no())) {

			}
			if (!StringUtils.isBlank(restClassQuery.getClasname())) {

			}
			if (!StringUtils.isBlank(restClassQuery.getPaperagre())) {

			}

			// 2、进行分页查询
			@SuppressWarnings("rawtypes")
			ResponseList queryListData = getCrmService().queryListPage(condition.toString(), restClassQuery,
					AggClasscyHVO.class, ClassInfoPOJO.class);

			// 3、返回结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 班级档案详情查询接口（词源）
	 */
	@POST
	@Path("classcycardquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString ClassCyCardQuery(@RequestBody ClassCyQueryPOJO queryvo) {
		try {
			String pk_classcy = queryvo.getPk_classcy();
			ClassCyCardReturnPOJO queryClassCyCard = getService().queryClassCyCard(pk_classcy);
			return ResponsePOJO.toJSON(queryClassCyCard, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	private IClasscyhvoMaintain service;

	public IClasscyhvoMaintain getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(IClasscyhvoMaintain.class);
		}
		return service;
	}

	private IUAPQueryBS iQueryBS;

	public IUAPQueryBS getIQueryBS() {
		if (iQueryBS == null) {
			iQueryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return iQueryBS;
	}

	private ICrmService crmService;

	public ICrmService getCrmService() {
		if (crmService == null) {
			crmService = NCLocator.getInstance().lookup(ICrmService.class);
		}
		return crmService;
	}

	/*
	 * 0804
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestClassQuery restclassquery) {
		try {
			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			if (!StringUtils.isBlank(restclassquery.getSname())) {
				condition.append("def1 in (select pk_school from cy_schoolBasics where sname like '%"
						+ restclassquery.getSname() + "%')");
			}
			if (!StringUtils.isBlank(restclassquery.getBill_no())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("bill_no like '%" + restclassquery.getBill_no() + "%'");
				} else {
					condition.append("bill_no like '%" + restclassquery.getBill_no() + "%'");
				}
			}
			if (!StringUtils.isBlank(restclassquery.getClasname())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("clasname like '%" + restclassquery.getClasname() + "%'");
				} else {
					condition.append("clasname like '%" + restclassquery.getClasname() + "%'");
				}
			}
			if (!StringUtils.isBlank(restclassquery.getPaperagre())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("paperagre = '" + restclassquery.getPaperagre() + "'");
				} else {
					condition.append("paperagre = '" + restclassquery.getPaperagre() + "'");
				}
			}
			if (!StringUtils.isBlank(restclassquery.getPk_school())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("def1 = '" + restclassquery.getPk_school() + "'");
				} else {
					condition.append("def1 = '" + restclassquery.getPk_school() + "'");
				}
			}
			// 2、进行分页查询
			@SuppressWarnings("rawtypes")
			ResponseList queryListData = getCrmService().queryListPage(condition.toString(), restclassquery,
					AggClasscyHVO.class, ClassInfoPOJO.class);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	@POST
	@Path("queryDeatil/{pk_classcy}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_classcy") String pk_classcy) {
		try {
			if (StringUtils.isBlank(pk_classcy)) {
				throw new BusinessException("主键不能为空");
			}
			// 1、查询NccAggvo
			AggClasscyHVO aggvo = (AggClasscyHVO) getCrmService().queryDeatil(pk_classcy, AggClasscyHVO.class);

			// 2、将当前NccAggvo数据转换成接口实体数据
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, ClassAggPOJO.class,
					false);
			ClassAggPOJO classaggpojo = (ClassAggPOJO) changeEntitySoPowerful.getRestObj();

			// 3、返回响应结果
			return ResponsePOJO.toJSON(classaggpojo, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestClassSave restclasssave) {
		try {
			// 1、将接口实体Vo转换成Aggvo
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restclasssave,
					AggClasscyHVO.class, true);
			AggClasscyHVO transformAggVO = (AggClasscyHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
			UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();

			AggClasscyHVO aggVO = null;
			if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
				// 修改
				aggVO = (AggClasscyHVO) getCrmService().queryDeatil(transformAggVO.getPrimaryKey(),
						AggClasscyHVO.class);
				CrmUtil.editBillReplenishData(aggVO, transformAggVO, ClassAggPOJO.class, userinfo.getUserMobile());
			} else {
				// 新增
				aggVO = transformAggVO;
				CrmUtil.addBillInitData(aggVO, userinfo.getUserMobile());
				String billno = CrmUtil.generateClassNUM();
				aggVO.getParentVO().setBill_no(billno);

				UserVO uservo = getCrmService().getUserInfoByUserMobile(userinfo.getUserMobile());
				aggVO.getParentVO().setSalesman(uservo.getPk_psndoc());// 业务员等于当前制单用户
				aggVO.getParentVO().setCreator(uservo.getCuserid());
				aggVO.getParentVO().setCreationtime(new UFDateTime());
			}

			// 获取班级档案上的学校信息
			String pk_school = aggVO.getParentVO().getDef1();
			// 1、查询NccAggvo
			AggSchoolBasicsHVO aggvo = (AggSchoolBasicsHVO) getCrmService().queryDeatil(pk_school,
					AggSchoolBasicsHVO.class);
			// 学校大区
			aggVO.getParentVO().setDef10(aggvo.getParentVO().getDef11());
			// 省份
			aggVO.getParentVO().setDef11(aggvo.getParentVO().getProvince());

			// 2、调用接口进行保存
			// getCrmService().pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO,
			// false);
			getCrmService().pfBusiAction(aggVO.getParentVO().getBilltype(), "SAVEBASE", aggVO);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(null, "200", "保存成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 词源班级提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestClassSave restSave) {
		try {
			CyCommonUtils.getInstance(IClasscyhvoMaintain.class).commit(restSave);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
