package com.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.model.bean.OrdiniBean;
import com.model.Cart;

public interface OrdineModel {
	public void doCartSave(int IdMetPagam, int IdUtetne,float totalePagamento,String codicePostale,String citta,String via,String codiceTracciab) throws SQLException;
	
	public Collection<OrdiniBean> getOridiniUtente(int idUtente) throws SQLException;
	
	public Collection<OrdiniBean> getOridini() throws SQLException;
	
	public Collection<OrdiniBean> getOridiniByData(String data1,String data2) throws SQLException;
	
	public Collection<OrdiniBean> getOridiniById(String id) throws SQLException;
	
	public  Collection<OrdiniBean> getOridiniUtenteData(int idUtente,String data1, String data2) throws SQLException;
	
	public int getidOrdine(int IdUtente) throws SQLException;
	
	public  Cart getProdottiOrdine(int idOrder) throws SQLException;
	
	public  Collection<OrdiniBean> getOridiniByDataAndIdUtente(String data1,String data2,int idUtente ) throws SQLException;
}
