package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan("com.bside.BSIDE.**")
@MapperScan(basePackages = "com.bside.BSIDE.**.persistence")
@SpringBootApplication(scanBasePackages = "com.bside.BSIDE.**")
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Start 2");
	}

}
