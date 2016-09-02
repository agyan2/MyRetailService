package com.myretail.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

public enum SystemExceptionEnum {
	SUCCESS(0,"SUCCESS",OK),
    PRODUCTNAME_NOT_FOUND(1,"ProductName not found",NOT_FOUND), 
    
    PRODUCTNAMES_NOT_FOUND(2,"ProductNames list not found",NOT_FOUND),  
    PRODUCTPRICE_NOT_FOUND   (3,"Product Price not found",NOT_FOUND),   
    PRODUCTPRICE_NOT_UPDATED   (4,"Product Price not updated",NOT_FOUND), 
    PRODUCTNAME_ERROR(5,"Error in Retrieveing Product Name",INTERNAL_SERVER_ERROR), 
    PRODUCTNAMES_ERROR(6,"Error in Retrieveing ProductName Collection",INTERNAL_SERVER_ERROR), 
    PRODUCTPRICE_RETRIEVE_ERROR(7,"Error in Retrieveing Price",INTERNAL_SERVER_ERROR), 
    PRODUCTPRICE_UPDATE_ERROR(8,"Error in updating Price",INTERNAL_SERVER_ERROR)   
    ; // semicolon needed when fields / methods follow


    private int code;
    private String message;
    private HttpStatus httpStatus;
    
    private SystemExceptionEnum(int code, String message,HttpStatus httpStatus) {
        this.code = code;
        this.message=message;
        this.httpStatus=httpStatus;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
    
	public HttpStatus getHttpStatus()
	{
		return this.httpStatus;
	}
}
