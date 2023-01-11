package com.bmb.exams.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bmb.common.exams.models.entity.Exam;
import com.bmb.commons.controllers.CommonController;
import com.bmb.exams.services.ExamService;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class ExamController extends CommonController<Exam, ExamService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit (@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
    		return this.validate(result);
    	}
		
		Optional<Exam> ex = service.findById(id);
		
		if(!ex.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Exam examDb = ex.get();
		examDb.setName(exam.getName());
		
		/*
		List<Question> queDelete = new ArrayList<>();
	
		examDb.getQuestions().forEach(pdb->{
			if(!exam.getQuestions().contains(pdb)) {
				queDelete.add(pdb);
			}
		});
		
		queDelete.forEach(q -> {
			examDb.removeQuestion(q);
		});*/
		
		examDb.getQuestions()
		.stream()
		.filter(pdb -> !exam.getQuestions().contains(pdb))
		.forEach(examDb::removeQuestion);
		
		
		
		examDb.setQuestions(exam.getQuestions());
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDb));
	}
	
	@GetMapping("/filter/{term}")
	public ResponseEntity<?> filter(@PathVariable String term){
		
		return ResponseEntity.ok(service.findByName(term));
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<?> listSubjects(){
		return ResponseEntity.ok(service.findAllSubjects());
	}
}
