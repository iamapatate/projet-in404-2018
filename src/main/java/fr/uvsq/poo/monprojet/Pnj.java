package fr.uvsq.poo.monprojet;

public class Pnj extends Joueur {

	public Pnj(Salle S) {
		super(S);
		this.type = "PNJ";
		this.puissance = 30;
	}
	
	public Pnj MoveAleat(Salle S) {
		int a = (int) (Math.random() * 101);
		if(a <= 20) {
			if(this.getPosY() > 1)
			this.Move(S,"up");
		}
		if(a > 20 & a <= 40) {
			if(this.getPosY() < Variables.hauteur_salle - 1)
			this.Move(S,"down");
		}
		if(a <= 60 & a > 40) {
			if(this.getPosX() > 1)
			this.Move(S,"left");
		}
		if(a > 60 & a <= 80) {
			if(this.getPosX() < Variables.largeur_salle - 1)
			this.Move(S,"right");
		}		
		return this;
	}
	
	public String LookingForFight(Salle S) {
		// recherche si joueur autour de nous, si oui attaque
		if(S.getString(this.getPosX() + 1, this.getPosY()).equals("PJ") || 
				S.getString(this.getPosX(), this.getPosY() + 1).equals("PJ") || 
				S.getString(this.getPosX() - 1, this.getPosY()).equals("PJ") ||
				S.getString(this.getPosX(), this.getPosY() - 1).equals("PJ")|| 
				S.getString(this.getPosX() , this.getPosY()).equals("PJ")) {
			if(this.getAttack() > S.joueur.getAttack()) {
				return "gagné";
			}
			else if(this.getAttack() == S.joueur.getAttack()) {
				return "égalité";
			}
			else if(this.getAttack() < S.joueur.getAttack()) {
				return "perdu";
			}
		}
		return "RAS";
	}
	
}
