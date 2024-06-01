package it.utente.tabelleDatabase;

public class Carrello {
private int id;
private int idutente;
private int idshops;
private String data_creazione;
private String data_acquisto;
private String payment_code;

public Carrello() {}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getIdutente() {
	return idutente;
}

public void setIdutente(int idutente) {
	this.idutente = idutente;
}

public int getIdshops() {
	return idshops;
}

public void setIdshops(int idshops) {
	this.idshops = idshops;
}

public String getData_creazione() {
	return data_creazione;
}

public void setData_creazione(String data_creazione) {
	this.data_creazione = data_creazione;
}

public String getData_acquisto() {
	return data_acquisto;
}

public void setData_acquisto(String data_acquisto) {
	this.data_acquisto = data_acquisto;
}

public String getPayment_code() {
	return payment_code;
}

public void setPayment_code(String payment_code) {
	this.payment_code = payment_code;
}


}
