package com.example.springreactive.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springreactive.model.Spell;
import com.example.springreactive.model.Wizard;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api")
public interface ReactiveController {
	
	@GetMapping("/test")
	Mono<String> getHolaNundo();
		
	@GetMapping("/rest-template/spells")
	List<Spell> getRestTemplateSpells();
	
	@GetMapping("/feign-client/spells")
	List<Spell> getFeignClientSpells();
	
	@GetMapping("/web-client/spells")
	Flux<Spell> getWebClientSpells();
	
	@GetMapping("/web-client/wizards")
	Flux<Wizard> geWebClientWizards();

}