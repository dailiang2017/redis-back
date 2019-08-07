package com.dail.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.dail.redis.mapper")
public class RedisBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisBackApplication.class, args);
	}

}
