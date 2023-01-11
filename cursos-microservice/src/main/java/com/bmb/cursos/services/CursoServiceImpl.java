package com.bmb.cursos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmb.commons.service.CommonServiceImpl;
import com.bmb.cursos.clients.ResponseFeignClient;
import com.bmb.cursos.entity.Curso;
import com.bmb.cursos.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {
	
	@Autowired
	private ResponseFeignClient client;

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> getExamsIdsWithAnswersStudent(Long idStudent) {
		return client.getExamsIdsWithAnswersStudent(idStudent);
	}

}
