package com.example.mvc.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.mvc.model.Empleado;

@Configuration
public class AppConfiguration {
	
	@Bean
	Map<UUID, Empleado> empleados() {
		Map<UUID, Empleado> empleados = new HashMap<>();
		
		Empleado e1 = new Empleado(UUID.fromString("cd5c5698-fbad-4bf0-9fd1-0dc0b77e540a"), "A1", "a1@email.com", new BigDecimal(10000));
		empleados.put(e1.getId(), e1);
		Empleado e2 = new Empleado(UUID.fromString("71886de5-2f69-47e4-a6a1-3fc92c2666d7"), "A2", "a2@email.com", new BigDecimal(20000));
		empleados.put(e2.getId(), e2);
		Empleado e3 = new Empleado(UUID.fromString("f9e19b86-dd11-4587-b33e-27148aef173e"), "A3", "a3@email.com", new BigDecimal(30000));
		empleados.put(e3.getId(), e3);
		
		return empleados;
	}
	
	@Bean
	List<SseEmitter> sseEmitters() {
		return Collections.synchronizedList(new ArrayList<>());
	}
}
