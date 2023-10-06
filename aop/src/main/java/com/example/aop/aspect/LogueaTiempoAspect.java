package com.example.aop.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Aspect
@Slf4j
@ConditionalOnProperty(name = "aop.loguea-tiempo.enabled", havingValue = "true", matchIfMissing = false)
public class LogueaTiempoAspect {
	
	private Map<Object, Object> cache = new HashMap<>();
	
	@Around("@annotation(com.example.aop.annotation.LogueaTiempo)")
	public Object logueaTiempoDeMetodo(ProceedingJoinPoint joinPoint) throws Throwable {
		long initTime = System.currentTimeMillis();
		//Ejecutar el método
		Object result = joinPoint.proceed();
		long duracion = System.currentTimeMillis() - initTime;
		
		log.info("Duración {}:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), duracion);
		
//		log.info("{}", duracion / 0);
		
		return result;
	}
	
	@Around("@annotation(com.example.aop.annotation.Cacheame)")
	public Object cacheaValor(ProceedingJoinPoint joinPoint) throws Throwable {
		Object valorEntrada = joinPoint.getArgs()[0];
		Object valorSalida = null;
		
		if(cache.containsKey(valorEntrada)) {
			log.info("Retornando valor cacheado:{}", cache.get(valorEntrada));
			return cache.get(valorEntrada);
		}
		
		valorSalida = joinPoint.proceed();
		cache.put(valorEntrada, valorSalida);
		
		return valorSalida;
	}
	
	@Before("@annotation(com.example.aop.annotation.Cacheame)")
	public void imprimeAntes() {
		log.info("ANTES");
	}
	
	@After("@annotation(com.example.aop.annotation.Cacheame)")
	public void imprimeDespues() {
		log.info("DESPUES");
	}
	
}
