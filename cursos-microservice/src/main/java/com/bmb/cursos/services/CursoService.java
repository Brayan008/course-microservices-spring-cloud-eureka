package com.bmb.cursos.services;

import com.bmb.commons.service.CommonService;
import com.bmb.cursos.entity.Curso;

public interface CursoService extends CommonService<Curso> {

	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> getExamsIdsWithAnswersStudent(Long idStudent);
}
