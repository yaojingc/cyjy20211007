package nc.api.cy.rest.itf.miniprogram.msginfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.msgstation.MsgQueryPOJO;
import nc.vo.cy.msgstation.MsgReturnPOJO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.IMsgstationhvoMaintain;

/**
 * 消息查询接口 入参：身份证号，页码，每页大小
 */
public class CyMsgQuery extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		MsgQueryPOJO queryvo = (MsgQueryPOJO) JSONTool.getObjByJson2(requestJson, MsgQueryPOJO.class);
		String resp = "";
		try {
			PagingPOJO result = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(
					new TableCountQueryVO("cy_msgstationHVO", "receiverid='"+queryvo.getReceiverid()+"'"));
			double maxresult = (double) result.getTotal() / (double) queryvo.getSize();
			// 最大页码数需要向上取整
			Double uploadResult = Math.ceil(maxresult);
			int pagetotal = new Double(uploadResult).intValue();
			result.setTotalPages(pagetotal);
			result.setCurrentPage(queryvo.getIndex());
			result.setPageSize(queryvo.getSize());
			List<MsgReturnPOJO> content = CyCommonUtils.getInstance(IMsgstationhvoMaintain.class).queryMsg(queryvo,pagetotal);
			result.setContent(content);
			if (CyCommonUtils.isNotEmpty(content)) {
				resp = ResponsePOJO.toRespObj(result, "200", "成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "查无数据");
			}
		} catch (Exception e) {
			resp = ResponsePOJO.toRespObj(null, "400", "数据返回异常 :" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
