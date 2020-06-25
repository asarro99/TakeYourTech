package com.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.ProductBean;
import com.model.DB;
@WebServlet("/getPicture")
public class GetPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetPictureServlet() {
        super();
    }
    private static DB ds = new DB();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		if (id != null)
		{
			ProductBean bean;
			try {
				bean = ds.doRetrieveByKey(Integer.parseInt(id));
				byte[] bt = bean.getPhotoBytes();
				ServletOutputStream out = response.getOutputStream();
				if(bt != null)
				{
					out.write(bt);
					response.setContentType("image/jpeg");			
				}
				out.close();
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
