package fr.uvsq.poo.monprojet;

public class DansLaMap {
	int x, y;
	
	public DansLaMap(Salle S) {
		do {
			this.x = (int) (Math.random() * (Variables.largeur_salle - 1)) + 1;
			this.y = (int) (Math.random() * (Variables.hauteur_salle - 1)) + 1;
		}
		while(S.getString(this.x, this.y) != "sol");

	}
	
	public int getPosX() {
		return this.x;
	}	
	public int  getPosY() {
		return this.y;
	}

}
