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
    	/*
        Commande com = new Commande();
        int test = -1;
    	
    	System.out.println("Load Game? y/n");
    	while(test == -1) {
    		test = com.analyseCommandeFichier("loadgame");
    	}
        if (test==0){
            Salle temp = new Salle();
            temp = temp.initSalleTemp();
           }
        else if(test==1){
        //charger une sauvegarde
        //Donjon D=charger();
        }
       
    	System.out.println("liste des commandes: ");
    	System.out.println("(move/turn) + (up/down/left/right)");
    	System.out.println("pick, see, open, inventory, sauvegarder");
    	temp.affSalleTemp();
   	for(int i = 0; i < 100; i++) {
    		com.analyseCommandeJoueur(temp);
    		temp.affSalleTemp();
    	}
    }
    	Etage test = new Etage();
    	test = test.initialisation();
    	test = test.generation();
    	test = test.remplirEtage(nb_Objets_max,nb_PNJS_max);
    	test.GetSalle(2,2).affSalle();
    	*/
    	Donjon donjon = new Donjon();
    	donjon = donjon.initDonjon();
    	donjon.niveaux[1].grille[2][1].affSalle();
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