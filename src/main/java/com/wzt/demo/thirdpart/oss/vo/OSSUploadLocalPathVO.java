package com.wzt.demo.thirdpart.oss.vo;

import java.io.Serializable;

/**
 * @author wangzitao
 * @create 2018-05-18 17:58
 **/

public class OSSUploadLocalPathVO implements Serializable {

    private String ossPath;

    private String ossname;

    private String localPath;


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

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
