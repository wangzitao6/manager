package com.wzt.demo.utils;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http连接工具类
 *
 * @author wujianhao
 * @date 2018/9/11 14:46
 */
public class HttpClientUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String doGet(String url) {
        return doGet(url, null);
    }

    private static String doGet(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return doGet(httpClient, url, param);
    }

    public static String doGetSSL(String url) {
        return doGetSSL(url, null);
    }

    public static String doGetSSL(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = createSSLClientDefault();
        return doGet(httpClient, url, param);
    }

    private static String doGet(CloseableHttpClient httpclient, String url, Map<String, String> param) {
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            LOG.error("异常:{}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                LOG.error("异常:{}", e);
            }
        }
        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LOG.error("异常:{}", e);
            }
        }

        return resultString;
    }


    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return doPostJson(httpClient, url, json);
    }

    public static String doPostJsonSSL(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = createSSLClientDefault();
        return doPostJson(httpClient, url, json);
    }

    private static String doPostJson(CloseableHttpClient httpClient, String url, String json) {
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                LOG.error("异常:{}", e);
            }
        }
        return resultString;
    }

    public static String[] doPostJsonCookie(String url, String json, String cookie) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return doPostJsonCookie(httpClient, url, json, cookie);
    }

    public static String[] doPostJsonCookie(CloseableHttpClient httpClient, String url, String json, String cookie) {
        CloseableHttpResponse response = null;
        String resultString = "";
        String[] results = new String[2];
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Content-Type", "text/json;charset=UTF-8");
            //当cookie不为空时，在请求头设置cookie
            if (null != cookie) {
                httpPost.setHeader("Cookie", cookie);
            }
            httpPost.setHeader("Charset", "UTF-8");
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            Header[] headers = response.getHeaders("set-cookie");
            if (headers.length > 0) {
                String value = headers[0].getValue();

                if (value != null) {
                    cookie = value.substring(0, value.indexOf(";"));
                } else {
                    cookie = "";
                }
                results[0]=cookie;
            }
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            results[1]=resultString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LOG.error("异常:{}", e);
            }
        }
        return results;

    }


    /**
     * 创建一个可以访问Https类型URL的工具类，返回一个CloseableHttpClient实例
     */
    private static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        //信任所有
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            LOG.error("异常:{}", e);
        }
        return HttpClients.createDefault();
    }
}