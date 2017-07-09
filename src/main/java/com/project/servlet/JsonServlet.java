package com.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String appid = request.getParameter("appid");
		System.out.println("get:"+appid);
		PrintWriter out = response.getWriter();
		out.print("{\"name\":\"Tom\"}");
		out.flush();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json");
		
		String appid = request.getParameter("appid");
		System.out.println("post:"+appid);
		PrintWriter out = response.getWriter();
		out.print("{\"name\":\"Marry\",\"sex\":\"18\"}");
		out.flush();
		out.close();
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
		String method = request.getMethod();
		System.out.println("method::"+method);
		if("checkout".equalsIgnoreCase(method)){
			System.out.println("checkout:ok");
			doGet(request, response);
		}
		System.out.println("============================================");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
	}

}
