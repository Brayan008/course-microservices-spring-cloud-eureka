/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmb.microserviceUser.services;


import java.util.List;

import com.bmb.commons.alumnos.models.entity.Student;
import com.bmb.commons.service.CommonService;

/**
 *
 * @author bmares008
 */
public interface StudentService extends CommonService<Student>{

	public List<Student> findByNameOrLastName(String term);
    
}
