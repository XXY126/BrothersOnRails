package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Ordine implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private Timestamp data;
	private double totale;
	private String userId;
	private int idIndirizzo;
	private ArrayList<OrdineSingolo> singoli = new ArrayList<>();
	
	public Ordine(int id, Timestamp data, double totale, String userId, int idIndirizzo) {
		super();
		this.id = id;
		this.data = data;
		this.totale = totale;
		this.userId = userId;
		this.idIndirizzo = idIndirizzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public List<OrdineSingolo> getSingoli() {
		return singoli;
	}
	
	public void add(OrdineSingolo o) {
		singoli.add(o);
	}
	
	
	
}
