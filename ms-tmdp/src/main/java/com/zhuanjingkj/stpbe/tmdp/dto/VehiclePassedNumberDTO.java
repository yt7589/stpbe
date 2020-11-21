package com.zhuanjingkj.stpbe.tmdp.dto;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/
public class VehiclePassedNumberDTO {

    /**
     * 今日过车量
     */
    private Integer todayPassedNumber;

    /**
     * 近七天过车量
     */
    private Integer sevenDayPassedNumber;

    /**
     * 本月过车量
     */
    private Integer currentMonthPassedNumber;

    /**
     * 日均过车量
     */
    private Integer dailyMean;

    public Integer getTodayPassedNumber() {
        return todayPassedNumber;
    }

    public void setTodayPassedNumber(Integer todayPassedNumber) {
        this.todayPassedNumber = todayPassedNumber;
    }

    public Integer getSevenDayPassedNumber() {
        return sevenDayPassedNumber;
    }

    public void setSevenDayPassedNumber(Integer sevenDayPassedNumber) {
        this.sevenDayPassedNumber = sevenDayPassedNumber;
    }

    public Integer getCurrentMonthPassedNumber() {
        return currentMonthPassedNumber;
    }

    public void setCurrentMonthPassedNumber(Integer currentMonthPassedNumber) {
        this.currentMonthPassedNumber = currentMonthPassedNumber;
    }

    public Integer getDailyMean() {
        return dailyMean;
    }

    public void setDailyMean(Integer dailyMean) {
        this.dailyMean = dailyMean;
    }
}
