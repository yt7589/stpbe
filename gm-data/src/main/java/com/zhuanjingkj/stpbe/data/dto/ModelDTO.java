package com.zhuanjingkj.stpbe.data.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "t_model")
public class ModelDTO extends BaseDTO {
    @Field("model_id")
    private int modelId;
    @Field("model_code")
    private String modelCode;
    @Field("model_name")
    private String modelName;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
