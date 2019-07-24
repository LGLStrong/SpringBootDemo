package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class ProductServiceInterceptorConfig implements  WebMvcConfigurer {

	@Autowired
	ProductServiceInterceptor productServiceInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(productServiceInterceptor);
		WebMvcConfigurer.super.addInterceptors(registry);
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
