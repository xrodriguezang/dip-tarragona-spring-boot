package com.example.aop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.aop.service.IClaseEjemploService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AopApplication {
	
	@Autowired
	IClaseEjemploService claseEjemploService;
	
	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
		log.info("AOP Application started!");
	}
	
	@PostConstruct
	public void init() {
//		claseEjemploService.metodo1();
//		claseEjemploService.metodo2();
		claseEjemploService.metodo3(10);
		claseEjemploService.metodo4("Pepe");
		claseEjemploService.metodo4("Pepe");
		claseEjemploService.metodo4("Juan");
		claseEjemploService.metodo3(10);
	}

}
