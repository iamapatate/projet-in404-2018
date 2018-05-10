package fr.uvsq.poo.monprojet;

import java.util.concurrent.ThreadLocalRandom;
import java.io.Serializable;

public class Etage implements Serializable{

	private static final long serialVersionUID = 1L;
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
		//On bloque les portes qui donnent vers l'exterieur
		for(int i=0;i<Variables.Nb_largeur_salles;i++) {
			this.grille[i][0].Portes[2] = 2;
			this.grille[i][Variables.Nb_hauteur_salles-1].Portes[0] = 2;
		}
		for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
			this.grille[0][j].Portes[3] = 2;
			this.grille[Variables.Nb_largeur_salles-1][j].Portes[1] = 2;
		}
		return this;
	}
	
	public Etage generation() {
		// On choisit un 1er sommet au hasard a marquer
		int x,y;
		x = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
		y = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
		graphe[x][y] = 1;
		// On parcourt tous les sommets au moins une fois
		boolean stop = false;
		while(stop == false){
			// On choisit un sommet marque au hasard
			while(graphe[x][y] != 1){
				x = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
				y = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
			}
			// On verifie si au moins un voisin est non marque
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
			if(test == false) graphe[x][y] = 2; // Si aucune porte n'est ouvrable, on bloque ce sommet pour ne plus revenir dessus (tous ses voisins sont marques)
			// Si au moins un voisin est non marque, on relie un voisin non marque au hasard a notre sommet en (x,y) et on marque ce voisin
			while(test == true){
				int k = ThreadLocalRandom.current().nextInt(0,4);
				switch(k){
					case 0 :
						if(grille[x][y].Portes[k] == 0){
							if(graphe[x][y+1] == 0) {
								test = false;
								grille[x][y].Portes[k] = 1;
								grille[x][y+1].Portes[2] = 1;
								graphe[x][y+1] = 1;
							}
						}
						break;
					case 1 :
						if(grille[x][y].Portes[k] == 0){
							if(graphe[x+1][y] == 0) {
								test = false;
								grille[x][y].Portes[k] = 1;
								grille[x+1][y].Portes[3] = 1;
								graphe[x+1][y] = 1;
							}
						}
						break;
					case 2 :
						if(grille[x][y].Portes[k] == 0){
							if(graphe[x][y-1] == 0) {
								test = false;
								grille[x][y].Portes[k] = 1;
								grille[x][y-1].Portes[0] = 1;
								graphe[x][y-1] = 1;
							}
						}
						break;
					case 3 :
						if(grille[x][y].Portes[k] == 0){
							if(graphe[x-1][y] == 0) {
								test = false;
								grille[x][y].Portes[k] = 1;
								grille[x-1][y].Portes[1] = 1;
								graphe[x-1][y] = 1;
							}
						}
						break;
				}
			}
			int som_visit = 0;
			for(int i=0;i<Variables.Nb_largeur_salles;i++) {
				for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
					if(graphe[i][j] != 0) som_visit++;
				}
			}
			if(som_visit == Variables.Nb_largeur_salles*Variables.Nb_hauteur_salles) stop = true;
		}
		return this;
	}
	
	public Etage remplirEtage(int nb_Objets_max,int nb_PNJs_max) {
		//On remplit chaque salle d'objets au hasard
		for(int i=0;i<Variables.Nb_largeur_salles;i++) {
			for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
				this.grille[i][j].remplirSalle(ThreadLocalRandom.current().nextInt(1,nb_Objets_max+1),ThreadLocalRandom.current().nextInt(1,nb_PNJs_max+1));
			}
		}
		//On place une cle au hasard dans tout l'etage
		int x = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
		int y = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
		Objet nouv = new Objet("cle",this.grille[x][y]);
		this.grille[x][y].objetsdelamap.add(nouv);
		this.grille[x][y].grille_string[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		//On place aussi la trappe au hasard
		x = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
		y = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
		int i = ThreadLocalRandom.current().nextInt(0,Variables.Nb_largeur_salles);
		int j = ThreadLocalRandom.current().nextInt(0,Variables.Nb_hauteur_salles);
		this.grille[x][y].grille_string[i][j] = "trappe";
		return this;
	}

	public Salle GetSalle(int x,int y) {
		return this.grille[x][y];
	}
}