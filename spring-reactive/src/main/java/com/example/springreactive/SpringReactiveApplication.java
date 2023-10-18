package com.example.springreactive;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

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
//		integers.subscribe(i -> log.info("en Main:{}", i));
//		integers.doOnNext(i -> log.info("en Main:{}", i));
//		log.info("aqui");
		
		log.info("Threads:{}", getThreads());
	}
	
	private List<Thread> getThreads() {
		return Thread.getAllStackTraces()
			.keySet()
			.stream()
			.collect(Collectors.toList());
	}
	
}
