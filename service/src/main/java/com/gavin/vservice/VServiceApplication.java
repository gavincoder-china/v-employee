package com.gavin.vservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VServiceApplication.class, args);
	}

}
