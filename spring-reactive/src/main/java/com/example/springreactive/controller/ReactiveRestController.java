package com.example.springreactive.controller;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springreactive.model.Spell;
import com.example.springreactive.model.Wizard;
import com.example.springreactive.service.PublicApiService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Slf4j
public class ReactiveRestController {
	
	@Autowired
	PublicApiService publicApiService;
	
	@GetMapping("/test")
	public Mono<String> getHolaMundo() {
		return Mono.just("Hola mundo!");
	}
	
	@GetMapping("/spells")
	public Flux<Spell> getSpells() {
		log.info("Getting spells...");
		return publicApiService.getSpellsReactive();
//		return publicApiService.getSpellsRestTemplate();
//		return publicApiService.getSpellsFeignClient();
	}
	
	@GetMapping("/wizards")
	public Flux<Wizard> getNSpellsParallel() {
		return publicApiService.getWizards(Arrays.asList(UUID.fromString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"), UUID.fromString("4c7e6819-a91a-45b2-a454-f931e4a7cce3"), UUID.fromString("c3b1f9a5-b87b-48bf-b00d-95b093ea6390")));
	}
	
}
