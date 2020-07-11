package com.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.model.bean.IndirizziBean;

public interface IndirizziModel {
	
	public ArrayList<String>  getidIndirizzoSpedizione(int IdUtente) throws SQLException;
	
	public  Collection<IndirizziBean> getIndirizziUtente(int idUtente) throws SQLException;
	
    public  void rimuoviIndirizzo(int idIndirizzo,int idUtente) throws SQLException;
    
    public  void aggiungiIndirizzo(IndirizziBean indirizzo) throws SQLException;
}
