package fr.uvsq.poo.monprojet;

import java.util.ArrayList;

public class Salle {

	String grillemin[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	ArrayList<Objet> objetsdelamap = new ArrayList<Objet>();
	ArrayList<Pnj> pnjs = new ArrayList<Pnj>();
	boolean Porte_N;
	boolean Porte_E;
	boolean Porte_S;
	boolean Porte_W;
	int Nb_portes;
	int Type;
	Pj joueur;

	

	public Salle() {		
		for(int i = 0; i < Variables.largeur_salle; i++) {
			for(int j = 0; j < Variables.hauteur_salle; j++) {
				this.grillemin[i][j] = "sol";
			}
		}
		this.Porte_N = true;
		this.Porte_E = true;
		this.Porte_S = false;
		this.Porte_W = false;
		this.Nb_portes = 4;
		this.Type = -1;
	}

	

	public String getString(int i, int j) {
		return this.grillemin[i][j];
	}

	public Salle initPnjsAndObjects(int nbpnjs, int nbobjects) {
		// init pnjs
		Objet nouv;
		Pnj nouvo;
		for(int i = 0; i < nbpnjs; i++) {
			nouvo = new Pnj(this);
			this.pnjs.add(nouvo);
			this.grillemin[nouvo.getPosX()][nouvo.getPosY()] = "PNJ";
		}
		
		// init objets
		for(int i = 0; i < nbobjects - 1; i++) {
			int a = (int) (Math.random());
			if(a < 0.50) {
				nouv = new Objet("lime", this);
			}
			else if(a < 0.90) {
				nouv = new Objet("surin", this);
			}
			else {
				nouv = new Objet("pistol", this);
			}
			this.objetsdelamap.add(nouv);
			this.grillemin[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		}
		nouv = new Objet("cle", this);
		this.objetsdelamap.add(nouv);
		this.grillemin[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		return this;
	}

	public Salle initSalleTemp() {
		for(int i = 0; i < Variables.largeur_salle; i++) {
			for(int j = 0; j < Variables.hauteur_salle; j++) {
				if(i == 0 || j == 0) this.grillemin[i][j] = "mur";
				else if(i == 0 || j == Variables.hauteur_salle - 1) 
					this.grillemin[i][j] = "mur";
				else if((j == 0) || (i == Variables.largeur_salle - 1) ) 
					this.grillemin[i][j] = "mur";
				else if(j == Variables.hauteur_salle - 1 || i == Variables.largeur_salle - 1)
					this.grillemin[i][j] = "mur";
			}
		}
		
		if(this.Porte_E == true) {
			this.grillemin[Variables.largeur_salle-1][Variables.hauteur_salle/2] = "porte";
		}
		if(this.Porte_S == true) {
			this.grillemin[Variables.largeur_salle/2][Variables.hauteur_salle-1] = "porte";
		}
		if(this.Porte_W == true) {
			this.grillemin[0][Variables.hauteur_salle/2] = "porte";
		}
		if(this.Porte_N == true) {
			this.grillemin[Variables.largeur_salle/2][0] = "porte";
		}
		this.initPnjsAndObjects(2,2);
		
		// on initialise la place des joueurs et des objets une fois que les murs & portes & pnjs & objets sont placés
		joueur = new Pj(this);
		// on place le joueur dans grillemin
		this.grillemin[this.joueur.getPosX()][this.joueur.getPosY()] = this.joueur.getString();		
		return this;
	}

	/*
	 *	Affiche la salle courante 
	 */	

	public void affSalleTemp() {
		int a=9619;
		for(int j = 0; j < Variables.hauteur_salle; j++) {
			for(int i = 0; i < Variables.largeur_salle; i++) {
				if(this.grillemin[i][j] == "mur") System.out.print((char)a);
				else if(this.grillemin[i][j] == "sol") System.out.print(".");
				else if(this.grillemin[i][j] == "PJ") System.out.print("+");
				else if(this.grillemin[i][j] == "porte") System.out.print("P");
				else if(this.grillemin[i][j] == "cle") System.out.print("C");
				else if(this.grillemin[i][j] == "lime") System.out.print("L");
				else if(this.grillemin[i][j] == "surin") System.out.print("S");
				else if(this.grillemin[i][j] == "pistol") System.out.print("P");
				else if(this.grillemin[i][j] == "PNJ") System.out.print("@");
			}
			System.out.println("");
		}
	}

	public Salle Update() {
		// on fait bouger le booty des pnjs	
		for(int j = 0; j < this.pnjs.size(); j++) {
			Pnj nouv = this.pnjs.get(j).MoveAleat(this);
			// remplacer l'ancien pnj par celui qui a bougé
			this.pnjs.set(j, nouv);
		}
		
		// effacer les anciens pjs/pnjs/objets qui sont sur le sol
		for(int j = 1; j< Variables.hauteur_salle - 1; j++) {
			for(int i = 1; i < Variables.largeur_salle - 1; i++) {
				if(this.grillemin[i][j]!= "sol" || this.grillemin[i][j] != "mur") {
					this.grillemin[i][j] = "sol";
				}
			}
		}
		
		// afficher les clefs et armes
		for(int i = 0; i < this.objetsdelamap.size(); i++) {
			this.grillemin[this.objetsdelamap.get(i).getPosX()][this.objetsdelamap.get(i).getPosY()]
					= this.objetsdelamap.get(i).getType();
		}
		
		// afficher les nouveaux pjs et pnjs
		this.grillemin[this.joueur.getPosX()][this.joueur.getPosY()] = "PJ";
		for(int i = 0; i < pnjs.size(); i++) {
			this.grillemin[this.pnjs.get(i).getPosX()][this.pnjs.get(i).getPosY()] = "PNJ";
		}		
		return this;
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
	
	public String DevantJoueur(Joueur J) {
		if(J.getPosY() > 0)
			return getString(J.getPosX(), J.getPosY() - 1);
		return "trop haut";
	}
	public String DerriereJoueur(Joueur J) {
		if(J.getPosY() < Variables.hauteur_salle - 1)
			return getString(J.getPosX(), J.getPosY() + 1);
		return "trop bas";
	}
	public String ADroiteJoueur(Joueur J) {
		if(J.getPosX() < Variables.largeur_salle - 1)
			return getString(J.getPosX() + 1, J.getPosY());
		return "trop à droite";
	}
	public String AGaucheJoueur(Joueur J) {
		if(J.getPosX() > 0)
			return getString(J.getPosX() - 1, J.getPosY());
		return "trop à gauche";
	}
	
	// il faut marcher sur l'objet pour le ramasser
	
	public Salle getObjetFromFloor() {
			for(int i = 0; i < this.objetsdelamap.size(); i++) {
				if(DevantJoueur(this.joueur).equals(this.getString(this.objetsdelamap.get(i).getPosX(),this.objetsdelamap.get(i).getPosY()))) {
					this.joueur.PickUp(this.objetsdelamap.get(i));
					System.out.println("objet " + this.objetsdelamap.get(i).getType() + " ramassé");
					this.objetsdelamap.remove(i);
				}
				else if(DerriereJoueur(this.joueur).equals(this.getString(this.objetsdelamap.get(i).getPosX(),this.objetsdelamap.get(i).getPosY()))) {
					this.joueur.PickUp(this.objetsdelamap.get(i));
					System.out.println("objet " + this.objetsdelamap.get(i).getType() + " ramassé");
					this.objetsdelamap.remove(i);
				}
				else if(ADroiteJoueur(this.joueur).equals(this.getString(this.objetsdelamap.get(i).getPosX(),this.objetsdelamap.get(i).getPosY()))) {
					this.joueur.PickUp(this.objetsdelamap.get(i));
					System.out.println("objet " + this.objetsdelamap.get(i).getType() + " ramassé");
					this.objetsdelamap.remove(i);
				}
				else if(AGaucheJoueur(this.joueur).equals(this.getString(this.objetsdelamap.get(i).getPosX(),this.objetsdelamap.get(i).getPosY()))) {
					this.joueur.PickUp(this.objetsdelamap.get(i));
					System.out.println("objet " + this.objetsdelamap.get(i).getType() + " ramassé");
					this.objetsdelamap.remove(i);
				}
			}
		return this;
	}
	
	
	public Salle PnjLookingForFight() {
		for(int i = 0; i < this.pnjs.size(); i++) {
			if(this.pnjs.get(i).LookingForFight(this).equals("RAS")) {
				System.out.println("RAS");
				return this;
			}
			else if(this.pnjs.get(i).LookingForFight(this).equals("gagné")) {
				this.joueur.LoseLife();				
				System.out.println("Le Pnj gagné le combat");
			}
			else if(this.pnjs.get(i).LookingForFight(this).equals("perdu")) {
				this.pnjs.remove(i);
				System.out.println("Vous gagnez le combat");
			}
			else if(this.pnjs.get(i).LookingForFight(this).equals("égalité")){
				this.pnjs.remove(i);
				this.joueur.LoseLife();
				System.out.println("Le joueur tue le PNJ mais se blesse");
			}	
		}
		return this;
	}
	
	public Salle ChangeRoom() {
		if(this.joueur.FindInInventory("cle") == 1 && this.WhatsInFrontOfPlayer(this.joueur).equals("porte")) {
			System.out.println("Porte ouverte, passage à une nouvelle salle");
			Salle nouv = new Salle();
			nouv.joueur = this.joueur;
			return nouv;
		}
		else System.out.println("Trouvez la clé du niveau pour passer à la salle suivante");
		return this;
	}

}

