package com.example.springreactive.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springreactive.client.HpApiFeignClient;
import com.example.springreactive.model.Spell;
import com.example.springreactive.model.Wizard;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PublicApiService {
	
	@Autowired
	HpApiFeignClient hpApiFeignClient;
	
	WebClient webClient;
	
	public PublicApiService(WebClient.Builder builder, ReactorClientHttpConnector connector) {
		this.webClient = builder.baseUrl("https://hp-api.onrender.com/").clientConnector(connector).build();
	}
	
	public Flux<Spell> getSpellsReactive() {
		log.info("antes de webclient");
		Flux<Spell> spells = webClient
				.get()
				.uri("api/spells")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Spell.class);
		
		spells.subscribe(System.out::println);
		
//		Spell spell = spells.blockLast();
//		log.info("Ultimo Spell:{}", spell);
		
		log.info("después de webclient");
		
		return spells;
	}
	
	public Mono<List<Wizard>> getWizardById(UUID id) {
		log.info(String.format("Calling getWizard(%s)", id));
		
	    Mono<List<Wizard>> wizard = webClient.get()
	        .uri("api/character/{id}", id)
	        .retrieve()
	        .bodyToMono(new ParameterizedTypeReference<List<Wizard>>() {})
	        .doOnSuccess(w -> log.info("Wizard retrieved:{}", w));
	    
	    return wizard;
	}
	
	public Flux<Wizard> getWizards(List<UUID> ids) {
		return Flux.fromIterable(ids)
		        .flatMap(this::getWizardById)
		        .flatMap(Flux::fromIterable);
	}
	
	public List<Spell> getSpellsRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Spell>> response = restTemplate.exchange("https://hp-api.onrender.com/api/spells"
				, HttpMethod.GET, null, new ParameterizedTypeReference<List<Spell>>() {});
		List<Spell> spells = response.getBody();
		spells.forEach(System.out::println);
		return spells;
	}
	
	public List<Spell> getSpellsFeignClient() {
		return hpApiFeignClient.getSpells();
	}
	
}
