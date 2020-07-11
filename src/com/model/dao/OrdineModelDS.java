package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.model.bean.OrdiniBean;
import com.model.bean.ProductBean;
import com.model.Cart;

public class OrdineModelDS implements OrdineModel{
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
	
	public synchronized void doCartSave(int IdMetPagam, int idUtente,float totalePagamento,String codicePostale,String citta,String via,String codiceTracciab) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO ordine (idMetodoPagamento,idUtente,totalePagamento,codicePostale,citta,via,codiceTracciabilita,Data) VALUES (?,?,?,?,?,?,?,?)";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String data = df.format(new Date());

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, IdMetPagam);
            preparedStatement.setInt(2, idUtente);
            preparedStatement.setFloat(3,totalePagamento);
            preparedStatement.setString(4, codicePostale);
            preparedStatement.setString(5, citta);
            preparedStatement.setString(6, via);
            preparedStatement.setString(7, codiceTracciab);
            preparedStatement.setString(8, data);

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
	
	public synchronized Collection<OrdiniBean> getOridiniUtente(int idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();

		String selectSQL = "SELECT * FROM ordine WHERE idUtente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setTotale(rs.getFloat("totalePagamento"));
				bean.setCodicePostale(rs.getString("codicePostale"));
				bean.setData(rs.getString("Data"));
				bean.setIdUtente(idUtente);
				bean.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				bean.setCode(rs.getInt("idOrdine"));
				bean.setCitta(rs.getString("citta"));
				bean.setVia(rs.getString("via"));
				bean.setCodiceTracciabilita(rs.getString("codiceTracciabilita"));
				
                products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	public synchronized Collection<OrdiniBean> getOridini() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();

		String selectSQL = "SELECT * FROM ordine" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setTotale(rs.getFloat("totalePagamento"));
				bean.setCodicePostale(rs.getString("codicePostale"));
				bean.setData(rs.getString("Data"));
				bean.setIdUtente(rs.getInt("idUtente"));
				bean.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				bean.setCode(rs.getInt("idOrdine"));
				bean.setCitta(rs.getString("citta"));
				bean.setVia(rs.getString("via"));
				bean.setCodiceTracciabilita(rs.getString("codiceTracciabilita"));
				
                products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	public synchronized Collection<OrdiniBean> getOridiniByData(String data1,String data2) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();
		String selectSQL = "SELECT * FROM ordine WHERE Data BETWEEN " + '"'+data1 +'"'+ " AND "+ '"'+data2 +'"';

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setTotale(rs.getFloat("totalePagamento"));
				bean.setCodicePostale(rs.getString("codicePostale"));
				bean.setData(rs.getString("Data"));
				bean.setIdUtente(rs.getInt("idUtente"));
				bean.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				bean.setCode(rs.getInt("idOrdine"));
				bean.setCitta(rs.getString("citta"));
				bean.setVia(rs.getString("via"));
				bean.setCodiceTracciabilita(rs.getString("codiceTracciabilita"));
				
                products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	public synchronized Collection<OrdiniBean> getOridiniById(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();
		String selectSQL = "SELECT * FROM ordini WHERE idUtente = " + id;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setCode(rs.getInt("idOrdine"));
				bean.setDescription(rs.getString("dettagliOrdine"));
                bean.setData(rs.getString("data"));
                bean.setIdUtente(rs.getInt("idUtente"));
                products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	public synchronized Collection<OrdiniBean> getOridiniUtenteData(int idUtente,String data1, String data2) throws SQLException {
   		Connection connection = null;
   		PreparedStatement preparedStatement = null;

   		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();

   		String selectSQL = "SELECT * FROM ordine WHERE idUtente = ? AND data BETWEEN "+ '"'+data1+'"'+" AND " + '"' + 	data2 + '"';

   		try {
   			connection = ds.getConnection();
   			preparedStatement = connection.prepareStatement(selectSQL);
               preparedStatement.setInt(1, idUtente);

   			ResultSet rs = preparedStatement.executeQuery();

   			while (rs.next()) {
   				OrdiniBean bean = new OrdiniBean();

				bean.setTotale(rs.getFloat("totalePagamento"));
				bean.setCodicePostale(rs.getString("codicePostale"));
				bean.setData(rs.getString("Data"));
				bean.setIdUtente(idUtente);
				bean.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				bean.setCode(rs.getInt("idOrdine"));
				bean.setCitta(rs.getString("citta"));
				bean.setVia(rs.getString("via"));
				bean.setCodiceTracciabilita(rs.getString("codiceTracciabilita"));
				
                products.add(bean);
   			}

   		} finally {
   			try {
   				if (preparedStatement != null)
   					preparedStatement.close();
   			} finally {
   				if (connection != null)
   					connection.close();
   			}
   		}
   		return products;
   	}
	
	public synchronized int getidOrdine(int IdUtente) throws SQLException {
  		Connection connection = null;
  		PreparedStatement preparedStatement = null;

  		int idOrdine =0;

  		String selectSQL = "SELECT idOrdine FROM ordine WHERE idUtente = ? ORDER BY idOrdine DESC" ;

  		try {
  			connection = ds.getConnection();
  			preparedStatement = connection.prepareStatement(selectSQL);
  			
  			preparedStatement.setInt(1, IdUtente);
  			ResultSet rs = preparedStatement.executeQuery();
  			
  			rs.next();
  			idOrdine=rs.getInt("idOrdine");


  		} finally {
  			try {
  				if (preparedStatement != null)
  				{
  					preparedStatement.close();
  					return idOrdine;
  				}
  			} finally {
  				if (connection != null)
  				{
  					connection.close();
  					return idOrdine;
  				}
  			}
  		}
  		return idOrdine;
  	}
	
	public synchronized Cart getProdottiOrdine(int idOrder) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Cart prodottiOrdinatiCart = new Cart();

		String selectSQL = "SELECT * FROM prodottoordinato WHERE idOrdine = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idOrder);

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
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return prodottiOrdinatiCart;
	}
	
	public synchronized Collection<OrdiniBean> getOridiniByDataAndIdUtente(String data1,String data2,int idUtente ) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();
		String selectSQL = "SELECT * FROM ordine  WHERE idUtente = ? AND Data BETWEEN " + '"'+data1 +'"'+ " AND "+ '"'+data2 +'"';

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setInt(1, idUtente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setTotale(rs.getFloat("totalePagamento"));
				bean.setCodicePostale(rs.getString("codicePostale"));
				bean.setData(rs.getString("Data"));
				bean.setIdUtente(rs.getInt("idUtente"));
				bean.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				bean.setCode(rs.getInt("idOrdine"));
				bean.setCitta(rs.getString("citta"));
				bean.setVia(rs.getString("via"));
				bean.setCodiceTracciabilita(rs.getString("codiceTracciabilita"));
				
                products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
}
