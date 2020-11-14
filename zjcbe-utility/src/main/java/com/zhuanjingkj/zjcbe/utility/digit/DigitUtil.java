package com.zhuanjingkj.zjcbe.utility.digit;

import java.math.BigDecimal;

/**
 * @description:数字帮助类
 * @author: liuxiaogang.bj
 * @create: 2019/6/18 11:13
 **/
public class DigitUtil {

    /**
     * 四舍五入保留小数据
     *
     * @param digits 小数位数
     */
    public static double roundDouble(double d, int digits) {
        BigDecimal bg = BigDecimal.valueOf(d).setScale(digits, BigDecimal.ROUND_HALF_UP);
        return bg.doubleValue();
    }
}
