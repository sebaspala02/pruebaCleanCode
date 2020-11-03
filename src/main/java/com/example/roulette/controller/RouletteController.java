package com.example.roulette.controller;

import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.roulette.model.Bet;
import com.example.roulette.model.Roulette;
import com.example.roulette.service.RouletteService;

@RestController
@RequestMapping("/roulette")
public class RouletteController {
	private final RouletteService rouletteService;

	public RouletteController(RouletteService rouletteService) {
		this.rouletteService = rouletteService;
	}

	@GetMapping
	public Map<String, Roulette> findAll() {
		return this.rouletteService.findAll();
	}

	@PostMapping
	public String create() {
		Roulette roulette = new Roulette();

		return this.rouletteService.create(roulette);
	}

	@PutMapping("/{id}/open")
	public String openRoulette(@PathVariable String id) {

		return this.rouletteService.openRoulette(id);
	};

	@PutMapping("/{id}/close")
	public Map<String, Bet> closeRoulette(@PathVariable String id) throws Exception {

		return this.rouletteService.closeRoulette(id);
	};

	@PutMapping("/{id}/bet")
	public Map<String, Bet> betInRoulette(@PathVariable String id, @RequestBody Bet bet,
			@NonNull @RequestHeader String userId) throws Exception {
		bet.setIdUser(userId);
		bet.setIdRoulette(id);

		return this.rouletteService.createBetInRoulette(id, bet);
	};
}
