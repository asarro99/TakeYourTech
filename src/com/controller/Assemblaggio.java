package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Cart;
import com.model.bean.ProductBean;
import com.model.dao.ProdottoModelDS;


@WebServlet("/Assemblaggio")
public class Assemblaggio extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ProdottoModelDS ds = new ProdottoModelDS();

    public Assemblaggio() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection < ProductBean > products = ds.getProdottiConfiguratore();
            request.getSession().setAttribute("configuratore", products);
            response.sendRedirect(request.getContextPath() + "/assemblaggio.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametri configurazione
        int[] id = new int[7];
        id[0] = Integer.parseInt(request.getParameter("cpubase"));
        id[1] = Integer.parseInt(request.getParameter("gpubase"));
        id[2] = Integer.parseInt(request.getParameter("hddbase"));
        id[3] = Integer.parseInt(request.getParameter("alibase"));
        id[4] = Integer.parseInt(request.getParameter("rambase"));
        id[5] = Integer.parseInt(request.getParameter("casebase"));
        id[6] = 100;

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        for (int i = 0; i < 7; i++) {
            ProductBean prodotto;
            try {
                prodotto = ds.doRetrieveByKey(id[i]);
                prodotto.setQuantity(1);
                cart.addProduct(prodotto);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/carrello.jsp");
    }

}