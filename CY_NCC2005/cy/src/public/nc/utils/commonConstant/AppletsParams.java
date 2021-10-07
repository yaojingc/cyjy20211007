package nc.utils.commonConstant;

import java.io.Serializable;
import java.util.ResourceBundle;

import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.mdm.pub.NtbLogger;

/**
 * 获取项目中所有的常量
 */
public class AppletsParams implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String APPID = "AppID";
	private static final String APPSECRET = "AppSecret";
	private static final String WXLOGINURL = "WxLoginUrl";
	private static final String PK_ORG = "Pk_Org";
	private static final String PK_GROUP = "Pk_Group";
	private static final String WJFURL = "WjfUrl";
	private static final String USERCODE = "UserCode";
	private static final String PSW = "Psw";
	private static final String DS = "ds";
	private static final String PK_MATERIAL = "pk_material";// 物料
	private static final String PK_CURRTYPE = "pk_currtype";// 币种
	private static final String PK_BILLTYPE = "pk_billtype";// 合同类型
	private static final String PK_COUNTRYZONE = "pk_countryzone";// 国家
	private static final String REYMONTYPE = "reymontype";// 收款类型
	private static final String PK_TRADETYPEID = "pk_tradetypeid";// 收款类型
	private static final String PK_BUSITYPE = "pk_busitype";// 业务流程
	private static final String PK_BALATYPE = "pk_balatype";// 结算方式
	private static final String ORG_HNMY = "org_hnmy";// 海南漫语组织主键
	private static final String CQQK = "Cqqk";// 出勤情况
	private static final String ZYWCQK = "Zywcqk";// 作业完成情况
	private static final String KTBXQK = "Ktbxqk";// 课堂表现情况
	private static final String EQUERY = "Equery";// 全查询权限
	private static final String AQUERY = "Aquery";// 区域查询权限
	private static final String SQUERY = "Squery";// 学校查询权限
	
	////退费申请单退费模式
	private static final String TXTK = "Txtk";// 退学退款
	private static final String TXBTK = "Txbtk";// 退学不退款
	
	// 威缴费商户号，建议用正式的，于申请成功后的开户邮件中获取，若未开户需用测试的请联系技术支持
	private static final String MCH_ID = "mch_id";
	// 威缴费MD5签名密钥，建议用正式的，于申请成功后的开户邮件中获取，若未开户需用测试的请联系技术支持
	private static final String KEY = "key";
	// 威缴费支付接口请求地址，请联系技术支持确认
	private static final String REQ_URL = "req_url";
	// 威缴费支付成功异步回调通知地址，商户需改为自己的
	private static final String NOTIFY_URL = "notify_url";
	// 威缴费RSA签名私钥，建议用正式的，商户开发人员本地生成，若未开户需用测试的请联系技术支持
	private static final String MCHPRIVATEKEY = "mchprivatekey";
	// 威缴费RSA验签平台公钥，建议用正式的，登录商户后台查看，若未开户需用测试的请联系技术支持
	private static final String PLATPUBLICKEY = "platpublickey";
	
	
	private static final String YONYOU_TENANTID = "YONYOU_tenantId";// 租户ID
	private static final String YONYOU_APPCODE = "YONYOU_appCode";// appcode
	private static final String YONYOU_APPKEY = "YONYOU_appKey";// 
	private static final String YONYOU_APPSECRET = "YONYOU_appSecret";// 
	private static final String YONYOU_GETTOKENURL = "YOUYOU_getTokenUrl";// 获取友空间token的URL
	private static final String YONYOU_PUSHMSGURL = "YOUYOU_pushMsgUrl";// 友空间推送工作通知URL
	private static final String YONYOU_SEARCHUSERURL = "YOUYOU_searchUserUrl";// 友空间通过电话号码查询用户信息URL
	private static final String YONYOU_BEFOREDAYS = "YOUYOU_beforedays";// 预警提前几天推送
	
	
    // 短信 appid
    private static final String SMSSDKAPPID = "smssdkappid";
    // 腾讯云账户密钥对: secretId
    public static final String SMSSECRETID = "smssecretid";
    // 腾讯云账户密钥对: secretKey
    public static final String SMSSECRETKEY = "smssecretkey";
    // sign sms签名
    public static final String SMSSIGN = "smssign";
    // sms 短信模板id:
    public static final String SMSTEMPLATEID = "smstemplateid";
	
	
	/*********************************** 分割线 ***********************************/

	public static String AppID = "";
	public static String AppSecret = "";
	public static String WxLoginUrl = "";
	public static String Pk_Org = "";
	public static String Pk_Group = "";
	public static String WjfUrl = "";
	public static String UserCode = "";
	public static String Psw = "";
	public static String ds = "";
	// 收款合同
	public static String pk_material = "";// 物料
	public static String pk_currtype = "";// 币种
	public static String pk_billtype = "";// 合同类型
	public static String pk_countryzone = "";// 国家
	public static String reymontype = "";// 收款类型
	public static String pk_tradetypeid = "";// 收款类型
	public static String pk_busitype = "";// 业务流程
	public static String pk_balatype = "";// 结算方式
	public static String org_hnmy = "";// 海南漫语组织主键
	public static String Cqqk = "";// 出勤情况
	public static String Zywcqk = "";// 作业完成情况
	public static String Ktbxqk = "";// 课堂表现情况
	public static String Equery = "";// 全查询
	public static String Aquery = "";// 区域查询
	public static String Squery = "";// 学校查询
	
	//退费申请单退费模式
	public static String Txtk = "";// 退学退款
	public static String Txbtk = "";// 退学不退款

	// 威缴费商户号，建议用正式的，于申请成功后的开户邮件中获取，若未开户需用测试的请联系技术支持
	public static String mch_id = "";
	// 威缴费支付接口请求地址，请联系技术支持确认
	public static String req_url = "";
	// 威缴费支付成功异步回调通知地址，商户需改为自己的
	public static String notify_url = "";
	
	public static String YONYOU_tenantId = "";
	public static String YONYOU_appCode = "";
	public static String YONYOU_appKey = "";
	public static String YONYOU_appSecret = "";
	public static String YOUYOU_getTokenUrl="";
	public static String YOUYOU_pushMsgUrl="";
	public static String YOUYOU_searchUserUrl="";
	public static String YOUYOU_beforedays="";
	
	
	// 短信 appid
	public static String smssdkappid = "";
    // 腾讯云账户密钥对: secretId
	public static String smssecretid = "";
    // 腾讯云账户密钥对: secretKey
	public static String smssecretkey = "";
    // sign sms签名
	public static String smssign = "";
    // sms 短信模板id:
	public static String smstemplateid = "";
	
	
	
	
	static {
		try {
			ResourceBundle res = ResourceBundle
					.getBundle(AppletsParams.class.getPackage().getName() + ".AppletsParams");

			AppletsParams.AppID = CyCommonUtils.isEmpty(res.getString(APPID)) ? "" : res.getString(APPID);

			AppletsParams.AppSecret = CyCommonUtils.isEmpty(res.getString(APPSECRET)) ? "" : res.getString(APPSECRET);

			AppletsParams.WxLoginUrl = CyCommonUtils.isEmpty(res.getString(WXLOGINURL)) ? ""
					: res.getString(WXLOGINURL);

			AppletsParams.Pk_Org = CyCommonUtils.isEmpty(res.getString(PK_ORG)) ? "" : res.getString(PK_ORG);

			AppletsParams.Pk_Group = CyCommonUtils.isEmpty(res.getString(PK_GROUP)) ? "" : res.getString(PK_GROUP);

			AppletsParams.WjfUrl = CyCommonUtils.isEmpty(res.getString(WJFURL)) ? "" : res.getString(WJFURL);

			AppletsParams.UserCode = CyCommonUtils.isEmpty(res.getString(USERCODE)) ? "" : res.getString(USERCODE);

			AppletsParams.Psw = CyCommonUtils.isEmpty(res.getString(PSW)) ? "" : res.getString(PSW);

			AppletsParams.ds = CyCommonUtils.isEmpty(res.getString(DS)) ? "" : res.getString(DS);

			AppletsParams.pk_material = CyCommonUtils.isEmpty(res.getString(PK_MATERIAL)) ? ""
					: res.getString(PK_MATERIAL);
			AppletsParams.pk_currtype = CyCommonUtils.isEmpty(res.getString(PK_CURRTYPE)) ? ""
					: res.getString(PK_CURRTYPE);
			AppletsParams.pk_billtype = CyCommonUtils.isEmpty(res.getString(PK_BILLTYPE)) ? ""
					: res.getString(PK_BILLTYPE);
			AppletsParams.pk_countryzone = CyCommonUtils.isEmpty(res.getString(PK_COUNTRYZONE)) ? ""
					: res.getString(PK_COUNTRYZONE);
			AppletsParams.reymontype = CyCommonUtils.isEmpty(res.getString(REYMONTYPE)) ? ""
					: res.getString(REYMONTYPE);
			AppletsParams.pk_tradetypeid = CyCommonUtils.isEmpty(res.getString(PK_TRADETYPEID)) ? ""
					: res.getString(PK_TRADETYPEID);
			AppletsParams.pk_busitype = CyCommonUtils.isEmpty(res.getString(PK_BUSITYPE)) ? ""
					: res.getString(PK_BUSITYPE);
			AppletsParams.pk_balatype = CyCommonUtils.isEmpty(res.getString(PK_BALATYPE)) ? ""
					: res.getString(PK_BALATYPE);
			AppletsParams.org_hnmy = CyCommonUtils.isEmpty(res.getString(ORG_HNMY)) ? "" : res.getString(ORG_HNMY);
			AppletsParams.Cqqk = CyCommonUtils.isEmpty(res.getString(CQQK)) ? "" : res.getString(CQQK);
			AppletsParams.Zywcqk = CyCommonUtils.isEmpty(res.getString(ZYWCQK)) ? "" : res.getString(ZYWCQK);
			AppletsParams.Ktbxqk = CyCommonUtils.isEmpty(res.getString(KTBXQK)) ? "" : res.getString(KTBXQK);
			AppletsParams.Equery = CyCommonUtils.isEmpty(res.getString(EQUERY)) ? "" : res.getString(EQUERY);
			AppletsParams.Aquery = CyCommonUtils.isEmpty(res.getString(AQUERY)) ? "" : res.getString(AQUERY);
			AppletsParams.Squery = CyCommonUtils.isEmpty(res.getString(SQUERY)) ? "" : res.getString(SQUERY);
			
			AppletsParams.Txtk = CyCommonUtils.isEmpty(res.getString(TXTK)) ? "" : res.getString(TXTK);
			AppletsParams.Txbtk = CyCommonUtils.isEmpty(res.getString(TXBTK)) ? "" : res.getString(TXBTK);

			AppletsParams.mch_id = CyCommonUtils.isEmpty(res.getString(MCH_ID)) ? "" : res.getString(MCH_ID);
			AppletsParams.req_url = CyCommonUtils.isEmpty(res.getString(REQ_URL)) ? "" : res.getString(REQ_URL);
			AppletsParams.notify_url = CyCommonUtils.isEmpty(res.getString(NOTIFY_URL)) ? ""
					: res.getString(NOTIFY_URL);
			
			
			AppletsParams.YONYOU_tenantId = CyCommonUtils.isEmpty(res.getString(YONYOU_TENANTID)) ? "" : res.getString(YONYOU_TENANTID);

			AppletsParams.YONYOU_appCode = CyCommonUtils.isEmpty(res.getString(YONYOU_APPCODE)) ? "" : res.getString(YONYOU_APPCODE);

			AppletsParams.YONYOU_appKey = CyCommonUtils.isEmpty(res.getString(YONYOU_APPKEY)) ? "" : res.getString(YONYOU_APPKEY);

			AppletsParams.YONYOU_appSecret = CyCommonUtils.isEmpty(res.getString(YONYOU_APPSECRET)) ? "" : res.getString(YONYOU_APPSECRET);
			
			AppletsParams.YOUYOU_getTokenUrl = CyCommonUtils.isEmpty(res.getString(YONYOU_GETTOKENURL)) ? "" : res.getString(YONYOU_GETTOKENURL);
			AppletsParams.YOUYOU_pushMsgUrl = CyCommonUtils.isEmpty(res.getString(YONYOU_PUSHMSGURL)) ? "" : res.getString(YONYOU_PUSHMSGURL);
			AppletsParams.YOUYOU_searchUserUrl = CyCommonUtils.isEmpty(res.getString(YONYOU_SEARCHUSERURL)) ? "" : res.getString(YONYOU_SEARCHUSERURL);
			AppletsParams.YOUYOU_beforedays = CyCommonUtils.isEmpty(res.getString(YONYOU_BEFOREDAYS)) ? "" : res.getString(YONYOU_BEFOREDAYS);
			
			
			
			// 短信 appid
			AppletsParams.smssdkappid = CyCommonUtils.isEmpty(res.getString(SMSSDKAPPID)) ? "" : res.getString(SMSSDKAPPID);
		    // 腾讯云账户密钥对: secretId
			AppletsParams.smssecretid = CyCommonUtils.isEmpty(res.getString(SMSSECRETID)) ? "" : res.getString(SMSSECRETID);
		    // 腾讯云账户密钥对: secretKey
			AppletsParams.smssecretkey = CyCommonUtils.isEmpty(res.getString(SMSSECRETKEY)) ? "" : res.getString(SMSSECRETKEY);
		    // sign sms签名
			AppletsParams.smssign = CyCommonUtils.isEmpty(res.getString(SMSSIGN)) ? "" : res.getString(SMSSIGN);
		    // sms 短信模板id:
			AppletsParams.smstemplateid = CyCommonUtils.isEmpty(res.getString(SMSTEMPLATEID)) ? "" : res.getString(SMSTEMPLATEID);

		} catch (Exception e) {
			NtbLogger.print("读取配置文件AppletsParams时异常：" + e.getMessage());
		}

	}
}
