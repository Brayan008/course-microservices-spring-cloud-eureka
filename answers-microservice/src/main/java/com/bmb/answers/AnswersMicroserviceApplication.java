package com.bmb.answers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.bmb.answers.models.entity",
	"com.bmb.commons.alumnos.models.entity",
	"com.bmb.common.exams.models.entity"})
public class AnswersMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnswersMicroserviceApplication.class, args);
	}

}
