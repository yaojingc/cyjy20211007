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
import nc.utils.swiftpass.XmlUtils;
import nc.vo.bd.banktype.BankTypeVO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;


/**
 * 银行类别全参照
 * @author yao
 */
public class CyBankDocListQuery  extends MiniProgramServlet implements IHttpServletAdaptor  {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		String resp = "";
		try {
			BankTypeVO[] bankTypeArr = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryAllBankType(null,null);
					
			if (CyCommonUtils.isNotEmpty(bankTypeArr)) {
				resp = ResponsePOJO.toRespObj(bankTypeArr, "200", "查询成功");
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
