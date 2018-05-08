package fr.uvsq.poo.monprojet;
import java.util.concurrent.ThreadLocalRandom;

public class Etage {
	Salle grille[][] = new Salle[Variables.Nb_largeur_salles][Variables.Nb_hauteur_salles];
	int graphe[][] = new int[Variables.Nb_largeur_salles][Variables.Nb_hauteur_salles];
	
	public Etage() {
		for(int i=0;i<Variables.Nb_largeur_salles;i++) {
			for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
				this.grille[i][j] = new Salle();
			}
		}
		for(int i=0;i<Variables.Nb_largeur_salles;i++) {
			for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
				graphe[i][j] = 0;
			}
		}
	}
	
	public Etage initialisation() {
		//On bloque les portes qui donnent vers l'extérieur
		for(int i=0;i<Variables.Nb_largeur_salles;i++) {
			this.grille[i][0].Portes[0] = 2;
			this.grille[i][Variables.Nb_largeur_salles-1].Portes[2] = 2;
		}
		for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
			this.grille[0][j].Portes[3] = 2;
			this.grille[Variables.Nb_hauteur_salles-1][j].Portes[1] = 2;
		}
		return this;
	}
	
	public Etage generation() {
		// On choisit un 1er sommet au hasard à marquer
		int x,y;
		x = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
		y = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
		graphe[x][y] = 1;
		// On parcourt tout le graphe
		for(int i=0;i<Variables.Nb_largeur_salles*Variables.Nb_hauteur_salles;i++){
			// On choisit un sommet marqué au hasard
			do{
				x = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
				y = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
			}while(graphe[x][y] != 1);
			// On vérifie si au moins un voisin est non marqué
			boolean test = false;
			for(int k=0;k<4;k++){
				if(grille[x][y].Portes[k] == 0){
					switch(k){
						case 0 :
							if(graphe[x][y+1] == 0)test = true;
							break;
						case 1 :
							if(graphe[x+1][y] == 0)test = true;
							break;
						case 2 :
							if(graphe[x][y-1] == 0)test = true;
							break;
						case 3 :
							if(graphe[x-1][y] == 0)test = true;
							break;
					}
				}
			}
			if(test == false) graphe[x][y] = 2; // Si aucune porte n'est ouvrable, on bloque ce sommet pour ne plus revenir dessus (tous ses voisins sont marqués)
			// Si au moins un voisin est non marqué, on relie un voisin non marqué au hasard à notre sommet en (x,y) et on marque ce voisin
			while(test == true){
				int k = ThreadLocalRandom.current().nextInt(0,4);
				switch(k){
					case 0 :
						if(graphe[x][y+1] == 0 && grille[x][y].Portes[k] == 0){
							test = false;
							grille[x][y].Portes[k] = 1;
							grille[x][y+1].Portes[2] = 1;
							graphe[x][y+1] = 1;
						}
						break;
					case 1 :
						if(graphe[x+1][y] == 0 && grille[x][y].Portes[k] == 0){
							test = false;
							grille[x][y].Portes[k] = 1;
							grille[x+1][y].Portes[3] = 1;
							graphe[x+1][y] = 1;
						}
						break;
					case 2 :
						if(graphe[x][y-1] == 0 && grille[x][y].Portes[k] == 0){
							test = false;
							grille[x][y].Portes[k] = 1;
							grille[x][y-1].Portes[0] = 1;
							graphe[x][y-1] = 1;
						}
						break;
					case 3 :
						if(graphe[x-1][y] == 0 && grille[x][y].Portes[k] == 0){
							test = false;
							grille[x][y].Portes[k] = 1;
							grille[x-1][y].Portes[1] = 1;
							graphe[x-1][y] = 1;
						}
						break;
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
						matrice[i*x][j*y] = this.grille[i][j].grille_string[x][y];
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
