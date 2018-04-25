package fr.uvsq.poo.monprojet;

import java.util.ArrayList;

public class Pj extends Joueur {
	int vie;
	ArrayList<Objet> inventaire;
	
	
	public Pj() {
		super();
		this.puissance = 30;
		this.vie = 3;
		this.inventaire = new ArrayList<Objet>();		
	}
	
	public Joueur Move(String dir) {
		super.Move(dir);
		return this;
	}
	
	public Joueur Turn(String dir) {
-		super.Turn(dir);
-		return this;
-	}
	
	public ArrayList<Objet> PickUp(Objet Obj){
		 this.inventaire.add(Obj);
		 return this.inventaire;
	}

}
