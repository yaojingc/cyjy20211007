package nc.api.cy.rest.itf.miniprogram.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;

public class MiniProgramServlet  {
	
	/**
	 * 父类的构造方法中，统一做模拟登陆
	 */
	public MiniProgramServlet() {
//		CyCommonUtils.login();
	}

	
	
	public void virtualLogin() {
		CyCommonUtils.login();
	}
	

}
