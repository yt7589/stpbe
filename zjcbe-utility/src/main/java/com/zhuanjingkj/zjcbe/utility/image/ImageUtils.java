package com.zhuanjingkj.zjcbe.utility.image;

import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ImageUtils {
	/**
	 * 将base64编码字符串转换为图片
	 *
	 * @param imgStr: base64编码字符串
	 * @param path:   图片路径-具体到文件
	 * @return
	 */
	public static boolean getBase64Image(String imgStr, String path) {
		if (imgStr == null) {
			return false;
		}
		try {
			// 解密
			byte[] b = Base64.decodeBase64(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	/**
	 * @return 需要注意的是，一般插件返回的base64编码的字符串都是有一个前缀的:"data:image/jpeg;base64," , 解码之前这个得去掉。
	 * @Description: 根据图片地址转换为base64编码字符串
	 */
	public static String getBase64Url(String imageFile) {
		if (StringUtils.isBlank(imageFile)) {
			return null;
		}
		InputStream in = null;
		byte[] data = null;

		// 读取图片字节数组
		try {
			logger.info("##### imageFile=" + imageFile + "!");
			in = new FileInputStream(imageFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		if (data != null) {
			// 返回Base64编码过的字节数组字符串
			return "data:image/png;base64," + Base64.encodeBase64String(data).replaceAll("\r|\n", "");
		}
		return null;
	}

	/**
	 * @param imgStr
	 * @param imgPath
	 * @return
	 */
	public static void saveToImgByStr(String imgStr, String imgPath) {
		if (imgStr != null && imgStr.length() > 0) {
			return;
		}
		try {

			// 将字符串转换成二进制，用于显示图片
			// 将上面生成的图片格式字符串 imgStr，还原成图片显示
			byte[] imgByte = hex2byte(imgStr);
			InputStream in = new ByteArrayInputStream(imgByte);
			File file = new File(imgPath);//可以是任何图片格式.jpg,.png等
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);

			byte[] b = new byte[1024];
			int nRead = 0;
			while ((nRead = in.read(b)) != -1) {
				fos.write(b, 0, nRead);
			}
			fos.flush();
			fos.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二进制转字符串
	 *
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}

		}
		return sb.toString();
	}

	/**
	 * 字符串转二进制
	 *
	 * @param str 要转换的字符串
	 * @return 转换后的二进制数组
	 */
	public static byte[] hex2byte(String str) { // 字符串转二进制
		if (str == null) {
			return null;
		}
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1) {
			return null;
		}
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}
}