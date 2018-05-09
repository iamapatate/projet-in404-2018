package fr.uvsq.poo.monprojet;

import java.util.ArrayList;

public class Salle {

	String grille_string[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	int Portes[] = new int[4]; // [0] = Nord, [1] = Est, [2] = Sud, [3] = West // 0 = faux, 1 = vrai, 2 = bloqué
	ArrayList<Objet> objetsdelamap = new ArrayList<Objet>();
	ArrayList<Pnj> pnjs = new ArrayList<Pnj>();
	Pj joueur;
	
	public Salle() {
		for(int i=0;i<Variables.largeur_salle;i++) {
			for(int j=0;j<Variables.hauteur_salle;j++) {
				if(i == 0 || i == Variables.largeur_salle-1) {
					this.grille_string[i][j] = "mur";
				}
				else if(j == 0 || j == Variables.hauteur_salle-1) {
					this.grille_string[i][j] = "mur";
				}
				else this.grille_string[i][j] = "sol";
				for(int k=0;k<4;k++) {
					this.Portes[k]= 0;
				}
			}
		}
	}
	

	public String getString(int i, int j) {
		return this.grille_string[i][j];
	}

	public Salle initPnjsAndObjects(int nbpnjs, int nbobjects) {
		// init pnjs
		Objet nouv;
		Pnj nouvo;
		for(int i = 0; i < nbpnjs; i++) {
			nouvo = new Pnj(this);
			this.pnjs.add(nouvo);
			this.grille_string[nouvo.getPosX()][nouvo.getPosY()] = "PNJ";
		}
		
		// init objets
		for(int i = 0; i < nbobjects - 1; i++) {
			nouv = new Objet(this);
			this.objetsdelamap.add(nouv);
			this.grille_string[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		}
		nouv = new Objet("cle", this);
		this.objetsdelamap.add(nouv);
		this.grille_string[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		return this;
	}

	public Salle initSalleTemp() {
		for(int i = 0; i < Variables.largeur_salle; i++) {
			for(int j = 0; j < Variables.hauteur_salle; j++) {
				if(i == 0 || j == 0) this.grille_string[i][j] = "mur";
				else if(i == 0 || j == Variables.hauteur_salle - 1) 
					this.grille_string[i][j] = "mur";
				else if((j == 0) || (i == Variables.largeur_salle - 1) ) 
					this.grille_string[i][j] = "mur";
				else if(j == Variables.hauteur_salle - 1 || i == Variables.largeur_salle - 1)
					this.grille_string[i][j] = "mur";
			}
		}
		
		if(this.Portes[1] == 1) {
			this.grille_string[Variables.largeur_salle-1][Variables.hauteur_salle/2] = "porte";
		}
		if(this.Portes[2] == 1) {
			this.grille_string[Variables.largeur_salle/2][Variables.hauteur_salle-1] = "porte";
		}
		if(this.Portes[3] == 1) {
			this.grille_string[0][Variables.hauteur_salle/2] = "porte";
		}
		if(this.Portes[0] == 1) {
			this.grille_string[Variables.largeur_salle/2][0] = "porte";
		}
		this.initPnjsAndObjects(2,2);
		
		// on initialise la place des joueurs et des objets une fois que les murs & portes & pnjs & objets sont placés
		joueur = new Pj(this);
		// on place le joueur dans grille_string
		this.grille_string[this.joueur.getPosX()][this.joueur.getPosY()] = this.joueur.getString();		
		return this;
	}

	/*
	 *	Affiche la salle courante 
	 */	

	public void affSalleTemp() {
		int a= 9619;
		for(int j = 0; j < Variables.hauteur_salle; j++) {
			for(int i = 0; i < Variables.largeur_salle; i++) {
				if(this.grille_string[i][j] == "mur") System.out.print((char)a);
				else if(this.grille_string[i][j] == "sol") System.out.print(".");
				else if(this.grille_string[i][j] == "PJ") System.out.print("+");
				else if(this.grille_string[i][j] == "porte") System.out.print("O");
				else if(this.grille_string[i][j] == "cle") System.out.print("C");
				else if(this.grille_string[i][j] == "lime") System.out.print("L");
				else if(this.grille_string[i][j] == "surin") System.out.print("S");
				else if(this.grille_string[i][j] == "pistol") System.out.print("P");
				else if(this.grille_string[i][j] == "PNJ") System.out.print("@");
			}
			System.out.println("");
		}
	}

	public Salle Update() {
		this.PnjLookingForFight();

		// on fait bouger le booty des pnjs	
		for(int j = 0; j < this.pnjs.size(); j++) {
			Pnj nouv = this.pnjs.get(j).MoveAleat(this);
			// remplacer l'ancien pnj par celui qui a bougé
			this.pnjs.set(j, nouv);
		}
	
		// effacer les anciens pjs/pnjs/objets qui sont sur le sol
		for(int j = 1; j< Variables.hauteur_salle - 1; j++) {
			for(int i = 1; i < Variables.largeur_salle - 1; i++) {
				if(this.grille_string[i][j]!= "sol" || this.grille_string[i][j] != "mur") {
					this.grille_string[i][j] = "sol";
				}
			}
		}
		
		// afficher les clefs et armes
		for(int i = 0; i < this.objetsdelamap.size(); i++) {
			this.grille_string[this.objetsdelamap.get(i).getPosX()][this.objetsdelamap.get(i).getPosY()]
					= this.objetsdelamap.get(i).getType();
		}
		
		// afficher les nouveaux pjs et pnjs
		this.grille_string[this.joueur.getPosX()][this.joueur.getPosY()] = "PJ";
		for(int i = 0; i < pnjs.size(); i++) {
			this.grille_string[this.pnjs.get(i).getPosX()][this.pnjs.get(i).getPosY()] = this.pnjs.get(i).getString();
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
	
	private String DevantJoueur(Joueur J) {
		if(J.getPosY() > 0)
			return getString(J.getPosX(), J.getPosY() - 1);
		return "trop haut";
	}
	private String DerriereJoueur(Joueur J) {
		if(J.getPosY() < Variables.hauteur_salle - 1)
			return getString(J.getPosX(), J.getPosY() + 1);
		return "trop bas";
	}
	private String ADroiteJoueur(Joueur J) {
		if(J.getPosX() < Variables.largeur_salle - 1)
			return getString(J.getPosX() + 1, J.getPosY());
		return "trop à droite";
	}
	private String AGaucheJoueur(Joueur J) {
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
				System.out.println("Le Pnj gagne le combat");
			}
			else if(this.pnjs.get(i).LookingForFight(this).equals("perdu")) {
				if(!this.pnjs.get(i).getInventory().isEmpty()) {
					Objet nouv = new Objet(this, this.pnjs.get(i).getPosX(), this.pnjs.get(i).getPosY(), this.pnjs.get(i).getInventory().get(0).getType());
					this.objetsdelamap.add(nouv);					
				}
				System.out.println("Vous gagnez le combat, oh! Le pnj a fait tomber " + this.pnjs.get(i).getInventory().get(0).getType());
				this.pnjs.remove(i);
			}
			else if(this.pnjs.get(i).LookingForFight(this).equals("égalité")){
				if(!this.pnjs.get(i).getInventory().isEmpty()) {
					Objet nouv = new Objet(this, this.pnjs.get(i).getPosX(), this.pnjs.get(i).getPosY(), this.pnjs.get(i).getInventory().get(0).getType());
					this.objetsdelamap.add(nouv);					
				}
				System.out.println("Le joueur tue le PNJ mais se blesse, oh! Le pnj a fait tomber" + this.pnjs.get(i).getInventory().get(0).getType());
				this.pnjs.remove(i);
				this.joueur.LoseLife();
			}	
		}
		return this;
	}
	
	public Salle ChangeRoom() {
		if(this.joueur.IsInInventory("cle") == 1) {
			System.out.println("Porte ouverte, passage à une nouvelle salle");
			Salle nouv = new Salle();
			nouv.joueur = this.joueur;
			return nouv;
		}
		else System.out.println("Trouvez la clé du niveau pour passer à la salle suivante");
		return this;
	}

}
