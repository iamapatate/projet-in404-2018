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
    	Commande com = new Commande();
    	Joueur J = new Pj();
    	UneSalleTemporaire temp = new UneSalleTemporaire();
    	temp.initSalleTemp(J);
    	temp.affSalleTemp(J);
    	for(int i = 0; i < 5; i++) {
    		com.analyseCommandeJoueur(J,temp);
    		temp = temp.Update(J);
    		temp.affSalleTemp(J);
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
