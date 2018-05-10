package fr.uvsq.poo.monprojet;

import java.util.*;
import java.io.Serializable;

public abstract class Joueur extends DansLaMap implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String dir;
	protected int puissance;
	protected ArrayList<Objet> inventaire;
	protected String type;
		
	public Joueur(Salle S) {
		super(S);
		dir = "up";
		this.inventaire = new ArrayList<Objet>();
	}
		
	public String getDir() {
		return this.dir;
	}
	
	public String getString() {
		return this.type;
	}
	
	/*
	 *  Commandes en anglais
	 */
	public Joueur Move(Salle S, String dir) {
		if(dir.equals("up") && this.getPosY() < Variables.hauteur_salle - 2 && S.getString(this.getPosX(),this.getPosY() + 1).equals("sol")) this.y += 1;
		else if(dir.equals("down") && this.getPosY() > 1 && S.getString(this.getPosX(),this.getPosY() - 1).equals("sol")) this.y -= 1;
		else if(dir.equals("left") && this.getPosX() > 1 && S.getString(this.getPosX()-1,this.getPosY()).equals("sol")) this.x -= 1;
		else if(dir.equals("right") && this.getPosX() < Variables.largeur_salle - 1 && S.getString(this.getPosX()+1, this.getPosY()).equals("sol")) this.x += 1;
		this.dir = dir;
		return this;
	}
	
	public int getAttack() {
		return this.puissance;
	}
	
	public ArrayList<Objet> getInventory(){
		return this.inventaire;
	}
	
	public int IsInInventory(String type) {
		for(int i = 0; i < this.inventaire.size(); i++) {
			if(this.inventaire.get(i).getType().equals(type)) {
				return 1;
			}
		}
		return 0;
	}
}