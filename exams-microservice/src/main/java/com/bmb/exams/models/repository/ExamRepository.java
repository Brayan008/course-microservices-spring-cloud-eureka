package com.bmb.exams.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bmb.common.exams.models.entity.Exam;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long>{

	@Query("select e from Exam e where e.name like %?1%")
	public List<Exam> findByName(String term);
}
