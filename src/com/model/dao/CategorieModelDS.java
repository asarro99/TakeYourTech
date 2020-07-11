package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CategorieModelDS implements CategorieModel{
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
