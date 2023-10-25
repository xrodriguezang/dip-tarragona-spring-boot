package com.curso.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.demojpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
