package com.example.demo.config;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class NumeroAleatorioConfiguration {
	
	@Bean("numero")
	@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	Integer numeroAleatorio() {
//		log.info("Generando número");
		Random random = new Random();
		return random.nextInt(0, 1000);
	}
	
}
