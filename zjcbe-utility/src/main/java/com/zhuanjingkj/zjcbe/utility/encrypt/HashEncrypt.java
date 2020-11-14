package com.zhuanjingkj.zjcbe.utility.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @description: 哈希加密类
 * @author: wangliying
 * @create: 2018-08-08
 **/
public class HashEncrypt {

    private static Logger logger = LoggerFactory.getLogger(HashEncrypt.class);
    private static final String ENCODING_TYPE = "utf-8";

    public static String GetMD5(String str) {
        return GetMD5(str, ENCODING_TYPE, true);
    }

    public static String GetMD5(String str, String encodingType, boolean isUpper) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(encodingType));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            if (isUpper) {
                return buf.toString().toUpperCase();// 32位的加密
            } else {
                return buf.toString();
            }
            //buf.toString().substring(8, 24);// 16位的加密

        } catch (Exception e) {

            logger.error("GetMD5 error:" + e.getMessage(), e);
            return "";
        }

    }
}
