package nc.api.cy.rest.itf.miniprogram.login;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.encode.Digest;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.miniprogram.MiniUserInfoPojo;
import nc.vo.cy.pojo.ResponsePOJO;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

/**
 * 登录方式： 身份证号+密码
 * 
 * @author yao
 */
public class CyMiniLogin extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.virtualLogin();

		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		String resp = null;
		MiniUserInfoPojo loginVO = null;
		try {
			// 参数解析
			loginVO = (MiniUserInfoPojo) JSONTool.getObjByJson2(requestJson, MiniUserInfoPojo.class);
			loginVO = CyCommonUtils.getInstance(IStudenthvoMaintain.class).miniLogin(loginVO);

			if(loginVO.getStatus()) {
				resp = ResponsePOJO.toRespObj(loginVO, "200", loginVO.getMsg());
			} else {
				resp = ResponsePOJO.toRespObj(loginVO, "400", loginVO.getMsg());
			}
		} catch (Exception e) {
			resp = ResponsePOJO.toRespObj(loginVO, "400", loginVO.getMsg());
		}
		
		// 响应返回
		response.getOutputStream().write(resp.getBytes()); 

	}

}
