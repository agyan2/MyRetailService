package com.myretail.model;
/**
 * Error Response
 * @author agyan2
 *
 */
public class ErrorResponse {

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	
	
	public ErrorResponse() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponse [code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
