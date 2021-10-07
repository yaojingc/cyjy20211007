package nc.api.cy.rest.itf.miniprogram.msginfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONString;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.bankcollflow.BankQueryPOJO;
import nc.vo.cy.bankcollflow.BankReturnPOJO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 缴费记录查询
 */
public class CyBankStateMentQuery extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		BankQueryPOJO queryvo = (BankQueryPOJO) JSONTool.getObjByJson(requestJson, BankQueryPOJO.class);
		String resp = "";
		try {
			PagingPOJO result = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(
					new TableCountQueryVO("cy_bankcollflow", "idcard='" + queryvo.getIdcard() + "'"));
			double maxresult = (double) result.getTotal() / (double) Integer.parseInt(queryvo.getPageSize());
			// 最大页码数需要向上取整
			Double uploadResult = Math.ceil(maxresult);
			int pagetotal = new Double(uploadResult).intValue();

			result.setTotalPages(pagetotal);
			result.setCurrentPage(Integer.parseInt(queryvo.getIndex()));
			result.setPageSize(Integer.parseInt(queryvo.getPageSize()));
			NoteDataUtils.addLog(pagetotal + "", "页面总数", false, null);

			List<BankReturnPOJO> content = CyCommonUtils.getInstance(IBankcollflowhvoMaintain.class).queryBak(queryvo,
					pagetotal);
			NoteDataUtils.addLog(content.toString(), "查询结果可以返回", false, null);
			result.setContent(content);
			if (CyCommonUtils.isNotEmpty(content)) {
				resp = ResponsePOJO.toRespObj(result, "200", "成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "查无数据");
			}
		} catch (Exception e) {
			resp = ResponsePOJO.toRespObj(null, "400", "数据返回异常:" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
