package com.curso.demojpa;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.curso.demojpa.entity.Student;
import com.curso.demojpa.repository.StudentRepository;
import com.curso.demojpa.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentService studentService) {
		return args -> {
			Student maria = new Student("Maria", "Garcia", "maria.garcia@email.com", 22);
			Student pepe = new Student("Pepe", "Garcia", "pepe.garcia@email.com", 28);
			
			log.info("Añadiendo a María y Pepe");
			studentRepository.saveAll(List.of(maria, pepe));
			
			log.info("Estudiantes #{}", studentRepository.count());
			
			try {
				studentService.deleteAndSelect();
			}
			catch(Throwable e) {
				log.error("Error en query");
			}
			
			log.info("Estudiantes #{}", studentRepository.count());
			
//			List<Student> students = studentRepository.findAll();
//			for (Student student : students) {
//				log.info("Estudiante:{}", student);
//			}
//			
//			studentRepository.deleteById(1L);
//			
//			log.info("Estudiantes después de delete #{}", studentRepository.count());
//			
//			studentRepository
//				.findStudentByEmail("pepe.garcia@email.com")
//				.ifPresentOrElse(System.out::println, () -> System.err.println("Estudiante no existe"));
//			
//			log.info("Estudiantes Nativo:{}", studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqualNative("Pepe", 18));
		};
	}

}
