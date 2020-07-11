package com.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.Bean.metPagaBean;

public interface PagamentoModel {
	
	public int getidMetodoPagamento(int IdUtente) throws SQLException;
	
	public  Collection<metPagaBean> getMetPagaUtente(int idUtente) throws SQLException;
	
	public  void rimuoviMetPaga(int idIndirizzo,int idUtente) throws SQLException;
	
	public  void aggiungiMetodoPagamento(metPagaBean metodoPagamento) throws SQLException;
}
