package com.myretail.exception;

public class SystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SystemExceptionEnum systemExceptionEnum;

	public SystemExceptionEnum getSystemExceptionEnum() {
		return systemExceptionEnum;
	}

	public void setSystemExceptionEnum(SystemExceptionEnum systemExceptionEnum) {
		this.systemExceptionEnum = systemExceptionEnum;
	}
	public SystemException(SystemExceptionEnum systemExceptionEnum) {
		super(systemExceptionEnum.getMessage());
		this.systemExceptionEnum = systemExceptionEnum;
	}

}
