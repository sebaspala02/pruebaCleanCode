package com.example.roulette.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

public class Bet implements Serializable{
	private String idRoulette;
	private int quantityBet;
	private int number;
	private String color;
	private String idUser;
	private String state;
	private final Date createdAt;
	private Date updatedAt;

	public Bet(int quantityBet, int number, String color) {
		super();
		this.quantityBet = quantityBet;
		this.number = number;
		this.color = color;
		this.createdAt = new Date();
		this.updatedAt = new Date();
		this.state = "open";
	}

	public String getIdRoulette() {
		
		return idRoulette;
	}

	public void setIdRoulette(String idRoulette) {
		this.idRoulette = idRoulette;
	}

	public int getQuantityBet() {
		
		return quantityBet;
	}

	public void setQuantityBet(int quantityBet) {
		this.quantityBet = quantityBet;
	}

	public int getNumber() {
		
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getColor() {
		
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIdUser() {
		
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getState() {
		
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Date getCreatedAt() {
		
		return createdAt;
	}

	public Date getUpdatedAt() {
		
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
