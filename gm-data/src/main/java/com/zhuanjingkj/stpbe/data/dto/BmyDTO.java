package com.zhuanjingkj.stpbe.data.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "t_bmy")
public class BmyDTO extends BaseDTO {
    @Field("bmy_id")
    private int bmyId;
    @Field("bmy_code")
    private String bmyCode;
    @Field("bmy_name")
    private String bmyName;
    private String yearName;

    public int getBmyId() {
        return bmyId;
    }

    public void setBmyId(int bmyId) {
        this.bmyId = bmyId;
    }

    public String getBmyCode() {
        return bmyCode;
    }

    public void setBmyCode(String bmyCode) {
        this.bmyCode = bmyCode;
    }

    public String getBmyName() {
        return bmyName;
    }

    public void setBmyName(String bmyName) {
        this.bmyName = bmyName;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }
}
