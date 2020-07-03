package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.ProductBean;
import com.model.Cart;
import com.model.DB;

@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DB ds = new DB();

    public Carrello() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		//Controllo quantita prodotto nel database
		String totale = "";
		Cart carrello = (Cart) request.getSession().getAttribute("cart");
		try {
			ProductBean prodottoOrdinato = (ProductBean) ds.doRetrieveByKey(id);
			if(prodottoOrdinato.getQuantity() >= quantita) {
				for(ProductBean b : carrello.getProducts()) {
					if(b.getCode() == id) {
						b.setQuantity(quantita);
						totale += (b.getPrice() * b.getQuantity());
					}
				}
				totale += " " + Integer.toString(quantita);
				totale += " " + Double.toString(carrello.getTotal());
				response.setContentType("text/plain");
				response.getWriter().write(totale);
			}else {
				for(ProductBean b : carrello.getProducts()) {
					if(b.getCode() == id) {
						b.setQuantity(prodottoOrdinato.getQuantity());
						totale += (b.getPrice() * b.getQuantity());
					}
				}
				totale += " " + Integer.toString(prodottoOrdinato.getQuantity());
				totale += " " + Double.toString(carrello.getTotal());
				response.setContentType("text/plain");
				response.getWriter().write(totale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
