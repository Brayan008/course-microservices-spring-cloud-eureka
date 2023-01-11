package com.bmb.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaMiceroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaMiceroservicesApplication.class, args);
	}

}
