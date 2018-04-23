package fr.uvsq.poo.monprojet;
import java.util.*;


public class Joueur {
	int x,y;
	String dir;
	int vie;
	String type;
	int puissance;
	ArrayList<Objet> inventaire;
	
	/* 	
	 *	créer un joueur de type PNJ ou PJ en fonction de l'argument
	 *	positionné aléatoirement dans une matrice salle
	 *	vie de base = 3, puissance de base = 30, si PNJ pas de vie
	 *
	 */
	
	public Joueur(String type) {
		x = (int) (Math.random() * (Variables.largeur_salle - 1)) + 1;
		y = (int) (Math.random() * (Variables.hauteur_salle - 1)) + 1;
		dir = "haut";
		this.type = type;
		this.puissance = 30;
		if(this.type == "PJ") {
			this.vie = 3;
			this.inventaire = new ArrayList<Objet>();
		}
		else this.vie = 0;		
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int  getPosY() {
		return this.y;
	}
	
	public String getDir() {
		return this.dir;
	}
	
	public Joueur Move(String dir) {
		if(dir == "bas" && this.getPosY() < Variables.hauteur_salle) this.x += 1;
		else if(dir == "haut" && this.getPosY() > 0) this.x -= 1;
		else if(dir == "gauche" && this.getPosX() > 0) this.y -= 1;
		else if(dir == "droite" && this.getPosX() < Variables.largeur_salle) this.y += 1;
		else System.out.println("erreur de direction");		
		this.dir = dir;
		return this;
	}
	
	public ArrayList<Objet> PickUp(Objet Obj){
		 this.inventaire.add(Obj);
		 return this.inventaire;
	}
	
}
