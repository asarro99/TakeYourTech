package com.model.bean;

public class IndirizziBean {
	int code;
	int idUtente;
	String codicePostale;
	String citta;
	String via;

	public IndirizziBean() {
		code = -1;
		citta="";
		via="";
		codicePostale="";
		idUtente=0;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getCodicePostale() {
		return codicePostale;
	}
	
	public void setCodicePostale(String codicePostale) {
		this.codicePostale = codicePostale;
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
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