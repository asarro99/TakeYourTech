package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.AccountBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.model.dao.AccountModelDS;

@WebServlet("/Account")
public class Account extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static AccountModelDS ds = new AccountModelDS();

    public Account() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountBean account = null;
        String idUtente = (String) request.getSession().getAttribute("idUtente");
        String json = "";
        try {
            account = ds.getInformazioniAccount(idUtente);
            //CREAZIONE JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.createObjectNode();
            ((ObjectNode) rootNode).put("Tipo Account", account.getTipoAccount());
            ((ObjectNode) rootNode).put("Nome", account.getNome());
            ((ObjectNode) rootNode).put("Cognome", account.getCognome());
            ((ObjectNode) rootNode).put("Email", account.getEmail());
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}