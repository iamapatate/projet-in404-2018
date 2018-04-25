import java.util.ArrayList;

public class Pj extends Joueur {
	int vie;
	ArrayList<Objet> inventaire;
	
	
	public Pj() {
		super();
		this.puissance = 30;
		this.vie = 3;
		this.inventaire = new ArrayList<Objet>();
		
		this.vie = 0;		
	}
	
	public Joueur Move(String dir) {
		super.Move(dir);
		
	}
	
	public ArrayList<Objet> PickUp(Objet Obj){
		 this.inventaire.add(Obj);
		 return this.inventaire;
	}

}
