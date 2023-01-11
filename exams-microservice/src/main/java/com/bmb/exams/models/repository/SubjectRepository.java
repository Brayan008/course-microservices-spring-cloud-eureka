package com.bmb.exams.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmb.common.exams.models.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
