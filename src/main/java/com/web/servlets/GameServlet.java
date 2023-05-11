package com.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bo.GameState;
import com.bo.Message;
import com.bo.Utilisateur;
import com.web.helpers.GameContextManagement;
import com.web.helpers.IGameDataManagement;

@WebServlet(name = "GameServlet", urlPatterns = {"/GameServlet"})

public class  GameServlet extends HttpServlet {

	public GameServlet() {

	}

	/**
	 * Méthode permettant à un utilisateur de jouer
	 * 
	 */
	protected void play(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<Message> messages = new ArrayList<Message>();

     	// On récupére la session de l'utilisateur en cours
		HttpSession session = request.getSession();
		// On récupére de la session, les informations du joueur en cours
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		// Pour gérer les données de l'application dans le ServletContext
		IGameDataManagement gameContext = (IGameDataManagement) getServletContext().getAttribute("gameData");

		// Cette objet déjà inséré dans la session au moment de login
		GameState gameSate = (GameState) session.getAttribute("gameState");

		// On teste si le nombre de lancers autorisés par le jeu n'est pas
		// atteint. Si oui "Game over"
		if(gameSate.getScors()==null)
		{ 
			String numeroScore = request.getParameter("numeroScore");
			int resultat = new Random().nextInt(1, 7);
			Map<String,Integer> scors = new HashMap<String, Integer>();
			scors.put(numeroScore, resultat);
			gameSate.setScors(scors);
			
			// On redirige vers la vue par une redirection au niveau du serveur
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/userHome.jsp").forward(request, response);

						// On arrete l'execution
						return;
		}
		
		else
		{
			
			String numeroScore = request.getParameter("numeroScore");
			int resultat = new Random().nextInt(1, 7); 
//on verifier si le dés est lances plus d'un fois 
			if(gameSate.getScors().get(numeroScore) !=null)
			{				

            	messages.add(new Message("Votre score :-1 |Meilluer "+user.getBestScore(), Message.WARN));
				request.setAttribute("messages", messages);
				gameSate.setScors(null);
                user.setScore(-1);
				gameContext.updateScore(user);
				getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
                 gameSate.setGameOver(true);
                 return ;
			}
			 if(gameSate.getScors().size()==1  )
			{
				if (gameSate.getScors().get("1") < resultat && resultat == 6 )
				{				

                	messages.add(new Message("Votre score :0 |Meilluer "+user.getBestScore(), Message.WARN));

					request.setAttribute("messages", messages);	
					gameSate.setScors(null);	
	                user.setScore(0);
	                if(user.getBestScore() == -1)
	                	user.setBestScore(0);
					gameContext.updateScore(user);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
	                 gameSate.setGameOver(true);
	                 return ;

	                 }
				if (gameSate.getScors().get("1") > resultat && resultat == 1 )
				{				

	                gameSate.setScors(null);	
	                user.setScore(0);
	                if(user.getBestScore() == -1)
	                	user.setBestScore(0);
					gameContext.updateScore(user);
                	messages.add(new Message("Votre score : 0 |Meilluer "+user.getBestScore(), Message.WARN));
					request.setAttribute("messages", messages);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
	                 gameSate.setGameOver(true);
	                 return ;

				}
			}
			 if(gameSate.getScors().containsValue(resultat) )
			{				

                gameSate.setScors(null);	
                user.setScore(0);
                if(user.getBestScore() == -1)
                	user.setBestScore(0);
				gameContext.updateScore(user);
            	messages.add(new Message("Votre score :0 |Meilluer "+user.getBestScore(), Message.WARN));
				request.setAttribute("messages", messages);
				getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
                 gameSate.setGameOver(true);
                 return ;
}
			 if(gameSate.getScors().size()==2  )
			{
				
				if (gameSate.getScors().get("1") < gameSate.getScors().get("2") &&  resultat <  gameSate.getScors().get("2")  )
				{				

	                gameSate.setScors(null);	
	                user.setScore(0);
	                if(user.getBestScore() == -1)
	                	user.setBestScore(0);
					gameContext.updateScore(user);
                	messages.add(new Message("Votre score :0 |Meilluer "+user.getBestScore(), Message.WARN));
					request.setAttribute("messages", messages);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
	                 gameSate.setGameOver(true);
	                 return ;

				}
				else if(gameSate.getScors().get("1") > gameSate.getScors().get("2") &&  resultat >  gameSate.getScors().get("2"))
				{				

	                gameSate.setScors(null);	
	                user.setScore(0);
	                if(user.getBestScore() == -1)
	                	user.setBestScore(0);
					gameContext.updateScore(user);
                	messages.add(new Message("Votre score :0 |Meilluer "+user.getBestScore(), Message.WARN));
					request.setAttribute("messages", messages);
					getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
	                 gameSate.setGameOver(true);
	                 return ;

				}
				else
				{
	                
	                if(gameSate.getScors().get("1") > gameSate.getScors().get("2"))
	                { 				

	                	int res = gameSate.getScors().get("1") * gameSate.getScors().get("2")* resultat;
	                	if(user.getBestScore() < res)
	                		user.setBestScore(res);
	                	user.setScore(res);
	                	messages.add(new Message("Votre score : "+res+" |Meilluer "+user.getBestScore(), Message.WARN));
						request.setAttribute("messages", messages);
						getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
		                 gameSate.setGameOver(true); 
			                gameSate.setScors(null);	
						return ;


	                }
	                else
	                {				

	                	int res = gameSate.getScors().get("1") + gameSate.getScors().get("2") + resultat;
	                	if(user.getBestScore() < res)
	                		user.setBestScore(res);
	                	user.setScore(res);
	                	messages.add(new Message("Votre score : "+res+" |Meilluer "+user.getBestScore(), Message.WARN));
						request.setAttribute("messages", messages);
						getServletContext().getRequestDispatcher("/WEB-INF/vues/back/resultatJoue.jsp").forward(request, response);
		                 gameSate.setGameOver(true); 
			                gameSate.setScors(null);	

						return ;

	                }
					
				}
			}
			
			
				Map<String, Integer> score = gameSate.getScors();
				String numDes =Integer.toString(score.size()+1);
				score.put( numDes, resultat);
				gameSate.setScors(score);
				getServletContext().getRequestDispatcher("/WEB-INF/vues/back/userHome.jsp").forward(request, response);
              return;
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		play(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		play(request, response);

	}

}
