package model;

import java.io.Serializable;

public class Indirizzo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String via;
	private String cap;
	private String citta;
	private String provincia;
	private String nazione;
	private String numeroCivico;
	private String idUtente;
	
	public Indirizzo(String via, String cap, String citta, String provincia, String nazione, String numeroCivico) {
		super();
		this.via = via;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
		this.nazione = nazione;
		this.numeroCivico = numeroCivico;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
}
