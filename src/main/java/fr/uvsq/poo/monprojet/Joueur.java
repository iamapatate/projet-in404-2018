package Projet;
import java.util.*;


public class Joueur {
	int x,y;
	String dir;
	int puissance;
	
	/* 	
	 *	cr�er un joueur de type PNJ ou PJ en fonction de l'argument
	 *	positionn� al�atoirement dans une matrice salle
	 *	vie de base = 3, puissance de base = 30, si PNJ pas de vie
	 *
	 */
	
	public Joueur() {
		x = (int) (Math.random() * (Variables.largeur_salle - 1)) + 1;
		y = (int) (Math.random() * (Variables.hauteur_salle - 1)) + 1;
		dir = "haut";
		this.puissance = 0;
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
	
	public Joueur Turn(String dir) {
-		this.dir = dir;
-		return this;
-	}
	
	
}
