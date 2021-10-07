package nc.api.cy.rest.itf;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindQuery;
import nc.api.cy.rest.entity.crm.schoolform.RestSchoolFindSave;
import nc.api.cy.rest.entity.crm.schoolform.SchoolFindAggPOJO;
import nc.api.cy.rest.entity.crm.schoolform.SchoolFindInfoPOJO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.uap.IUAPQueryBS;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.crm.ChangeEntitySoPowerful;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.utils.transfer.ObjTransformHvo;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.schoolform.SchoolformHVO;
import nc.vo.cy.schoolform.pojo.AggSchoolFormPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormCardReturnPOJO;
import nc.vo.cy.schoolform.pojo.SchoolFormQueryPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.sm.UserVO;
import nccloud.itf.cy.cy.ISchoolformhvoMaintain;
import nccloud.itf.cy.cy.IStudenthvoMaintain;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/***
 * 新学校发现申请营销端接口
 * 
 * @author RL
 *
 */
@Path("cyschoolform")
public class CySchoolFormResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 学校基础档案更新
	 */
	@POST
	@Path("schoolformupdate")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString SchoolFormUpdate(@RequestBody AggSchoolFormPOJO aggpojo) {
		try {
			ArrayList<String> liststr = new ArrayList<String>();
			List<AggSchoolformHVO> agglist = new ArrayList<AggSchoolformHVO>();
			AggSchoolformHVO aggvo = new AggSchoolformHVO();
			String pk_schoolf = aggpojo.getPk_schoolf();
			SchoolformHVO hvo = new SchoolformHVO();
			hvo = (SchoolformHVO) ObjTransformHvo.dataTransform(aggpojo, hvo);
			if (StringUtils.isBlank(pk_schoolf)) {

				hvo.setBillstatus(-1);
				hvo.setPk_group(AppletsParams.Pk_Group);
				hvo.setPk_org(AppletsParams.Pk_Org);
//				String proposer = aggpojo.getPetitioner().getValue().toString();
				hvo.setSchool(aggpojo.getSchool());
//				hvo.setProposer(proposer);
				String userId = aggpojo.getUserinfo().getUserId();
				hvo.setCreator(userId);
				hvo.setProposer(userId);
				aggvo.setParentVO(hvo);
				agglist.add(aggvo);
				AggSchoolformHVO[] array = agglist.toArray(new AggSchoolformHVO[agglist.size()]);
				CyCommonUtils.getInstance(ISchoolformhvoMaintain.class).insert(array, null);
				// 新增
				return ResponsePOJO.toJSON(null, "200", "成功");
			} else {
				// 修改
				liststr.add(pk_schoolf);
				BillQuery<AggSchoolformHVO> query = new BillQuery<AggSchoolformHVO>(AggSchoolformHVO.class);
				AggSchoolformHVO[] aggvos = query.query(liststr.toArray(new String[liststr.size()]));
				SchoolformHVO parentVO = aggvos[0].getParentVO();
				SchoolformHVO clone = SerializationUtils.clone(parentVO);
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
				clone.setStatus(VOStatus.UPDATED);
				aggvo.setParentVO(clone);
				CyCommonUtils.getInstance(ISchoolformhvoMaintain.class).update(new AggSchoolformHVO[] { aggvo },
						aggvos);
				return ResponsePOJO.toJSON(null, "200", "成功");
			}

		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学校发现申请列表查询
	 */
	@POST
	@Path("schoolformlistquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString SchoolFormListQuery(@RequestBody SchoolFormQueryPOJO queryvo) {
		try {
			PagingPOJO rvo = CyCommonUtils.getInstance(ISchoolformhvoMaintain.class).querySchoolList(queryvo);
			if (CyCommonUtils.isNotEmpty(rvo)) {
				return ResponsePOJO.toJSON(rvo, "200", "成功");
			} else {
				return JSONTool.returnVoIsNull();
			}
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学校发现申请详情查询
	 */
	@POST
	@Path("schoolformcardquery")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString SchoolFormCardQuery(@RequestBody SchoolFormQueryPOJO queryvo) {
		try {
			String pk_schoolf = queryvo.getPk_schoolf();
			SchoolFormCardReturnPOJO querySchoolCard = CyCommonUtils.getInstance(ISchoolformhvoMaintain.class)
					.querySchoolCard(pk_schoolf);
			return ResponsePOJO.toJSON(querySchoolCard, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 0805
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestSchoolFindQuery restschoolfindquery) {
		try {
			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			if (!StringUtils.isBlank(restschoolfindquery.getSchool())) {
				if (condition.length() > 0) {
					condition.append("and school = '" + restschoolfindquery.getSchool() + "') ");
				} else {
					condition.append(" school ='" + restschoolfindquery.getSchool() + "') ");
				}
			}
			if (!StringUtils.isBlank(restschoolfindquery.getRegion())) {
				if (condition.length() > 0) {
					condition.append(" and def1  ='" + restschoolfindquery.getRegion() + "'");
				} else {
					condition.append(" def1  ='" + restschoolfindquery.getRegion() + "'");
				}
			}
			if (!StringUtils.isBlank(restschoolfindquery.getDef1())) {
				if (condition.length() > 0) {
					condition.append(" and def1  ='" + restschoolfindquery.getDef1() + "'");
				} else {
					condition.append(" def1  ='" + restschoolfindquery.getDef1() + "'");
				}
			}

			// 2、进行分页查询
			@SuppressWarnings("rawtypes")
			ResponseList queryListData = CyCommonUtils.getInstance(ICrmService.class).queryListPage(
					condition.toString(), restschoolfindquery, AggSchoolformHVO.class, SchoolFindInfoPOJO.class);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	@POST
	@Path("queryDeatil/{pk_schoolf}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_schoolf") String pk_schoolf) {
		try {
			if (StringUtils.isBlank(pk_schoolf)) {
				throw new BusinessException("主键不能为空");
			}
			// 1、查询NccAggvo
			AggSchoolformHVO aggvo = (AggSchoolformHVO) CyCommonUtils.getInstance(ICrmService.class)
					.queryDeatil(pk_schoolf, AggSchoolformHVO.class);

			// 2、将当前NccAggvo数据转换成接口实体数据
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(aggvo, SchoolFindAggPOJO.class,
					false);
			SchoolFindAggPOJO schoolfindaggpojo = (SchoolFindAggPOJO) changeEntitySoPowerful.getRestObj();

			// 3、返回响应结果
			return ResponsePOJO.toJSON(schoolfindaggpojo, "200", "成功");
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
	public JSONString billSave(@RequestBody RestSchoolFindSave restschoolfindsave) {
		try {
			// 1、将接口实体Vo转换成Aggvo
			ChangeEntitySoPowerful changeEntitySoPowerful = new ChangeEntitySoPowerful(restschoolfindsave,
					AggSchoolformHVO.class, true);
			AggSchoolformHVO transformAggVO = (AggSchoolformHVO) changeEntitySoPowerful.getAggvo();// 转换后得到的Aggvo
			UserInfoByDiworkPOJO userinfo = changeEntitySoPowerful.getUserInfo();

			AggSchoolformHVO aggVO = null;
			if (!StringUtils.isBlank(transformAggVO.getPrimaryKey())) {
				// 修改
				aggVO = (AggSchoolformHVO) CyCommonUtils.getInstance(ICrmService.class)
						.queryDeatil(transformAggVO.getPrimaryKey(), AggSchoolformHVO.class);
				CrmUtil.editBillReplenishData(aggVO, transformAggVO, SchoolFindAggPOJO.class, userinfo.getUserMobile());
			} else {
				// 新增
				aggVO = transformAggVO;
				CrmUtil.addBillInitData(aggVO, userinfo.getUserMobile());
				UserVO user = CyCommonUtils.getInstance(ICrmService.class)
						.getUserInfoByUserMobile(userinfo.getUserMobile());
				aggVO.getParentVO().setCreator(user.getCuserid());
				aggVO.getParentVO().setCreationtime(new UFDateTime());
				aggVO.getParentVO().setProposer(user.getPk_psndoc());
			}

			// 2、调用接口进行保存
			CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggVO.getParentVO().getBilltype(), aggVO,
					false);

			// 3、返回响应结果
			return ResponsePOJO.toJSON(null, "200", "保存成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 新学校发现提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestSchoolFindSave restSave) {
		try {
			CyCommonUtils.getInstance(ISchoolformhvoMaintain.class).commit(restSave);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
}
