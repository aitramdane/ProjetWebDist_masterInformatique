package com.example.discoverysevrer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoverySevrerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoverySevrerApplication.class, args);
	}

}
