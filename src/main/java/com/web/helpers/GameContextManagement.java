package com.web.helpers;

import java.util.List;

import com.bo.Utilisateur;

import jakarta.servlet.ServletContext;

public class GameContextManagement implements IGameDataManagement {

	private ServletContext conext;

	private static GameContextManagement instance;

	private GameContextManagement(ServletContext conext)
	{
		this.conext = conext;
	}

	synchronized public static final GameContextManagement getInstance(ServletContext conext) {
		if (instance == null) {
			instance = new GameContextManagement(conext);
		}
		return instance;
	}

	public List<Utilisateur> getAllUsers() {
		return (List<Utilisateur>) conext.getAttribute("users");

	}

	public void updateScore(Utilisateur user) {
		List<Utilisateur> users = getAllUsers();
		for (Utilisateur it : users) {
			if (it.getLogin().equals(user.getLogin())) {
				it.setBestScore(user.getBestScore());
				break;
			}
		}

	}

	public void insertUser(Utilisateur user) {
		List<Utilisateur> userList = (List<Utilisateur>) conext.getAttribute("users");

		userList.add(user);

	}

	public Utilisateur getUserByLogin(String login) {
		List<Utilisateur> users = getAllUsers();
		for (Utilisateur it : users) {
			System.out.println(it);
			if (it.getLogin().equals(login)) {
				return it;
			}
		}

		return null;
	}

}
