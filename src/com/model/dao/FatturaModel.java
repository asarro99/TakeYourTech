package com.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FatturaModel {

    public ArrayList < Object > getDatiFattura(int idOrdine) throws SQLException;
}