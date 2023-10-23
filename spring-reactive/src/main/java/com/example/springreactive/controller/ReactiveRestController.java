package com.example.springreactive.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springreactive.model.Spell;
import com.example.springreactive.model.Wizard;
import com.example.springreactive.service.PublicApiService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ReactiveRestController implements ReactiveController {
	
	@Autowired
	PublicApiService publicApiService;
	
	@Override
	public Mono<String> getHolaNundo() {
		return Mono.just("Hola mundo!");
	}
		
	@Override
	public List<Spell> getRestTemplateSpells() {
		return publicApiService.getSpellsRestTemplate();
	}

	@Override
	public List<Spell> getFeignClientSpells() {
		List<Spell> spells =publicApiService.getSpellsFeignClient();
		log.info("Spells:{}", spells);
		return spells;
	}

	@Override
	public Flux<Spell> getWebClientSpells() {
		log.info("Antes de controller");
		Flux<Spell> spells = publicApiService.getSpellsWebClient();
		
		log.info("Después de controller");
		return spells;
	}

	@Override
	public Flux<Wizard> geWebClientWizards() {
		log.info("Antes de controller");
		List<UUID> uuids = Arrays.asList(UUID.fromString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"), UUID.fromString("4c7e6819-a91a-45b2-a454-f931e4a7cce3"), UUID.fromString("c3b1f9a5-b87b-48bf-b00d-95b093ea6390"));
		Flux<Wizard> wizards = publicApiService.getWizards(uuids);
		
		log.info("Después de controller");
		return wizards;
	}
		
}
