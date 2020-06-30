package com.controller;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DB;
import com.model.DBModel;
import com.utility.PasswordHashing;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static DB ds = new DB();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{	
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String redirectedPage = null;
			try {
				ArrayList<String> conferma = ds.Login(email, password);
				request.getSession().setAttribute("roleUtente", conferma.get(0));
				request.getSession().setAttribute("idUtente", conferma.get(1));
				redirectedPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("roleUtente", null);
				redirectedPage = "/login.jsp";
			}
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
	}

	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
