package com.bmb.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulMicroserviceApplication.class, args);
	}

}
