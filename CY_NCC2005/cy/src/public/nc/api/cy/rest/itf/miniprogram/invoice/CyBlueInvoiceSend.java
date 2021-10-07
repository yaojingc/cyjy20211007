package nc.api.cy.rest.itf.miniprogram.invoice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import nc.api.cy.rest.entity.BlueInvoiceBody;
import nc.api.cy.rest.entity.BlueInvoiceHead;
import nc.api.cy.rest.entity.Email;
import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.bs.framework.common.NCLocator;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.ConstantUtil;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.invoice.CallUtil;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.bankcollflow.BankcollflowHVO;
import nc.vo.cy.bankcollflow.InvoiceSendPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;

/**
 * 开蓝票
 * 
 * @param json
 * @return
 */
public class CyBlueInvoiceSend extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.virtualLogin();

		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String json = XmlUtils.parseRequst(request);

		boolean invoiceflat = true;
		String result = "";
		String resp = "";
		try {
			InvoiceSendPOJO queryvo = (InvoiceSendPOJO) JSONTool.getObjByJson(json, InvoiceSendPOJO.class);
			CallUtil callUtil = new CallUtil();

			if (MMValueCheck.isEmpty(queryvo.getIdcard()) || MMValueCheck.isEmpty(queryvo.getBill_no())) {
				resp = ResponsePOJO.toRespObj(null, "400", "身份证号或单据号为空");
			}
			// 根据josn信息查询完整银行流水缴费信息，转BlueInvoiceHead格式josn
			List<BankcollflowHVO> listBFHVO = CyCommonUtils.getInstance(IBankcollflowhvoMaintain.class)
					.queryBakFlows(queryvo);
			if (MMValueCheck.isEmpty(listBFHVO)) {
				resp = ResponsePOJO.toRespObj(null, "400", "未匹配到对应的银行收款流水信息");
			}
			if (!MMValueCheck.isEmpty(listBFHVO) && listBFHVO.size() > 1) {
				resp = ResponsePOJO.toRespObj(null, "400", "匹配到多条银行收款流水信息");
			}

			BankcollflowHVO bf = listBFHVO.get(0);
			String sname = bf.getSname();
			if (MMValueCheck.isEmpty(sname)) {
				resp = ResponsePOJO.toRespObj(null, "400", "对应的银行收款流水中学生姓名为空");
			}
			String payamount = bf.getPayamount();
			try {
				new UFDouble(payamount);
			} catch (Exception e) {
				resp = ResponsePOJO.toRespObj(null, "400", "对应的银行收款流水中付款金额：" + payamount + "非法");
			}
			double payamountDou = new UFDouble(payamount).setScale(2, 5).doubleValue();
			if (payamountDou < 0) {
				resp = ResponsePOJO.toRespObj(null, "400", "对应的银行收款流水中付款金额小于零");
			}
			String paytype = bf.getPaytype();
			List<BlueInvoiceHead> listhead = new ArrayList<BlueInvoiceHead>();
			BlueInvoiceHead headInv = new BlueInvoiceHead();
			if ("威缴费".equals(paytype)) {
				headInv.setXSF_NSRSBH("91429004331898126W");// 销售方纳税人识别号：必传
				headInv.setXSF_MC("湖北词源教育投资管理有限公司");// 销售方名称
				headInv.setXSF_DZDH("湖北省仙桃市 0728-3256649");// 销售方地址、电话
				headInv.setXSF_YHZH("1813031309100010326");// 销售方银行账号
			} else if ("平安银行".equals(paytype)) {
				headInv.setXSF_NSRSBH("91460100MA5T6PR05K");// 销售方纳税人识别号：必传
				headInv.setXSF_MC("海南漫语教育科技有限公司");// 销售方名称
				headInv.setXSF_DZDH("海南省三亚市崖州区崖州湾科技城雅布伦产业园3号楼6层3607号 0898-38215548");// 销售方地址、电话
				headInv.setXSF_YHZH("15447099260036");// 销售方银行账号
			} else if ("家长端".equals(paytype)) {
				headInv.setXSF_NSRSBH("91429004331898126W");// 销售方纳税人识别号：必传
				headInv.setXSF_MC("湖北词源教育投资管理有限公司");// 销售方名称
				headInv.setXSF_DZDH("湖北省仙桃市 0728-3256649");// 销售方地址、电话
				headInv.setXSF_YHZH("1813031309100010326");// 销售方银行账号
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "未匹配到对应的缴费方式：" + paytype);
			}

			headInv.setGMF_MC(sname);// 购买方名称：必传
			// headInv.setGMF_NSRSBH("201609140000004");// 购买方纳税人识别号：必传
			headInv.setFPQQLSH(bf.getBill_no());
			// headInv.setGMF_YHZH("中国农业银行股份有限公司广州花都名门支行 44087001040011474");// 购买方银行账号：必传
			headInv.setFPLX("1");// 发票类型：必传
			headInv.setGMF_DZDH(bf.getPhonenum());// 购买方地址、电话：必传
			headInv.setJSHJ(payamountDou);
			headInv.setBZ(bf.getIdcard());// 备注
			headInv.setKPR("洪梅");// 开票人：必传
			// headInv.setSKR("彭业倩");// 收款人：必传
			headInv.setFHR("彭业倩");// 复核人：必传
			headInv.setJSHJ(payamountDou);// 价税合计：必传
			String stuemail = bf.getStrkey();// 邮箱
			if (MMValueCheck.isNotEmpty(stuemail)) {
				headInv.setZDYBZ(stuemail);// 15907176458@163.com
			}

			List<BlueInvoiceBody> items = new ArrayList<BlueInvoiceBody>();

			BlueInvoiceBody body = new BlueInvoiceBody();
			body.setXMMC("*非学历教育服务*小语种课程教学");
			body.setSPBM("3070201020000000000");
			body.setXMJSHJ(payamountDou);
			body.setSL(0.03);
			// body.setSE(payamountDou-(payamountDou / 1.03));//无需赋值，税务云计算
			items.add(body);

			headInv.setItems(items);

			listhead.add(headInv);

			JSONArray array = JSONArray.parseArray(new Gson().toJson(listhead));

			// 接收到的开票数据，放到map里面
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("requestdatas", array.toString()); // 开票数据
			// paramsMap.put("url", jutil.getJsonByObject(urls)); //开票成功回调

			// 如果为电票，则增加邮箱地址的推送
			String fplx = headInv.getFPLX(); // 1，表示电票
			String fpqqlsh = headInv.getFPQQLSH(); // 发票请求流水号
			String address = headInv.getZDYBZ(); // 自定义备注，存储邮箱地址
			if ("1".equals(fplx) && address != null && !address.trim().isEmpty()) {
				// 电票且邮箱地址不为空
				List<Email> emails = new ArrayList<Email>();
				Email email = new Email();
				email.setFpqqlsh(fpqqlsh);
				email.setAddress(address);
				emails.add(email);
				String emailJson = JSONTool.getJsonByObject(emails);
				paramsMap.put("email", emailJson);
			}

			paramsMap.put("autoAudit", "false"); // 自动开票为否

			// result = util.callTaxCloudApi(ConstantUtil.BLUE_URL, paramsMap);//单张蓝票地址
			result = callUtil.callTaxCloudApi(ConstantUtil.JOIN_URL, paramsMap);// 合并开票地址
			JSONObject retJson = JSONObject.parseObject(result);
			if ("9999".equals(retJson.getString("code"))) {
				bf.setAcquiescent("3");
				invoiceflat = false;
				result = retJson.getString("msg");
//				logger.error("蓝票异常：" + result);
			} else {
				bf.setAcquiescent("1");
			}

			// 开票成功回写缴费清单开票标识
			bf.setStatus(VOStatus.UPDATED);
			IBankcollflowhvoMaintain ibankflow = NCLocator.getInstance().lookup(IBankcollflowhvoMaintain.class);
			try {
				ibankflow.bankFlowUpdate(bf);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			invoiceflat = false;
			resp = ResponsePOJO.toRespObj(result, "400", "开蓝票异常，开票失败:" + ex.getMessage());
		} finally {
			CyCommonUtils.logout();
		}

		if (invoiceflat) {
			resp = ResponsePOJO.toRespObj(result, "200", "开票成功");
		} else {
			resp = ResponsePOJO.toRespObj(result, "400", resp);
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
