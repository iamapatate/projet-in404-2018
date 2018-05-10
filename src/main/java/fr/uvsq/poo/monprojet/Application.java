package fr.uvsq.poo.monprojet;
/**

 * Cette classe est le programme principal du projet.

 *

 * Elle est une implémentation du <em>design pattern</em>

 * <a href="https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)">Singleton</a>.

 *

 * @author Stéphane Lopes

 * @version fév. 2018

 */

public enum Application {

    APPLICATION;


    /**

     * Cette méthode est destinée à initialiser et lancer l'exécution du programme.

     *

     * @param args les paramètres de la ligne de commande du shell

     */

    public void run(String[] args) {
    	
    	Donjon donjon = new Donjon();       	
        Commande com = new Commande();
        int test = -1; int ChangingPlaces = 0;
        int salleX = 0; int salleY = 0; int NumEtage = 0;
    	
    	System.out.println("Load Game? y/n");
    	while(test == -1) {
    		test = com.analyseCommandeFichier("loadgame");
    	}
        if (test==0){
        	donjon = donjon.initDonjon();
           }
        else if(test==1){
        // charger une sauvegarde
        donjon = com.charger();
        }
		System.out.println("Votre objectif est de trouver la clé de chaque étage \nafin de remonter le donjon par des trappes, bonne chance!");       
    	System.out.println("Voici la liste des commandes mise à votre disposition: ");
    	System.out.println("(move/turn) + (up/down/left/right)");
    	System.out.println("pick, see, open, inventory, sauvegarder");
		donjon.getNiveau(NumEtage).GetSalle(salleX, salleY).affSalle();    	
    	while(ChangingPlaces != 4 && NumEtage != Variables.Nb_etages) {
    		System.out.println("Vous êtes en Salle " + salleX + " " + salleY + " à l'étage: " + NumEtage);
    		ChangingPlaces = com.analyseCommandeJoueur(donjon.getNiveau(NumEtage).GetSalle(salleX,salleY), donjon);
    		
    		if(ChangingPlaces == 0) { // haut
    			salleY++;
    		}
    		else if(ChangingPlaces == 1) {// droite
    			salleX++;
    		}
    		else if(ChangingPlaces == 2) {// bas
    			salleY--;
    		}
    		else if(ChangingPlaces == 3) {// gauche
    			salleX--;
    		}
    		else if(ChangingPlaces == 4) {
    			NumEtage++;
    			salleX = 0;
    			salleY = 0;
    		}
    	}
    	
    	
    	/*
    	for(int i = 0; i < Variables.Nb_etages; i++) {
    		for(int j = 0; j < Variables.Nb_largeur_salles; j++) {
    			for(int k = 0; k < Variables.Nb_hauteur_salles; k++) {
    				System.out.println(i);
    				System.out.println(j);
    				System.out.println(k);
    	    		donjon.getNiveau(i).getGrille(j,k).affSalle();
    			}
    		}
    	}
    	*/
}

    /**

     * La méthode de classe <em>main</em> se contente de déléguer le lancement du programme à la méthode <em>run</em>.

     *

     * @param args les paramètres de la ligne de commande du shell

     */

    public static void main(String[] args) {

        APPLICATION.run(args);

    }

}