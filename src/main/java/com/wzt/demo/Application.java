package com.wzt.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author wangzitao
 * @create 2018-05-17 10:23
 **/
@EnableCaching
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplicationBuilder builder=new SpringApplicationBuilder(Application.class);
		builder.bannerMode(Banner.Mode.OFF);
		builder.run(args);
	}

	@Bean
	public CookieHttpSessionIdResolver cookieHttpSessionStrategy(){
		CookieHttpSessionIdResolver strategy=new CookieHttpSessionIdResolver();
		DefaultCookieSerializer cookieSerializer=new DefaultCookieSerializer();
		//去掉sessionID的httpOnly限制
		cookieSerializer.setUseHttpOnlyCookie(false);
		String sessionName = "RETAILSESSIONID_mybatis";
		//cookies名称
		cookieSerializer.setCookieName(sessionName);
		strategy.setCookieSerializer(cookieSerializer);
		return strategy;
	}
}
