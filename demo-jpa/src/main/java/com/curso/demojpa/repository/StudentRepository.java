package com.curso.demojpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.demojpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);
	
	@Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.age = :age")
	List<Student> findStudentWhereFirstNameAndAgeGreaterOrEqual(@Param("firstName") String firstName, @Param("age") Integer age);
	
	@Query(value = "SELECT s.* FROM students s WHERE s.first_name = :firstName AND s.age = :age", nativeQuery = true)
	List<Student> findStudentWhereFirstNameAndAgeGreaterOrEqualNative(@Param("firstName") String firstName, @Param("age") Integer age);
	
	@Modifying
	@Query("DELETE FROM Student s WHERE s.id = ?1")
	int deleteStudentById(Long id);
	
}
