package com.bmb.answers.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bmb.common.exams.models.entity.Question;
import com.bmb.commons.alumnos.models.entity.Student;

import lombok.Data;

@Entity
@Table(name = "answers")
@Data
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Question question;
	
	

}
