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
		x = (int) Math.random() * 10 * Variables.largeur_salle;
		y = (int) Math.random() * 10 * Variables.hauteur_salle;
		dir = "bas";
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
	
}
