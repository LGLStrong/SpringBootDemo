package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableEurekaServer
@EnableSwagger2
@EnableCaching
@EnableSpringDataWebSupport
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
    @Bean
    public Docket productApi() {
    	/*
    	 * the package that user interfaces which you will expose/swagger located in
    	 * then hit the URL(/swagger-ui.html) in your Web browser and see the Swagger API functionalities.
    	 */
    	String basePackage = "com.controller"; 
       return new Docket(DocumentationType.SWAGGER_2).select()
          .apis(RequestHandlerSelectors.basePackage(basePackage)).build();
    }
    
//    /**
//     * set the CORS configuration globally
//     * @return
//     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//       return new WebMvcConfigurer() {
//          @Override
//          public void addCorsMappings(CorsRegistry registry) {
//             registry.addMapping("/products").allowedOrigins("http://localhost:8080");
//          }
//       };
//    }
}