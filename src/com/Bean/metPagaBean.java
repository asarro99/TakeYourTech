package com.Bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Cart;

public class metPagaBean {
	int code;
	int idUtente;
	String tipologia;
	String codiceCarta;
	String intestatario;
	String dataDiScadenza;

	public metPagaBean() {
		code = -1;
		codiceCarta="";
		intestatario="";
		tipologia="";
		idUtente=0;
		dataDiScadenza="";
	}
	
	public String getCodiceCarta() {
		return codiceCarta;
	}
	
	public void setCodiceCarta(String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}
	
	public String getDataDiScadenza() {
		return dataDiScadenza;
	}
	
	public void setDataDiScadenza(String dataDiScadenza) {
		this.dataDiScadenza = dataDiScadenza;
	}
	
	public String getIntestatario() {
		return intestatario;
	}
	
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}



    public int getIdUtente() {
		return idUtente;
	}
    
    public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

}
