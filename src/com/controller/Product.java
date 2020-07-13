package com.controller;

import java.io.File;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.bean.IndirizziBean;
import com.model.bean.OrdiniBean;
import com.model.bean.ProductBean;
import com.model.bean.metPagaBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Cart;
import com.model.dao.CategorieModelDS;
import com.model.dao.IndirizziModelDS;
import com.model.dao.OrdineModelDS;
import com.model.dao.PagamentoModelDS;
import com.model.dao.ProdottoModelDS;
import com.model.dao.ProdottoOrdinatoModelDS;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Product extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static String SAVE_DIR = "/uploadTemp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
    }

    private static ProdottoModelDS ds = new ProdottoModelDS();
    private static PagamentoModelDS ds2 = new PagamentoModelDS();
    private static IndirizziModelDS ds3 = new IndirizziModelDS();
    private static OrdineModelDS ds4 = new OrdineModelDS();
    private static ProdottoOrdinatoModelDS ds5 = new ProdottoOrdinatoModelDS();
    private static CategorieModelDS ds6 = new CategorieModelDS();

    /***
     * Insert = rigo 79
     * Modifica = riga 121
     * Rimozone = riga 141
     * AddC = riga 153
     * RemoveC = riga 188
     * Checkout = riga 195
     * Ordini = riga 224
     * Indirizzi = riga 243
     * MetPagamenti = riga 281
     * IndexProd =  riga 320
     * OrdiniAdmin = riga 327
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("insert")) {
                String nome = request.getParameter("nome");
                String categoria = request.getParameter("categoria");
                String descrizione = request.getParameter("descrizione");
                float prezzo = Float.parseFloat(request.getParameter("prezzo"));
                int quantita = Integer.parseInt(request.getParameter("quantita"));
                int iva = Integer.parseInt(request.getParameter("iva"));
                String pathPhotoString = "";

                String appPath = request.getServletContext().getRealPath("");
                String savePath = appPath + File.separator + SAVE_DIR;

                File fileSaveDir = new File(savePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }

                for (Part part: request.getParts()) {
                    String fileName = extractFileName(part);
                    if (fileName != null && !fileName.equals("")) {
                        part.write(savePath + File.separator + fileName);
                        pathPhotoString = savePath + File.separator + fileName;
                    }
                }

                ProductBean bean = new ProductBean();
                bean.setName(nome);
                bean.setCategoria(categoria);
                bean.setDescription(descrizione);
                bean.setPrice(prezzo);
                bean.setIva(iva);
                bean.setQuantity(quantita);
                bean.setPhoto(pathPhotoString);
                try {
                    ds.doSave(bean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountGestione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (request.getParameter("action").equals("modifica")) {
                int codice = Integer.parseInt(request.getParameter("codice"));
                String name = request.getParameter("nome");
                int price = Integer.parseInt(request.getParameter("prezzo"));
                int quantity = Integer.parseInt(request.getParameter("quantita"));

                ProductBean bean = new ProductBean();
                bean.setCode(codice);
                bean.setName(name);
                bean.setPrice(price);
                bean.setQuantity(quantity);
                try {
                    ds.doUpdate(bean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountGestione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (request.getParameter("action").equals("rimozione")) {
                int codice = Integer.parseInt(request.getParameter("codice"));

                try {
                    ds.doDelete(codice);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountGestione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (request.getParameter("action").equals("addC")) {
                int id = Integer.parseInt(request.getParameter("codiceprod"));

                try {
                    int quantitaDisponibile = ((ProductBean) ds.doRetrieveByKey(id)).getQuantity();

                    if (quantitaDisponibile > Integer.parseInt(request.getParameter("quantita"))) {
                        for (int i = 0; i < Integer.parseInt(request.getParameter("quantita")); i++) {
                            try {
                                ProductBean prodotto = ds.doRetrieveByKey(id);
                                prodotto.setQuantity(1);
                                cart.addProduct(prodotto);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        for (int i = 0; i < quantitaDisponibile; i++) {
                            try {
                                ProductBean prodotto = ds.doRetrieveByKey(id);
                                prodotto.setQuantity(1);
                                cart.addProduct(prodotto);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            } else if (request.getParameter("action").equals("removeC")) {
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    cart.deleteProduct(ds.doRetrieveByKey(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("action").equals("checkout")) {
                String role = (String) request.getSession().getAttribute("roleUtente");
                if (role != null && role.equals("utente")) {
                    try {
                        int idMetodoPagamento = ds2.getidMetodoPagamento(Integer.parseInt((String) request.getSession().getAttribute("idUtente")));
                        ArrayList < String > IndirizzoSpedizione = ds3.getidIndirizzoSpedizione(Integer.parseInt((String) request.getSession().getAttribute("idUtente")));

                        if (idMetodoPagamento == 0) {
                            response.sendRedirect(request.getContextPath() + "/metodiDiPagamento.jsp");
                            return;
                        } else {
                            if (IndirizzoSpedizione.size() == 0) {
                                response.sendRedirect(request.getContextPath() + "/indirizzi.jsp");
                                return;
                            } else {
                                ds4.doCartSave(idMetodoPagamento, Integer.parseInt((String) request.getSession().getAttribute("idUtente")), (float) cart.getTotal(), IndirizzoSpedizione.get(3), IndirizzoSpedizione.get(2), IndirizzoSpedizione.get(1), "2353V2HJ5V");
                                int idOrdine = ds4.getidOrdine(Integer.parseInt((String) request.getSession().getAttribute("idUtente")));
                                ds5.doProdottiOrdinatiSave(cart, idOrdine);
                                ds.aggiornaQuantitaProdotti(cart);
                                cart.removeAll();
                            }
                        }
                    } catch (JsonProcessingException | SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }
            } else if (request.getParameter("action").equals("ordini")) {
                String IdUtente = (String) request.getSession().getAttribute("idUtente");
                if (IdUtente != null) {
                    try {
                        String data1 = request.getParameter("data1");
                        String data2 = request.getParameter("data2");

                        if (data1 != null && data2 != null && !data1.equals("2017-06-01") && !data2.equals("2017-06-01")) {
                            Collection < OrdiniBean > ordini = ds4.getOridiniUtenteData(Integer.parseInt(IdUtente), data1, data2);
                            request.setAttribute("ordini", ordini);
                        } else {
                            Collection < OrdiniBean > ordini = ds4.getOridiniUtente(Integer.parseInt(IdUtente));
                            request.setAttribute("ordini", ordini);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (request.getParameter("action").equals("indirizzi")) {

                String IdUtente = (String) request.getSession().getAttribute("idUtente");

                if (request.getParameter("type") != null && request.getParameter("type").equals("rimuovi")) {
                    try {
                        ds3.rimuoviIndirizzo(Integer.parseInt(request.getParameter("idind")), Integer.parseInt(IdUtente));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (request.getParameter("type") != null && request.getParameter("type").equals("ins")) {
                    IndirizziBean indirizzo = new IndirizziBean();
                    indirizzo.setIdUtente(Integer.parseInt((String) request.getSession().getAttribute("idUtente")));
                    indirizzo.setVia(request.getParameter("via"));
                    indirizzo.setCitta(request.getParameter("citta"));
                    indirizzo.setCodicePostale(request.getParameter("CAP"));

                    try {
                        ds3.aggiungiIndirizzo(indirizzo);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (IdUtente != null) {
                    try {

                        Collection < IndirizziBean > indirizzi = ds3.getIndirizziUtente(Integer.parseInt(IdUtente));
                        request.setAttribute("indirizzi", indirizzi);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } else if (request.getParameter("action").equals("metPagamento")) {

                String IdUtente = (String) request.getSession().getAttribute("idUtente");

                if (request.getParameter("type") != null && request.getParameter("type").equals("rimuovi")) {
                    try {
                        ds2.rimuoviMetPaga(Integer.parseInt(request.getParameter("idind")), Integer.parseInt(IdUtente));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (request.getParameter("type") != null && request.getParameter("type").equals("ins")) {
                    metPagaBean met = new metPagaBean();
                    met.setIdUtente(Integer.parseInt((String) request.getSession().getAttribute("idUtente")));
                    met.setTipologia(request.getParameter("tipologia"));
                    met.setDataDiScadenza(request.getParameter("scadenza"));
                    met.setIntestatario(request.getParameter("intestatario"));
                    met.setCodiceCarta(request.getParameter("codiceCarta"));

                    try {
                        ds2.aggiungiMetodoPagamento(met);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (IdUtente != null) {
                    try {

                        Collection < metPagaBean > metPag = ds2.getMetPagaUtente(Integer.parseInt(IdUtente));
                        request.setAttribute("metPagamento", metPag);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } else if (request.getParameter("action").equals("indexProd")) {
                try {
                    Collection < ProductBean > prodotti = ds.getSediciProdRandom();
                    request.setAttribute("prodotti", prodotti);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("action").equals("ordiniAdmin")) {

                try {

                    String data1 = request.getParameter("data1");
                    String data2 = request.getParameter("data2");
                    String idUtente = request.getParameter("idUtenteRic");

                    if (data1 != null && data2 != null && !data1.equals("2017-06-01") && !data2.equals("2017-06-01")) {
                        if (idUtente != null && idUtente != "") {
                            Collection < OrdiniBean > ordini = ds4.getOridiniByDataAndIdUtente(data1, data2, Integer.parseInt(idUtente));
                            request.setAttribute("ordini", ordini);
                        } else {
                            Collection < OrdiniBean > ordini = ds4.getOridiniByData(data1, data2);
                            request.setAttribute("ordini", ordini);
                        }

                    } else {

                        if (idUtente != null && idUtente != "") {
                            Collection < OrdiniBean > ordini = ds4.getOridiniUtente(Integer.parseInt(idUtente));
                            request.setAttribute("ordini", ordini);
                        } else {
                            Collection < OrdiniBean > ordini = ds4.getOridini();
                            request.setAttribute("ordini", ordini);
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if (request.getParameter("categoria") != null) {
            if (request.getParameter("categoria").equals("Piu_venduti")) {
                try {
                    Collection < ProductBean > prodotti = ds.getSediciProdRandom();
                    request.setAttribute("prodotti", prodotti);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("categoria").equals("ricerca")) {
                try {
                    String ric = request.getParameter("par");
                    Collection < ProductBean > prodotti = ds.getSearchProdotti("%" + ric + "%");
                    request.setAttribute("prodotti", prodotti);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Collection < ProductBean > prodotti = ds.doRetrieveByCategoria(request.getParameter("categoria"));
                    request.setAttribute("prodotti", prodotti);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if (request.getParameter("codiceprod") != null) {
            try {
                ProductBean prodotto = ds.doRetrieveByKey(Integer.parseInt(request.getParameter("codiceprod")));
                request.setAttribute("prodotto", prodotto);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Caricamento sidemenu
        try {
            String categorie = ds6.getCategorie();
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

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s: items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}