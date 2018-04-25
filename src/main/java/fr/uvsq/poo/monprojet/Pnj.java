package fr.uvsq.poo.monprojet;

public class Pnj extends Joueur {

	public Pnj(Salle S) {
		super(S);
	}
	
	public Joueur Move(String dir) {
		super.Move(dir);
		return this;

	}
}