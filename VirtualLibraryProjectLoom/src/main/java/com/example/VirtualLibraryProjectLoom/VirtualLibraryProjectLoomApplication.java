package com.example.VirtualLibraryProjectLoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VirtualLibraryProjectLoomApplication {
	public static void main(String[] args) {
		SpringApplication.run(VirtualLibraryProjectLoomApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
