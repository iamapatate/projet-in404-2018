package fr.uvsq.poo.monprojet;

public class UneSalleTemporaire {
	String grillemin[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	boolean Porte_N;
	boolean Porte_E;
	boolean Porte_S;
	boolean Porte_W;
	int Nb_portes;
	int Type;
	
	public UneSalleTemporaire() {		
		for(int i = 0; i < Variables.largeur_salle; i++) {
			for(int j = 0; j < Variables.hauteur_salle; j++) {
				this.grillemin[i][j] = new String();
			}
		}		
		this.Porte_N = true;
		this.Porte_E = false;
		this.Porte_S = false;
		this.Porte_W = false;
		this.Nb_portes = 4;
		this.Type = -1;
	}
	
	/*		Initialise la grille[][] avec des string 
	 * (seront affichés: murs = #, sol = . , portes = P, joueur = +)
	 * 
	 */
	public UneSalleTemporaire initSalleTemp() {
		Joueur J = new Joueur("PJ");
		
		for(int i = 0; i < Variables.largeur_salle; i++) {
			for(int j = 0; j < Variables.hauteur_salle; j++) {
				
				if(i == 0 || j == 0) this.grillemin[i][j] = "mur";
				
				else if(i == 0 || j == Variables.hauteur_salle - 1) 
					this.grillemin[i][j] = "mur";
				
				else if((j == 0) || (i == Variables.largeur_salle - 1) ) 
					this.grillemin[i][j] = "mur";
				
				else if(j == Variables.hauteur_salle - 1 || i == Variables.largeur_salle - 1)
					this.grillemin[i][j] = "mur";
				
				else 
					this.grillemin[i][j] = "sol";
			}
		}
		this.grillemin[J.getPosX()][J.getPosY()] = "PJ";
		if(this.Porte_E == true) {
			this.grillemin[Variables.largeur_salle-1][Variables.hauteur_salle/2] = "porte";
		}
		if(this.Porte_S == true) {
			this.grillemin[Variables.largeur_salle/2][Variables.hauteur_salle] = "porte";
		}
		if(this.Porte_W == true) {
			this.grillemin[0][Variables.hauteur_salle/2] = "porte";
		}
		if(this.Porte_N == true) {
			this.grillemin[Variables.largeur_salle/2][0] = "porte";
		}
		return this;
	}
	
	/*
	 *	Affiche la salle courante 
	 */
	
	public void affSalleTemp() {
		for(int j = 0; j < Variables.hauteur_salle; j++) {
			for(int i = 0; i < Variables.largeur_salle; i++) {
				if(this.grillemin[i][j] == "mur") System.out.print("#");
				else if(this.grillemin[i][j] == "sol") System.out.print(".");
				else if(this.grillemin[i][j] == "PJ") System.out.print("+");
				else if(this.grillemin[i][j] == "porte") System.out.print("P");
			}
			System.out.println("");
		}
	}
}
