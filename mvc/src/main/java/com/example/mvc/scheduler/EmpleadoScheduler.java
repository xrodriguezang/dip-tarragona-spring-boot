package com.example.mvc.scheduler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.mvc.model.Empleado;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Service
@Slf4j
public class EmpleadoScheduler {
	
	@Autowired
	List<SseEmitter> sseEmitters;
	
	@Scheduled(fixedDelay = 1000)
	public void processNewEmpleado() {
		log.info("Broadcast de ampleados. subscriptores[{}]", sseEmitters.size());
		
		List<Empleado> empleados = new ArrayList<>();
		
		
		Empleado empleado = new Empleado(UUID.randomUUID(),"empleado", "e@email.com", new BigDecimal(20000));
		
		empleados.add(empleado);
		empleados.add(empleado);
		for (SseEmitter sseEmitter : sseEmitters) {
			try {
				sseEmitter.send(empleados);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		
	}
	
}
