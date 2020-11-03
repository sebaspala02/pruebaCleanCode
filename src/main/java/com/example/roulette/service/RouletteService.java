package com.example.roulette.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Service;

import com.example.roulette.model.Bet;
import com.example.roulette.model.Roulette;
import com.example.roulette.repository.BetRepository;
import com.example.roulette.repository.RouletteRepository;

@Service
public class RouletteService {
	private final RouletteRepository rouletteRepository;
    private final BetRepository betRepository;

    public RouletteService(RouletteRepository rouletteRepository, BetRepository betRepository) {
        this.rouletteRepository = rouletteRepository;
        this.betRepository = betRepository;
    }

    public Map<String, Roulette> findAll() {
        return this.rouletteRepository.findAll();
    }

    public String create(Roulette roulette) {
        return this.rouletteRepository.save(roulette);
    }

    public String openRoulette(String id) {
        try {
            this.rouletteRepository.openRoulette(id);
        } catch (Exception e) {

            return "Roulette not open beacause: " + e.getMessage();
        }

        return "Open Roulette {id: " + id + "}";
    }

    public Map<String, Bet> closeRoulette(String id) throws Exception {
        this.rouletteRepository.closeRoulette(id);

        return this.closeBetsInRoulette(id);
    }

    private Map<String, Bet> closeBetsInRoulette(String idRoulette) {
        Map<String, Bet> result = new HashMap<>();
        this.betRepository.findByRouletteId(idRoulette).forEach(new BiConsumer<String, Bet>() {
            @Override
            public void accept(String s, Bet bet) {
                if (bet.getIdRoulette().equals(idRoulette) && bet.getState().equalsIgnoreCase("open")) {
                    bet.setState("closed");
                    bet.setUpdatedAt(new Date());
                    result.put(s, bet);
                    betRepository.edit(s, bet);
                }
            }
        });

        return result;
    }

    public Map<String, Bet> createBetInRoulette(String idRoulette, Bet bet) throws Exception {
        Map<String, Bet> response = new HashMap<>();
        Roulette roulette = this.rouletteRepository.findById(idRoulette);
        String color = roulette.getColor(bet.getColor());
        if (!roulette.getStatus().equalsIgnoreCase("open")) {
            throw new Exception("roulette is not open");
        }
        if (bet.getQuantityBet() > roulette.getBetMax()) {
            throw new Exception("the bet must not be greater than $10.000");
        } else if (bet.getQuantityBet() <= 0) {
            throw new Exception("the bet cannot be $0.0");
        } else if (!roulette.containsNumber(bet.getNumber()))
            throw new Exception("that number does not exist in roulette");
        if (color == null) {
            throw new Exception("the color of the bet does not exist");
        } else {
            bet.setColor(color);
        }
        response.put(this.betRepository.save(bet), bet);

        return response;
    }
}
