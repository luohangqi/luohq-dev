package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter {

	@Override
	public void destroy() {
		System.out.println("MyFilter1.destroy()");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		String path = req.getContextPath();
		ServletContext servletContext = req.getServletContext();
		String servletPath = req.getServletPath();
		String requestURI = req.getRequestURI();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;
		System.out.println(basePath);
		arg0.setAttribute("nameFilter", "luoh");
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("MyFilter1.init()");
	}

}
