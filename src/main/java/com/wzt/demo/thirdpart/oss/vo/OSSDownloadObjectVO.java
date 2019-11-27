package com.wzt.demo.thirdpart.oss.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wangzitao
 * @create 2018-05-18 17:58
 **/

public class OSSDownloadObjectVO implements Serializable {

    private String ossPath;

    private String localPath;

    @NotNull(message = "本地文件名不能为空")
    private String localName;

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}
