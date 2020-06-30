package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DB;

@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static DB ds = new DB();
	
    public SignIn() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		
		String redirectedPage = null;
		try {
			boolean check = ds.SignIn(email, password, nome, cognome);
			if(check) {
				redirectedPage = "/login.jsp";
			}
		}catch(Exception e) {
			e.printStackTrace();
			redirectedPage = "/login.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

}
