package com.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.Bean.ProductBean;
import com.model.Cart;

public interface ProdottoModel {
	
	public void doSave(ProductBean product) throws SQLException;
	
	public void doUpdate(ProductBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean>  doRetrieveByCategoria(String categoria) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public  Collection<ProductBean> getSediciProdRandom() throws SQLException ;
	
    public  void aggiornaQuantitaProdotti(Cart carrello) throws SQLException ;
    
    public  int getQuantitaProdotto(int idProdotto) throws SQLException ;
}
