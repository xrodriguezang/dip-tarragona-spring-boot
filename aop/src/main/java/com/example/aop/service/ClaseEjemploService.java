package com.example.aop.service;

import org.springframework.stereotype.Service;

import com.example.aop.annotation.Cacheame;
import com.example.aop.annotation.LogueaTiempo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClaseEjemploService implements IClaseEjemploService {

	@Override
	public void metodo1() {
		log.info("Dentro de metodo1");

	}

	@Override
	@LogueaTiempo
	public int metodo2() {
		log.info("Dentro de metodo2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
//	@LogueaTiempo
	@Cacheame
	public int metodo3(int num) {
		log.info("Dentro de metodo3:{}", num);
		return num * 3;
	}

	@Override
	@Cacheame
//	@LogueaTiempo
	public String metodo4(String s) {
		log.info("Dentro de metodo4:{}", s);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s.concat("FIN");
	}

}
