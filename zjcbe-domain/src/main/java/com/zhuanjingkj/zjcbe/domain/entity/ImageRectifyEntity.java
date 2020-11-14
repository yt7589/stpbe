package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_image_rectify")
public class ImageRectifyEntity {
    @TableId(value = "image_rectify_id")
    private Long imageRectifyId;
    @TableField("imageId")
    private String imageId;
    @TableField("image_state")
    private Integer imageState;
    @TableField("user_json")
    private String userJson;
    @TableField("user_notes")
    private String userNotes;

    public Long getImageRectifyId() {
        return imageRectifyId;
    }

    public void setImageRectifyId(Long imageRectifyId) {
        this.imageRectifyId = imageRectifyId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getImageState() {
        return imageState;
    }

    public void setImageState(Integer imageState) {
        this.imageState = imageState;
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
