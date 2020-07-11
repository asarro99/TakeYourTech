package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.model.bean.ProductBean;
import com.model.Cart;

public class ProdottoOrdinatoModelDS implements ProdottoOrdinatoModel{
private static DataSource ds;
	
	static 
	{
		try 
		{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/storage");
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void doProdottiOrdinatiSave(Cart carrello, int idOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO prodottoordinato (idOrdine,idProdotto,quantita,prezzo,nome,iva) VALUES (?,?,?,?,?,?)";
		
		List<ProductBean> prodottiBeans = carrello.getProducts();
		

		for(int i=0;i<prodottiBeans.size();i++)
		{			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			try {
	            preparedStatement.setInt(1,idOrdine);
	            preparedStatement.setInt(2,prodottiBeans.get(i).getCode());
	            preparedStatement.setInt(3, prodottiBeans.get(i).getQuantity());
	            preparedStatement.setFloat(4, prodottiBeans.get(i).getPrice());
	            preparedStatement.setString(5, prodottiBeans.get(i).getName());
	            preparedStatement.setInt(6, prodottiBeans.get(i).getIva());

				preparedStatement.executeUpdate();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}
		
	}
}
