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
    	Commande com = new Commande();
    	Joueur J = new Joueur("PJ");
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

     * La m�thode de classe <em>main</em> se contente de d�l�guer le lancement du programme � la m�thode <em>run</em>.

     *

     * @param args les param�tres de la ligne de commande du shell

     */

    public static void main(String[] args) {

        APPLICATION.run(args);

    }

}