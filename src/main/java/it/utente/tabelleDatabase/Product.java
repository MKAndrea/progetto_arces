package it.utente.tabelleDatabase;

public class Product {
	private int id;
	private String nome_prodotto ;
	private int prezzo;
	private int idshop;

	public Product() {}

	public int getId() {
		return id;
	}
	public String getnome_prodotto() {
		return nome_prodotto;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public int getIdshop() {
		return idshop;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setnome_prodotto(String nome_prodotto) {
		this.nome_prodotto = nome_prodotto;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public void setIdshop(int idshop) {
		this.idshop = idshop;
	}
}
