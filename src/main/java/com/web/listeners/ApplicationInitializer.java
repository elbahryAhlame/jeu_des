package com.web.listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.bo.Utilisateur;
import com.web.helpers.DataManagementFactory;
import com.web.helpers.IGameDataManagement;

import configuration.configurationStockage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class ApplicationInitializer
 *
 */
@WebListener
//Il est utilisé pour initialiser l'application et configurer les objets nécessaires lors du démarrage de l'application
public class ApplicationInitializer implements ServletContextListener 
   {

	/**
	 * Default constructor.
	 */
	public ApplicationInitializer()
	{

	}

	public void contextInitialized(ServletContextEvent sce) //sera apler hor de la demmarage de application
	{
//sce permet de recuperer des parametre, configurer et initialiser des elements(comme servletCtx)
		ServletContext ctx = sce.getServletContext();
		configurationStockage confStockage= new configurationStockage();
        String type=null;
		try {
			type = confStockage.getType_stockage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IGameDataManagement gameData = DataManagementFactory.getFactory(type, ctx);
//on recupere la classe qui gere bd 
		ctx.setAttribute("gameData", gameData);

		List<Utilisateur> userList = Collections.synchronizedList(new ArrayList<Utilisateur>());
//La méthode Collections.synchronizedList prend en entrée une liste et retourne une version synchronisée 
//de cette liste. L'utilisation de cette méthode garantit que tous les accès à la liste sont correctement 
		//synchronisés, sans que vous ayez à vous soucier de la mise en place de votre propre verrou de synchronisation.
		ctx.setAttribute("users", userList);
	}
   
}
