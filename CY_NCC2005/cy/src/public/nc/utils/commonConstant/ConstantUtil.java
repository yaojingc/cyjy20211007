package nc.utils.commonConstant;

import java.util.ArrayList;
import java.util.List;

public class ConstantUtil {

	// 词源正式环境参数
	public static String APPID = "b14b6cfb-585d-449d-ae1a-30d1197d7e81"; // appid
	public static String DOMAIN = "https://tax.diwork.com"; // 税务云地址
	public static String CAFILE = "/cyjy235874.pfx";
	public static String PASSWORD = "235874";
	public static final List<String> grouplist = new ArrayList<String>();

	// 以下是测试环境地址
//	public static String APPID = "commontesterCA"; // appid
//	public static String DOMAIN = "https://yesfp.yonyoucloud.com"; // 税务云地址
//	public static String CAFILE = "/pro22.pfx";
//	public static String PASSWORD = "password";

	// 以下是税务云开蓝票地址
	public static String BLUE_URL = DOMAIN + "/invoiceclient-web/api/invoiceApply/insertWithArray?appid=" + APPID;

	// 以下是税务云开红票地址
	public static String RED_URL = DOMAIN + "/invoiceclient-web/api/invoiceApply/red?appid=" + APPID;

	// 以下是税务云开票查询地址
	public static String QUERY_URL = DOMAIN + "/invoiceclient-web/api/invoiceApply/queryInvoiceStatus?appid=" + APPID;

	// 以下税务云合并开票地址，指的是单次申请超过当前企业的发票限额，999999
	public static String JOIN_URL = DOMAIN + "/invoiceclient-web/api/invoiceApply/insertWithSplit?appid=" + APPID;

	static {
		grouplist.add("SC1");// 总经理
		grouplist.add("11");// 销售副总经理
		grouplist.add("111");// 销售总监
		grouplist.add("113");// 讲师团长
	}
}
