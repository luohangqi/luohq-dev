package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.annotation.myAnnotation;
import com.example.demo.entity.Hello;
import com.example.demo.entity.User;


@RestController
@RequestMapping(value = "/rest")
public class MyRestController {

	private static final String cookieName="hh";
	
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name) {
		System.out.println("hello" + name);
        return "Hello " + name;
    }

	@myAnnotation(description = "cookie测试")
	@RequestMapping(value = "/cookie", method = RequestMethod.GET)
    @ResponseBody
    public String cookie(HttpServletRequest req, HttpServletResponse response) {
		HttpSession session = req.getSession(true);
		return "Hello ";
    }
	
	private void setCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, "testcookie");
		response.addCookie(cookie);
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public String testJson(@RequestBody Hello hello) {
		System.out.println(hello);
		return "Hello ";
    }
	
	@RequestMapping(value = "/json2", method = RequestMethod.POST)
    @ResponseBody
    public String testJson2(@RequestBody String json) {
		Hello parseObject = JSON.parseObject(json, Hello.class);
		System.out.println(parseObject);
		return "Hello ";
    }
}
