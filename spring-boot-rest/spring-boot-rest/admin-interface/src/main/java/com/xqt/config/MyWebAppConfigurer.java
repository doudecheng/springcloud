package com.xqt.config;

import com.xqt.interceptor.DuplicateSubmitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xqt.interceptor.AuthInterceptor;
import com.xqt.interceptor.EncodingInterceptor;
import com.xqt.interceptor.ExceptionInterceptor;
import com.xqt.util.SysConfig;


/**
 * 
 * @author Andy
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

	
	/**
	 * 配置拦截器链
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new EncodingInterceptor());
		registry.addInterceptor(new DuplicateSubmitInterceptor());
        registry.addInterceptor(new AuthInterceptor());
        registry.addInterceptor(new ExceptionInterceptor());
        super.addInterceptors(registry);
	}

	/**
	 * 自定义资源映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("file:"+ SysConfig.fileUploadPath+"/");
		super.addResourceHandlers(registry);
	}
	
	

	
	
}
