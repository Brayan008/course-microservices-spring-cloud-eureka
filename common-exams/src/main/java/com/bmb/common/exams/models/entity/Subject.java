package com.bmb.common.exams.models.entity;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "subjects")
@Data
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonIgnoreProperties(value = {"children"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Subject father;
	
	@JsonIgnoreProperties(value = {"father"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy = "father",
			cascade = CascadeType.ALL)
	private List<Subject> children;
	
	public Subject() {
		this.children = new ArrayList<>();
	}
	
	
}
