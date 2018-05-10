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
}