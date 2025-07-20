package com.utp.casa_europa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CasaEuropaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaEuropaApplication.class, args);
	}

}
