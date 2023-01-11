package com.bmb.cursos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bmb.cursos.entity.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>{

	@Query("select c from Curso c join fetch c.student a where a.id=?1")
	public Curso findCursoByAlumnoId(Long id);
}
