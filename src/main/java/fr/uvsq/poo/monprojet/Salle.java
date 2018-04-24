package fr.uvsq.poo.monprojet;

public class Salle {
	String grillemin[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	boolean Porte_N;
	boolean Porte_E;
	boolean Porte_S;
	boolean Porte_W;
	int Nb_portes;
	int Type;
	
	
	
	public Salle(int largeur, int hauteur) {		
		for(int i = 0; i < largeur; i++) {
			for(int j = 0; j < hauteur; j++) {
				this.grillemin[i][j] = new String();
			}
		}
		
		this.Porte_N = true;
		this.Porte_E = true;
		this.Porte_S = true;
		this.Porte_W = true;
		this.Nb_portes = 4;
		this.Type = -1;
	}
	
	public void aff_salle(){
		for(int i=0;i<Variables.largeur_salle;i++){
			for(int j=0;i<Variables.hauteur_salle;j++){
				if(this.grillemin[i][j] == "sol") {
					System.out.print(" ");
				}
				if(this.grillemin[i][j] == "mur") {
					System.out.print("#");
				}
				if(this.grillemin[i][j] == "PNJ") {
					System.out.print("+");
				}
			}
			System.out.print("\n");
		}
	}
}
