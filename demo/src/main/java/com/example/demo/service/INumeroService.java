package com.example.demo.service;

import java.time.LocalDate;

public interface INumeroService {

	String getNombre();

	Integer getNumero();

	void setNombre(String nombre);

	public void metodoLargaDuracion();
	public void metodoCortaDuracion();
	
	public int devuelveNumerox2(int numero);
	public String parseaLocalDate(LocalDate localDate);

}