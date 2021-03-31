package com.wzt.demo.bean;

import java.io.Serializable;

public class Resp<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 结果描述
	 */
	private String message;
	/**
	 * 结果编码
	 */
	private String code;

	public T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
