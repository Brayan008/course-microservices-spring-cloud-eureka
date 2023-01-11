package com.bmb.answers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmb.answers.models.entity.Answer;
import com.bmb.answers.models.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository repository;

	@Override
	@Transactional
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		
		return repository.saveAll(answers);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Answer> findAnswerByStudentByExam(Long idStudent, Long idExam) {
		
		return repository.findAnswerByStudentByExam(idStudent, idExam);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long idStudent) {
		
		return repository.findExamsIdsWithAnswersByStudent(idStudent);
	}

}
