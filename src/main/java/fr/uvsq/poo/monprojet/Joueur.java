package fr.uvsq.poo.monprojet;
import java.util.*;


public class Joueur extends DansLaMap{
	String dir;
	int puissance;
	
	/* 	
	 *	créer un joueur de type PNJ ou PJ en fonction de l'argument
	 *	positionné aléatoirement dans une matrice salle
	 *	vie de base = 3, puissance de base = 30, si PNJ pas de vie
	 *
	 */
	
	public Joueur(Salle S) {
		super(S);
		dir = "up";
		inventaire = new ArrayList<Objet>();
	}
		
	public String getDir() {
		return this.dir;
	}
	
	/*
	 *  Commandes en anglais
	 */
	public Joueur Move(String dir) {
		System.out.println(this.getPosX() + " " + this.getPosY());
		if(dir.equals("down") && this.getPosY() < Variables.hauteur_salle - 1) this.y += 1;
		else if(dir.equals("up") && this.getPosY() > 0) this.y -= 1;
		else if(dir.equals("left") && this.getPosX() > 0) this.x -= 1;
		else if(dir.equals("right") && this.getPosX() < Variables.largeur_salle - 1) this.x += 1;
		else System.out.println("Erreur de direction");		
		this.dir = dir;
		return this;
	}
}
