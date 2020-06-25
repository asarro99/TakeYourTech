package com.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.Bean.OrdiniBean;
import com.Bean.ProductBean;


public interface DBModel {
	public void doSave(ProductBean product) throws SQLException;
	
	public void doCartSave(String cart, String id) throws SQLException;
	
	public void doUpdate(ProductBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public ArrayList<String> Login(String username,String password) throws SQLException ;
	
	public Collection<OrdiniBean> getOridiniUtente(String idUtente) throws SQLException;
	
	public Collection<OrdiniBean> getOridini() throws SQLException;
	
	public Collection<OrdiniBean> getOridiniByData(String data1,String data2) throws SQLException;
	
	public Collection<OrdiniBean> getOridiniById(String id) throws SQLException;
	
	public  Collection<OrdiniBean> getOridiniUtenteData(String idUtente,String data1, String data2) throws SQLException;
}
