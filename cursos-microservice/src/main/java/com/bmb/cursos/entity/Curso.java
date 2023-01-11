package com.bmb.cursos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.bmb.common.exams.models.entity.Exam;
import com.bmb.commons.alumnos.models.entity.Student;

import lombok.Data;

@Entity
@Table
@Data
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@CreationTimestamp
	private Date createdAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Student> student;
	
	@ManyToMany(fetch =FetchType.LAZY)
	private List<Exam> exams;
	
	public Curso() {
		this.student = new ArrayList<>();
		this.exams = new ArrayList<>();
	}
	
	public void addStudent(Student student) {
		this.student.add(student);
	}
	
	public void removeStudent(Student student) {
		this.student.remove(student);
	}
	
	public void addExam(Exam exam) {
		this.exams.add(exam);
	}
	
	public void removeExam(Exam exam) {
		this.exams.remove(exam);
	}
	
	
}
