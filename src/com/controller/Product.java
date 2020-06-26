package com.controller;

import java.io.File;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Bean.ProductBean;
import com.model.Cart;
import com.model.DB;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR ="/uploadTemp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static DB ds = new DB();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		if(request.getParameter("action") != null)
		{
			if(request.getParameter("action").equals("insert"))
			{
				String nome = request.getParameter("nome");
				String categoria = request.getParameter("categoria");
				String descrizione = request.getParameter("descrizione");
				float prezzo =Float.parseFloat(request.getParameter("prezzo"));
				int quantita = Integer.parseInt(request.getParameter("quantita"));
				int iva = Integer.parseInt(request.getParameter("iva"));
				String pathPhotoString ="";
				
			    String appPath = request.getServletContext().getRealPath("");
			    String savePath = appPath + File.separator + SAVE_DIR;
			         
				File fileSaveDir = new File(savePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}

				for (Part part : request.getParts()) {
					String fileName = extractFileName(part);
					if (fileName != null && !fileName.equals("")) {
						part.write(savePath + File.separator + fileName);
						pathPhotoString=savePath + File.separator + fileName;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountGestione.jsp");
				dispatcher.forward(request, response);
				return;
			}else if (request.getParameter("action").equals("modifica")) {
				int codice = Integer.parseInt(request.getParameter("codice"));
				String categoria =request.getParameter("categoria");
				String name = request.getParameter("nome");
				String description = request.getParameter("descrizione");
				int price = Integer.parseInt(request.getParameter("prezzo"));
				int quantity = Integer.parseInt(request.getParameter("quantita"));
				
				ProductBean bean = new ProductBean();
				bean.setCode(codice);
				bean.setCategoria(categoria);
				bean.setName(name);
				bean.setDescription(description);
				bean.setPrice(price);
				bean.setQuantity(quantity);
				try {
					ds.doUpdate(bean);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountGestione.jsp");
				dispatcher.forward(request, response);
				return;
			}else if (request.getParameter("action").equals("rimozione")) {
				int codice = Integer.parseInt(request.getParameter("codice"));

				try {
					ds.doDelete(codice);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/accountGestione.jsp");
				dispatcher.forward(request, response);
				return;
			}else if (request.getParameter("action").equals("addC")){
				int id = Integer.parseInt(request.getParameter("codiceprod"));
				
				try {
					
					for (int i = 0; i < Integer.parseInt(request.getParameter("quantita")); i++) 
					{
						cart.addProduct(ds.doRetrieveByKey(id));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if (request.getParameter("action").equals("removeC")) {
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					cart.deleteProduct(ds.doRetrieveByKey(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		
		 if (request.getParameter("categoria")!=null) {
				try {
					Collection<ProductBean> prodotti = ds.doRetrieveByCategoria(request.getParameter("categoria"));
					request.setAttribute("prodotti", prodotti);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
		 if (request.getParameter("codiceprod")!=null) {
				try {
					ProductBean prodotto = ds.doRetrieveByKey(Integer.parseInt(request.getParameter("codiceprod")));
					request.setAttribute("prodotto", prodotto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			// TODO Auto-generated catch block
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
	
	 private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length()-1);
	            }
	        }
	        return "";
	    }	

}
