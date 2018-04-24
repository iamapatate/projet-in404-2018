package fr.uvsq.poo.monprojet;
import java.util.*;

public class UneSalleTemporaire {
	String grillemin[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	ArrayList<Objet> objetsdelamap = new ArrayList<Objet>();
	
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
	public UneSalleTemporaire initSalleTemp(Joueur J) {
		
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
	public void affSalleTemp(Joueur J) {
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
	
	public UneSalleTemporaire Update(Joueur J) {
		for(int j = 0; j< Variables.hauteur_salle; j++) {
			for(int i = 0; i < Variables.largeur_salle; i++) {
				if(this.grillemin[i][j].equals("PJ")) this.grillemin[i][j] = "sol";
			}
		}
		this.grillemin[J.getPosX()][J.getPosY()] = "PJ";
		return this;
	}
	
	/*
	 *  Renvoie un String symbolisant ce qui se trouve à la case i,j
	 */
	public String getString(int i, int j) {
		return this.grillemin[i][j];
	}
	
	/*
	 *  Renvoie un String symbolisant ce qui se trouve en face du joueur j
	 *  si sa position le permet (limites de la matrice)
	 */
	public String WhatsInFrontOfPlayer(Joueur J) {
		String dirplayer = J.getDir();
		if(dirplayer.equals("up")) {
			if(J.getPosY() > 0)
			return getString(J.getPosX(), J.getPosY() - 1);
		}
		else if(dirplayer.equals("down")) {
			if(J.getPosY() < Variables.hauteur_salle - 1)
			return getString(J.getPosX(), J.getPosY() + 1);
		}
		else if(dirplayer.equals("left")) {
			if(J.getPosX() > 0)
			return getString(J.getPosX() - 1, J.getPosY());
		}
		else if(dirplayer.equals("right")) {
			if(J.getPosX() < Variables.largeur_salle - 1)
			return getString(J.getPosX() + 1, J.getPosY());
		}
		return "erreur tu essaies d'interagir avec qq chose qui te dépasse";
	}
}
