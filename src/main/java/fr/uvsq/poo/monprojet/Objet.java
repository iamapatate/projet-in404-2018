package fr.uvsq.poo.monprojet;

public class Objet extends DansLaMap{
	String type;
		
	/* créer un objet du type en argument (clé, arme, essence, croquettes, whatever)*/
	public Objet(String type, Salle S){
		super(S);
		this.type = type;
	}
	
	public String get_type() {
		return this.type;
	}
	
	
}
