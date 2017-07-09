package com.xiaomi.jsonp;

import com.project.utils.http.HttpHandler;
import com.project.utils.http.HttpUtils;



public class JsonpHttp {
	
	public static final void main(String[]args){
		String url = "http://127.0.0.1:2222/jsonp?jsonpCallback=callback&r=1457966653817";
		HttpResult  results = new HttpResult();
		HttpUtils.sendRequest(url, null, "jsonp", results);
	}
	
}
class HttpResult implements HttpHandler{

	public void handler(String result) {
		
		System.out.println(result);
	}
	
}