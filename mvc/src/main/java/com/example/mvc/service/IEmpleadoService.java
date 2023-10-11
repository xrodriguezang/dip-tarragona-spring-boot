package com.example.mvc.service;

import java.util.List;
import java.util.UUID;

import com.example.mvc.exception.ElementNotFoundException;
import com.example.mvc.model.Empleado;

public interface IEmpleadoService {
	
	List<Empleado> findAll();
	
	Empleado findById(UUID id) throws ElementNotFoundException;
	
	Empleado create(Empleado empleado);
	
}
