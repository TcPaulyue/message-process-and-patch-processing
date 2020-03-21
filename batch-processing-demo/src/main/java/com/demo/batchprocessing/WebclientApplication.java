package com.demo.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class WebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclientApplication.class, args);
	}

}
