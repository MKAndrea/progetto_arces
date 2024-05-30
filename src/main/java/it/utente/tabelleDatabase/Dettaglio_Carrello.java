package it.utente.tabelleDatabase;

public class Dettaglio_Carrello {
private int id;
private int idcarrello;
private int idProdotto;
private int quantita;

public Dettaglio_Carrello(){}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getIdProdotto() {
	return idProdotto;
}

public void setIdProdotto(int idProdotto) {
	this.idProdotto = idProdotto;
}

public int getQuantita() {
	return quantita;
}

public void setQuantita(int quantita) {
	this.quantita = quantita;
}

public int getIdcarrello() {
	return idcarrello;
}

public void setIdcarrello(int idcarrello) {
	this.idcarrello = idcarrello;
}



}
