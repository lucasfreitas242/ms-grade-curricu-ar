package com.rasmoo.cliente.escola.gradecurricular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;


@RestController 
@RequestMapping("/materia")

public class MateriaController {
	
	@Autowired
	private IMateriaRepository materiaRepository;
	
	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias(){
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MateriaEntity> consultarMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findById(id).get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletaMateria(@PathVariable Long id) {
		try {
			this.materiaRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	@PostMapping
	public ResponseEntity<Boolean> cadastrarMaterias(@RequestBody MateriaEntity materia){
		try {
			this.materiaRepository.save(materia);
			return ResponseEntity.status(HttpStatus.OK).body(true); 
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false); 
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Boolean> atualizaMateria(@RequestBody MateriaEntity materia){
		try {
			MateriaEntity materiaEntityAtualizada = this.materiaRepository.findById(materia.getId()).get();
			
			materiaEntityAtualizada.setNome(materia.getNome());
			materiaEntityAtualizada.setHoras(materia.getHoras());
			materiaEntityAtualizada.setCodigo(materia.getCodigo());
			materiaEntityAtualizada.setFrequencia(materia.getFrequencia());
			
			this.materiaRepository.save(materiaEntityAtualizada);
			
			return ResponseEntity.status(HttpStatus.OK).body(true);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}
		
		
	}
	
















