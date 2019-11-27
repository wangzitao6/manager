package com.wzt.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

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
}
