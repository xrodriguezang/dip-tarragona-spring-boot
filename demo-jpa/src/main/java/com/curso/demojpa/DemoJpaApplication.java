package com.curso.demojpa;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.curso.demojpa.entity.Student;
import com.curso.demojpa.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student maria = new Student(
				"Maria",
				"Garcia",
				"maria.garcia@email.com",
				22
			);
			
			Student pepe = new Student(
				"Pepe",
				"Garcia",
				"pepe.garcia@email.com",
				28
			);

			log.info("Insertados María y Pepe:{}", studentRepository.saveAllAndFlush(List.of(maria, pepe)));
			
		};
	}

}
