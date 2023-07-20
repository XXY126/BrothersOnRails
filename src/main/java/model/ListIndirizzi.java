package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListIndirizzi implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Indirizzo> list;
	
	public ListIndirizzi() {
		list = new ArrayList<>();
	}
	
	public void addIndirizzo(Indirizzo i) {
		list.add(i);
	}
	
	public void removeIndirizzo(Indirizzo i) {
		list.remove(i);
	}

	public ArrayList<Indirizzo> getList() {
		return list;
	}

	public void setList(ArrayList<Indirizzo> list) {
		this.list = list;
	}
	
}
