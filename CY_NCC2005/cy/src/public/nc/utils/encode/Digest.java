package nc.utils.encode;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5正向加密
 * @author yao
 */
public class Digest {
	
	
	public static void main(String[] args) {
		try {
			String pw = Digest.md5AsHex("123qwe");//4c99d4392b383b9e241bc09af30fe13d
			
			String pw1 = Digest.md5AsHex("1234qwer");//05a8b64ea9bd3fb4aba28281f3600ce5
			
		} catch (Exception e) {
			
		}
	}

	// 用来将字节转换成 16 进制表示的字符
	public static final char[] chars = new char[] { '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static final String SLAT = "@#$%^&*&^%$#@";

	/**
	 * 获取字符串MD5
	 * 
	 * @param message
	 *            字符串
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5AsHex(String message)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return md5AsHex((message + SLAT).getBytes("UTF-8"));
	}

	/**
	 * 获取字符串MD5
	 * 
	 * @param message
	 *            字符串
	 * @param split
	 *            拼接符
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5AsHex(String message, String split)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return md5AsHex((message + split).getBytes("UTF-8"));
	}

	/**
	 * 获取 byte[] MD5
	 * 
	 * @param bytes
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5AsHex(byte[] bytes) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("md5");
		byte[] digest = md5.digest(bytes);
		StringBuilder sb = new StringBuilder();
		// 16进制字符串
		for (byte b : digest) {
			sb.append(chars[(b >> 4) & 15]);
			sb.append(chars[b & 15]);
		}
		return sb.toString();
	}
}
