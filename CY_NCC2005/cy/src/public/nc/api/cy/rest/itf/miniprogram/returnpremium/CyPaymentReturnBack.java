package nc.api.cy.rest.itf.miniprogram.returnpremium;

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
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.returncost.AggReturncostHVO;
import nc.vo.cy.returncost.MiniReturnCostInsertPojo;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;

/**
 * 退费申请单新增
 * @author yao
 */
public class CyPaymentReturnBack extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		MiniReturnCostInsertPojo miniReturnCostInsertPojo = (MiniReturnCostInsertPojo) JSONTool.getObjByJson2(requestJson,
				MiniReturnCostInsertPojo.class);
		String resp = "";
		
		try {
			AggReturncostHVO[] result = CyCommonUtils.getInstance(IReturncosthvoMaintain.class).insertByMiniPro(miniReturnCostInsertPojo);
			if(CyCommonUtils.isNotEmpty(result)){
				resp = ResponsePOJO.toRespObj(result, "200", "操作成功");
			}
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "操作异常");
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}
	

}
