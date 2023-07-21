package model;

public class OrdineSingolo {
	
	private int id_ordine;
	private String id_utente;
	private int quantita;
	private Double costo;
	Prodotto p;

	public OrdineSingolo(int id_ordine, String id_utente, int quantita, Double costo, Prodotto p) {
		this.id_ordine = id_ordine;
		this.id_utente = id_utente;
		this.quantita = quantita;
		this.costo = costo;
		this.p = p;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public String getId_utente() {
		return id_utente;
	}

	public void setId_utente(String id_utente) {
		this.id_utente = id_utente;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	public Prodotto getProdotto() {
		return p;
	}
	
}
