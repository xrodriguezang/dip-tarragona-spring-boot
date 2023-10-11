package com.example.mvc.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvc.model.Empleado;
import com.example.mvc.service.IEmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	IEmpleadoService empleadoService;
	
	@GetMapping(path = "/empleados")
	public List<Empleado> findAllEmpleados() {
		return empleadoService.findAll();
	}
	
	@GetMapping(path = "/empleados/text")
	public String findAllEmpleadosText() {
		return empleadoService.findAll().toString();
	}
	
	@GetMapping(path = "/empleados/{idEmpleado}")
	public Empleado findEmpleadoById(@PathVariable(name = "idEmpleado") UUID id) {
		return empleadoService.findById(id);
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(
		path = "/empleados", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Empleado createEmpleado(@Validated @RequestBody Empleado empleado) {
		return empleadoService.create(empleado);
	}
	
}
