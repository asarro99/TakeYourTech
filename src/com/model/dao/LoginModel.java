package com.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginModel {
	
	public ArrayList<String> Login(String username,String password) throws SQLException ;
	
	public boolean SignIn(String email, String password, String nome, String cognome) throws SQLException ;
}
