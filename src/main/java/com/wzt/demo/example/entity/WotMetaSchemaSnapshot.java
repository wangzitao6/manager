package com.wzt.demo.example.entity;

import org.springframework.data.annotation.Id;

/**
 * @Description 元数据Schema快照
 * @Author wangzt29
 * @Date 2021/3/22 17:46
 * @Version 1.0.0
 */
public class WotMetaSchemaSnapshot {

    private static final long serialVersionUID = 1L;

    @Id
    private Long   id;
    /**
     * 名称
     */
    private String name;
    /**
     * 标识
     */
    private String bizType;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否是历史版本
     */
    private Boolean history;

    private Boolean additionalProperties;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHistory() {
        return history;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

    public Boolean getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Boolean additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
