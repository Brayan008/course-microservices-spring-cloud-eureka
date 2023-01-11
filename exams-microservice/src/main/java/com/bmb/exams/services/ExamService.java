package com.bmb.exams.services;

import java.util.List;

import com.bmb.common.exams.models.entity.Exam;
import com.bmb.common.exams.models.entity.Subject;
import com.bmb.commons.service.CommonService;

public interface ExamService extends CommonService<Exam>{

	public List<Exam> findByName(String term);
	
	public List<Subject> findAllSubjects();
}
