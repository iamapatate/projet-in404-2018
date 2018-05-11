package fr.uvsq.poo.monprojet;

import java.util.*;

public class Pnj extends Joueur {

	private static final long serialVersionUID = 1L;

	public Pnj(Salle S) {
		super(S);
		this.type = "PNJ";
		this.puissance = 30;
		this.inventaire = new ArrayList<Objet>();
		
		// une chance sur deux que le PNJ porte un objet
		double a = Math.random();
		if(a < 0.5) {
			this.inventaire.add(new Objet(S));
		}
		this.AddStrength();
	}
	
	public void AddStrength() {
		if (this.inventaire.get(0).getType().equals("pistol")) {
			this.puissance += 30;
		}
		else if(this.inventaire.get(0).getType().equals("lime")) {
			this.puissance += 5;
		}
		else if(this.inventaire.get(0).getType().equals("surin")) {
			this.puissance += 15;
		}
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
				(S.getJoueur().getPosX() == this.getPosX() && S.getJoueur().getPosY() == this.getPosY())) {
			if(this.getAttack() > S.getJoueur().getAttack()) {
				return "gagné";
			}
			else if(this.getAttack() == S.getJoueur().getAttack()) {
				return "égalité";
			}
			else if(this.getAttack() < S.getJoueur().getAttack()) {
				return "perdu";
			}
			System.out.println("j'ai vu quelqu'un mais je n'ai pas réagi");
		}
		return "RAS";
	}
	
}