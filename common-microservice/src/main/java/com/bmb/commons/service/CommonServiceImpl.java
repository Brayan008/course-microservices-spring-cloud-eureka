/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmb.commons.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bmares008
 */
//De esta forma se tiene una clase generica, se hace dinamica y se puede reutilizar para cualquier entidad.
//Esto no tiene relacion con microservicios solamente se esta generando una buena practica
public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {
    
    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }
    
	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
