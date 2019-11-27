package com.wzt.demo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * WebApi接口对象.
 *
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
public class WebResult implements Serializable {

	private static final long serialVersionUID = 372525545013064618L;
	/**
     * 结果描述
     */
    private String message;
    /**
     * 结果编码
     */
    private String code;
    /**
     * 业务数据
     */
    private Object data;

    public WebResult() {
    }

    public static WebResult ok() {
        return ok(null);
    }

    public static WebResult ok(Object data) {
        WebResult result = new WebResult();
        result.setData(data);
        result.setCode("200");
        result.setMessage("操作成功");
        return result;
    }

    public static <T> WebResult okAndListData(List<T> data) {
        return ok(data);
    }

    public static WebResult fail(String code, String message) {
        WebResult result = new WebResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
