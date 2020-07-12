package com.model.dao;

import java.sql.SQLException;

import com.model.Cart;

public interface ProdottoOrdinatoModel {

    public void doProdottiOrdinatiSave(Cart carrello, int idOrdine) throws SQLException;
}