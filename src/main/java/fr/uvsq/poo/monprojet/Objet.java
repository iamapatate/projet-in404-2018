package fr.uvsq.poo.monprojet;

public class Objet extends DansLaMap{
	String type;
	
	public Objet(String type, Salle S) {
		super(S);
		this.type = type;
	}
	
	String getType() {
		return this.type;
	}
	
}
