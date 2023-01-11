package com.bmb.answers.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bmb.answers.models.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	@Query("select a from Answer a join fetch a.student s join fetch a.question q join fetch q.exam e"
			+ " where s.id=?1 and e.id=?2")
	public Iterable<Answer> findAnswerByStudentByExam(Long idStudent, Long idExam);
	
	@Query("select e.id from Answer a join a.student s join a.question q join q.exam e "
			+ " where s.id=?1 group by e.id")
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long idStudent);

}
