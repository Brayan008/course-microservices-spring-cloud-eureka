package com.bmb.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.bmb.common.exams.models.entity"})
public class ExamsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsMicroserviceApplication.class, args);
	}

}
