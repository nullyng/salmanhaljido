package com.salmanhaljido.demo;

import com.salmanhaljido.demo.domain.news.service.NewServiceImpl;
import com.salmanhaljido.demo.domain.news.service.NewsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

}
