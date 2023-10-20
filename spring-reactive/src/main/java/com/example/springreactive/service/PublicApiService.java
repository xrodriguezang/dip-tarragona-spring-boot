package com.example.springreactive.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
		this.webClient = builder.baseUrl("https://hp-api.onrender.com").clientConnector(connector).build();
	}
	
	public List<Spell> getSpellsRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		
		ResponseEntity<List<Spell>> response = restTemplate.exchange("https://hp-api.onrender.com/api/spells", HttpMethod.GET, null, new ParameterizedTypeReference<List<Spell>>() {});
		
		if(!response.getStatusCode().equals(HttpStatus.OK)) {
			log.error("Error al invocar spells");
		}
		
		List<Spell> spells = response.getBody();
		
		return spells.stream().map(spell -> {
			spell.setName("H-" + spell.getName());
			return spell;
		}).toList();
	}
	
	public List<Spell> getSpellsFeignClient() {
		return hpApiFeignClient.getSpells();
	}
	
	public Flux<Spell> getSpellsWebClient() {
		log.info("Antes de webclient");
		
		Flux<Spell> spells = webClient
				.get()
				.uri("/api/spells")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Spell.class);
		
		spells.subscribe(System.out::println);
		
		log.info("Después del webclient");
		
		return spells;
	}
	
	public Mono<List<Wizard>> getWizardById(UUID id) {
		log.info(String.format("Llamando getWizard(%s)", id));
		
		Mono<List<Wizard>> wizard = webClient
				.get()
				.uri("/api/character/{is}", id)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Wizard>>() {});
		
		return wizard;
		
	}
	
	public Flux<Wizard> getWizards(List<UUID> ids) {
		log.info("Antes de webclient");
		Flux<Wizard> wizards = Flux.fromIterable(ids)
				.flatMap(this::getWizardById)
				.flatMap(Flux::fromIterable);
		log.info("Después del webclient");
		return wizards;
	}
	
}
