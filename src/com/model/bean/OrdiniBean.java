package com.model.bean;

public class OrdiniBean {
	int code;
	String description;
	int idUtente;
	int idMetodoPagamento;
	String codicePostale;
	String citta;
	String via;
	String codiceTracciabilita;
    String data;
    float totale;

	public OrdiniBean() {
		code = -1;
		idMetodoPagamento=0;
		citta="";
		via="";
		codiceTracciabilita="";
		codicePostale="";
		description = "";
		data = "";
		idUtente=0;
        totale=0;
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
	
	public String getCodiceTracciabilita() {
		return codiceTracciabilita;
	}
	
	public void setCodiceTracciabilita(String codiceTracciabilita) {
		this.codiceTracciabilita = codiceTracciabilita;
	}
	
	public int getIdMetodoPagamento() {
		return idMetodoPagamento;
	}
	
	public void setIdMetodoPagamento(int idMetodoPagamento) {
		this.idMetodoPagamento = idMetodoPagamento;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

    public int getIdUtente() {
		return idUtente;
	}
    
    public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	
	public float getTotale(){
		return this.totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}

	@Override
	public String toString() {
		return code + description + data + idUtente;
	}
}
