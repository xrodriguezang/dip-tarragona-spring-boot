package com.example.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mvc.service.IEmpleadoService;

@Controller
public class MainController {
	
	@Autowired
	IEmpleadoService empleadoService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("empleados", empleadoService.findAll());
		return "index";
	}
	
}
