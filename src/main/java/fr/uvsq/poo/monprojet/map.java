package fr.uvsq.poo.monprojet;


public class map {
		public int hauteur;
		public int largeur;
		public salles grille[][];
		public player chienj;
		
		public map() {
			this.hauteur = 4; // 4 cases de 7 hauteur
			this.largeur = 5; // 5 cases de 15 largeur
			this.grille = new salles[this.hauteur][this.largeur];
			this.chienj = new player();
		}
		
		public map init_map() {
				for(int i = 0; i<this.hauteur;i++) {
					for(int j = 0; j<this.largeur;j++) {
						this.grille[i][j] = new salles();
						this.grille[i][j] = this.grille[i][j].init_salle();
					}
				}
			return this;
		}
		
		public void affiche_map() {
			for(int i = 0; i<this.hauteur;i++) {
				for(int j = 0; j<this.largeur; j++) {
					this.grille[i][j].print_ligne_salle(i);
				}
			}
		}
}
