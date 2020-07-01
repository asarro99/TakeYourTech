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
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Bean.IndirizziBean;
import com.Bean.OrdiniBean;
import com.Bean.ProductBean;
import com.Bean.metPagaBean;
import com.model.DBModel;
import com.utility.PasswordHashing;

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
	public synchronized void doCartSave(int IdMetPagam, int IdUtetne,float totalePagamento,String codicePostale,String citta,String via,String codiceTracciab) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO ordine (idMetodoPagamento,idUtente,totalePagamento,codicePostale,citta,via,codiceTracciabilita,Data) VALUES (?,?,?,?,?,?,?,?)";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String data = df.format(new Date());

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, IdMetPagam);
            preparedStatement.setInt(2, IdUtetne);
            preparedStatement.setFloat(3,totalePagamento);
            preparedStatement.setString(4, codicePostale);
            preparedStatement.setString(5, citta);
            preparedStatement.setString(6, via);
            preparedStatement.setString(7, codiceTracciab);
            preparedStatement.setString(8, data);

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
			String passwordDb = rSet.getString("pwd");
			String tipo = rSet.getString("TipoAccount");
            String id = rSet.getString("idUtente");
            ArrayList<String> risultato = new ArrayList<String>();
			if (PasswordHashing.autenticazione(password, passwordDb))
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
	public boolean SignIn(String email, String password, String nome, String cognome) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String querySignIn = "INSERT INTO utente (tipoAccount,nome,cognome,email,pwd) VALUES (?,?,?,?,?)";
		try {
			connection = ds.getConnection();
			
			//Generazione password criptata
			String hashPassword = PasswordHashing.generatore(password);
			
			preparedStatement = connection.prepareStatement(querySignIn);
			preparedStatement.setString(1, "utente");
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, cognome);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, hashPassword);
			
			preparedStatement.executeUpdate();
			return true;
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
    
    @Override
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
    
    public synchronized int getidMetodoPagamento(int IdUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int datiMetodoPagamento =0;

		String selectSQL = "SELECT * FROM metodopagamento WHERE idUtente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, IdUtente);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.next();
			datiMetodoPagamento=rs.getInt("idMetodoPagamento");


		} finally {
			try {
				if (preparedStatement != null)
				{
					preparedStatement.close();
					return datiMetodoPagamento;
				}
			} finally {
				if (connection != null)
				{
					connection.close();
					return datiMetodoPagamento;
				}
			}
		}
		return datiMetodoPagamento;
	}
    
    
    public synchronized ArrayList<String>  getidIndirizzoSpedizione(int IdUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<String> inidirizzoSpedizioneDati = new ArrayList<String>();

		String selectSQL = "SELECT * FROM indirizzospedizione WHERE idUtente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, IdUtente);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.next();
			inidirizzoSpedizioneDati.add(Integer.toString(rs.getInt("idIndirizzo")));
			inidirizzoSpedizioneDati.add(rs.getString("via"));
			inidirizzoSpedizioneDati.add(rs.getString("citta"));
			inidirizzoSpedizioneDati.add(rs.getString("codicePostale"));

		} finally {
			try {
				if (preparedStatement != null)
				{
					preparedStatement.close();
					return inidirizzoSpedizioneDati;
				}
			} finally {
				if (connection != null)
				{
					connection.close();
					return inidirizzoSpedizioneDati;
				}
			}
		}
		return inidirizzoSpedizioneDati;
	}
    
    public synchronized int getidOrdine(int IdUtente) throws SQLException {
  		Connection connection = null;
  		PreparedStatement preparedStatement = null;

  		int idOrdine =0;

  		String selectSQL = "SELECT idOrdine FROM ordine WHERE idUtente = ? ORDER BY idOrdine " ;

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
    
	@Override
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
		
	}
	
	   @Override
		public synchronized Collection<IndirizziBean> getIndirizziUtente(int idUtente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<IndirizziBean> indirizzi = new LinkedList<IndirizziBean>();

			String selectSQL = "SELECT * FROM indirizzospedizione WHERE idUtente = ?" ;

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, idUtente);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					IndirizziBean bean = new IndirizziBean();

	
					bean.setCodicePostale(rs.getString("codicePostale"));
					bean.setIdUtente(idUtente);
					bean.setCode(rs.getInt("idIndirizzo"));
					bean.setCitta(rs.getString("citta"));
					bean.setVia(rs.getString("via"));
					
	                indirizzi.add(bean);
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
			return indirizzi;
		}
	   
	   public synchronized void rimuoviIndirizzo(int idIndirizzo,int idUtente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String selectSQL = "DELETE FROM indirizzospedizione WHERE idIndirizzo = ? AND idUtente = ?" ;

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, idIndirizzo);
	            preparedStatement.setInt(2, idUtente);

				int rs = preparedStatement.executeUpdate();


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   @Override
		public synchronized Collection<metPagaBean> getMetPagaUtente(int idUtente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<metPagaBean> indirizzi = new LinkedList<metPagaBean>();

			String selectSQL = "SELECT * FROM metodopagamento WHERE idUtente = ?" ;

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, idUtente);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					metPagaBean bean = new metPagaBean();

	
					bean.setTipologia(rs.getString("tipologia"));
					bean.setIdUtente(idUtente);
					bean.setCode(rs.getInt("idMetodoPagamento"));
					bean.setCodiceCarta(rs.getString("codiceCarta"));
					bean.setIntestatario(rs.getString("intestatario"));
					bean.setDataDiScadenza(rs.getString("dataDiScadenza"));
					
	                indirizzi.add(bean);
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
			return indirizzi;
		}
		
		   public synchronized void rimuoviMetPaga(int idMetPag,int idUtente) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				String selectSQL = "DELETE FROM metodopagamento WHERE idMetodoPagamento = ? AND idUtente = ?" ;

				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
		            preparedStatement.setInt(1, idMetPag);
		            preparedStatement.setInt(2, idUtente);

					int rs = preparedStatement.executeUpdate();


				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		   
		   @Override
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
}
