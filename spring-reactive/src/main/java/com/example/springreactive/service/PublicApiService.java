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
		log.info("Antes de restTemplate");
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Spell>> response = restTemplate.exchange("https://hp-api.onrender.com/api/spells", HttpMethod.GET, null, new ParameterizedTypeReference<List<Spell>>() {});
		
		if(!response.getStatusCode().equals(HttpStatus.OK)) {
			log.error("Error al invocar spells");
		}
		
		List<Spell> spells = response.getBody();
		
		spells.forEach(spell -> log.info("Spell:{}", spell));
		
		log.info("Después de restTemplate");
		
		return spells.stream().map(spell -> {
			spell.setName("H-" + spell.getName());
			return spell;
		}).toList();
	}
	
	public List<Spell> getSpellsFeignClient() {
		log.info("Antes de Feign");
		List<Spell> spells = hpApiFeignClient.getSpells();
		
		spells.forEach(spell -> log.info("Spell:{}", spell));
		
		log.info("Después de Feign");
		return spells;
	}
	
	public Flux<Spell> getSpellsWebClient() {
		log.info("Antes de webclient");
		
		Flux<Spell> spells = webClient
				.get()
				.uri("/api/spells")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Spell.class)
				.doOnComplete(() -> log.info("WebClient terminado"));
		
		spells.subscribe(spell -> log.info("Spell:{}", spell));
		
		log.info("Después del webclient");
		
		return spells;
	}
	
	public Mono<List<Wizard>> getWizard(UUID id) {
		log.info("Antes de getWizard: {}", id);
		
		Mono<List<Wizard>> wizard = webClient
				.get()
				.uri("/api/character/{id}", id)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Wizard>>() {});
		
		wizard.subscribe(lw -> log.info("wizard{}", lw));
		
		log.info("Después de getWizard: {}", id);
		
		return wizard;
		
	}
	
	public Flux<Wizard> getWizards(List<UUID> ids) {
		log.info("Antes de getWizards");
		
		Flux<Wizard> wizards = Flux
				.fromIterable(ids)
				.flatMap(id -> this.getWizard(id))
				.flatMap(listWizards -> Flux.fromIterable(listWizards));
		
		log.info("Después de getWizards");
		
		return wizards;
	}
	
}
