package com.project.core.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		 ModelAndView modelView = new ModelAndView();
		if (ex instanceof ApiException) {
			FastJsonJsonView view = new FastJsonJsonView(); 
			JSONObject json = JSON.parseObject(ex.getMessage());  
			view.setAttributesMap(json);
			modelView.setView(view);
			return modelView;
		}
		modelView.setViewName("exceptions");
		return modelView;
	}

}
