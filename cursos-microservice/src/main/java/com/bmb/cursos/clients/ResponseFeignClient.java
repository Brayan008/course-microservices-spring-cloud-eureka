package com.bmb.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "answers-microservice")
public interface ResponseFeignClient {

	@GetMapping("/student/{idStudent}/exams-answered")
	public Iterable<Long> getExamsIdsWithAnswersStudent(@PathVariable Long idStudent);
}
