package fr.uvsq.poo.monprojet;

import java.util.ArrayList;
import java.io.Serializable;

public class Salle implements Serializable{

	private static final long serialVersionUID = 1L;
	private String grille_string[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	int Portes[] = new int[4]; // [0] = Nord, [1] = Est, [2] = Sud, [3] = West // 0 = faux, 1 = vrai, 2 = bloqué
	private ArrayList<Objet> objetsdelamap = new ArrayList<Objet>();
	private ArrayList<Pnj> pnjs = new ArrayList<Pnj>();
	private Pj joueur;
	
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
	
	public Pj getJoueur() {
		return this.joueur;
	}
	
	public int getValueofPorte(int indice) {
		return this.Portes[indice];
	}

	private Salle initPnjs(int nb_PNJs) {
		Pnj nouv;
		for(int i = 0; i < nb_PNJs; i++) {
			nouv = new Pnj(this);
			this.pnjs.add(nouv);
			this.grille_string[nouv.getPosX()][nouv.getPosY()] = "PNJ";
		}
		return this;
	}
		
	private Salle initObjects(int nb_objects) {
		Objet nouv;
		for(int i = 0; i < nb_objects - 1; i++) {
			nouv = new Objet(this);
			this.objetsdelamap.add(nouv);
			this.grille_string[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		}
		return this;
	}

	private Salle initDoors() {
		for(int k=0;k<4;k++) {
			if(this.Portes[k] == 1) {
				switch(k) {
					case 0 :
						this.grille_string[Variables.largeur_salle/2][Variables.hauteur_salle-1] = "porte";
						break;
					case 1 :
						this.grille_string[Variables.largeur_salle-1][Variables.hauteur_salle/2] = "porte";
						break;
					case 2 :
						this.grille_string[Variables.largeur_salle/2][0] = "porte";
						break;
					case 3 :
						this.grille_string[0][Variables.hauteur_salle/2] = "porte";
						break;
				}
			}
		}
		return this;
	}
	
	public Salle remplirSalle(int nb_objects,int nb_PNJs) {
		this.initDoors();
		this.initObjects(nb_objects);
		this.initPnjs(nb_PNJs);
		return this;
	}
	
	public Salle addObjet(Objet nouv) {
		this.objetsdelamap.add(nouv);
		return this;
	}
	
	public Salle modifGrilleString(Objet nouv) {
		this.grille_string[nouv.getPosX()][nouv.getPosY()] = nouv.getType();
		return this;
	}
	
	public void modifJoueur(Pj joueur) {
		this.joueur = joueur;
		this.grille_string[joueur.getPosX()][joueur.getPosY()] = "PJ";
	}

	public void affSalle() {
		int a= 9619;
		for(int j = Variables.hauteur_salle-1; j >= 0; j--) {
			for(int i = 0; i < Variables.largeur_salle; i++) {
				if(this.grille_string[i][j].equals("mur")) System.out.print((char)a);
				else if(this.grille_string[i][j].equals("sol")) System.out.print(".");
				else if(this.grille_string[i][j].equals("PJ")) System.out.print("+");
				else if(this.grille_string[i][j].equals("porte")) System.out.print("O");
				else if(this.grille_string[i][j].equals("trappe")) System.out.print("O");
				else if(this.grille_string[i][j].equals("cle")) System.out.print("C");
				else if(this.grille_string[i][j].equals("lime")) System.out.print("L");
				else if(this.grille_string[i][j].equals("surin")) System.out.print("S");
				else if(this.grille_string[i][j].equals("pistol")) System.out.print("P");
				else if(this.grille_string[i][j].equals("PNJ")) System.out.print("@");
				else if(this.grille_string[i][j].equals("vie")) System.out.print("V");
			}
			System.out.println("");
		}
	}

	public void Update() {
		// tous les pnjs recherchent le joueur
		for(int i = 0; i < this.pnjs.size(); i++) {
			this.PnjLookingForFight(this.pnjs.get(i));
		}
		
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
		if(this.joueur.getVie() != 0){
			this.grille_string[this.joueur.getPosX()][this.joueur.getPosY()] = "PJ";
		}
		for(int i = 0; i < pnjs.size(); i++) {
			this.grille_string[this.pnjs.get(i).getPosX()][this.pnjs.get(i).getPosY()] = this.pnjs.get(i).getString();
		}
		affSalle();
	}	

	/*
	 *  Renvoie un String symbolisant ce qui se trouve en face du joueur j
	 *  si sa position le permet (limites de la matrice)
	 */
	public String WhatsInFrontOfPlayer(Joueur J) {

		String dirplayer = J.getDir();
		if(dirplayer.equals("down")) {
			if(J.getPosY() > 0)
			return getString(J.getPosX(), J.getPosY() - 1);
		}

		else if(dirplayer.equals("up")) {
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
		return "trop bas";
	}
	private String DerriereJoueur(Joueur J) {
		if(J.getPosY() < Variables.hauteur_salle - 1)
			return getString(J.getPosX(), J.getPosY() + 1);
		return "trop haut";
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
	
	
	public Salle PnjLookingForFight(Pnj p) {
			if(p.LookingForFight(this).equals("RAS")) {
				System.out.println("RAS");
				return this;
			}
			else if(p.LookingForFight(this).equals("gagné")) {
				this.joueur.LoseLife();				
				System.out.println("Le Pnj gagne le combat");
			}
			else if(p.LookingForFight(this).equals("perdu")) {
				if(!p.getInventory().isEmpty()) {
					Objet nouv = new Objet(this, p.getPosX(), p.getPosY(), p.getInventory().get(0).getType());
					this.objetsdelamap.add(nouv);
					System.out.println("Vous gagnez le combat, oh! Le pnj a fait tomber " + p.getInventory().get(0).getType());
				}
				else {
					System.out.println("Vous gagnez le combat!");	
				}
				int indice = this.pnjs.indexOf(p);
				this.pnjs.remove(this.pnjs.get(indice));
			}
			else if(p.LookingForFight(this).equals("égalité")){
				if(!p.getInventory().isEmpty()) {
					Objet nouv = new Objet(this, p.getPosX(), p.getPosY(), p.getInventory().get(0).getType());
					this.objetsdelamap.add(nouv);
					System.out.println("Le joueur tue le PNJ mais se blesse, oh! Le pnj a fait tomber " + p.getInventory().get(0).getType());
				}
				else {
					System.out.println("Le joueur tue le PNJ mais se blesse...");
				}
				int indice = this.pnjs.indexOf(p);
				this.pnjs.remove(this.pnjs.get(indice));
				this.joueur.LoseLife();
			}	
		return this;
	}
	
	// retourne l'int correspondant au cardinal vers lequel le joueur tente d'ouvrir une porte
	public int GetOutOfRoom() {
		if(ADroiteJoueur(this.joueur).equals("porte")) {
			System.out.println("Changement de salle");
			return 1;
		}
		else if(DevantJoueur(this.joueur).equals("porte")) {
			System.out.println("Changement de salle");
			return 2;
		}
		else if(DerriereJoueur(this.joueur).equals("porte")) {
			System.out.println("Changement de salle");
			return 0;
		}
		else if(AGaucheJoueur(this.joueur).equals("porte")) {
			System.out.println("Changement de salle");
			return 3;
		}
		else if((ADroiteJoueur(this.joueur).equals("trappe") || AGaucheJoueur(this.joueur).equals("trappe")
				|| DevantJoueur(this.joueur).equals("trappe") || DerriereJoueur(this.joueur).equals("trappe")) && this.joueur.hasKey() == 1) {
			System.out.println("Changement d'étage");
			return 4;
		}	
		return -1;
	}
	
	public Salle getInRoom(Pj J) {
		this.joueur = J;
		return this;
	}
}