package nc.utils.commonConstant;

public class Unicode {
	/**
	 * 字符串转unicode, 【properties文件专用】
	 */
	public static String stringToUnicode(String str) {
		StringBuilder sb = new StringBuilder();
		char[] c = str.toCharArray();
		for (char value : c) {
			sb.append("\\").append("\\u").append(Integer.toHexString(value));
		}
		return sb.toString();
	}

	/**
	 * 字符串转unicode, 【java 正常使用】
	 */
	public static String stringToUnicode2(String str) {
		StringBuilder sb = new StringBuilder();
		char[] c = str.toCharArray();
		for (char value : c) {
			sb.append("\\u").append(Integer.toHexString(value));
		}
		return sb.toString();
	}

	/**
	 * unicode转字符串
	 */
	public static String unicodeToString(String unicode) {
		StringBuilder sb = new StringBuilder();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int index = Integer.parseInt(hex[i], 16);
			sb.append((char) index);
		}
		return sb.toString();
	}

}
