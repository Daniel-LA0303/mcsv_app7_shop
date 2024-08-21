package com.mx.mcsv.service_bike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceBikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBikeApplication.class, args);
	}

}
