package fr.uvsq.poo.monprojet;


public class Etage {
	
	Salle grille[][];
	String grillestring[][];
	
	public Etage() {
		for(int i = 0; i < Variables.Nb_largeur_salles;i++) {
			for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
				try {
					this.grille[i][j] = new Salle(Variables.largeur_salle, Variables.hauteur_salle);
				}
				catch(Exception e) {System.out.println("probleme init salle de grille étage");}
			}
		}
		this.grillestring = new String[Variables.Nb_largeur_salles * Variables.largeur_salle][Variables.Nb_hauteur_salles * Variables.hauteur_salle];
	}
	
	
	public Etage Init_etage() {
		
		/* On met à "false" toutes les portes qui donnent vers l'extérieur de la grille (on ne veut pas de porte qui donne vers le néant) */
		for(int i = 0; i < Variables.Nb_largeur_salles; i++) {
			this.grille[i][0].Porte_N = false;
			this.grille[i][Variables.Nb_largeur_salles-1].Porte_S = false;
			this.grille[i][0].Nb_portes -= 1;
			this.grille[i][Variables.Nb_largeur_salles-1].Nb_portes -= 1;
		}
		for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
			this.grille[0][j].Porte_W = false;
			this.grille[0][j].Nb_portes -= 1;
			this.grille[Variables.Nb_hauteur_salles-1][j].Porte_E = false;
			this.grille[Variables.Nb_hauteur_salles-1][j].Nb_portes -= 1;
		}
		
		/* On crée des murs au hasard */
		for(int i = 0; i < Variables.Nb_largeur_salles; i++) {
			for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
				for(int k = 0; (k < 4) && (this.grille[i][j].Nb_portes > 1); k++) {
					if(this.grille[i][j].Porte_N == true) {
						double proba = Math.random();
						if(proba < 0.33) {
							this.grille[i][j].Porte_N = false;
							this.grille[i][j].Nb_portes--;
						}
					}
					if(this.grille[i][j].Porte_E == true) {
						double proba = Math.random();
						if(proba < 0.33) {
							this.grille[i][j].Porte_E = false;
							this.grille[i][j].Nb_portes--;
						}
					}
					if(this.grille[i][j].Porte_S == true) {
						double proba = Math.random();
						if(proba < 0.33) {
							this.grille[i][j].Porte_S = false;
							this.grille[i][j].Nb_portes--;
						}
					}
					if(this.grille[i][j].Porte_W == true) {
						double proba = Math.random();
						if(proba < 0.33) {
							this.grille[i][j].Porte_W = false;
							this.grille[i][j].Nb_portes--;
						}
					}
				}
			}
		}
		
		
		/* On génère les portes de chaque salle en fonctions des salles voisines (On ne teste que les salles voisines accessibles) */
		for(int i = 0; i < Variables.Nb_largeur_salles; i++) {
			for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
				if(this.grille[i][j].Porte_N == true) {
					if(this.grille[i][j+1].Porte_S == false) {
						this.grille[i][j].Porte_N = false;
						this.grille[i][j].Nb_portes--;
					}
				}
				if(this.grille[i][j].Porte_E == true) {
					if(this.grille[i+1][j].Porte_W == false) {
						this.grille[i][j].Porte_E = false;
						this.grille[i][j].Nb_portes--;
					}
				}
				if(this.grille[i][j].Porte_S == true) {
					if(this.grille[i][j-1].Porte_N == false) {
						this.grille[i][j].Porte_S = false;
						this.grille[i][j].Nb_portes--;
					}
				}
				if(this.grille[i][j].Porte_W == true) {
					if(this.grille[i-1][j].Porte_E == false) {
						this.grille[i][j].Porte_W = false;
						this.grille[i][j].Nb_portes--;
					}
				}
			}
		}
		
		/* On remplit la grille de la salle avec du sol ou des murs */
		for(int i = 0; i < Variables.Nb_largeur_salles; i++) {
			for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
				for(int x = 0; x < Variables.largeur_salle; x++) {
					for(int y = 0; y < Variables.hauteur_salle; y++) {
						if(x == 0 || x == Variables.largeur_salle-1 || y == 0 || y == Variables.hauteur_salle-1) {
							this.grille[i][j].grillemin[x][y] = "mur";
						}
						else {
							this.grille[i][j].grillemin[x][y] = "sol";
						}
					}
				}
				/* Ici on enlève les murs où il y a une porte et les remplace par du sol */
				if(this.grille[i][j].Porte_N == true) {
					this.grille[i][j].grillemin[(Variables.largeur_salle-1)/2][Variables.hauteur_salle-1] = "sol";
				}
				if(this.grille[i][j].Porte_E == true) {
					this.grille[i][j].grillemin[Variables.largeur_salle-1][(Variables.hauteur_salle-1)/2] = "sol";
				}
				if(this.grille[i][j].Porte_S == true) {
					this.grille[i][j].grillemin[(Variables.largeur_salle-1)/2][0] = "sol";
				}
				if(this.grille[i][j].Porte_W == true) {
					this.grille[i][j].grillemin[0][(Variables.hauteur_salle-1)/2] = "sol";
				}
			}
		}
		
		/* On place les PNJs */
		for(int i = 0; i < Variables.Nb_largeur_salles; i++) {
			for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
				double nb_PNJ = Math.random();
				for(int x = 0; x < Variables.largeur_salle; x++) {
					for(int y = 0; y < Variables.hauteur_salle; y++) {
						double proba = Math.random();
						if(nb_PNJ > 0 && proba < 0.10) {
							this.grille[i][j].grillemin[x][y] = "PNJ";
							nb_PNJ -= 0.20;
						}
					}
				}
				
			}
		}
		return this;
	}
	
	public void aff_etage() {
		String matrice[][] = new String[Variables.Nb_largeur_salles * Variables.largeur_salle][Variables.Nb_hauteur_salles * Variables.hauteur_salle];
		
		for(int i = 0; i < Variables.Nb_largeur_salles; i++) {
			for(int j = 0; j < Variables.Nb_hauteur_salles; j++) {
				for(int x = 0; x < Variables.largeur_salle; x++) {
					for(int y = 0; y < Variables.hauteur_salle; y++) {
						matrice[i*x][j*y] = this.grille[i][j].grillemin[x][y];
					}
				}
			}
		}
		for(int j = 0; j < Variables.Nb_hauteur_salles * Variables.hauteur_salle; j++) {
			for(int i = 0; i < Variables.Nb_largeur_salles * Variables.largeur_salle; i++) {
				if(matrice[i][j] == "sol") {
					System.out.print(" ");
				}
				if(matrice[i][j] == "mur") {
					System.out.print("#");
				}
				if(matrice[i][j] == "PNJ") {
					System.out.print("+");
				}
			}
			System.out.print("\n");
		}
	}
}
