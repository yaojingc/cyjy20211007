package nc.utils.commonConstant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *     所有的枚举
 * @author yao
 *
 */
public class EnumConstant implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * 单据状态
	 */
	public static final Map<String, String> statusMap = new HashMap<String, String>();
	static {
		statusMap.put("-1", "自由");
		statusMap.put("0", "未通过");
		statusMap.put("1", "通过");
		statusMap.put("2", "进行中");
		statusMap.put("3", "提交");
	}
	/*
	 * 商务级别
	 */
	public static final Map<String, String> busiMap = new HashMap<String, String>();
	static {
		busiMap.put("0", "A");
		busiMap.put("1", "B");
		busiMap.put("2", "C");
	}
	/*
	 * 意向度
	 */
	public static final Map<String, String> intedegreeMap = new HashMap<String, String>();
	static {
		intedegreeMap.put("0", "高");
		intedegreeMap.put("1", "较高");
		intedegreeMap.put("2", "一般");
		intedegreeMap.put("3", "低");
	}
	/*
	 * 稳定级别
	 */
	public static final Map<String, String> stableMap = new HashMap<String, String>();
	static {
		stableMap.put("0", "失去");
		stableMap.put("1", "稳定");
		stableMap.put("2", "不稳定");
	}

	// 师资申请
	public static final Map<String, String> teacherapplyMap = new HashMap<String, String>();
	static {
		teacherapplyMap.put("0", "新学校初次开班");
		teacherapplyMap.put("1", "老学校低年级开班");
		teacherapplyMap.put("2", "已开班申请增派师资");
		teacherapplyMap.put("3", "特殊情况增派师资");
	}
	// 新开班对应缴费公司
	public static final Map<String, String> paycompanyMap = new HashMap<String, String>();
	static {
		paycompanyMap.put("0", "湖北词源教育投资管理有限公司");
		paycompanyMap.put("1", "海南漫语教育科技有限公司");
	}
	// 老师类型
	public static final Map<String, String> teachertypeMap = new HashMap<String, String>();
	static {
		teachertypeMap.put("0", "老师");
		teachertypeMap.put("1", "讲师");
	}
	// 区域类型
	public static final Map<String, String> areatypeMap = new HashMap<String, String>();
	static {
		areatypeMap.put("0", "东大区");
		areatypeMap.put("1", "南大区");
		areatypeMap.put("2", "西大区");
		areatypeMap.put("3", "北大区");
		areatypeMap.put("4", "湖北地区");
	}
	
	// 老师类型
	public static final Map<String, String> stutypeMap = new HashMap<String, String>();
	static {
		stutypeMap.put("0", "艺术生");
		stutypeMap.put("1", "文化生");
	
	}
	
	// 学校档案开发类型
	public static final Map<String, String> schooltypeMap = new HashMap<String, String>();
	static {
		schooltypeMap.put("0", "未开发");
		schooltypeMap.put("1", "已开发");
	
	}
}
