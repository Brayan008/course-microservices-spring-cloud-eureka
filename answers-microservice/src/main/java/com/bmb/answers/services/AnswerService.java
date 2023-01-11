package com.bmb.answers.services;

import com.bmb.answers.models.entity.Answer;

public interface AnswerService {

	public Iterable<Answer> saveAll(Iterable<Answer> answers);
	
	public Iterable<Answer> findAnswerByStudentByExam(Long idStudent, Long idExam);
	
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long idStudent);
}
