package com.project.weixin.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.core.ApiResult;
import com.project.core.exception.ApiException;

@Controller
public class HelloController{
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String sayHello(){
		System.out.println("======================Hello World!!!================");
		return "hello";
	}
	@ResponseBody
	@RequestMapping(value="/list")
	public ApiResult getList() throws Exception{
		ApiResult result = new ApiResult();
		List list = new ArrayList();
		list.add(new Object());
		if(list.isEmpty()){
			throw new ApiException("出现异常啦");

		}else{
			return result.setErrMessage(-2,"列表不能为空");
		}
//		result.setResults(list);
		//System.out.println("======================list!!!================");
		//return  result;
	}
}
