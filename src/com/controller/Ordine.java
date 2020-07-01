package com.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;
import com.Bean.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.DB;

/**
 * Servlet implementation class Ordine
 */
@WebServlet("/Ordine")
public class Ordine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DB ds = new DB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ordine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); 
		
			if(action!=null) {
				if(action.equalsIgnoreCase("show")) {
		            String IdUtente = (String) request.getSession().getAttribute("idUtente");
		            if(IdUtente != null)
		            {   
		                try {
							int orderID = Integer.parseInt(request.getParameter("orderID"));
							
							Cart carrello = null;
							
							carrello = ds.getProdottiOrdine(orderID);
							request.setAttribute("ordine", carrello);

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
			}

				}
			
			//Caricamento sidemenu
			try {
				String categorie = ds.getCategorie();
				request.setAttribute("sidemenu", categorie);
				String paginaCorrente = (String) request.getParameter("page");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaCorrente);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
