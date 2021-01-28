package com.zhuanjingkj.stpbe.common.util;

import java.io.File;
import java.util.List;

public class FileUtil {
    public static void getFolderFnsRecursive(String folderName, List<String> files) {
        System.out.println("folderName=" + folderName + "!");
        File baseFo = new File(folderName);
        File currFo = null;
        if (!baseFo.isDirectory()) {
            System.out.println("step 2: " + baseFo.getAbsolutePath() + "!");
            files.add(baseFo.getAbsolutePath());
        } else {
            System.out.println("step 3");
            String[] subFiles = baseFo.list();
            System.out.println("step 4 subFiles=" + subFiles + "!");
            for (String subFile : subFiles) {
                System.out.println("step 5 subFile=" + subFile + "!");
                currFo = new File(subFile);
                if (!currFo.isDirectory()) {
                    System.out.println("step 6");
                    files.add(currFo.getAbsolutePath());
                } else {
                    System.out.println("step 7");
                    getFolderFnsRecursive(currFo.getAbsolutePath(), files);
                }
            }
        }
    }
}
