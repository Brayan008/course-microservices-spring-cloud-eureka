package com.bmb.common.exams.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "exams")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@CreationTimestamp
	private Date createAt;
	
	@JsonIgnoreProperties(value = {"exam"}, allowSetters = true) //Suprimir la relacion inversa
	@OneToMany(mappedBy = "exam",
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<Question> questions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Subject subject;
	
	@Transient
	private boolean answered;
	
	public Exam() {
		this.questions = new ArrayList<>();
	}
	
	public void setQuestions(List<Question> question) {
		this.questions.clear();
		question.forEach(this::addQuestion);
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
		question.setExam(this);
	}
	
	public void removeQuestion(Question question) {
		this.questions.remove(question);
		question.setExam(null);
	}
	
	@Override
	public boolean equals(Object obj) {
    	if(this == obj) {
    		return true;
    	}
    	
    	if(!(obj instanceof Exam)) {
    		return false;
    	}
    	
    	Exam s = (Exam) obj;
    	
    	
    	return this.id != null && this.id.equals(s.getId());
	}
}
