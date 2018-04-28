package fr.uvsq.poo.monprojet;

public class Pnj extends Joueur {

	public Pnj(Salle S) {
		super(S);
		this.type = "PNJ";
	}
	
	public Pnj MoveAleat(Salle S) {
		int a = (int) (Math.random() * 101);
		if(a <= 25) {
			this.Move(S,"up");
		}
		if(a > 25 & a <= 50) {
			this.Move(S,"down");
		}
		if(a <= 75 & a > 50) {
			this.Move(S,"left");
		}
		if(a > 75 & a <= 100) {
			this.Move(S,"right");
		}		
		return this;
	}
}