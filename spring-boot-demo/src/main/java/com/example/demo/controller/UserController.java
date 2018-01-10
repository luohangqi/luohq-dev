package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;

@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/query/user")
	public String queryByPage(Model model, HttpServletRequest req) {
	
		String nameFilter = req.getAttribute("nameFilter").toString();
		System.out.println("过滤器设置：" + nameFilter);
//		String nameInterceptor = req.getAttribute("nameInterceptor").toString();
//		System.out.println("拦截器设置：" + nameInterceptor);
		logger.info("queryByPage : {}", model);
		
		return "user";
		
	}
	
	@RequestMapping(value = "/query/home")
	public String queryHome(Model model, HttpServletRequest req) {
	
		String nameFilter = req.getAttribute("nameFilter").toString();
		System.out.println("过滤器设置：" + nameFilter);
		/*String nameInterceptor = req.getAttribute("nameInterceptor").toString();
		System.out.println("拦截器设置：" + nameInterceptor);*/
		logger.info("queryHome : {}", model);
		
		return "forward:/query/fwd";
		
	}
	
	@RequestMapping(value = "/query/fwd")
	public String fwd(Model model, HttpServletRequest req) {
	
		logger.info("fwd : {}", model);
		
		return "index";
		
	}
	
	@RequestMapping(value = "/save/user")
	public String saveUser(Model model, @RequestBody List<User> users, HttpServletRequest req) {
		String nameFilter = req.getAttribute("nameFilter").toString();
		System.out.println("/save/user过滤器设置：" + nameFilter);
		String nameInterceptor = req.getAttribute("nameInterceptor").toString();
		System.out.println("/save/user拦截器设置：" + nameInterceptor);
		logger.info("saveUser : {}", users);
		
		return "user";
		
	}
}
