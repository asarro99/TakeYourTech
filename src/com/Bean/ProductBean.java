package com.Bean;

public class ProductBean {
	int code;
	String name;
	String description;
	String categoria;
	int iva;
	float price;
	int quantity;
	String photo;
	byte[] photoByte;

	public ProductBean() {
		code = 0;
		name = "";
		categoria="";
		iva=0;
		description = "";
		quantity = 0;
		photo="";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public int getIva() {
		return iva;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String path) {
		this.photo = path;
	}
	
	public byte[] getPhotoBytes() {
		return photoByte;
	}

	public void setPhotoBytes(byte[] photoB) {
		this.photoByte = photoB;
	}

	@Override
	public String toString() {
		return name + " (" + code + "), " + price + " " + quantity + ". " + description;
	}
}
