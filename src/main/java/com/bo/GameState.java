package com.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {

	private Utilisateur user;

	private boolean gameOver = false;

	private List<Message> messages = new ArrayList<Message>();
	Map<String, Integer> des_score = null;
	public void reinit() {
		gameOver = false;
		des_score = null;
		messages = new ArrayList<Message>();
		user.setScore(0);
		
	}
  public Map<String,Integer> getScors()
  {
	  return des_score;
  }
  public void setScors(Map<String, Integer> s)
  {
	  this.des_score=s;
  }
	public String toString() {
		return "GameState [Score=" + user.getScore() + ", nombre lancers=" + ", messages="
				+ messages + "]";
	}

	public void addMessage(Message ms) {
		messages.add(ms);
	}

	public GameState(Utilisateur user) {
		this.user = user;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
