package com.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rays.common.FrontCtl;

/**
 * @author Akshay joshi
 *
 */
@SpringBootApplication
//main annotation hai aur 3 annotation ka combination hai 
// 1. Springboot Configuration - Spring container se manage karta 
// 2. Enable Auto Configuration- Automatic Configuration
// 3. Component scan - pure project ke package scan karta
public class ProjectOrsApplication extends SpringBootServletInitializer {

	@Autowired
	// its use for Automatic dependency injection
	private Environment env;

	@Autowired
	FrontCtl frontCtl;

	public static void main(String[] args) {
		SpringApplication.run(ProjectOrsApplication.class, args);
	}

	/**
	 * Enables CORS to all urls
	 * 
	 * @return
	 */
	@Bean

	/* connectivity ke liye */
	public WebMvcConfigurer corsConfigurer() {

		WebMvcConfigurer w = new WebMvcConfigurer() {

			/**
			 * Add CORS [cross origin resourse sharing] ek domain se dusre domain me
			 * Req-Resp aur data transfer ka kaam krta
			 */

			// WebMvcConfigurer ki addCorsMappings method ka use kiya aur usme ..

			public void addCorsMappings(CorsRegistry registry) {

				// CorsRegistration ka obj bnaya aur uski addmapping method ka use kiya

				CorsRegistration cors = registry.addMapping("/**");
				// cors.allowedOrigins ka use kiya uske andar...
				cors.allowedOrigins("http://localhost:4200");
				cors.allowedHeaders("*");
				cors.allowCredentials(true);
			}

			/**
			 * Add Interceptors
			 */

			/*
			 * @Override public void addInterceptors(InterceptorRegistry registry) {
			 * registry.addInterceptor(frontCtl).addPathPatterns("/**").excludePathPatterns(
			 * "/Auth/**"); }
			 */

			/*
			 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
			 * registry.addResourceHandler("/**").addResourceLocations("classpath:/public/")
			 * ; }
			 * 
			 */

		};

		return w;
	}

}
