package com.project.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils {
	private static final String ENCODE = "UTF-8";
	private static final String POST_METHOD = "POST";
	private static final String GET_METHOD = "GET";
	private static final int TIMEOUT = 60;
	public  static final int HTTP_OK = 200;
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String APPLICATION_TEXT = "application/x-www-form-urlencoded";
	private static final String APPLICATION_JAVASCRIPT = "application/javascript";
	
	public static InputStream getInputStream(String path){
		InputStream inputSteam = null;
		HttpURLConnection httpURLConnection = null;
		
		try {
			URL url = new URL(path);
			if(url != null){
				httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setConnectTimeout(TIMEOUT);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod(GET_METHOD);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HTTP_OK) {
                	inputSteam = httpURLConnection.getInputStream();
                }
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputSteam;
	}
	 public InputStream getXML(String path){
	        InputStream inputStream = null;
	        try {
	            URL url = new URL(path);
	            if(url!=null){
	                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	                connection.setConnectTimeout(TIMEOUT);
	                connection.setDoInput(true);
	                connection.setRequestMethod(GET_METHOD);
	                int reponseCode = connection.getResponseCode();
	                if(reponseCode == HTTP_OK){
	                    inputStream = connection.getInputStream();
	                }
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (ProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return inputStream;
	    }
	 
	    private static String sendPost(String path, Map<String, String> params) {
	        StringBuffer buffer = new StringBuffer();
	        HttpURLConnection httpURLConnection = null;
	        if (params != null && !params.isEmpty()) {
	            try {
	                for (Map.Entry<String, String> entry : params.entrySet()) {
	                    buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), ENCODE)).append("&");
	                }
	                buffer.deleteCharAt(buffer.length()-1);
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }

	        }

	        try {
	            URL url = new URL(path);
	            if (url != null) {
	                httpURLConnection = (HttpURLConnection) url.openConnection();
	                httpURLConnection.setConnectTimeout(3000);
	                httpURLConnection.setRequestMethod(HttpUtils.POST_METHOD);
	                httpURLConnection.setDoInput(true);//�ӷ�������ȡ����
	                httpURLConnection.setDoOutput(true);//�������д����
	                byte[] data = buffer.toString().getBytes();
	                //�����������Ϊ�ı�����
	                httpURLConnection.setRequestProperty(CONTENT_TYPE, APPLICATION_TEXT);
	                httpURLConnection.setRequestProperty(CONTENT_LENGTH, String.valueOf(data.length));
	                //��ȡ���������������������
	                OutputStream outputStream = httpURLConnection.getOutputStream();
	                outputStream.write(data);


	                //��÷�������Ӧ�Ľ����״̬��
	                int responseCode = httpURLConnection.getResponseCode();
	                if (responseCode == HTTP_OK) {
	                    return changeInputStream(httpURLConnection.getInputStream());
	                }
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return "";
	    }
	    
	    
	    
	    public static String sendGET(String path, Map<String, String> params,HttpHandler handler){
	    	 StringBuffer buffer = new StringBuffer();
		        HttpURLConnection httpURLConnection = null;
		        if (params != null && !params.isEmpty()) {
		            try {
		                for (Map.Entry<String, String> entry : params.entrySet()) {
		                    buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), ENCODE)).append("&");
		                }
		                buffer.deleteCharAt(buffer.length()-1);
		            } catch (UnsupportedEncodingException e) {
		                e.printStackTrace();
		            }

		        }
		        
		        try {
		            URL url = new URL(path);
		            if (url != null) {
		                httpURLConnection = (HttpURLConnection) url.openConnection();
		                httpURLConnection.setConnectTimeout(3000);
		                httpURLConnection.setRequestMethod(HttpUtils.GET_METHOD);
		                httpURLConnection.setDoInput(true);//�ӷ�������ȡ����
		              
		                //��÷�������Ӧ�Ľ����״̬��
		                int responseCode = httpURLConnection.getResponseCode();
		                if (responseCode == HTTP_OK) {
		                    return changeInputStream(httpURLConnection.getInputStream());
		                }
		            }
		        } catch (MalformedURLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        return "";
	    	
	    }
	    
	    private static String sendJsonp(String path) {
	        StringBuffer buffer = new StringBuffer();
	        HttpURLConnection httpURLConnection = null;
	        
	        try {
	            URL url = new URL(path);
	            if (url != null) {
	                httpURLConnection = (HttpURLConnection) url.openConnection();
	                httpURLConnection.setConnectTimeout(3000);
	                httpURLConnection.setRequestMethod(HttpUtils.GET_METHOD);
	                httpURLConnection.setDoInput(true);//�ӷ�������ȡ����
	                httpURLConnection.setDoOutput(true);//�������д����
	                byte[] data = buffer.toString().getBytes();
	                //�����������Ϊ�ı�����
	                httpURLConnection.setRequestProperty(CONTENT_TYPE, APPLICATION_JAVASCRIPT);
	                httpURLConnection.setRequestProperty(CONTENT_LENGTH, String.valueOf(data.length));
	                //��ȡ���������������������
	                OutputStream outputStream = httpURLConnection.getOutputStream();
	                outputStream.write(data);

	                //��÷�������Ӧ�Ľ����״̬��
	                int responseCode = httpURLConnection.getResponseCode();
	                if (responseCode == HTTP_OK) {
	                    return changeInputStream(httpURLConnection.getInputStream());
	                }
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return "";
	    }
	    public static void sendRequest(String url, Map<String, String> params,String type,HttpHandler httpHandler){
	    	type = type == null ? POST_METHOD : type;
	    	String data = null;
	    	if("jsonp".equals(type)){
	    		System.out.println("======================");
	    		data = sendJsonp(url);
	    	}else{
	    		data = sendPost(url,null);
	    	}
	    	httpHandler.handler(data);
	    }

	    private static String changeInputStream(InputStream inputStream) {
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        String results = "";
	        if (inputStream != null) {
	            int len = 0;
	            byte[] bytes = new byte[1024];
	            try {
	                while ((len = inputStream.read(bytes))!=-1) {
	                    outputStream.write(bytes,0,len);
	                }
	                results = new String(outputStream.toByteArray(),ENCODE);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }finally {
	                try {
	                    inputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	            }
	        }
	        return results;
	    }

	    public static void saveImageToDisk() {
	        String imagePath = "";
	        InputStream inputStream = getInputStream(imagePath);
	        byte[] data = new byte[1024];
	        int len = 0;
	        FileOutputStream fileOutputStream = null;
	        try {
	            fileOutputStream = new FileOutputStream("d:/test.png");
	            while ((len = inputStream.read(data)) != -1) {
	                fileOutputStream.write(data, 0, len);

	            }
	            ;
	        } catch (IOException e) {

	        } finally {
	            if (inputStream != null) {
	                try {
	                    inputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (fileOutputStream != null) {
	                try {
	                    fileOutputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
}
