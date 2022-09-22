package com.salmanhaljido.demo;

import com.salmanhaljido.demo.domain.mart.service.MartServiceImpl;
import com.salmanhaljido.demo.domain.restaurant.service.RestaurantServiceImpl;
import com.salmanhaljido.demo.domain.sportsfacilities.service.SportsFacilitiesServiceImpl;
import com.salmanhaljido.demo.domain.theater.service.TheaterServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try{
			MartServiceImpl e = new MartServiceImpl();
			e.getData();
		}catch(Exception e){

		}
	}

}
