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
    	/*Commande com = new Commande();
    	Salle temp = new Salle();
    	temp = temp.initSalleTemp();
    	int test = -1;
    	
    	System.out.println("Load Game? y/n");
    	while(test == -1) {
    		test = com.analyseCommandeFichier("loadgame");
    	}
       
    	System.out.println("liste des commandes: ");
    	System.out.println("(move/turn) + (up/down/left/right)");
    	System.out.println("pick, see, open, inventory");
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

     * La m�thode de classe <em>main</em> se contente de d�l�guer le lancement du programme � la m�thode <em>run</em>.

     *

     * @param args les param�tres de la ligne de commande du shell

     */

    public static void main(String[] args) {

        APPLICATION.run(args);

    }

}