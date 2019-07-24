package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class SimpleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("Remote Host:" + request.getRemoteHost());
		System.out.println("Remote Address:" + request.getRemoteAddr());
		System.out.println("Remote Path:"+((HttpServletRequest) request).getRequestURL());
		filterChain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		System.out.println("Simple Filter Destroy");
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Simple Filter Init");
		Filter.super.init(filterConfig);
	}

}
