package com.wzt.demo.thirdpart.oss;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.wzt.demo.thirdpart.oss.dto.OSSObjectSummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OSS接口
 *
 * @author wangzitao
 * @create 2018-06-02 10:28
 */
@Component("ossApi")
public class OSSApi {

    private static final Logger LOG = LoggerFactory.getLogger(OSSApi.class);

    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 本地上传文件
     *
     * @param ossPath   上传到oss的文件夹路径
     * @param ossname   上传oss后文件名
     * @param localPath 本地文件路径
     */
    public void PostObjectBypath(String ossPath, String ossname, String localPath) {
        OSSClient ossClient = new OSSClient(OSSConstants.EndPoint.HUADONG_ONE, accessKeyId, accessKeySecret);
        try {
            // 上传文件。
            ossClient.putObject(OSSConstants.BucketName.WANG_DEMO, ossPath + ossname, new File(localPath));
        } catch (Exception e) {
            throw new OSSApiException("上传文件出现异常: \n" + e.getMessage());
        } finally {
            // 关闭Client。
            ossClient.shutdown();
        }

    }

    /**
     * 从网络上上传文件
     *
     * @param ossPath 上传到oss的文件夹路径
     * @param ossname 上传oss后文件名
     * @param url     文件的网络url
     */
    public void PostObjectByurl(String ossPath, String ossname, String url) throws Exception {
        OSSClient ossClient = new OSSClient(OSSConstants.EndPoint.HUADONG_ONE, accessKeyId, accessKeySecret);
        try {
            // 上传网络流。
            InputStream inputStream = new URL(url).openStream();
            ossClient.putObject(OSSConstants.BucketName.WANG_DEMO, ossPath + ossname, inputStream);
        } catch (Exception e) {
            throw new OSSApiException("上传文件出现异常: \n" + e.getMessage());
        } finally {
            // 关闭Client。
            ossClient.shutdown();
        }

    }

    /**
     * 本地上传文件
     *
     * @param ossPath       上传到oss的文件夹路径
     * @param multipartFile 上传的文件
     */
    public void PostObjectByFile(String ossPath, MultipartFile multipartFile) {
        OSSClient ossClient = new OSSClient(OSSConstants.EndPoint.HUADONG_ONE, accessKeyId, accessKeySecret);
        // 上传文件流。
        String originalFilename = multipartFile.getOriginalFilename();

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            ossClient.putObject(OSSConstants.BucketName.WANG_DEMO, ossPath + originalFilename, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new OSSApiException("上传文件出现异常: \n" + e.getMessage());
        } finally {
            // 关闭Client。
            ossClient.shutdown();
        }

    }

    /**
     * 从网络上上传文件
     *
     * @param ossPath   oss中的文件夹路径（包含文件名）
     * @param localPath 下载到本地的地址
     * @param localName 下载到本地文件名
     */
    public void getObject(String ossPath, String localPath, String localName) {
        OSSClient ossClient = new OSSClient(OSSConstants.EndPoint.HUADONG_ONE, accessKeyId, accessKeySecret);
        try {
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ObjectMetadata objectMetadata = ossClient.getObject(new GetObjectRequest(OSSConstants.BucketName.WANG_DEMO, ossPath),
                    new File(localPath + localName));
        } catch (Exception e) {
            throw new OSSApiException("上传文件出现异常: \n" + e.getMessage());
        } finally {
            // 关闭Client。
            ossClient.shutdown();
        }
    }

    /**
     * 获取路径下的所有文件列表
     *
     * @param ossDirPath oss中的文件夹路径
     */
    public List<OSSObjectSummaryDTO> getObjectsByPath(String ossDirPath) {
        OSSClient ossClient = new OSSClient(OSSConstants.EndPoint.HUADONG_ONE, accessKeyId, accessKeySecret);
        ObjectListing listing = null;
        List<OSSObjectSummaryDTO> dtos = null;
        try {
            // 构造ListObjectsRequest请求。
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(OSSConstants.BucketName.WANG_DEMO);
            //设置 Prefix 参数来获取某个目录ossDirPath下的所有文件
            listObjectsRequest.setPrefix(ossDirPath);
            // 递归列出images目录下的所有文件。
            listing = ossClient.listObjects(listObjectsRequest);
            dtos = new ArrayList<>();
            for (OSSObjectSummary summary : listing.getObjectSummaries()) {
                OSSObjectSummaryDTO summaryDTO = new OSSObjectSummaryDTO();
                URL url = getURL(summary.getKey());
                summaryDTO.setBucketName(summary.getBucketName());
                summaryDTO.setKey(summary.getKey());
                summaryDTO.setSize(summary.getSize());
                summaryDTO.setLastModified(summary.getLastModified());
                summaryDTO.setUrl(url.toString());
                dtos.add(summaryDTO);
            }
        } catch (Exception e) {
            throw new OSSApiException("OSS获取路径下的文件列表: \n" + e.getMessage());
        } finally {
            // 关闭Client。
            ossClient.shutdown();
        }

        return dtos;

    }

    /**
     * 获取文件的URL
     *
     * @param ossObjectPath oss中的文件路径
     */
    public URL getURL(String ossObjectPath) {
        OSSClient ossClient = new OSSClient(OSSConstants.EndPoint.HUADONG_ONE, accessKeyId, accessKeySecret);
        URL url = null;
        try {
            //设置url一年后过期
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 24 * 365);
            url = ossClient.generatePresignedUrl(OSSConstants.BucketName.WANG_DEMO, ossObjectPath, expiration);
        } catch (Exception e) {
            throw new OSSApiException("OSS获取URL出现异常: \n" + e.getMessage());
        } finally {
            // 关闭Client。
            ossClient.shutdown();
        }
        return url;
    }


}
