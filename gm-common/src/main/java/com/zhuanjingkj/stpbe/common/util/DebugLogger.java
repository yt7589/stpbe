package com.zhuanjingkj.stpbe.common.util;

import java.io.*;

public class DebugLogger {
    public static String LOG_FILE = "/home/ps/yantao/stp/logs/yt.log";
    private static FileOutputStream fos = null;
    private static OutputStreamWriter osw = null;

    public static void log(String msg) {
        log(LOG_FILE, msg);
    }

    public static void log(String logFile, String msg) {
        if (null == fos) {
            try {
                fos = new FileOutputStream(logFile, true); // 以追加方式打开文件
                osw = new OutputStreamWriter(fos, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try {

            osw.write(msg);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
