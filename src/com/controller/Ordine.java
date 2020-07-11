package com.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;
import com.model.dao.CategorieModelDS;
import com.model.dao.OrdineModelDS;

/**
 * Servlet implementation class Ordine
 */
@WebServlet("/Ordine")
public class Ordine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static OrdineModelDS ds = new OrdineModelDS();
	private static CategorieModelDS ds2 = new CategorieModelDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ordine() {
        super();
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
							e.printStackTrace();
						}
		            }
			}

				}
			
			//Caricamento sidemenu
			try {
				String categorie = ds2.getCategorie();
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
		doGet(request, response);
	}

}
