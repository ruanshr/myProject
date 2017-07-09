package com.project.core.exception;

import java.util.Map;

import com.project.core.ApiResult;

public class ApiException extends RuntimeException{

	private ApiResult errResult;
	public ApiException(String errMessage) { 
		errResult = new ApiResult();
		errResult.setErrMessage(-99,errMessage);
	}
	public ApiException(int errCode,String errMessage) { 
		errResult = new ApiResult();
		errResult.setErrMessage(errCode,errMessage);
	}

	@Override
	public String getMessage() {
		return errResult.toString();
	}

	@Override
	public String toString() {
		return errResult.toString();
	}

	@Override
	public void printStackTrace() {
		
	}

}
