package nc.api.cy.rest.itf.miniprogram.contract;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONString;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.creditcontract.pojo.CContractQueryPojo;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.api.rest.utils.NCCRestUtils;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;

/**
 * 学生收款合同（虚拟合同）变更（生效）
 */
public class CyContractUpdate extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		String resp = "";
		try {
			CContractQueryPojo updateVO = (CContractQueryPojo) JSONTool.getObjByJson2(requestJson,
					CContractQueryPojo.class);
			boolean cctret = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).ccontractUpdate(updateVO);
			if (cctret) {
				resp = ResponsePOJO.toRespObj(null, "200", "操作成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "操作失败");
			}
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "操作失败" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}
}
