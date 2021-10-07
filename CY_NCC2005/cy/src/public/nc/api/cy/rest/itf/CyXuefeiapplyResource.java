package nc.api.cy.rest.itf;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.workplan.RestWorkPlanSave;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiQuery;
import nc.api.cy.rest.entity.crm.xuefeiapply.RestXuefeiSave;
import nc.api.cy.rest.entity.crm.xuefeiapply.XuefeiAggPOJO;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.PushDataUtil;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.IWorkplanhvoMaintain;
import nccloud.itf.cy.cy.IXuefeijmhvoMaintain;
import uap.ws.rest.resource.AbstractUAPRestResource;

/*@author huangcong
 * 学费减免申请单接口
 */
@Path("cyxf")
public class CyXuefeiapplyResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}


	/*
	 * 学费减免申请单列表查询
	 */
	@POST
	@Path("queryList")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryList(@RequestBody RestXuefeiQuery restXuefei) {
		try {
			ResponseList queryListData = CyCommonUtils.getInstance(IXuefeijmhvoMaintain.class).queryListPage(restXuefei);
			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryListData, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学费减免申请单详情查询
	 */
	@POST
	@Path("queryDeatil/{pk_xfapply}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString queryDeatil(@PathParam("pk_xfapply") String pk) {
		try {
			XuefeiAggPOJO result = CyCommonUtils.getInstance(IXuefeijmhvoMaintain.class).queryDeatil(pk);
			if (CyCommonUtils.isEmpty(result)) {
				return ResponsePOJO.toJSON(result, "400", "查询失败");
			}
			return ResponsePOJO.toJSON(result, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学费减免申请更新
	 */
	@POST
	@Path("billSave")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString billSave(@RequestBody RestXuefeiSave restXuefei) {
		try {
			CyCommonUtils.getInstance(IXuefeijmhvoMaintain.class).billSave(restXuefei);
			return ResponsePOJO.toJSON(null, "200", "保存成功");

		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 学费减免申请提交
	 */
	@POST
	@Path("commit")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString commit(@RequestBody RestXuefeiSave restXuefei) {
		try {
			CyCommonUtils.getInstance(IXuefeijmhvoMaintain.class).commit(restXuefei);
			return ResponsePOJO.toJSON(null, "200", "提交成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}
	
	/*
	 * 学费减免申请提交
	 */
	@POST
	@Path("test")
	public void test() {
		
		Date now = new Date();
		Calendar after = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		after.setTime(now);
		after.set(Calendar.DATE, after.get(Calendar.DATE) + 3);
		String afters = sdf.format(after.getTime());
		String n=sdf.format(now.getTime());
		SqlBuilder sqlsale = new SqlBuilder();
		sqlsale.append("select * from bd_psndoc where dr=0 and pk_psndoc in");
		sqlsale.append("(select DISTINCT(saleman) from cy_schoolbasics where ");
		sqlsale.append("dr", 0);
		sqlsale.append(" and def16 <='" + afters + "')");
		SqlBuilder sqlsenior = new SqlBuilder();
		sqlsenior.append("select * from bd_psndoc where dr=0 and pk_psndoc in");
		sqlsenior.append("(select DISTINCT(senior) from cy_schoolbasics where ");
		sqlsenior.append("dr", 0);
		
		sqlsenior.append(" and def16 <='" + afters + "')");
		try {
		@SuppressWarnings("unchecked")
		List<PsndocVO> saleids = (List<PsndocVO>) new BaseDAO().executeQuery(sqlsale.toString(),
					new BeanListProcessor(PsndocVO.class));
		
		@SuppressWarnings("unchecked")
		List<PsndocVO> seniorids = (List<PsndocVO>) new BaseDAO().executeQuery(sqlsenior.toString(),
				new BeanListProcessor(PsndocVO.class));

//		NoteDataUtils.addLog("获取到需要发送消息的业务员和高管" + saleids.toString() + seniorids.toString(), fileName, true, null);
		String msgTitle = "学校档案预警提醒";
		String msgsale = "您有学校即将回收，请及时处理相关业务";
		String msgsenior = "分配给您的学校即将回收，请及时处理相关业务";
		saleids.addAll(seniorids);
	

			// 推送数据到友空间
			PushDataUtil.pushData(saleids, msgTitle, msgsale);
//			PushDataUtil.pushData(seniorids, msgTitle, msgsenior);
		} catch (Exception e) {
			e.printStackTrace();
		}
//			return ResponsePOJO.toJSON(null, "200", "提交成功");
		
	}
	
	/*
	 * 学费减免申请单列表查询
	 */
	@POST
	@Path("feeAmountQuery/{pk_student}")
	@Consumes("application/json")
	@Produces("application/json")
	public JSONString feeAmountQuery(@PathParam("pk_student") String pk) {
		try {
			String result = CyCommonUtils.getInstance(IXuefeijmhvoMaintain.class).feeAmountQuery(pk);
			if (CyCommonUtils.isEmpty(result)) {
				return ResponsePOJO.toJSON(result, "400", "查询失败");
			}
			return ResponsePOJO.toJSON(result, "200", "成功");
		} catch (BusinessException e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

}
