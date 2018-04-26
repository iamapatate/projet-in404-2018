package fr.uvsq.poo.monprojet;

public abstract class Joueur extends DansLaMap{
	protected String dir;
	protected int puissance;
		
	public Joueur(Salle S) {
		super(S);
		dir = "up";
	}
		
	public String getDir() {
		return this.dir;
	}
	
	/*
	 *  Commandes en anglais
	 */
	public Joueur Move(String dir) {
		System.out.println(this.getPosX() + " " + this.getPosY());
		if(dir.equals("down") && this.getPosY() < Variables.hauteur_salle - 1) this.y += 1;
		else if(dir.equals("up") && this.getPosY() > 0) this.y -= 1;
		else if(dir.equals("left") && this.getPosX() > 0) this.x -= 1;
		else if(dir.equals("right") && this.getPosX() < Variables.largeur_salle - 1) this.x += 1;
		else System.out.println("Erreur de direction");		
		this.dir = dir;
		return this;
	}
}
