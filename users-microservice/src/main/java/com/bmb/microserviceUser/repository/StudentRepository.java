/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmb.microserviceUser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bmb.commons.alumnos.models.entity.Student;

/**
 *
 * @author bmares008
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{
	
	@Query("select u from Student u where u.name like %?1% or u.lastName like %?1%")
	public List<Student> findByNameOrLastName(String term);
    
}
