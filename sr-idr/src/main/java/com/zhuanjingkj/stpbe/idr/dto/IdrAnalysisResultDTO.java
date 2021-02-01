package com.zhuanjingkj.stpbe.idr.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class IdrAnalysisResultDTO extends BaseDTO {
    private long tvisJsonId;
    private String originImageUrl;
    private List<IdrAnalysisItemDTO> items;

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public String getOriginImageUrl() {
        return originImageUrl;
    }

    public void setOriginImageUrl(String originImageUrl) {
        this.originImageUrl = originImageUrl;
    }

    public List<IdrAnalysisItemDTO> getItems() {
        return items;
    }

    public void setItems(List<IdrAnalysisItemDTO> items) {
        this.items = items;
    }
}
