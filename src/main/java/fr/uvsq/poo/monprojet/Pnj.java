package fr.uvsq.poo.monprojet;

public class Pnj extends Joueur {

	public Pnj(Salle S) {
		super(S);
		this.type = "PNJ";
		this.puissance = 30;
	}
	
	public Pnj MoveAleat(Salle S) {
		int a = (int) (Math.random() * 101);
		if(a <= 25) {
			if(this.getPosY() > 1)
			this.Move(S,"up");
		}
		if(a > 25 & a <= 50) {
			if(this.getPosY() < Variables.hauteur_salle - 1)
			this.Move(S,"down");
		}
		if(a <= 75 & a > 50) {
			if(this.getPosX() > 1)
			this.Move(S,"left");
		}
		if(a > 75 & a <= 100) {
			if(this.getPosX() < Variables.largeur_salle - 1)
			this.Move(S,"right");
		}		
		return this;
	}
	
}