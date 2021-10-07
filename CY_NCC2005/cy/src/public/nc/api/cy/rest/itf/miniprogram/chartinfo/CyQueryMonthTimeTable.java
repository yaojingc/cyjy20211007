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
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.stumarkdata.ArrayMarkDayVOS;
import nc.vo.cy.stumarkdata.CurriculumInfo;
import nc.vo.cy.stumarkdata.CurriculumMsg;
import nc.vo.cy.stumarkdata.MarkDayVO;
import nc.vo.cy.stumarkdata.StumarkdataYearPOJO;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;

public class CyQueryMonthTimeTable extends MiniProgramServlet implements IHttpServletAdaptor{

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.virtualLogin();
		String resp = "";
		
		try {
			InputStream is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String requestJson = XmlUtils.parseRequst(request);
			CurriculumInfo queryvo = (CurriculumInfo) JSONTool.getObjByJson2(requestJson, CurriculumInfo.class);
			//根据月份和状态判断 是查课程还是查考勤
			List<MarkDayVO> listData = CyCommonUtils.getInstance(IStumarkdatahvoMaintain.class)
					.queryMonthTimeTable(queryvo);
			if (CyCommonUtils.isNotEmpty(listData)) {
				//进行转换为数组
				List<String> days = new ArrayList<String>();
				for (MarkDayVO stupojo : listData) {
					days.add(stupojo.getMarkday());
					
				}
				ArrayMarkDayVOS markdays = new ArrayMarkDayVOS();
				markdays.setMarkDays(days.toArray(new String[0]));
				resp = ResponsePOJO.toRespObj(markdays, "200", "查询成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "无数据");
			}
		} catch (Exception e) {
			resp = ResponsePOJO.toRespObj(null, "400", "查询失败：" + e.getMessage());
		}finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
