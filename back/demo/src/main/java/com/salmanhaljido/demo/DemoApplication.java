package com.salmanhaljido.demo;

import com.salmanhaljido.demo.domain.concerthall.service.ConcertHallServiceImpl;
import com.salmanhaljido.demo.domain.entertainment.service.EntertainmentServiceImpl;
import com.salmanhaljido.demo.domain.sportsfacilities.service.SportsFacilitiesServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try{
			ConcertHallServiceImpl e = new ConcertHallServiceImpl();
			e.getData();
		}catch(Exception e){

		}
	}

}
