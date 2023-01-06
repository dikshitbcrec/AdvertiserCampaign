package com.adtech.model;

import java.util.Date;

import lombok.Data;
@Data
public class ErrorModel {
	private Date timestamp;
	private String message;
	private int status;
	private String path;
	public ErrorModel(Date timestamp, String message, int status, String path) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.path = path;
	}
	
	
	

}
