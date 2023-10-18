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
		
		log.info("Nums < 10: {}", ints.stream().filter(i -> i > 10).toList());
		
		Stream<Integer> intStream = ints.stream();
		
		Flux<Integer> fluxFromStream = Flux.fromStream(intStream);
		
		
		
		log.info("en config");
		
		return fluxFromStream;
		
	}
	
}
