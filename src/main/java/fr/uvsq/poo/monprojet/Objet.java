package fr.uvsq.poo.monprojet;

public class Objet extends DansLaMap{

	private static final long serialVersionUID = 1L;
	private String type;
	
	public Objet(String type, Salle S) {
		super(S);
		this.type = type;
	}
	
	public Objet(Salle S) {
		super(S);	
		double a = Math.random();
		if(a < 0.1) {
			this.type = "pistol";
		}
		else if(a < 0.4) {
			this.type = "surin";
		}
		else {
			this.type = "lime";
		}
	}
	
	public Objet(Salle S, int posX, int posY, String type) {
		super(S);
		this.x = posX; this.y = posY;
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
