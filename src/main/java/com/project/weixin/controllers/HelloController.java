package com.project.weixin.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.project.core.ApiResult;
import com.project.core.exception.ApiException;
import com.project.weixin.model.WxAccessToken;
import com.project.weixin.service.WxAccessTokenService;

@Controller
public class HelloController{
	private static final Logger logger = Logger.getLogger(HelloController.class);
	@Autowired
	WxAccessTokenService wxAccessTokenService;
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String sayHello(){
		logger.info("======================Hello World!!!================");
		System.out.println("这里");
		return "hello";
	}
	@ResponseBody
	@RequestMapping(value="/list")
	public ApiResult getList() throws Exception{
		WxAccessToken accessToken = new WxAccessToken();
		accessToken.setAccessToken("21EdseEcdEE3232");
		wxAccessTokenService.insert(accessToken);
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
