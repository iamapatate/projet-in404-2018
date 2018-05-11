package fr.uvsq.poo.monprojet;
/**

 * Cette classe est le programme principal du projet.

 *

 * Elle est une impl�mentation du <em>design pattern</em>

 * <a href="https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)">Singleton</a>.

 *

 * @author St�phane Lopes

 * @version f�v. 2018

 */

public enum Application {

    APPLICATION;


    /**

     * Cette m�thode est destin�e � initialiser et lancer l'ex�cution du programme.

     *

     * @param args les param�tres de la ligne de commande du shell

     */

    public void run(String[] args) {
    	
    	Donjon donjon = new Donjon();       	
        Commande com = new Commande();
        int test = -1;    	
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
		System.out.println("Votre objectif est de trouver la cl� de chaque �tage \nafin de remonter le donjon par des trappes, bonne chance!");       
    	System.out.println("Voici la liste des commandes mise � votre disposition: ");
    	System.out.println("(move/turn) + (up/down/left/right)");
    	System.out.println("pick, see, open, inventory, sauvegarder");
    	
    	donjon.PlayTheGame(com);
    	
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
    	}*/
}

    /**

     * La m�thode de classe <em>main</em> se contente de d�l�guer le lancement du programme � la m�thode <em>run</em>.

     *

     * @param args les param�tres de la ligne de commande du shell

     */

    public static void main(String[] args) {

        APPLICATION.run(args);

    }

}