package com.wzt.demo.thirdpart.oss.vo;

import java.io.Serializable;

/**
 * @author wangzitao
 * @create 2018-05-18 17:58
 **/

public class OSSUploadURLVO implements Serializable {

    private String ossPath;

    private String ossname;

    private String url;


    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }

    public String getOssname() {
        return ossname;
    }

    public void setOssname(String ossname) {
        this.ossname = ossname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
