package com.bmb.common.exams.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@JsonIgnoreProperties(value = {"questions"}) //Suprimir la relacion inversa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private Exam exam;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
    		return true;
    	}
    	
    	if(!(obj instanceof Question)) {
    		return false;
    	}
    	
    	Question s = (Question) obj;
    	
    	
    	return this.id != null && this.id.equals(s.getId());
	}

}
