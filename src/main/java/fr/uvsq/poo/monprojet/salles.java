package fr.uvsq.poo.monprojet;

public class salles {
	boolean porteN;
	boolean porteS;
	boolean porteE;
	boolean porteW;
	char grille[][];
	int hauteur;
	int largeur;
	int type_salle; // 0 = vide, 1 = cellule, 2 = bureau, 3 = couloir;
	
	public salles() {
		this.porteN = true;
		this.porteS = true;
		this.porteE = true;
		this.porteW = true;
		this.hauteur = 6;
		this.largeur = 14;
		this.grille = new char[this.hauteur][this.largeur];
		this.type_salle = 0;
	}
	
	public salles init_salle() {
		for(int i = 0; i<this.hauteur; i++) {
			for(int j = 0; j<this.largeur; j++) {
				if(i == 0 || i == this.hauteur-1 || j == 0 || j == this.largeur-1) {
					grille[i][j] = '#';
				}
				else {
					grille[i][j] = '.';
				}
			}
		}
		return this;
	}
	
	public void print_ligne_salle(int i) {
		for(int j = 0; j<this.largeur; j++) {
			System.out.print(this.grille[i][j]);
		}
	}

}
