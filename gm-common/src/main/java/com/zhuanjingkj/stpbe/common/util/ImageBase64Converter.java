package com.zhuanjingkj.stpbe.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class ImageBase64Converter {
    /**
     * 本地文件（图片、excel等）转换成Base64字符串
     *
     * @param imgPath
     */
    public static String convertFileToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            System.out.println("文件大小（字节）="+in.available());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0; i<50; i++) {
            System.out.println("raw_" + i + ": " + data[i] + "!");
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        BASE64Encoder encoder = new BASE64Encoder();
        String rawStr = encoder.encode(data);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(rawStr.getBytes())));
        String line = null;
        StringBuilder base64Str = new StringBuilder("data:image/jpg;base64,");
        try {
            while ((line = bfr.readLine()) != null) {
                base64Str.append(line);
            }
        } catch (Exception ex) {
        }
        return base64Str.toString();
    }

    public static byte[] convertBase64ToBytes(String rawB64) {
        String fileBase64String = rawB64.substring("data:image/jpg;base64,".length());
        byte[] bfile = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            bfile = decoder.decodeBuffer(fileBase64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bfile;
    }

    /**
     * 将base64字符串，生成文件
     */
    public static File convertBase64ToFile(String fileBase64String, String filePath, String fileName) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            BASE64Decoder decoder = new BASE64Decoder();
            //byte[] bfile = decoder.decodeBuffer(fileBase64String);
            byte[] bfile = convertBase64ToBytes(fileBase64String);
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}