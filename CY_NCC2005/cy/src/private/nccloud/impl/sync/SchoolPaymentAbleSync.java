package nccloud.impl.sync;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.ErpItfDataPool;
import nc.utils.commonplugin.JSONTool;
import nc.utils.encode.SignUtils;
import nc.utils.transfer.SchoolPaymentToOldBankVO;
import nc.vo.cy.bankcollflow.pojo.RequestPOJO;
import nc.vo.cy.bankcollflow.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nccloud.impl.dao.CreditContractBaseDao;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;

/*
 * 缴费记录查询后台任务ronglei(旧)
 */
public class SchoolPaymentAbleSync implements IBackgroundWorkPlugin {

	private IBankcollflowhvoMaintain service;

	public IBankcollflowhvoMaintain getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(IBankcollflowhvoMaintain.class);
		}
		return service;
	}

	// 调用第三方接口查询缴费记录
	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		// 拼装接口所需的参数
		RequestPOJO req = new RequestPOJO();
		//
		ResponsePOJO resp = new ResponsePOJO();
		/*
		 * 日期
		 */
		String nowdate;
		LinkedHashMap<String, Object> keyMap = context.getKeyMap();// 后台任务参数
		Object nobj = keyMap.get("nowdate");
		boolean flag2 = false;
		if (CyCommonUtils.isNotEmpty(nobj)) {
			String regex = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher((CharSequence) nobj);
			flag2 = matcher.matches();
		}
		if (flag2) {
			nowdate = (String) nobj;
			req.setStartDate(nowdate + " 00:00:00");// 开始时间
			req.setEndDate(nowdate + " 23:59:59");// 结束时间
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			Date date = new Date();
			cd.setTime(date);
			nowdate = sdf.format(cd.getTime()); // 当前日期
			req.setStartDate(nowdate + " 00:00:00");// 开始时间
			req.setEndDate(nowdate + " 23:59:59");// 结束时间
		}

		/*
		 * 
		 */
		req.setBizType("01");// 学校
		req.setSignType("RSA_1_256");
		req.setMchNo("212590026127");// 词源的商户号
//		req.setStartDate("2021-09-04 00:00:00");// 开始时间
//		req.setEndDate("2021-09-06 23:59:59");// 结束时间

		String str = "bizType=01&endDate=" + nowdate + " 23:59:59&mchNo=212590026127&signType=RSA_1_256&startDate="
				+ nowdate + " 00:00:00";
//		String str = "bizType=01&endDate=2021-09-06 23:59:59&mchNo=212590026127&signType=RSA_1_256&startDate=2021-09-04 00:00:00";

		String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKIKk2ELLQI4GFnbURuNPz5qG911hS8trWu4bThVCORO3VZwDGoNW2G2c5Df9dIWFlSe4+2syPHCMFpF3Whbsn5rg7DcfAy3yCNI8I58/YnLUx+l64tDV1qBRlW0uvEXldtxNvVCiNttyEqGYxvPPPIgKgd8vMHjg6ssCRiarw8BAgMBAAECgYAMUrGSl778hDeV3c/1QHCm1nr4BI/0XozpvETSG+VpN3YttKe9CPcCH1X6lo2jRHq+RLcgxBskB5iVxOppnU+dnp30JMXm2mScKX88OLmmnJHqMBCyn5uj9DaCk4hxH6gKLVLcZ8NjKO+lc2ebfz9I+uAAcggiouGJob0rySWbYQJBANR4VsNU4bJxy0IRDXA/nOmvLiA5ok2NSbMmRuB4saWjU97Tz07eS6LDNt2SR5Fq/K/4PuuequIT2JiBaT0yfusCQQDDPVd+Ig0CSHJsMc3klN4uCyWbU0LvTclQQjneyZxOjIdL3LC0zpJyh2twQrV7UIJFaKzuXYq6i4hxfE55xKbDAkBBYL4h4M6KICXRMHoYHhrkdTkWRWXBw0X4UEDi9YCEXVuDZ12VASX48WTCjS5keq1CYHrL+cjPRBgT5U5CvHT9AkBh13zgNhpE2Z+V/wOKmNwbQPTQuVIi8N2BPh8YOfr+Fki7SNa5VYhootrBwHyIVtliBUocjn42bDStgrklJtrXAkEAkrM4lNbBy4CmLMdNT9BaIfupZs+8xJeTle7ij80i3t/ULgzfTiVrJxRxk/oYDDVvHQujMI6q6L0OgvxrS9QMPg==";

		String url = "https://wjf.swiftpass.cn/schoolpayment/api/bill/query";
		try {
			String rsaSign = SignUtils.rsaSign(str, "RSA_1_256", key);
			req.setSign(rsaSign);
			String jsonByObj = JSONTool.getJsonByObj(req);
			if (CyCommonUtils.isNotEmpty(rsaSign)) {
				String response = ErpItfDataPool.erpHttpPost(jsonByObj, url);
//				String responsestr = StringEscapeUtils.unescapeJavaScript(response);
				resp = (ResponsePOJO) JSONTool.getObjByJson(response, ResponsePOJO.class);
				Boolean flag = new SchoolPaymentToOldBankVO().transResptoVo(resp);
				if (flag) {
					CreditContractBaseDao.updateStatus(resp);// 修改学费收款合同已生效状态
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		RequestPOJO req = new RequestPOJO();
		req.setBizType("01");// 学校
		req.setSignType("RSA_1_256");
		req.setMchNo("212590026127");// 词源的商户号
		req.setStartDate("2021-08-05 00:00:00");// 开始时间
		req.setEndDate("2021-08-05 23:59:59");// 结束时间
		String str = "bizType=01&endDate=2021-08-05 23:59:59&mchNo=212590026127&signType=RSA_1_256&startDate=2021-08-05 00:00:00";
		String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKIKk2ELLQI4GFnbURuNPz5qG911hS8trWu4bThVCORO3VZwDGoNW2G2c5Df9dIWFlSe4+2syPHCMFpF3Whbsn5rg7DcfAy3yCNI8I58/YnLUx+l64tDV1qBRlW0uvEXldtxNvVCiNttyEqGYxvPPPIgKgd8vMHjg6ssCRiarw8BAgMBAAECgYAMUrGSl778hDeV3c/1QHCm1nr4BI/0XozpvETSG+VpN3YttKe9CPcCH1X6lo2jRHq+RLcgxBskB5iVxOppnU+dnp30JMXm2mScKX88OLmmnJHqMBCyn5uj9DaCk4hxH6gKLVLcZ8NjKO+lc2ebfz9I+uAAcggiouGJob0rySWbYQJBANR4VsNU4bJxy0IRDXA/nOmvLiA5ok2NSbMmRuB4saWjU97Tz07eS6LDNt2SR5Fq/K/4PuuequIT2JiBaT0yfusCQQDDPVd+Ig0CSHJsMc3klN4uCyWbU0LvTclQQjneyZxOjIdL3LC0zpJyh2twQrV7UIJFaKzuXYq6i4hxfE55xKbDAkBBYL4h4M6KICXRMHoYHhrkdTkWRWXBw0X4UEDi9YCEXVuDZ12VASX48WTCjS5keq1CYHrL+cjPRBgT5U5CvHT9AkBh13zgNhpE2Z+V/wOKmNwbQPTQuVIi8N2BPh8YOfr+Fki7SNa5VYhootrBwHyIVtliBUocjn42bDStgrklJtrXAkEAkrM4lNbBy4CmLMdNT9BaIfupZs+8xJeTle7ij80i3t/ULgzfTiVrJxRxk/oYDDVvHQujMI6q6L0OgvxrS9QMPg==";
		String rsaSign = SignUtils.rsaSign(str, "RSA_1_256", key);
		req.setSign(rsaSign);
		String jsonByObj = JSONTool.getJsonByObj(req);
		System.out.println(jsonByObj);
	}
}
