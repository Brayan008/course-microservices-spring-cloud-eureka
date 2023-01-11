/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmb.microserviceUser.services;


import com.bmb.commons.alumnos.models.entity.Student;
import com.bmb.commons.service.CommonServiceImpl;
import com.bmb.microserviceUser.repository.StudentRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author bmares008
 */
@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService {

	@Override
	@Transactional(readOnly = true)
	public List<Student> findByNameOrLastName(String term) {
		return repository.findByNameOrLastName(term);
	}
    
}
