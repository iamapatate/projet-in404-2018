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

     * La m�thode de classe <em>main</em> se contente de d�l�guer le lancement du programme � la m�thode <em>run</em>.

     *

     * @param args les param�tres de la ligne de commande du shell

     */

    public static void main(String[] args) {

        APPLICATION.run(args);

    }

}