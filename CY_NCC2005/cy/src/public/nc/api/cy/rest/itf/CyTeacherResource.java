package nc.api.cy.rest.itf;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.teacherform.RestTeacherQuery;
import nc.api.cy.rest.entity.crm.teacherform.RestTeacherSave;
import nc.api.cy.rest.entity.crm.teacherform.TeacherAggPOJO;
import nc.api.cy.rest.entity.crm.teacherform.TeacherInfoPOJO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.teacherform.TeacherformHVO;
import nc.vo.cy.teacherform.pojo.BaseRef;
import nc.vo.cy.teacherform.pojo.CContent;
import nc.vo.cy.teacherform.pojo.CContentRet;
import nc.vo.cy.teacherform.pojo.DataContent;
import nc.vo.cy.teacherform.pojo.LContentRet;
import nc.vo.cy.teacherform.pojo.ListQueryReturnPOJO;
import nc.vo.cy.teacherform.pojo.QueryParmPOJO;
import nc.vo.cy.teacherform.pojo.TeacherAssign;
import nc.vo.cy.teacherform.pojo.UserInfoRef;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sm.UserVO;
import nccloud.api.rest.utils.NCCRestUtils;
import nccloud.itf.cy.cy.ITeacherformhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/**
 * 老师/讲师申请单接口
 */
@Path("cyteacher")
public class CyTeacherResource extends AbstractUAPRestResource {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ITeacherformhvoMaintain service;
	private IUAPQueryBS iQueryBS;

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 214老师、讲师申请单列表查询
	 */
	@POST
	@Path("listquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString teacherListQuery(JSONString requestJson) {
		logger.info("老师申请单列表查询参数：" + requestJson);
		QueryParmPOJO queryvo = (QueryParmPOJO) JSONTool.getObjByJson(requestJson.toString(), QueryParmPOJO.class);
		// 将vo拼接成查寻条件
		ListQueryReturnPOJO retPojo = new ListQueryReturnPOJO();
		try {
			CyCommonUtils.login();
			DataContent<LContentRet> dcontent = getService().queryTeacherList(queryvo);
			if (null == dcontent) {
				return JSONTool.returnVoIsNull("未查询到对应的数据");
			}
			retPojo.setData(dcontent);
		} catch (Exception e) {
			retPojo.setCode("400");
			retPojo.setMsg(e.getMessage());
			return NCCRestUtils.toJSONString(retPojo);
		} finally {
			CyCommonUtils.logout();
		}
		return NCCRestUtils.toJSONString(retPojo);
	}

	/*
	 * 215老师、讲师详情查询接口
	 */
	@POST
	@Path("cardquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString teacherCardQuery(JSONString requestJson) {
		logger.info("老师申请单详情查询参数：" + requestJson);
		QueryParmPOJO queryvo = (QueryParmPOJO) JSONTool.getObjByJson(requestJson.toString(), QueryParmPOJO.class);
		String pk_teacher = queryvo.getPk_teacherform();// 主键
		ResponsePOJO retPojo = new ResponsePOJO();
		if (StringUtils.isBlank(pk_teacher)) {
			retPojo.setCode("400");
			retPojo.setMsg("主键为空");
			return NCCRestUtils.toJSONString(retPojo);
		}
		try {
			CyCommonUtils.login();
			CContentRet data = getService().queryTeacherDetail(queryvo);

			if (null == data) {
				retPojo.setMsg("未查询到对应的数据");
//				return CyCommonUtils.returnVoIsNull("未查询到对应的数据");
			}
			retPojo.setCode("200");
			retPojo.setMsg("成功");
			retPojo.setData(data);
		} catch (Exception e) {
			retPojo.setCode("400");
			retPojo.setMsg(e.getMessage());
			return NCCRestUtils.toJSONString(retPojo);
		} finally {
			CyCommonUtils.logout();
		}

		return NCCRestUtils.toJSONString(retPojo);
		// return new Gson().toJson(retPojo);

	}

	/*
	 * 老师/讲师更新、新增
	 */
	@POST
	@Path("formsave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString teacherFormSave(JSONString requestJson) {
		logger.info("老师申请单修改参数：" + requestJson);
		ResponsePOJO retPojo = new ResponsePOJO();
		try {
			CContent paramVO = (CContent) JSONTool.getObjByJson(requestJson.toString(), CContent.class);
			UserInfoRef userinfo = paramVO.getUserinfo();

			String pk = paramVO.getPk_teacherform();
			CyCommonUtils.login();
			// 参数vo转换成接口老师申请单vo
			TeacherformHVO thvo = dataTransform(paramVO);
			thvo.setDef1(thvo.getSchool());
			thvo.setDef2(thvo.getClasscy());

			// 新增
			if (StringUtils.isBlank(pk)) {
				AggTeacherformHVO aggtvo = new AggTeacherformHVO();
				String pk_group = InvocationInfoProxy.getInstance().getGroupId();
				thvo.setPk_teacherform(CyCommonUtils.generatePK());
				thvo.setDbilldate(new UFDate());
				thvo.setPk_group(pk_group);
				thvo.setPk_org(userinfo.getPkOrg());
				thvo.setCreator(userinfo.getUserId());
				thvo.setBillstatus(-1);
				thvo.setApprovestatus(-1);
				thvo.setStatus(VOStatus.NEW);

				aggtvo.setParentVO(thvo);

				AggTeacherformHVO[] aggretvo = getService().insert(new AggTeacherformHVO[] { aggtvo }, null);
				if (ArrayUtils.isNotEmpty(aggretvo)) {
					String retpk = aggretvo[0].getParentVO().getPrimaryKey();
					retPojo.setCode("200");
					retPojo.setMsg("新增成功!" + retpk);
				}
			} else {
				// 修改
				AggTeacherformHVO aggvo = new AggTeacherformHVO();
				AggTeacherformHVO aggOld = new AggTeacherformHVO();
				TeacherformHVO origintvo = (TeacherformHVO) getIQueryBS().retrieveByPK(TeacherformHVO.class, pk);
				TeacherformHVO origintvoCopy = (TeacherformHVO) SerializationUtils.clone(origintvo);
				aggOld.setParentVO(origintvoCopy);
				if (null == origintvo) {
					retPojo.setCode("400");
					retPojo.setMsg("未查询到对应的单据");
					return NCCRestUtils.toJSONString(retPojo);
				}

				// 将参数数据赋值到查询到的VO中
				Class updateClass = thvo.getClass();
				Field[] uCfields = updateClass.getDeclaredFields();
				for (Field uf : uCfields) {
					uf.setAccessible(true); // 私有属性必须设置访问权限
					Object objVal = uf.get(thvo);
					if (null != objVal) {
						CyCommonUtils.reflexToSetValue(origintvo, uf.getName(), objVal);
					}
				}

//				origintvo.setDef1(thvo.getDef1());
//				origintvo.setDef2(thvo.getDef2());
				origintvo.setStatus(VOStatus.UPDATED);
				aggvo.setParentVO(origintvo);
				AggTeacherformHVO[] aggretvo = getService().update(new AggTeacherformHVO[] { aggvo },
						new AggTeacherformHVO[] { aggOld });
				if (ArrayUtils.isNotEmpty(aggretvo)) {
					String retpk = aggretvo[0].getParentVO().getPrimaryKey();
					retPojo.setCode("200");
					retPojo.setMsg("修改成功!" + retpk);
				}
			}
		} catch (Exception e) {
			retPojo.setCode("400");
			retPojo.setMsg(e.getMessage());
			return NCCRestUtils.toJSONString(retPojo);
		} finally {
			CyCommonUtils.logout();
		}

		return NCCRestUtils.toJSONString(retPojo);
	}

	/**
	 * 参数vo转换成接口老师申请单vo
	 * 
	 * @param paramVO
	 * @param object
	 * @param thvo
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public static TeacherformHVO dataTransform(CContent paramVO) throws IllegalAccessException, Exception {
		TeacherformHVO transformVO = new TeacherformHVO();
		Class object = paramVO.getClass();// 入参数据类
		Class hvoObj = transformVO.getClass(); // 转换后数据类
		Field[] fields = object.getDeclaredFields();
		for (Field field : fields) {
			String fieldname = field.getName();
			field.setAccessible(true); // 私有属性必须设置访问权限
			String value = "";
			String typeName = field.getType().getName();
			if (null == field.get(paramVO) || "".equals(field.get(paramVO))
					|| typeName.equals("nc.vo.cy.teacherform.pojo.UserInfoRef")) {
				continue;
			}
			if (typeName.equals("nc.vo.cy.teacherform.pojo.BaseRef")
					|| typeName.equals("nc.vo.cy.teacherform.pojo.SchoolRef")) {
				BaseRef rf = (BaseRef) field.get(paramVO);
				value = rf.getValue();
			} else if (fieldname.equals("dbilldate") && StringUtils.isNotBlank(field.get(paramVO).toString())) {
				value = field.get(paramVO).toString() + " 00:00:00";
			} else if ((fieldname.equals("startdate") || fieldname.equals("enddate"))
					&& StringUtils.isNotBlank(field.get(paramVO).toString())) {
				value = field.get(paramVO).toString() + " 00:00:00";
			} else {
				value = field.get(paramVO).toString();
			}

			Object objVal = CyCommonUtils.fieldTypeTranslater(hvoObj, fieldname, value);

			if (null != objVal) {
				CyCommonUtils.reflexToSetValue(transformVO, fieldname, objVal);
			}
		}
		return transformVO;
	}

	public ITeacherformhvoMaintain getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(ITeacherformhvoMaintain.class);
		}
		return service;
	}

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

	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestTeacherQuery restTeacherQuery) {
		try {
			if (StringUtils.isBlank(restTeacherQuery.getBillType())) {
				throw new BusinessException("类型不能为空");
			}

			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			condition.append("teachertype = '" + restTeacherQuery.getBillType() + "'");
			if (CyCommonUtils.isNotEmpty(restTeacherQuery.getSname())) {
				if (condition.length() > 0) {
					condition.append(" and ")
							.append("def1 in (select pk_school from cy_schoolBasics where sname like '%"
									+ restTeacherQuery.getSname() + "%')");
				} else {
					condition.append("def1 in (select pk_school from cy_schoolBasics where sname like '%"
							+ restTeacherQuery.getSname() + "%')");
				}
			}
			if (CyCommonUtils.isNotEmpty(restTeacherQuery.getDef6())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("def6 ='" + restTeacherQuery.getDef6() + "'");
				} else {
					condition.append("def6 ='" + restTeacherQuery.getDef6() + "'");
				}
			}

			// 2、进行分页查询
			@SuppressWarnings("rawtypes")
			ResponseList queryListData = getCrmService().queryListPage(condition.toString(), restTeacherQuery,
					AggTeacherformHVO.class, TeacherInfoPOJO.class);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	@POST
	@Path("queryDeatil/{pk_teacherform}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_teacherform") String pk_teacherform) {
		try {
			if (StringUtils.isBlank(pk_teacherform)) {
				throw new BusinessException("主键不能为空");
			}
			// 1、查询NccAggvo
			AggTeacherformHVO aggvo = (AggTeacherformHVO) getCrmService().queryDeatil(pk_teacherform,
					AggTeacherformHVO.class);

			// 2、将当前NccAggvo数据转换成接口实体数据
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, TeacherAggPOJO.class,
					false);
			TeacherAggPOJO teacherAggPOJO = (TeacherAggPOJO) changeEntitySoPowerful.getRestObj();

			// 3、返回响应结果
			return ResponsePOJO.toJSON(teacherAggPOJO, "200", "成功");
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
	public JSONString billSave(@RequestBody RestTeacherSave restTeacherQuery) {
		try {
			// 1、将接口实体Vo转换成Aggvo
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restTeacherQuery,
					AggTeacherformHVO.class, true);
			AggTeacherformHVO transformAggVO = (AggTeacherformHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
			UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();

			AggTeacherformHVO aggVO = null;
			if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
				// 修改
				aggVO = (AggTeacherformHVO) getCrmService().queryDeatil(transformAggVO.getPrimaryKey(),
						AggTeacherformHVO.class);
				CrmUtil.editBillReplenishData(aggVO, transformAggVO, TeacherAggPOJO.class, userinfo.getUserMobile());
			} else {
				// 新增
				aggVO = transformAggVO;
				CrmUtil.addBillInitData(aggVO, userinfo.getUserMobile());
				UserVO user = CyCommonUtils.getInstance(ICrmService.class)
						.getUserInfoByUserMobile(userinfo.getUserMobile());
				aggVO.getParentVO().setCreator(user.getCuserid());
				aggVO.getParentVO().setCreationtime(new UFDateTime());
			}

			// 2、调用接口进行保存(老师/讲师申请单是交易类型发布的节点，故此处用交易类型编码)
			getCrmService().pfSaveBusiAction(aggVO.getParentVO().getTranstypecode(), aggVO, false);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(null, "200", "保存成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 讲师分配提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestTeacherSave restTeacherQuery) {
		try {

			CyCommonUtils.getInstance(ITeacherformhvoMaintain.class).commit(restTeacherQuery);
			return ResponsePOJO.toJSON(null, "200", "保存成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 讲师分配
	 */
	@POST
	@Path("assign")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString assign(@RequestBody TeacherAssign teachervo) {
		try {

			CyCommonUtils.getInstance(ITeacherformhvoMaintain.class).assign(teachervo);
			return ResponsePOJO.toJSON(null, "200", "保存成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
