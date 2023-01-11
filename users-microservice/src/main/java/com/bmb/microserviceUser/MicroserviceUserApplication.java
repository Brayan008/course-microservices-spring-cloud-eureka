package com.bmb.microserviceUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.bmb.commons.alumnos.models.entity"})
public class MicroserviceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserApplication.class, args);
	}

}
