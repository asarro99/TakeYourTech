CREATE SCHEMA takeyourtech;
USE takeyourtech;

CREATE TABLE utente(
    idUtente INT PRIMARY KEY AUTO_INCREMENT,
    tipoAccount VARCHAR(30) not null,
    nome VARCHAR(20) not null,
    cognome VARCHAR(20) not null,
    email VARCHAR(20) not null,
    pwd VARCHAR(20) not null
);

CREATE TABLE indirizzoSpedizione(
    idIndirizzo INT AUTO_INCREMENT,
    idUtente INT,
    via VARCHAR(40) not null,
    citta VARCHAR(40) not null,
    codicePostale VARCHAR(40) not null,
    PRIMARY KEY(idIndirizzo,idUtente),
    FOREIGN KEY(idUtente) REFERENCES utente(idUtente) 
    on UPDATE CASCADE 
    on DELETE CASCADE
);

CREATE TABLE metodoPagamento(
    idMetodoPagamento INT AUTO_INCREMENT,
    idUtente INT,
    tipologia VARCHAR(40) not null,
    codiceCarta VARCHAR(40) not null,
    intestatario VARCHAR(40) not null,
    dataDiScadenza VARCHAR(40) not null,
    PRIMARY KEY(idMetodoPagamento,idUtente),
    FOREIGN KEY(idUtente) REFERENCES utente(idUtente) 
    on UPDATE CASCADE 
    on DELETE CASCADE
);

CREATE TABLE ordine(
    idOrdine INT AUTO_INCREMENT,
    idMetodoPagamento INT,
    idUtente INT,
    totalePagamento FLOAT not null,
    codicePostale VARCHAR(40) not null,
    citta VARCHAR(40) not null,
    via VARCHAR(40) not null,
    codiceTracciabilita VARCHAR(40) not null,
    PRIMARY KEY(idOrdine,idMetodoPagamento,idUtente),
    FOREIGN KEY(idUtente) REFERENCES utente(idUtente) 
    on UPDATE CASCADE 
    on DELETE CASCADE,
    FOREIGN KEY(idMetodoPagamento) REFERENCES metodoPagamento(idMetodoPagamento) 
    on UPDATE CASCADE 
    on DELETE CASCADE
);

CREATE TABLE categoria(
    nomeCategoria VARCHAR(30) PRIMARY KEY
);

CREATE TABLE prodotto(
    idProdotto INT AUTO_INCREMENT,
    nomeCategoria VARCHAR(30),
    nome VARCHAR(30) not null,
    IVA INT not null,
    prezzo FLOAT not null,
    immagine MEDIUMBLOB not null,
    descrizione JSON not null,
    quantita INT not null,
    PRIMARY KEY(idProdotto,nomeCategoria),
    FOREIGN KEY(nomeCategoria) REFERENCES categoria(nomeCategoria) 
    on UPDATE CASCADE 
    on DELETE CASCADE
);

CREATE TABLE prodottoOrdinato(
    idProdottoOrdinato INT AUTO_INCREMENT,
    idOrdine INT,
    idProdotto INT,
    quantita INT not null,
    prezzo FLOAT not null,
    nome VARCHAR(30) not null,
    IVA INT not null,
    PRIMARY KEY(idProdottoOrdinato,idOrdine,idProdotto),
    FOREIGN KEY(idOrdine) REFERENCES ordine(idOrdine) 
    on UPDATE CASCADE 
    on DELETE CASCADE,
    FOREIGN KEY(idProdotto) REFERENCES prodotto(idProdotto) 
    on UPDATE CASCADE 
    on DELETE CASCADE
);