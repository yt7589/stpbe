package com.zhuanjingkj.stpbe.common;


import com.zhuanjingkj.stpbe.common.util.ImageBase64Converter;

import java.text.NumberFormat;

public class AppExp {
    public static void main(String[] args) {
        System.out.println("试验工具 v0.0.1");
        int num = 123;
        String ss = String.format("%08d", num);
        System.out.println(ss);
    }
}
