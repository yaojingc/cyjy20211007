package nc.api.cy.rest.itf.miniprogram.chartinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc.vo.cy.stumarkdata.CurriculumMsg;
import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.stumarkdata.CurriculumInfo;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;

/**
 * 学生考勤查询
 * @author yao
 *
 */
public class CyQueryCheckInInfo extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.virtualLogin();
		String resp = "";
		try {
			InputStream is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String requestJson = XmlUtils.parseRequst(request);
			CurriculumInfo queryvo = (CurriculumInfo) JSONTool.getObjByJson2(requestJson, CurriculumInfo.class);

			List<CurriculumMsg> listData = CyCommonUtils.getInstance(IStumarkdatahvoMaintain.class)
					.queryCurriculumByIdcard(queryvo);

			if (CyCommonUtils.isNotEmpty(listData)) {
				resp = ResponsePOJO.toRespObj(listData, "200", "查询成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "无数据");
			}
		} catch (Exception e) {
			resp = ResponsePOJO.toRespObj(null, "400", "查询失败：" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
