package com.zhuanjingkj.zjcbe.utility.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;

/**
 * @description: des加密解密
 * @author: zhiwe
 * @create: 2019-03-20 14:13
 **/
public class DESUtil {

	/**
	 * 密码DES加密
	 *
	 * @param encryptString 待加密字符串
	 * @param encryptKey    密钥
	 * @return 密文
	 */
	public static String encryptDES(String encryptString, String encryptKey) {
		try {
			byte[] key = encryptKey.getBytes("UTF-8");
			DESKeySpec dks = new DESKeySpec(key);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

			IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

			byte[] src = encryptString.getBytes("UTF-8");
			src = cipher.doFinal(src);

			Base64 base64 = new Base64();
			return new String(base64.encode(src));
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 密码DES加密
	 *
	 * @param encryptString 待加密字符串（账号密码）
	 * @param encryptKey    密钥
	 * @param encryptIV     偏移量
	 * @return 密文
	 */
	public static String encryptDES(String encryptString, String encryptKey, String encryptIV) {
		try {
			byte[] key = encryptKey.getBytes("UTF-8");
			DESKeySpec dks = new DESKeySpec(key);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

			IvParameterSpec iv = new IvParameterSpec(encryptIV.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

			byte[] src = encryptString.getBytes("UTF-8");
			src = cipher.doFinal(src);

			Base64 base64 = new Base64();
			return new String(base64.encode(src));
		} catch (Exception e) {
			return "";
		}
	}

	public static String decryptDES(String data, String key, String encryptIV) throws Exception {

		Base64 base64 = new Base64();

		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(key.getBytes("UTF-8"));
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(encryptIV.getBytes("UTF-8"));
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		// 真正开始解密操作
		byte[] bt = cipher.doFinal(base64.decode(data.getBytes("UTF-8")));

		String strs = new String(bt);
		return strs;
	}

	public static String decryptDES(String data, String key) throws Exception {

		Base64 base64 = new Base64();

		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(key.getBytes("UTF-8"));
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		// 真正开始解密操作
		byte[] bt = cipher.doFinal(base64.decode(data.getBytes("UTF-8")));

		String strs = new String(bt);
		return strs;
	}

	public static String encryptBase64ToHexString(String data, String key) throws Exception {
		String strs = encryptDES(data, key);
		return toHexString(strs.getBytes(Charset.forName("utf-8")));
	}

	public static String toHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2) {
				plainText = "0" + plainText;
			}
			hexString.append(plainText);
		}
		return hexString.toString().toUpperCase();
	}

}
