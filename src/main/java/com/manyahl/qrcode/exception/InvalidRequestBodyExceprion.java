package com.manyahl.qrcode.exception;

@SuppressWarnings("serial")
public class InvalidRequestBodyExceprion extends Exception {
	
	public InvalidRequestBodyExceprion() {}
	public InvalidRequestBodyExceprion(String msg) {
		super(msg);
	}
	public InvalidRequestBodyExceprion(Throwable cause) {
		super(cause);
	}
	public InvalidRequestBodyExceprion(String msg,Throwable cause) {
		super(msg,cause);
	}
	
	

}
