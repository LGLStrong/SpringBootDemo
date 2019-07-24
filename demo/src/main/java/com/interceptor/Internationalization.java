package com.interceptor;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Component
public class Internationalization {

	/**
	 * to determine default Locale,the default message file name should be message.properties 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		//the specific message file name will be message_XX.properties,the XX is the value which pair the key bellow(language) 
		localeChangeInterceptor.setParamName("language");//the param can be changed
		return localeChangeInterceptor;
	}
}
