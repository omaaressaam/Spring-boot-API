package com.SpringBoot.api.SpringApi.Exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime time;
	private String msg;
	private String details;
	
	public ErrorDetails(LocalDateTime time, String msg, String details) {
		super();
		this.time = time;
		this.msg = msg;
		this.details = details;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public String getMsg() {
		return msg;
	}

	public String getDetails() {
		return details;
	}
	
	
}
