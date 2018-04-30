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

    	System.out.println("debut");
       // Etage e = new Etage();
       // e.Init_etage();
       // e.aff_etage();
    	System.out.println("liste des commandes: ");
    	System.out.println("(move/turn) + (up/down/left/right)");
    	System.out.println("pick, see, open, attack, inventory");
    	Commande com = new Commande();
    	Salle temp = new Salle();
    	temp = temp.initSalleTemp();
    	temp.affSalleTemp();
    	for(int i = 0; i < 5; i++) {
    		com.analyseCommandeJoueur(temp);
    		temp.affSalleTemp();
    	}
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