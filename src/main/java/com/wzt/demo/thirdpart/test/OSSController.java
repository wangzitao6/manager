package com.wzt.demo.thirdpart.test;

import cn.hutool.core.io.FileUtil;
import com.wzt.demo.bean.ReqParamBody;
import com.wzt.demo.bean.WebResult;
import com.wzt.demo.thirdpart.oss.OSSApi;
import com.wzt.demo.thirdpart.oss.dto.OSSObjectSummaryDTO;
import com.wzt.demo.thirdpart.oss.vo.OSSDownloadObjectVO;
import com.wzt.demo.thirdpart.oss.vo.OSSUploadLocalPathVO;
import com.wzt.demo.thirdpart.oss.vo.OSSUploadStringVO;
import com.wzt.demo.thirdpart.oss.vo.OSSUploadURLVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author wangzitao
 * @create 2018-06-02 19:58
 **/

@RestController
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private OSSApi ossApi;

    @ResponseBody
    @PostMapping(value = "/post")
    public WebResult postObject(@Valid @RequestBody ReqParamBody<OSSUploadLocalPathVO> paramBody) {
        OSSUploadLocalPathVO localPathVO = paramBody.getData();
        ossApi.PostObjectBypath(localPathVO.getOssPath(), localPathVO.getOssname(), localPathVO.getLocalPath());

        return WebResult.ok();

    }

    @ResponseBody
    @PostMapping(value = "/postByUrl")
    public WebResult postObjectByUrl(@Valid @RequestBody ReqParamBody<OSSUploadURLVO> paramBody) {
        OSSUploadURLVO urlvo = paramBody.getData();
        try {
            ossApi.PostObjectByurl(urlvo.getOssPath(), urlvo.getOssname(), urlvo.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResult.ok();
    }

    /**
     * 上传文件
     *
     * @author wangzitao
     * @date 2018/4/3 15:22
     */
    @ResponseBody
    @PostMapping(value = "/postByFile")
    public WebResult importExcel(@RequestParam("image") MultipartFile multipartFile, @RequestParam("path") String path) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new RuntimeException("上传文件为空");
        }
        try {
            ossApi.PostObjectByFile(path, multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResult.ok();
    }

    @ResponseBody
    @PostMapping(value = "/downloadObject")
    public WebResult downloadObject(@Valid @RequestBody ReqParamBody<OSSDownloadObjectVO> paramBody) {
        OSSDownloadObjectVO vo = paramBody.getData();
        ossApi.getObject(vo.getOssPath(), vo.getLocalPath(), vo.getLocalName());

        return WebResult.ok();
    }

    @ResponseBody
    @PostMapping(value = "/getlists")
    public WebResult getObjects(@RequestParam("ossDirPath") String ossDirPath) {
        List<OSSObjectSummaryDTO> dtos = ossApi.getObjectsByPath(ossDirPath);

        return WebResult.ok(dtos);
    }

    @ResponseBody
    @PostMapping(value = "/getUrl")
    public WebResult getUrl(@RequestParam("ossObjectPath") String ossObjectPath) {
        URL url = ossApi.getURL(ossObjectPath);
        return WebResult.ok(url);
    }

    /**
     * 保存文件到本地
     *
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/saveJson")
    public WebResult writeOcrStrtoFile(@Valid @RequestBody ReqParamBody<OSSUploadStringVO> paramBody){
        OSSUploadStringVO vo = paramBody.getData();
        ossApi.PostObjectByString(vo.getOssPath(), vo.getOssname(), vo.getJson());
        return WebResult.ok();
    }
}
