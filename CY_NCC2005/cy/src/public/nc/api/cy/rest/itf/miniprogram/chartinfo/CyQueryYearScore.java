package nc.api.cy.rest.itf.miniprogram.chartinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.bs.framework.common.NCLocator;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.stumarkdata.StumarkdataQueryPOJO;
import nc.vo.cy.stumarkdata.StumarkdataYearPOJO;
import nc.vo.pub.BusinessException;
import nccloud.api.rest.utils.NCCRestUtils;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;

/**
 * 学生年度成绩查询,表单
 */
public class CyQueryYearScore  extends MiniProgramServlet implements IHttpServletAdaptor  {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		String resp = "";
		try {
			StumarkdataQueryPOJO queryvo = (StumarkdataQueryPOJO) JSONTool.getObjByJson2(requestJson,
					StumarkdataQueryPOJO.class);
			List<StumarkdataYearPOJO> listData = NCLocator.getInstance().lookup(IStumarkdatahvoMaintain.class)
					.queryStuYearScoreByIdcard(queryvo);
			if (MMValueCheck.isNotEmpty(listData)) {
				resp = ResponsePOJO.toRespObj(listData, "200", "查询成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "无数据");
			}
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "查询失败：" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}
}
