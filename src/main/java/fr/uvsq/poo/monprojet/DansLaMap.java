package fr.uvsq.poo.monprojet;

import java.io.Serializable;

public abstract class DansLaMap implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int x, y;
	private String type;
	
	/*
	 * Constructeur pour les objets avec lesquels on peut intéragir
	 */
	public DansLaMap(Salle S) {
		do {
			this.x = (int) (Math.random() * (Variables.largeur_salle - 1)) + 1;
			this.y = (int) (Math.random() * (Variables.hauteur_salle - 1)) + 1;
		}
		while(S.getString(this.x, this.y) != "sol");
		type = new String();
	}
		
	public int getPosX() {
		return this.x;
	}	
	
	public int  getPosY() {
		return this.y;
	}
	
	public String getString() {
		return this.type;
	}

}