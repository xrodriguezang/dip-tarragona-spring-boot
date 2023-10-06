package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.INumeroService;

//@RestController
//@RequestMapping("/api/numero")
public class NumeroRestController {
	
	@Autowired
	@Qualifier("numeroAleatorioService")
	INumeroService numeroService;
	
	@GetMapping("/request")
	public Integer devuelveNumero() {
		return numeroService.getNumero();
	}
	
}
