package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DbInsertResultDTO extends BaseDTO {
    @JSONField(name = "primaryKey")
    private long primaryKey;
    @JSONField(name = "affectedRows")
    private int affectedRows;

    public DbInsertResultDTO(long primaryKey, int affectedRows) {
        this.primaryKey = primaryKey;
        this.affectedRows = affectedRows;
    }

    public long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(int affectedRows) {
        this.affectedRows = affectedRows;
    }
}
