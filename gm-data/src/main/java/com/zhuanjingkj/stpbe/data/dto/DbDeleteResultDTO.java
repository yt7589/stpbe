package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DbDeleteResultDTO extends BaseDTO {
    @JSONField(name = "affectedRows")
    private int affectedRows;

    public DbDeleteResultDTO(int affectedRows) {
        this.affectedRows = affectedRows;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(int affectedRows) {
        this.affectedRows = affectedRows;
    }
}
