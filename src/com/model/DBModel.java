package com.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.Bean.IndirizziBean;
import com.Bean.OrdiniBean;
import com.Bean.ProductBean;
import com.Bean.metPagaBean;


public interface DBModel {
	public void doSave(ProductBean product) throws SQLException;
	
	public void doCartSave(int IdMetPagam, int IdUtetne,float totalePagamento,String codicePostale,String citta,String via,String codiceTracciab) throws SQLException;
	
	public void doUpdate(ProductBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean>  doRetrieveByCategoria(String categoria) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public ArrayList<String> Login(String username,String password) throws SQLException ;
	
	public boolean SignIn(String email, String password, String nome, String cognome) throws SQLException ;
	
	public Collection<OrdiniBean> getOridiniUtente(int idUtente) throws SQLException;
	
	public Collection<OrdiniBean> getOridini() throws SQLException;
	
	public Collection<OrdiniBean> getOridiniByData(String data1,String data2) throws SQLException;
	
	public Collection<OrdiniBean> getOridiniById(String id) throws SQLException;
	
	public  Collection<OrdiniBean> getOridiniUtenteData(int idUtente,String data1, String data2) throws SQLException;
	
	public String getCategorie() throws SQLException;
	
	public int getidMetodoPagamento(int IdUtente) throws SQLException;
	
	public int getidOrdine(int IdUtente) throws SQLException;
	
	public ArrayList<String>  getidIndirizzoSpedizione(int IdUtente) throws SQLException;
	
	public void doProdottiOrdinatiSave(Cart carrello, int idOrdine) throws SQLException;
	
	public  Collection<IndirizziBean> getIndirizziUtente(int idUtente) throws SQLException;
	
    public  void rimuoviIndirizzo(int idIndirizzo,int idUtente) throws SQLException;
    
    public  Collection<metPagaBean> getMetPagaUtente(int idUtente) throws SQLException;
    
    public  void rimuoviMetPaga(int idIndirizzo,int idUtente) throws SQLException;
    
    public  Cart getProdottiOrdine(int idOrder) throws SQLException;
}
