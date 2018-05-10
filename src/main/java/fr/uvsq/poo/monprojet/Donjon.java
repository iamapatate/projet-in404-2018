package fr.uvsq.poo.monprojet;
import java.io.Serializable;

public class Donjon implements Serializable{

	private static final long serialVersionUID = 1L;
	Etage niveaux[] = new Etage[Variables.Nb_etages];
	
	public Donjon() {
		for(int i=0;i<Variables.Nb_etages;i++) {
			this.niveaux[i] = new Etage();
		}
	}
	
	public Donjon initDonjon() {
		int nb_Objets_max = 2;
		int nb_PNJs_max = 2;
		for(int i=0;i<Variables.Nb_etages;i++) {
			this.niveaux[i] = new Etage();
			this.niveaux[i].initialisation();
			this.niveaux[i].generation();
			this.niveaux[i].remplirEtage(nb_Objets_max,nb_PNJs_max);
			nb_Objets_max++;
			nb_PNJs_max++;
		}
		return this;
	}
}