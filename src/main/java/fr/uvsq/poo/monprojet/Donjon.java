package fr.uvsq.poo.monprojet;
import java.io.Serializable;

public class Donjon implements Serializable{

	private static final long serialVersionUID = 1L;
	private Etage niveaux[] = new Etage[Variables.Nb_etages];
	private int salleX;
	private int salleY;
	private int NumEtage;
	
	public int GetSallex() {
		return this.salleX;
	}
	public int GetSalley() {
		return this.salleY;
	}
	public int GetNumEtage() {
		return this.NumEtage;
	}
	public void SetSallex(int x) {
		this.salleX = x;
	}
	public void SetSalley(int y) {
		this.salleY = y;
	}
	public void SetNumEtage(int k) {
		this.NumEtage = k;
	}
	
	public Donjon() {
		for(int i=0;i<Variables.Nb_etages;i++) {
			this.niveaux[i] = new Etage();
		}
		this.salleX=0;
		this.salleY=0;
		this.NumEtage=0;
	}
	public Etage getNiveau(int numero){
		return this.niveaux[numero];
	}
	
	public Donjon initDonjon() {
		int nb_Objets_max = 2;
		int nb_PNJs_max = 2;
		for(int i = 0; i < Variables.Nb_etages; i++) {
			this.niveaux[i] = new Etage();
			this.niveaux[i].initialisation();
			this.niveaux[i].generation();
			this.niveaux[i].remplirEtage(nb_Objets_max,nb_PNJs_max);
			nb_Objets_max++;
			nb_PNJs_max++;
		}
		this.niveaux[0].GetSalle(0,0).modifJoueur(new Pj(this.niveaux[0].GetSalle(0,0)));			
		return this;
	}
	
	public void PlayTheGame(Commande com) {
		int ChangingPlaces = 0; Pj change = new Pj(new Salle());
		getNiveau(this.NumEtage).GetSalle(this.salleX, this.salleY).affSalle();    	
        while(ChangingPlaces != 4 && this.NumEtage != Variables.Nb_etages) {
    		System.out.println("Vous êtes en Salle " + this.salleX + " " + salleY + " à l'étage: " + this.NumEtage);
    		if(getNiveau(this.NumEtage).GetSalle(this.salleX, this.salleY).getJoueur().getVie() != 0) {
    			ChangingPlaces = com.analyseCommandeJoueur(getNiveau(this.NumEtage).GetSalle(this.salleX,this.salleY), this);	
    			if(ChangingPlaces != -1) {
    				change = getNiveau(this.NumEtage).GetSalle(this.salleX, this.salleY).getJoueur();
    				if(ChangingPlaces == 0) {// haut
    					this.salleY++;
    					change.EntreSud();
    				}
    				else if(ChangingPlaces == 1) {// droite
    					this.salleX++;
    					change.EntreEst();
    				}
    				else if(ChangingPlaces == 2) {// bas
    					this.salleY--;
    					change.EntreNord();
    				}
    				else if(ChangingPlaces == 3) {// gauche
    					this.salleX--;
    					change.EntreOuest();
    				}
    				else if(ChangingPlaces == 4) {
    					change.removeCle();
    					this.NumEtage++;
    					this.salleX = 0;
    					this.salleY = 0;
    				}
    				getNiveau(this.NumEtage).GetSalle(this.salleX, this.salleY).getInRoom(change);
    				getNiveau(this.NumEtage).GetSalle(this.salleX, this.salleY).Update();
    			}
    		}
    		else {
    			System.out.println("Vous avez failli");
    			System.exit(0);
    		}
        }
        if(change.getVie()!= 0)System.out.println("C'est gagné! Vous êtes sortis de la prison sain et sauf!");
	}
}
