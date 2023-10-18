package com.example.springreactive.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springreactive.model.Spell;

@FeignClient(value = "hp-api-client", url = "https://hp-api.onrender.com")
public interface HpApiFeignClient {
	
	@GetMapping("/api/spells")
	public List<Spell> getSpells();
	
}
