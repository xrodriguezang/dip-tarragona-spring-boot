package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class NumeroNoAleatorioService implements INumeroService {
	
	private final Integer numero;
	@Getter
	private String nombre;
	
	
	public Integer getNumero() {
		return numero - numero;
	}


	@Override
	public void metodoLargaDuracion() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void metodoCortaDuracion() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int devuelveNumerox2(int numero) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String parseaLocalDate(LocalDate localDate) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
