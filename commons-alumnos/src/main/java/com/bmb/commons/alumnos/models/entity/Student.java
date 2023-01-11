/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmb.commons.alumnos.models.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 *
 * @author bmares008
 */
@Entity
@Table(name = "user")
@Data
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    
    @Lob
    @JsonIgnore
    private byte[] photo;
    
    @CreationTimestamp
    private Date createAt;
    
    public Integer getPhotoHashCode() {
    	return (this.photo != null ) ? this.photo.hashCode() : null;
    }
    
    @Override
    public boolean equals(Object obj) {
    	
    	if(this == obj) {
    		return true;
    	}
    	
    	if(!(obj instanceof Student)) {
    		return false;
    	}
    	
    	Student s = (Student) obj;
    	
    	
    	return this.id != null && this.id.equals(s.getId());
    }
    
}
