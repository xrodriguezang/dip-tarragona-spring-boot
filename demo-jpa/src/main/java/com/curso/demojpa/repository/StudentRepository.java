package com.curso.demojpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.demojpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);
	
	@Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.age >= :age")
	List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(@Param("firstName") String firstName, @Param("age") Integer age);
	
//	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age")
	List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(@Param("firstName") String firstName, @Param("age") Integer age);
	
//	@Transactional
	@Modifying
	@Query("DELETE FROM Student s WHERE s.id = ?1")
	int deleteStudentById(Long id);
}
