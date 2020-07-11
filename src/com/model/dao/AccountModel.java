package com.model.dao;

import java.sql.SQLException;

import com.model.bean.AccountBean;

public interface AccountModel {
	
	public  AccountBean getInformazioniAccount(String idUtente) throws SQLException;
	
}
