package fr.uvsq.poo.monprojet;

public class Pnj extends Joueur {
	

	public Pnj() {
		super();
		this.vie=1
	}
	
	public Joueur Move(String dir) {
		super.Move(dir);
		return this;
		
	}
	public Joueur Turn(String dir) {
-		super.Turn(dir);
-		return this;
-	}

}
