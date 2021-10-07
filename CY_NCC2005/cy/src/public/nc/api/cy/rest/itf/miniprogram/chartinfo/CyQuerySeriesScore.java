package nc.api.cy.rest.itf.miniprogram.chartinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import nc.vo.cy.stumarkdata.SeriesLeve;
import nc.vo.cy.stumarkdata.StumarkdataQueryPOJO;
import nc.vo.cy.stumarkdata.StumarkdataSeriesPOJO;
import nc.vo.cy.stumarkdata.StumarkdataYearPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nccloud.api.rest.utils.NCCRestUtils;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;

/**
 * 学生历史成绩查询,图表
 */
public class CyQuerySeriesScore extends MiniProgramServlet implements IHttpServletAdaptor {

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
				List<String> listCategories = new ArrayList<String>();
				List<String> listScores = new ArrayList<String>();
				SeriesLeve series = new SeriesLeve();
				for (StumarkdataYearPOJO stupojo : listData) {
					listCategories.add(stupojo.getKssjian());
					listScores.add(stupojo.getXsscore());// .setScale(2, 5)
					series.setName(stupojo.getXsxm() + "成绩");
				}
				series.setData(listScores.toArray(new String[0]));// listScores.toArray(new UFDouble[0])
				StumarkdataSeriesPOJO stuSeries = new StumarkdataSeriesPOJO();
				stuSeries.setCategories(listCategories.toArray(new String[0]));
				stuSeries.setSeries(new SeriesLeve[] { series });
				resp = ResponsePOJO.toRespObj(stuSeries, "200", "查询成功");
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
