package com.project.weixin.service.impl;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.utils.http.HttpUtils;
import com.project.weixin.base.WxConstant;
import com.project.weixin.dao.WxAccessTokenMapper;
import com.project.weixin.model.WxAccessToken;
import com.project.weixin.service.WxAccessTokenService;
@Service
public class WxAccessTokenServiceImpl implements WxAccessTokenService {
	private static final Logger logger = LoggerFactory.getLogger(WxAccessTokenServiceImpl.class);
	@Autowired
	private WxAccessTokenMapper wxAccessTokenMapper;
	public List<WxAccessToken> findAll() { 
		return null;
	}
	public int insert(WxAccessToken token) {
		return wxAccessTokenMapper.insertSelective(token);
	}
	public WxAccessToken fleshAccessToken() {
		WxAccessToken wxAccessToken = new WxAccessToken();
		String url = WxConstant.tokenUrl;
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("grant_type", "client_credential");
		jsonParam.put("appid", WxConstant.appid);
		jsonParam.put("secret", WxConstant.secret);
		JSONObject result = HttpUtils.httpGet(url, jsonParam); 
		if(null !=result && result.getIntValue("errcode")==0){
			String accessToken = result.getString("access_token");
			int expiresIn = result.getIntValue("expires_in");
			wxAccessToken.setAccessToken(accessToken);
			wxAccessToken.setExpiresIn(expiresIn); 
			
		}
		return wxAccessToken;
	}
	public void updateAccessToken() {
		WxAccessToken accessToken = this.fleshAccessToken();
		
	}

}
