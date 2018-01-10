package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfiguration {
	
	@Bean
//	@Order(1)
    public FilterRegistrationBean filterDemo4Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new MyFilter1());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("myFilter1");
        //是否自动注册 false 取消Filter的自动注册
//        registration.setEnabled(false);

        return registration;
    }
	
}
