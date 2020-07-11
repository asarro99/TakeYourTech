package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.model.bean.AccountBean;
import com.model.bean.OrdiniBean;
import com.model.bean.ProductBean;
import com.model.Cart;

public class FatturaModelDS implements FatturaModel{
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
	
	public synchronized ArrayList<Object> getDatiFattura(int idOrdine) throws SQLException{
    	ArrayList<Object> lista = new ArrayList<Object>();
    	
    	OrdiniBean ordine = new OrdiniBean();
    	AccountBean account = new AccountBean();
    	Cart prodottiOrdinatiCart = new Cart();
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String ordineSQL = "SELECT * FROM ordine WHERE idOrdine = ?";
		String utenteSQL = "SELECT * FROM utente WHERE idUtente = ?";
		String prodottiSQL = "SELECT * FROM prodottoordinato WHERE idOrdine = ?" ;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(ordineSQL);
			preparedStatement.setInt(1,idOrdine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ordine.setCode(rs.getInt("idOrdine"));;
				ordine.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				ordine.setIdUtente(rs.getInt("idUtente"));
				ordine.setTotale(rs.getFloat("totalePagamento"));
				ordine.setCodicePostale(rs.getString("codicePostale"));
				ordine.setCitta(rs.getString("citta"));
				ordine.setVia(rs.getString("via"));
				ordine.setCodiceTracciabilita(rs.getString("codiceTracciabilita"));
				ordine.setData(rs.getString("Data"));
			}
			lista.add(ordine);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(utenteSQL);
			preparedStatement.setInt(1,ordine.getIdUtente());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				account.setTipoAccount(rs.getString("tipoAccount"));
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setEmail(rs.getString("email"));
			}
			lista.add(account);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(prodottiSQL);
            preparedStatement.setInt(1, ordine.getCode());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCode(rs.getInt("idProdotto"));
				bean.setQuantity(rs.getInt("quantita"));
				bean.setPrice(rs.getFloat("prezzo"));
				bean.setName(rs.getString("nome"));
				bean.setIva(rs.getInt("iva"));
				

				prodottiOrdinatiCart.addProduct(bean);
			}
			lista.add(prodottiOrdinatiCart);
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
    	
		return lista;
    }
}
