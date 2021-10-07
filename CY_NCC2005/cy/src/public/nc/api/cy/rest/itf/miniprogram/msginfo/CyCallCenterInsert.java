package nc.api.cy.rest.itf.miniprogram.msginfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONString;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.callcenter.AggCallcenterHVO;
import nc.vo.cy.callcenter.CallCenterPOJO;
import nc.vo.cy.callcenter.CallcenterHVO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.api.rest.utils.ResultMessageUtil;
import nccloud.itf.cy.cy.ICallcenterhvoMaintain;

/**
 * 客服通道信息新增
 */
public class CyCallCenterInsert extends MiniProgramServlet implements IHttpServletAdaptor {
	
	
	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		String resp = "";
		try {
			CallCenterPOJO cpojo = (CallCenterPOJO) JSONTool.getObjByJson(requestJson, CallCenterPOJO.class);
			String problemcont = cpojo.getProblemcont();// 问题内容
			String contactway = cpojo.getContactway();// 联系方式
			List<AggCallcenterHVO> agglist = new ArrayList<AggCallcenterHVO>();
			AggCallcenterHVO aggvo = new AggCallcenterHVO();
			CallcenterHVO hvo = new CallcenterHVO();
			hvo.setProblemcont(problemcont);
			hvo.setContactway(contactway);
			hvo.setBillstatus(-1);
			hvo.setPk_group(AppletsParams.Pk_Group);
			hvo.setPk_org(AppletsParams.Pk_Org);
			aggvo.setParentVO(hvo);
			agglist.add(aggvo);
			AggCallcenterHVO[] array = agglist.toArray(new AggCallcenterHVO[agglist.size()]);
			AggCallcenterHVO[] insert = CyCommonUtils.getInstance(ICallcenterhvoMaintain.class).insert(array, null);
			
			if (CyCommonUtils.isNotEmpty(insert)) {
				resp = ResponsePOJO.toRespObj(requestJson, "200", "成功");
			}
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "数据返回异常 :" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
		
	}

}
