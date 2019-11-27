package com.wzt.demo.thirdpart.oss.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangzitao
 * @create 2018-06-04 16:20
 **/

public class OSSObjectSummaryDTO  implements Serializable{

    private String bucketName;
    private String key;
    private long size;
    private Date lastModified;
    private String url;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
