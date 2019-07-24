package com.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	Internationalization internationalization;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(internationalization.localeChangeInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
