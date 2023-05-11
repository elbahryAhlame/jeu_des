package com.web.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HomePageServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/vues/pages/loginForm.jsp");
        rd.forward(request,response);  
        return ;
    }
}
