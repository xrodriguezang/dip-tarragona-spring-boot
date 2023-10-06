package com.example.demo;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.INumeroService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
//@ComponentScan(lazyInit = true)
@Slf4j
public class DemoApplication {
	
	@Autowired
	@Qualifier("numeroAleatorioService")
	INumeroService numeroService;
	
//	@Autowired
//	Integer numero;
	 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		log.info("DemoApplication iniciada!!!");
	}
	
	@PostConstruct
	public void init() {
		log.info("NumeroAleatorioService:{}", numeroService.getNumero());
//		log.info("Numero:{}", numero);
		numeroService.metodoLargaDuracion();
		numeroService.metodoCortaDuracion();
//		int numerox2 = numeroService.devuelveNumerox2(3);
//		log.info("1 Numerox2:{}", numerox2);
//		numerox2 = numeroService.devuelveNumerox2(3);
//		log.info("2 Numerox2:{}", numerox2);
		
//		LocalDate date = LocalDate.now();
//		String resultDate = numeroService.parseaLocalDate(date);
//		log.info("Result Date:{}", resultDate);
		
		LocalDate date2 = LocalDate.now();
		String resultDate2 = numeroService.parseaLocalDate(date2);
		log.info("Result Date2:{}", resultDate2);
		
	}

}
