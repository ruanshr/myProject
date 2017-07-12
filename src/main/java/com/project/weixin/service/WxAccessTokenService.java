package com.project.weixin.service;

import java.util.List;

import com.project.weixin.model.WxAccessToken;

public interface WxAccessTokenService {
	
	public List<WxAccessToken> findAll();
	
	public int insert(WxAccessToken token);
	
	public WxAccessToken fleshAccessToken();
	
	public void updateAccessToken();
}
