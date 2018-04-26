package fr.uvsq.poo.monprojet;

public class Pnj extends Joueur {

	public Pnj(Salle S) {
		super(S);
		this.type = "PNJ";
	}
	
	public Pnj MoveAleat() {
		int a = (int) Math.random();
		if(a <= 0.25) {
			this.Move("up");
		}
		if(a > 0.25 & a <= 0.5) {
			this.Move("down");
		}
		if(a <= 0.75 & a > 0.5) {
			this.Move("left");
		}
		if(a > 0.75 & a <= 1) {
			this.Move("right");
		}
		
		return this;
	}
}