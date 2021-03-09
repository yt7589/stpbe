package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.Arrays;
import java.util.List;

public class FileExpDTO extends BaseDTO {
    @JSONField(name = "fileName")
    private String fileName;
    @JSONField(name = "title")
    private String title; //sheet名称
    @JSONField(name = "columns")
    private String[] columns;
    @JSONField(name = "list")
    private List<Object> list;
    @JSONField(name = "path")
    private String path;

    public FileExpDTO(String fileName, String title, String[] columns, List<?> list) {
        this.fileName = fileName;
        this.title = title;
        this.columns = columns;
        this.list = Arrays.asList(list.toArray());;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
