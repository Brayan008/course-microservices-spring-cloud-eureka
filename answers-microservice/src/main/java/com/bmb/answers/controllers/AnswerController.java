package com.bmb.answers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bmb.answers.models.entity.Answer;
import com.bmb.answers.services.AnswerService;

@RestController
public class AnswerController {

	@Autowired
	private AnswerService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Iterable<Answer> answers){
		
		Iterable<Answer> dbAnswers = service.saveAll(answers);
		return ResponseEntity.status(HttpStatus.CREATED).body(dbAnswers);
	}
	
	@GetMapping("/student/{idStudent}/exam/{idExam}")
	public ResponseEntity<?> getAnswerToStudentAndExam(@PathVariable Long idStudent, @PathVariable Long idExam){
		Iterable<Answer> answers = service.findAnswerByStudentByExam(idStudent, idExam);
		
		return ResponseEntity.ok(answers);
	}
	
	@GetMapping("/student/{idStudent}/exams-answered")
	public ResponseEntity<?> getExamsIdsWithAnswersStudent(@PathVariable Long idStudent){
		Iterable<Long> idExams = service.findExamsIdsWithAnswersByStudent(idStudent);
		
		return ResponseEntity.ok(idExams);
	}
}
