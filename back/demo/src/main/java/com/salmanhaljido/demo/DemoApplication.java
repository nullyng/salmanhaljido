package com.salmanhaljido.demo;

import com.salmanhaljido.demo.domain.caraccident.service.CarAccidentServiceImpl;
import com.salmanhaljido.demo.domain.crime.service.CrimeServiceImpl;
import com.salmanhaljido.demo.domain.drugstore.service.DrugStoreServiceImpl;
import com.salmanhaljido.demo.domain.hospital.service.HospitalServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		try{
			CrimeServiceImpl s = new CrimeServiceImpl();
			s.getCrime();
		}catch(Exception e ){
			e.printStackTrace();
		}

	}

}
