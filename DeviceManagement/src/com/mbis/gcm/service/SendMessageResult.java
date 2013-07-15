package com.mbis.gcm.service;

public class SendMessageResult {
	boolean success = false;
	String	message = "";
	
	public SendMessageResult(boolean succ, String msg) {
		success = succ;
		message	= msg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
