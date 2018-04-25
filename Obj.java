package Projet;

public enum Obj {
	essence (0),
	cle (1),
	lime (5),
	surin (10),
	revolver (20);
	
	//Objets directement construits

	  private int correspondance;

	  //Constructeur
	  Obj(int correspondance){
	    this.correspondance = correspondance;
	  }	   
	  
	  public int getCorrespondance(){
		  	return correspondance;
		  }

}
