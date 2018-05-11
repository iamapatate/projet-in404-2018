package fr.uvsq.poo.monprojet;
import java.io.Serializable;

public class Donjon implements Serializable{

	private static final long serialVersionUID = 1L;
	private Etage niveaux[] = new Etage[Variables.Nb_etages];
	
	public Donjon() {
		for(int i=0;i<Variables.Nb_etages;i++) {
			this.niveaux[i] = new Etage();
		}
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
        int salleX = 0; int salleY = 0; int NumEtage = 0;
		getNiveau(NumEtage).GetSalle(salleX, salleY).affSalle();    	
        while(ChangingPlaces != 4 && NumEtage != Variables.Nb_etages) {
    		System.out.println("Vous �tes en Salle " + salleX + " " + salleY + " � l'�tage: " + NumEtage);
    		if(getNiveau(NumEtage).GetSalle(salleX, salleY).getJoueur().getVie() != 0) {
    			ChangingPlaces = com.analyseCommandeJoueur(getNiveau(NumEtage).GetSalle(salleX,salleY), this);	
    			if(ChangingPlaces != -1) {
    				change = getNiveau(NumEtage).GetSalle(salleX, salleY).getJoueur();
    				if(ChangingPlaces == 0) {// haut
    					salleY++;
    					change.EntreSud();
    				}
    				else if(ChangingPlaces == 1) {// droite
    					salleX++;
    					change.EntreEst();
    				}
    				else if(ChangingPlaces == 2) {// bas
    					salleY--;
    					change.EntreNord();
    				}
    				else if(ChangingPlaces == 3) {// gauche
    					salleX--;
    					change.EntreOuest();
    				}
    				else if(ChangingPlaces == 4) {
    					change.removeCle();
    					NumEtage++;
    					salleX = 0;
    					salleY = 0;
    				}
    				getNiveau(NumEtage).GetSalle(salleX, salleY).getInRoom(change);
    				getNiveau(NumEtage).GetSalle(salleX, salleY).Update();
    			}
    		}
    		else {
    			System.out.println("Vous avez failli");
    			break;
    		}
        }
        if(change.getVie()!= 0)System.out.println("C'est gagn�! Vous �tes sortis de la prison sain et sauf!");
	}
}