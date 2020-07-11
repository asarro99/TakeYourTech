package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.model.bean.AccountBean;

public class AccountModelDS implements AccountModel{
	
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
	
	public synchronized AccountBean getInformazioniAccount(String idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AccountBean account = new AccountBean();

		String selectSQL = "SELECT * FROM utente WHERE idUtente = ?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,idUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
					account.setTipoAccount(rs.getString("tipoAccount"));
					account.setNome(rs.getString("nome"));
					account.setCognome(rs.getString("cognome"));
					account.setEmail(rs.getString("email"));
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
		return account;
	}
	
}
