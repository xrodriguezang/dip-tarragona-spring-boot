package com.example.mvc.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc.exception.ElementNotFoundException;
import com.example.mvc.model.Empleado;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpleadoService implements IEmpleadoService {
	
	@Autowired
	Map<UUID, Empleado> empleados;

	@Override
	public List<Empleado> findAll() {
		return empleados.values().stream().toList();
	}

	@Override
	public Empleado findById(UUID id) {
		Empleado empleado = empleados.get(id);
		
		log.info("Empleado:{}", empleado.getNombre());
		
		if(empleado == null) {
			throw new ElementNotFoundException("El empleado no existe!");
		}
		
		return empleado;
	}

	@Override
	public Empleado create(Empleado empleado) {
		empleado.setId(UUID.randomUUID());
		empleados.put(empleado.getId(), empleado);
		
		return empleado;
	}

}
