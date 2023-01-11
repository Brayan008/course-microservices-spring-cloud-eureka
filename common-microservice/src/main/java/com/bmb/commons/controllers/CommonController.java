/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmb.commons.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bmb.commons.service.CommonService;

/**
 *
 * @author bmares008
 */
//Solo se esta usando programacion orientada a objectos. EL api generic
public class CommonController<E, S extends CommonService<E>> {
    
    @Autowired
    protected S service;
    
    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @GetMapping("/page")
    public ResponseEntity<?> list(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<E> s = service.findById(id);
        
        if(s.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        
        return ResponseEntity.ok(s);
    }
    
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result){
    	
    	if(result.hasErrors()) {
    		return this.validate(result);
    	}
    	
        E entityDB = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
    
    protected ResponseEntity<?> validate(BindingResult result){
    	Map<String, Object> errors = new HashMap<>();
    	
    	result.getFieldErrors().forEach(err->{
    		errors.put(err.getField(), "The field " + err.getField() + " " +err.getDefaultMessage());
    	});
    	return ResponseEntity.badRequest().body(errors);
    }
}
