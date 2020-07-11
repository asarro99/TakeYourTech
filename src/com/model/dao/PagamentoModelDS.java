package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.model.bean.metPagaBean;

public class PagamentoModelDS implements PagamentoModel{
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

			preparedStatement.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void aggiungiMetodoPagamento(metPagaBean metodoPagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO metodopagamento (idUtente,tipologia,codiceCarta,intestatario,dataDiScadenza) VALUES (?,?,?,?,?)";
				
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			try {
	            preparedStatement.setInt(1,metodoPagamento.getIdUtente());
	            preparedStatement.setString(2,metodoPagamento.getTipologia());
	            preparedStatement.setString(3,metodoPagamento.getCodiceCarta() );
	            preparedStatement.setString(4, metodoPagamento.getIntestatario());
	            preparedStatement.setString(5, metodoPagamento.getDataDiScadenza());

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
