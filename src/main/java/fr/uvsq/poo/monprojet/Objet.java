package fr.uvsq.poo.monprojet;

public class Objet {
	String type;
		
	/* créer un objet du type en argument (clé, arme, essence, croquettes, whatever)*/
	public Objet(String type){
		this.type = type;
	}
	
	public String get_type() {
		return this.type;
	}
	
}
