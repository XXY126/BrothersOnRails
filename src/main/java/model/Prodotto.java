package model;

import java.io.Serializable;

public class Prodotto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String idProdotto;
	private String nome;
	private String descrizione;
	private String img;
	private String genere;
	private String categoria;
	private int quantita;
	private double prezzo;
	
	public Prodotto(String idProdotto, String nome, String descrizione, String img, String categoria,
			int quantita, double prezzo) {
		super();
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.img = img;
		this.categoria = categoria;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	
	public String getIdProdotto() {
		return idProdotto;
	}



	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
}
