package fr.uvsq.poo.monprojet;

import java.util.ArrayList;

public class Pj extends Joueur {
	protected int vie;
	protected ArrayList<Objet> inventaire;

	public Pj(Salle S) {
		super(S);
		this.puissance = 30;
		this.vie = 3;
		this.inventaire = new ArrayList<Objet>();
		this.type = "PJ";
	}
	
	public Joueur Turn(String dir) {
		this.dir = dir;
		return this;
	}
	
	public ArrayList<Objet> PickUp(Objet Obj){
		 this.inventaire.add(Obj);
		 return this.inventaire;
	}
}