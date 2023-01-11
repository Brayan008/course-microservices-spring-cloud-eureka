package com.bmb.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bmb.common.exams.models.entity.Exam;
import com.bmb.commons.alumnos.models.entity.Student;
import com.bmb.commons.controllers.CommonController;
import com.bmb.cursos.entity.Curso;
import com.bmb.cursos.services.CursoService;

@RestController
public class CursoController extends CommonController<Curso, CursoService>{
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("balanceador", balanceadorTest);
		res.put("cursos", service.findAll());
		
		return ResponseEntity.ok(res);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Curso curso,BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
    		return this.validate(result);
    	}
		
		Optional<Curso> cu = this.service.findById(id);
		if(!cu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso = cu.get();
		dbCurso.setName(curso.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@PutMapping("/{id}/asign-students")
	public ResponseEntity<?> asignStudents(@RequestBody List<Student> students, @PathVariable Long id){
		Optional<Curso> cu = this.service.findById(id);
		if(!cu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso = cu.get();
		
		students.forEach(s -> {
			dbCurso.addStudent(s);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@PutMapping("/{id}/delete-student")
	public ResponseEntity<?> deleteStudent(@RequestBody Student student, @PathVariable Long id){
		Optional<Curso> cu = this.service.findById(id);
		if(!cu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso = cu.get();
		
		dbCurso.removeStudent(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> findCursoById(@PathVariable Long id){
		Curso cu = service.findCursoByAlumnoId(id);
		
		if(cu != null) {
			List<Long> examsId = (List<Long>) service.getExamsIdsWithAnswersStudent(id);
			
			List<Exam> exams = cu.getExams().stream().map(exam -> {
				if(examsId.contains(exam.getId())) {
					exam.setAnswered(true);
				}
				return exam;
			}).collect(Collectors.toList());
			
			cu.setExams(exams);
		}
		return ResponseEntity.ok(cu);
	}
	
	@PutMapping("/{id}/asign-exams")
	public ResponseEntity<?> asignExams(@RequestBody List<Exam> exams, @PathVariable Long id){
		Optional<Curso> cu = this.service.findById(id);
		if(!cu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso = cu.get();
		
		exams.forEach(e -> {
			dbCurso.addExam(e);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@PutMapping("/{id}/delete-exam")
	public ResponseEntity<?> deleteExam(@RequestBody Exam exam, @PathVariable Long id){
		Optional<Curso> cu = this.service.findById(id);
		if(!cu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso = cu.get();
		
		dbCurso.removeExam(exam);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	
	
}
