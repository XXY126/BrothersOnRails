package model;

import java.io.Serializable;

public class Utente implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String nome;
	private String cognome;
	private String telefono;
	
	public Utente(String email, String nome, String cognome) {
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
