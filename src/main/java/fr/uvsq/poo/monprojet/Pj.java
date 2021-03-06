package fr.uvsq.poo.monprojet;

import java.util.ArrayList;

public class Pj extends Joueur {

	private static final long serialVersionUID = 1L;
	protected int vie;

	public Pj(Salle S) {
		super(S);
		this.puissance = 30;
		this.vie = 3;
		this.type = "PJ";
	}
	
	public int getVie() {
		return this.vie;
	}
	
	public Joueur Turn(String dir) {
		this.dir = dir;
		return this;
	}
	
	public ArrayList<Objet> PickUp(Objet Obj){
		if(!this.inventaire.contains(Obj)) {
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
			if(Obj.getType().equals("vie")) {
				this.inventaire.remove(this.inventaire.indexOf(Obj));
				this.vie += 1;
			}
		}
		else System.out.println("Qu'allez vous faire avec deux " + Obj.getType() + "s" + " ?");
		return this.inventaire; 
	}
	
	public void CheckStatsAndInventory() {
		System.out.println("vie: " + this.vie + ", puissance: " + this.puissance + ", Nombre d'objets: " + this.inventaire.size());
		for(int i = 0; i < inventaire.size(); i++) {
			System.out.print(this.inventaire.get(i).getType() + " ");
		}
		System.out.println();
	}
	
	public Joueur LoseLife() {
		this.vie -= 1;
		return this;
	}
	
	public void removeCle() {
		for(int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i).getType().equals("cle")){
				this.inventaire.remove(i);
			}
		}
	}
	
	public int hasKey() {
		for(int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i).getType().equals("cle")){
				return 1;
			}
		}
		return 0;
	}
	
}
