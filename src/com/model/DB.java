package com.model;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Bean.OrdiniBean;
import com.Bean.ProductBean;
import com.model.DBModel;

public class DB implements DBModel {
	
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
	
	
	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM prodotto WHERE IdProdotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM prodotti" ;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCode(rs.getInt("CODICE"));
				bean.setName(rs.getString("NOME"));
				bean.setDescription(rs.getString("DESCRIZIONE"));
				bean.setPrice(rs.getInt("PREZZO"));
				bean.setQuantity(rs.getInt("QUANTITA"));
				bean.setPhotoBytes(rs.getBytes("IMMAGINE"));
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
	
	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM prodotto WHERE idProdotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCode(rs.getInt("idProdotto"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setIva(rs.getInt("iva"));
				bean.setName(rs.getString("NOME"));
				bean.setDescription(rs.getString("DESCRIZIONE"));
				bean.setPrice(rs.getFloat("PREZZO"));
				bean.setQuantity(rs.getInt("QUANTITA"));
				bean.setPhotoBytes(rs.getBytes("IMMAGINE"));
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
		return bean;
	}
	
	@Override
	public synchronized Collection<ProductBean>  doRetrieveByCategoria(String categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM prodotto WHERE nomeCategoria = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, categoria);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCode(rs.getInt("idProdotto"));
				bean.setCategoria(rs.getString("nomeCategoria"));
				bean.setIva(rs.getInt("iva"));
				bean.setName(rs.getString("NOME"));
				bean.setDescription(rs.getString("DESCRIZIONE"));
				bean.setPrice(rs.getFloat("PREZZO"));
				bean.setQuantity(rs.getInt("QUANTITA"));
				bean.setPhotoBytes(rs.getBytes("IMMAGINE"));
				
				prodotti.add(bean);
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
		return prodotti;
	}
	
	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO prodotto (NomeCategoria, Nome,IVA,Prezzo, Immagine, Descrizione,Quantita) VALUES (?, ?, ?, ?,?,?,?)";

		File file = new File(product.getPhoto());
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getCategoria());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setInt(3, product.getIva());
			preparedStatement.setFloat(4, product.getPrice());
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				try {
					preparedStatement.setBinaryStream(5, fis, fis.available());
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			preparedStatement.setString(6, product.getDescription());
			preparedStatement.setInt(7, product.getQuantity());

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
	
	@Override
	public synchronized void doUpdate(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "UPDATE prodotto SET Nome = ?, Descrizione=?, Prezzo=?, Quantita=? WHERE IdProdotto = ? AND NomeCategoria=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(5, product.getCode());
			preparedStatement.setString(6, product.getCategoria());
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setFloat(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());

			preparedStatement.executeUpdate();
			
			//connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	@Override
	public synchronized void doCartSave(String cart, String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO ordini (dettagliOrdine,data,idUtente) VALUES (?,?,?)";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String data = df.format(new Date());
		System.out.println(data);
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, cart);
            preparedStatement.setString(3, id);
            preparedStatement.setString(2,data);

			preparedStatement.executeUpdate();

			//connection.commit();
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
	
	@Override
	public ArrayList<String> Login(String email, String password) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String querylogin = "SELECT * FROM utente WHERE Email = ?";
		try 
		{
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(querylogin);
			preparedStatement.setString(1, email);
			ResultSet rSet = preparedStatement.executeQuery();
			
			rSet.next();
			String passwordDb =rSet.getString("pwd");
			String tipo = rSet.getString("TipoAccount");
            String id = rSet.getString("idUtente");
            ArrayList<String> risultato = new ArrayList<String>();
			if (passwordDb.equals(password)) 
			{	
				risultato.add(tipo);
				risultato.add(id);
				return risultato;
			}
		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return null;		
	}

    @Override
	public synchronized Collection<OrdiniBean> getOridiniUtente(String idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();

		String selectSQL = "SELECT * FROM ordini WHERE idUtente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, idUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setCode(rs.getInt("idOrdine"));
				bean.setDescription(rs.getString("dettagliOrdine"));
                bean.setData(rs.getString("data"));
                bean.setidUtente(rs.getString("idUtente"));
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
    
    @Override
   	public synchronized Collection<OrdiniBean> getOridiniUtenteData(String idUtente,String data1, String data2) throws SQLException {
   		Connection connection = null;
   		PreparedStatement preparedStatement = null;

   		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();

   		String selectSQL = "SELECT * FROM ordini WHERE idUtente = ? AND data BETWEEN "+ '"'+data1+'"'+" AND " + '"' + 	data2 + '"';

   		try {
   			connection = ds.getConnection();
   			preparedStatement = connection.prepareStatement(selectSQL);
               preparedStatement.setString(1, idUtente);

   			ResultSet rs = preparedStatement.executeQuery();

   			while (rs.next()) {
   				OrdiniBean bean = new OrdiniBean();

   				bean.setCode(rs.getInt("idOrdine"));
   				bean.setDescription(rs.getString("dettagliOrdine"));
                   bean.setData(rs.getString("data"));
                   bean.setidUtente(rs.getString("idUtente"));
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
    
    @Override
	public synchronized Collection<OrdiniBean> getOridini() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();

		String selectSQL = "SELECT * FROM ordini" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setCode(rs.getInt("idOrdine"));
				bean.setDescription(rs.getString("dettagliOrdine"));
                bean.setData(rs.getString("data"));
                bean.setidUtente(rs.getString("idUtente"));
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
    
    @Override
	public synchronized Collection<OrdiniBean> getOridiniByData(String data1,String data2) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdiniBean> products = new LinkedList<OrdiniBean>();
		String selectSQL = "SELECT * FROM ordini WHERE data BETWEEN " + '"'+data1 +'"'+ " AND "+ '"'+data2 +'"';

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdiniBean bean = new OrdiniBean();

				bean.setCode(rs.getInt("idOrdine"));
				bean.setDescription(rs.getString("dettagliOrdine"));
                bean.setData(rs.getString("data"));
                bean.setidUtente(rs.getString("idUtente"));
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
    
    @Override
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
                bean.setidUtente(rs.getString("idUtente"));
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
    
    public synchronized String getCategorie() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String categorieString = "";

		String selectSQL = "SELECT * FROM categoria" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
                categorieString += rs.getString("nomeCategoria") + " ";
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
		return categorieString;
	}
}
