package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Bean.IndirizziBean;

public class IndirizziModelDS implements IndirizziModel{
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

			preparedStatement.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void aggiungiIndirizzo(IndirizziBean indirizzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO indirizzospedizione (idUtente,via,citta,codicePostale) VALUES (?,?,?,?)";
				
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			try {
	            preparedStatement.setInt(1,indirizzo.getIdUtente());
	            preparedStatement.setString(2,indirizzo.getVia());
	            preparedStatement.setString(3,indirizzo.getCitta() );
	            preparedStatement.setString(4, indirizzo.getCodicePostale());

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
