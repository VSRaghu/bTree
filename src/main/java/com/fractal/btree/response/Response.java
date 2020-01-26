package com.fractal.btree.response;

import org.springframework.http.HttpStatus;

public class Response<T> {
	private HttpStatus status;
	private String description;
	private T data;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
