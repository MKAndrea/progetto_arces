package it.utente.tabelleDatabase;

public class Utente {
private int id;	
private String nome;
private String cognome;
private String username;
private String cellulare;
private String indirizzo;
private int eta;
private String password;



public Utente() {}

public int getId() {
	return id;
}

public String getNome() {
	return nome;
}
public String getCognome() {
	return cognome;
}
public String getUsername() {
	return username;
}
public String getCellulare() {
	return cellulare;
}
public String getIndirizzo() {
	return indirizzo;
}
public int getEta() {
	return eta;
}

public void setId(int id) {
	this.id = id;
}

public void setNome(String nome) {
	this.nome = nome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public void setUsername(String username) {
	this.username = username;
}

public void setCellulare(String cellulare) {
	this.cellulare = cellulare;
}

public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}

public void setEta(int eta) {
	this.eta = eta;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


}
