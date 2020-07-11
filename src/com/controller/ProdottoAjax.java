package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.ProductBean;
import com.model.dao.ProdottoModelDS;

@WebServlet("/ProdottoAjax")
public class ProdottoAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static ProdottoModelDS ds = new ProdottoModelDS();
	
    public ProdottoAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBean prodotto = null;
		String descrizione = "{}";
		try {
			prodotto = ds.doRetrieveByKey(Integer.parseInt(request.getParameter("codiceprod")));
			descrizione = prodotto.getDescription();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(descrizione);
		pw.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
