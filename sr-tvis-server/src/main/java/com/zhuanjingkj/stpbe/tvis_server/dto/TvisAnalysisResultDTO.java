package com.zhuanjingkj.stpbe.tvis_server.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class TvisAnalysisResultDTO extends BaseDTO {
    private long tvisJsonId;
    private String originImageUrl;
    private List<TvisAnalysisItemDTO> items;

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

    public List<TvisAnalysisItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TvisAnalysisItemDTO> items) {
        this.items = items;
    }
}
