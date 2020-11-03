package com.example.roulette.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

public class Roulette implements Serializable{
	private int[] numbers;
	private String[] colors;
	private String status;
	private int betMax;
	private Date createdAt;
	private Date updateAt;

	public Roulette() {
		super();
		this.numbers = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
		;
		this.colors = new String[] { "Red", "Black" };
		this.status = "close";
		this.betMax = 10000;
		this.createdAt = new Date();
		this.updateAt = new Date();
	}

	public Date getCreatedAt() {

        return createdAt;
    }

    public Date getUpdateAt() {

        return updateAt;
    }

    public void setUpdateAt(Date updatedAt) {
        this.updateAt = updatedAt;
    }

    public int getBetMax() {

        return betMax;
    }

    public int[] getNumbers() {

        return this.numbers;
    }

    public String[] getColors() {

        return this.colors;
    }

    public String getColor(String color) {
        for (String colorOfColors : this.colors) {
            if (colorOfColors.equalsIgnoreCase(color)) return colorOfColors;
        }

        return null;
    }

    public boolean containsNumber(int number) {
        for (int numberOfNumbers : this.numbers) {
            if (number == numberOfNumbers) {

                return true;
            }
        }

        return false;
    }

    public String getStatus() {

        return this.status;
    }

    public void setStatus(String state) {
        this.status = state;
    }
}
