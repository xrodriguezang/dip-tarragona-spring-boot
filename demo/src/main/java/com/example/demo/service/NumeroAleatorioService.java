package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
//@Primary
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class NumeroAleatorioService implements INumeroService {
	
	private final Integer numero;
	private String nombre;
	
	@Override
	public void metodoLargaDuracion() {
		log.info("Dentro de metodoLargaDuracion");
//		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		log.info("metodoLargaDuracion:{}ms", System.currentTimeMillis() - startTime);
	}

	@Override
	public void metodoCortaDuracion() {
		log.info("Dentro de metodoCortaDuracion");
//		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		log.info("metodoCortaDuracion:{}ms", System.currentTimeMillis() - startTime);
		
	}

	@Override
	public int devuelveNumerox2(int numero) {
		log.info("Dentro de devuelveNumerox2");
		return numero * 2;
	}

	@Override
	public String parseaLocalDate(LocalDate localDate) {
		log.info("Dentro de parseaLocalDate");
		return localDate.toString();
	}
	
}
