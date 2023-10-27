package com.curso.demojpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.demojpa.entity.Student;
import com.curso.demojpa.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAndSelect() {
		log.info("deleteAndSelect antes: {}", studentRepository.count());
		studentRepository.deleteById(1L);
		
		try {
			log.info("Durmiendo:{}", studentRepository.count());
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("deleteAndSelect después de delete: {}", studentRepository.count());
		try {
			log.info("Students deleteAndSelect:{}", deleteAndfind());
		} catch(Exception e) {}
		log.info("deleteAndSelect después de findAll: {}", studentRepository.count());
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Student> deleteAndfind() {
		log.info("deleteAndSelect ants de findAll: {}", studentRepository.count());
		studentRepository.deleteById(2L);
		return studentRepository.findStudentWhereFirstNameAndAgeGreaterOrEqualNative("Pepe", 18);
	}
	
}
