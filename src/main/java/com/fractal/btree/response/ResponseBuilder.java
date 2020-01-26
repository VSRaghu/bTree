package com.fractal.btree.response;

import org.springframework.http.HttpStatus;

public class ResponseBuilder {
	
	public static <T> Response<T> successResponse(T data) {
		HttpStatus status = HttpStatus.OK;
		Response<T> response = new Response<T>();
		response.setStatus(status);
		response.setData(data);
		response.setDescription(status.getReasonPhrase());
		return response;
	}
	
	public static <T> Response<T> errorResponse(T data) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Response<T> response = new Response<T>();
		response.setStatus(status);
		response.setData(data);
		response.setDescription(status.getReasonPhrase());
		return response;
	}
}
