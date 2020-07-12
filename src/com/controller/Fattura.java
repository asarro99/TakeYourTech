package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.FatturaModelDS;
import com.utility.GeneratoreFattura;

@WebServlet("/Fattura")
public class Fattura extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static FatturaModelDS ds = new FatturaModelDS();

    public Fattura() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pdfFilename = "TakeYourTech.pdf";
        ArrayList < Object > lista = null;
        try {
            lista = ds.getDatiFattura(Integer.parseInt(request.getParameter("ordineID")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GeneratoreFattura.createPDF(pdfFilename, getServletContext().getRealPath("/img/logo.png"), lista);
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=fattura.pdf");

        File my_file = new File(pdfFilename);

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in .read(buffer)) > 0) {
            out.write(buffer, 0, length);
        } in .close();
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}