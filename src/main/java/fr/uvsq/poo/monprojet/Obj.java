package fr.uvsq.poo.monprojet;

public enum Obj {
	mur(-1),
	sol(-2),
	essence (0),
	cle (1),
	lime (5),
	surin (10),
	revolver (20);

	  private int correspondance;

	  //Constructeur
	  Obj(int correspondance){
	    this.correspondance = correspondance;
	  }	   
	  
	  public int getCorrespondance(){
		  	return correspondance;
		  }

}
