package com.zhuanjingkj.zjcbe.commondata.baseDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 通用的分页返回类
 * @author: wangliying
 * @create: 2018-05-10
 **/
public class PageDTO<T> implements Serializable {

    private static final long serialVersionUID = 629951810346655809L;
    private Integer totalpage;
    private Integer totalcount;
    private Integer size;
    private Integer currentpage;

    private List<T> records;


    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public Integer getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(Integer currentpage) {
        this.currentpage = currentpage;
    }


    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
