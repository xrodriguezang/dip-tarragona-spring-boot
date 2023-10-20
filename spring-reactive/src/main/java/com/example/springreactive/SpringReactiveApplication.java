package com.example.springreactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class SpringReactiveApplication {
	
	@Autowired
	Flux<Integer> integers;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApplication.class, args);
	}
	
//	@PostConstruct
	public void init() {
		integers.subscribe(i -> log.info("Añadido {}", i));
		
		Flux<String> fluxString = Flux.just("e1", "e2", "e3");
//		fluxString.subscribe(System.out::println);
		
		fluxString.subscribe(
			data -> onNext(data),
			err -> onError(err),
			() -> onComplete()
		);
		
		log.info("AQUI");
		
	}
	
	private void onNext(String data) {
		log.info("Data:{}", data);
	}
	
	private void onError(Throwable err) {
		log.info("Error:{}", err.getMessage());
	}
	
	private void onComplete() {
		log.info("Completed!");
	}

}
