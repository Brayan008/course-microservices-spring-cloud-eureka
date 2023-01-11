package com.bmb.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.bmb.commons.alumnos.models.entity",
	"com.bmb.cursos.entity",
	"com.bmb.common.exams.models.entity"})
public class CursosMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosMicroserviceApplication.class, args);
	}

}
