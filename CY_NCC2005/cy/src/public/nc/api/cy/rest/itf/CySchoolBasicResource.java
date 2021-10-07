package nc.api.cy.rest.itf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.schoolarchives.RestSchoolQuery;
import nc.api.cy.rest.entity.crm.schoolarchives.RestSchoolSave;
import nc.api.cy.rest.entity.crm.schoolarchives.SchoolAggPOJO;
import nc.api.cy.rest.entity.crm.schoolarchives.SchoolPOJO;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolarchives.pojo.AggSchoolBasicsPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolCardRetuenPOJO;
import nc.vo.cy.schoolarchives.pojo.SchoolQueryPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.sm.UserVO;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/***
 * 
 * @author RL 学校档案营销端接口
 */
@Path("cyschoolbasic")
public class CySchoolBasicResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 学校基础档案更新
	 */
	@POST
	@Path("schoolbasicupdate")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString SchoolBasicUpdate(@RequestBody AggSchoolBasicsPOJO aggpojo) {
		try {
			String msg = getService().UpdateSchoolList(aggpojo);
			return ResponsePOJO.toJSON(null, "200", msg);
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学校档案查询接口
	 */
	@POST
	@Path("schoollistquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString SchoolListQuery(@RequestBody SchoolQueryPOJO schoolpojo) {
		try {
			PagingPOJO rvo = getService().querySchoolList(schoolpojo);
			if (CyCommonUtils.isNotEmpty(rvo)) {
				return ResponsePOJO.toJSON(rvo, "200", "成功");
			} else {
				return JSONTool.returnVoIsNull();
			}
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学校档案详情查询接口
	 */
	@POST
	@Path("schoolcardquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString SchoolCardQuery(@RequestBody SchoolQueryPOJO schoolpojo) {
		try {
			String pk_school = schoolpojo.getPk_school();// 学校主键
			SchoolCardRetuenPOJO aggpojo = getService().querySchoolCard(pk_school);
			return ResponsePOJO.toJSON(aggpojo, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	private IUAPQueryBS iQueryBS;

	public IUAPQueryBS getIQueryBS() {
		if (iQueryBS == null) {
			iQueryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return iQueryBS;
	}

	private ISchoolbasicshvoMaintain service;

	public ISchoolbasicshvoMaintain getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(ISchoolbasicshvoMaintain.class);
		}
		return service;
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
	public JSONString queryList(@RequestBody RestSchoolQuery restschoolquery) {
		try {
			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			if (!StringUtils.isBlank(restschoolquery.getSname())) {
				condition.append(" sname like '%" + restschoolquery.getSname() + "%'");
			}
			if (!StringUtils.isBlank(restschoolquery.getDef11())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("def11 = '" + restschoolquery.getDef11() + "'");
				} else {
					condition.append("def11 = '" + restschoolquery.getDef11() + "'");
				}
			}
			if (!StringUtils.isBlank(restschoolquery.getDef12())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("def12 = '" + restschoolquery.getDef12() + "'");
				} else {
					condition.append("def12 = '" + restschoolquery.getDef12() + "'");
				}
			}
			if (!StringUtils.isBlank(restschoolquery.getProvince())) {
				if (condition.length() > 0) {
					condition.append(" and ").append("province = '" + restschoolquery.getProvince() + "'");
				} else {
					condition.append("province = '" + restschoolquery.getProvince() + "'");
				}
			}
			// 2、进行分页查询
			@SuppressWarnings("rawtypes")
			ResponseList queryListData = getCrmService().queryListPage(condition.toString(), restschoolquery,
					AggSchoolBasicsHVO.class, SchoolPOJO.class);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	@POST
	@Path("queryDeatil/{pk_school}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_school") String pk_school) {
		try {
			if (StringUtils.isBlank(pk_school)) {
				throw new BusinessException("主键不能为空");
			}
			// 1、查询NccAggvo
			AggSchoolBasicsHVO aggvo = (AggSchoolBasicsHVO) getCrmService().queryDeatil(pk_school,
					AggSchoolBasicsHVO.class);

			// 2、将当前NccAggvo数据转换成接口实体数据
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, SchoolAggPOJO.class,
					false);
			SchoolAggPOJO schoolaggpojo = (SchoolAggPOJO) changeEntitySoPowerful.getRestObj();

			// 3、返回响应结果
			return ResponsePOJO.toJSON(schoolaggpojo, "200", "成功");
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
	public JSONString billSave(@RequestBody RestSchoolSave restschoolsave) {
		try {
			// 1、将接口实体Vo转换成Aggvo
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restschoolsave,
					AggSchoolBasicsHVO.class, true);
			AggSchoolBasicsHVO transformAggVO = (AggSchoolBasicsHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
			UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();

			AggSchoolBasicsHVO aggVO = null;
			if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
				// 修改
				aggVO = (AggSchoolBasicsHVO) getCrmService().queryDeatil(transformAggVO.getPrimaryKey(),
						AggSchoolBasicsHVO.class);
				CrmUtil.editBillReplenishData(aggVO, transformAggVO, SchoolAggPOJO.class, userinfo.getUserMobile());
			} else {
				// 新增
				aggVO = transformAggVO;
				CrmUtil.addBillInitData(aggVO, userinfo.getUserMobile());
				UserVO user = CyCommonUtils.getInstance(ICrmService.class)
						.getUserInfoByUserMobile(userinfo.getUserMobile());
				aggVO.getParentVO().setCreator(user.getCuserid());
				aggVO.getParentVO().setCreationtime(new UFDateTime());
			}

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
	 * 学校档案提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestSchoolSave restSave) {
		try {
			CyCommonUtils.getInstance(ISchoolbasicshvoMaintain.class).commit(restSave);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
