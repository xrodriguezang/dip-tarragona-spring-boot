package com.example.springreactive.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@Slf4j
public class AppConfiguration {
	
	@Bean
	Flux<Integer> integers() {
		List<Integer> ints = new ArrayList<>(Arrays.asList(17, 10, 5, 20));
		
		//Crear Stream
		Stream<Integer> intStream = ints.stream();
		
		//Crear el Flux desde el Stream
		Flux<Integer> fluxFromStream = Flux.fromStream(intStream);
		
//		fluxFromStream.subscribe(i -> log.info("{}", i));
		
		return fluxFromStream;
	}
	
}
