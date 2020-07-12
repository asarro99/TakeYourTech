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

import com.utility.PasswordHashing;

public class LoginModelDS implements LoginModel {
    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/storage");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public ArrayList < String > Login(String email, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String querylogin = "SELECT * FROM utente WHERE Email = ?";
        try {
            connection = ds.getConnection();

            preparedStatement = connection.prepareStatement(querylogin);
            preparedStatement.setString(1, email);
            ResultSet rSet = preparedStatement.executeQuery();

            rSet.next();
            String passwordDb = rSet.getString("pwd");
            String tipo = rSet.getString("TipoAccount");
            String id = rSet.getString("idUtente");
            ArrayList < String > risultato = new ArrayList < String > ();
            if (PasswordHashing.autenticazione(password, passwordDb)) {
                risultato.add(tipo);
                risultato.add(id);
                return risultato;
            } else {
                return null;
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
    }

    public boolean SignIn(String email, String password, String nome, String cognome) throws SQLException {
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