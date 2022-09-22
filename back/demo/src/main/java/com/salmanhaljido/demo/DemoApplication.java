package com.salmanhaljido.demo;

import com.salmanhaljido.demo.domain.entertainment.service.EntertainmentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try{
			EntertainmentServiceImpl e = new EntertainmentServiceImpl();
			e.getData();
		}catch(Exception e){

		}
	}

}
