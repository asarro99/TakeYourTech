package com.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.model.bean.ProductBean;
import com.model.Cart;

public class ProdottoModelDS implements ProdottoModel{
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
	
	public synchronized void doUpdate(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "UPDATE prodotto SET Nome = ?, Descrizione=?, Prezzo=?, Quantita=? WHERE IdProdotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(5, product.getCode());
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setFloat(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());

			preparedStatement.executeUpdate();
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
	
	public synchronized Collection<ProductBean> getSediciProdRandom() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM prodotto ORDER BY RAND() LIMIT 16" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

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
	
	public synchronized Collection<ProductBean> getSearchProdotti(String ricerca) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();
		
		String selectSQL = "SELECT * FROM prodotto WHERE nome LIKE ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ricerca);

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
	
	public synchronized void aggiornaQuantitaProdotti(Cart carrello) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "UPDATE prodotto SET Quantita=? WHERE idProdotto = ?";
		
		List<ProductBean> prodottiBeans = carrello.getProducts();

		for(int i=0;i<prodottiBeans.size();i++)
		{			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			try {
				int quantitaDisp = getQuantitaProdotto(prodottiBeans.get(i).getCode());
	            preparedStatement.setInt(1,quantitaDisp - prodottiBeans.get(i).getQuantity());
	            preparedStatement.setInt(2, prodottiBeans.get(i).getCode());
	            
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
	
	public synchronized int getQuantitaProdotto(int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int quantita =0;

		String selectSQL = "SELECT quantita FROM prodotto WHERE idProdotto = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1,idProdotto);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
					quantita=rs.getInt("quantita");
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
		return quantita;
	}
}
