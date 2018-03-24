package fr.uvsq.poo.monprojet;

public class player {
	
	private int placeX; private int placeY;
	private inventaire inventaire;
	private stat stat;
	
	public player() {
		this.placeX = 0;
		this.placeY = 0;
		this.inventaire = new inventaire();
		this.stat = new stat();
	}
	
	
	public player d() {
		this.placeX += 1;
		return this;
	}
	
	public player g() {
		this.placeX -= 1;
		return this;
	}
	
	public player h() {
		this.placeY += 1;
		return this;
	}
	
	public player b() {
		this.placeY -= 1;
		return this;
	}
	
}
