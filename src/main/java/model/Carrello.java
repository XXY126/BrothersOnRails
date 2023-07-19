package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrello implements Serializable{
	private static final long serialVersionUID = 1L;
	private transient ArrayList<Prodotto> list = new ArrayList<>();
	private String Id;
	
	
	
	public Carrello(String id) {
		this.Id = id;
	}
	
	public List<Prodotto> getCarrello() {
		return list;
	}
	
	public String getId() {
		return Id;
	}

	public void setCarrello(List<Prodotto> carrello) {
		this.list = (ArrayList<Prodotto>) carrello;
	}

	public void add(Prodotto p) {
		list.add(p);
	}
	
	public void empty() {
		list.clear();
	}
	
	public void remove(Prodotto p) {
		for(Prodotto temp : list) {
			if(temp.getIdProdotto().equals(p.getIdProdotto())){
				list.remove(temp);
			}
		}
	}
	
	public void addQuantita(String idProdotto, int incremento) {
	    list.stream()
	        .filter(p -> p.getIdProdotto().equals(idProdotto))
	        .forEach(p -> p.setQuantita(p.getQuantita() + incremento));
	}

	@Override
	public String toString() {
		return "Carrello [Id=" + Id + "]" + list.toString();
	}
}
