package com.project.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class ApiResult {
	private String errMsg = "调用成功";
	private int errCode = 0;
	private Object results = (Object) new ArrayList();;  
	public ApiResult(){ 
		
	}
	public ApiResult(int errCode,String errMsg){ 
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	public Map<String,Object> obtainResultMap(){ 
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("errCode",this.errCode);
		resultMap.put("errNsg",this.errMsg);
		resultMap.put("results",this.results);
		return resultMap;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public Object getResults() {
		return this.results;
	}
 

	public void setResults(Object object) {
		this.results = object;
	}
	
	public void setResults(List<?> list) {
		this.results = (Object)list;
	}
	public ApiResult setErrMessage(int errCode,String errMessage){
		this.setErrCode(errCode);
		this.setErrMsg(errMessage);
		return this;
	}
	public ApiResult setSucMessage(String sucMessage,Object o){
		this.setErrMsg(sucMessage);
		this.setResults(o);
		return this;
	}
	public ApiResult setSucMessage(Object o){ 
		this.setResults(o);
		return this;
	}

	@Override
	public String toString() { 
		return JSON.toJSONString(this);
	}
	
}
