package com.project.utils;

public class StringUtil {
	public static boolean isEmpty(String str){
		if(str == null){
			return true;
		}
		if("".equals(str)){
			return true;
		}
		return false;
	}
}
