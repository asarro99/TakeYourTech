package com.Bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Cart;

public class OrdiniBean {
	int code;
	String description;
	String idUtente;
    String data;
    int totale;

	public OrdiniBean() {
		code = -1;
		description = "";
		data = "";
		idUtente="";
        totale=0;
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

    public String getIdUtente() {
		return idUtente;
	}

	public void setidUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	
	public int getTotale() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Cart carrello = objectMapper.readValue(description, Cart.class);
		return (int) carrello.getTotal();
	}

	public void setTotale(int totale) {
		this.totale = totale;
	}

	@Override
	public String toString() {
		return code + description + data + idUtente;
	}
}
