/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmb.microserviceUser.controllers;

import com.bmb.commons.alumnos.models.entity.Student;
import com.bmb.commons.controllers.CommonController;
import com.bmb.microserviceUser.services.StudentService;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author bmares008
 */
@RestController
public class StudentController extends CommonController<Student, StudentService> {

	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> getPhoto(@PathVariable Long id) {
		Optional<Student> st = service.findById(id);

		if (st.isEmpty() || st.get().getPhoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource image = new ByteArrayResource(st.get().getPhoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Student student, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validate(result);
		}

		Optional<Student> st = service.findById(id);

		if (st.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Student studentDb = st.get();

		studentDb.setName(student.getName());
		studentDb.setLastName(student.getLastName());
		studentDb.setEmail(studentDb.getEmail());
		studentDb.setCreateAt(student.getCreateAt());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNameOrLastName(term));
	}

	@PostMapping("/save-with-photo")
	public ResponseEntity<?> saveWithPhoto(@Valid Student student, BindingResult result,
			@RequestParam MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			student.setPhoto(file.getBytes());
		}

		return super.save(student, result);
	}

	@PutMapping("/edit-with-photo/{id}")
	public ResponseEntity<?> editWithPhoto(@Valid Student student, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile file) throws IOException {

		if (result.hasErrors()) {
			return this.validate(result);
		}

		Optional<Student> st = service.findById(id);

		if (st.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Student studentDb = st.get();

		studentDb.setName(student.getName());
		studentDb.setLastName(student.getLastName());
		studentDb.setEmail(studentDb.getEmail());
		studentDb.setCreateAt(student.getCreateAt());

		if (!file.isEmpty()) {
			studentDb.setPhoto(file.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
	}

}
