package fr.uvsq.poo.monprojet;

import java.util.ArrayList;

public class Pj extends Joueur {
	protected int vie;

	public Pj(Salle S) {
		super(S);
		this.puissance = 30;
		this.vie = 3;
		this.type = "PJ";
	}
	
	public Joueur Turn(String dir) {
		this.dir = dir;
		return this;
	}
	
	public ArrayList<Objet> PickUp(Objet Obj){
		 this.inventaire.add(Obj);
		 if(Obj.getType().equals("lime")) {
			 this.puissance += 5;
		 }
		 if(Obj.getType().equals("surin")) {
			 this.puissance += 15;
		 }
		 if(Obj.getType().equals("pistol")) {
			 this.puissance += 30;
		 }		 
		 return this.inventaire;
	}
	
	public void CheckStatsAndInventory() {
		System.out.println("vie " + this.vie + " puissance " + this.puissance + " Nbobjets " + this.inventaire.size() + "\n");
		for(int i = 0; i < inventaire.size(); i++) {
			System.out.print(this.inventaire.get(i).getType() + " ");
		}
		System.out.println();
	}
	
	public Joueur LoseLife() {
		this.vie -= 1;
		return this;
	}
	
}