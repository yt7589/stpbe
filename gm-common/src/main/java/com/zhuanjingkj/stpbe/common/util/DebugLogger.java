package com.zhuanjingkj.stpbe.common.util;

import java.io.*;

public class DebugLogger {
    public static String LOG_FILE = "/home/ps/yantao/stp/logs/yt.log";
    public static void log(String msg) {
        log(LOG_FILE, msg);
    }

    public static void log(String logFile, String msg) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(logFile, true); // 以追加方式打开文件
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(msg);
            osw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
