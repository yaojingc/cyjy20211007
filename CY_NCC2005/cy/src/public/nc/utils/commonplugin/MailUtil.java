package nc.utils.commonplugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 邮箱合法性校验工具类
 * 
 * @author tanrg
 *
 */
public class MailUtil {
	
	public final static String regExa = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
	
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		Pattern p = Pattern.compile(regExa);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		String msg = "159071@qq.com)";
		System.out.println(isEmail(msg));
	}
}
