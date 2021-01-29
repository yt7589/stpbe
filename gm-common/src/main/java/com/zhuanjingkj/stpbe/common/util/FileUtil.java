package com.zhuanjingkj.stpbe.common.util;

import java.io.File;
import java.util.List;

public class FileUtil {
    /**
     * 获取目录下包含子目录在内的所有文件列表，放在files中返回
     * @param folderName
     * @param files
     */
    public static void getFolderFnsRecursive(String folderName, List<String> files) {
        File baseFo = new File(folderName);
        File currFo = null;
        if (!baseFo.isDirectory()) {
            files.add(baseFo.getAbsolutePath());
        } else {
            String[] subFiles = baseFo.list();
            for (String subFile : subFiles) {
                currFo = new File(folderName + "/" + subFile);
                if (!currFo.isDirectory()) {
                    files.add(currFo.getAbsolutePath());
                } else {
                    getFolderFnsRecursive(currFo.getAbsolutePath(), files);
                }
            }
        }
    }
}
