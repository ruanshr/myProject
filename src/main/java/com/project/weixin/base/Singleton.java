package com.project.weixin.base;

import java.util.Map;

import com.project.weixin.model.WxAccessToken;

public class Singleton {
	private Map<String, WxAccessToken> wxAccessTokenMap = null;
	
	private Singleton(){
		
	}
	private static class LazyHolder{
		private static final Singleton INSTANCE = new Singleton();
	}
	public static final Singleton getInstance(){
		return LazyHolder.INSTANCE;
	}
	public void setWxAccesssTokenByAppid(String appid,WxAccessToken token){
		wxAccessTokenMap.put(appid, token);
	}
	public WxAccessToken getWxAccesssTokenByAppid(String appid){
		return wxAccessTokenMap.get(appid);
	}
	
}
