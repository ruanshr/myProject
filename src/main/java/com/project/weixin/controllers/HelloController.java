package com.project.weixin.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.core.ApiResult;
import com.project.core.exception.ApiException;
import com.project.weixin.model.WxAccessToken;
import com.project.weixin.service.WxAccessTokenService;

@Controller
public class HelloController{
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	WxAccessTokenService wxAccessTokenService;
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String sayHello(){
		logger.info("======================Hello World!!!================");
		
		System.out.println("这里");
		return "hello";
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ApiResult getList() throws Exception{
		wxAccessTokenService.updateAccessToken();
		ApiResult result = new ApiResult();
		List list = new ArrayList();
		list.add(new Object());
		if(list.isEmpty()){
			throw new ApiException("出现异常啦");

		}else{
			return result.setErrMessage(-2,"列表不能为空");
		}
//		result.setResults(list); 
		//return  result;
	}
}
