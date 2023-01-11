package com.bmb.exams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmb.common.exams.models.entity.Exam;
import com.bmb.common.exams.models.entity.Subject;
import com.bmb.commons.service.CommonServiceImpl;
import com.bmb.exams.models.repository.ExamRepository;
import com.bmb.exams.models.repository.SubjectRepository;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService {
	
	@Autowired
	private SubjectRepository subjectRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Exam> findByName(String term) {
		return repository.findByName(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subject> findAllSubjects() {
		return subjectRepo.findAll();
	}

	
}
