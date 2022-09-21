package com.salmanhaljido.demo;

import com.salmanhaljido.demo.domain.femalesafety.service.FemaleSafetyService;
import com.salmanhaljido.demo.domain.femalesafety.service.FemaleSafetyServiceImpl;
import com.salmanhaljido.demo.domain.shelter.service.ShelterServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try{
			ShelterServiceImpl f = new ShelterServiceImpl();
			f.getShelter();
		}catch(Exception e){

		}
	}

}
