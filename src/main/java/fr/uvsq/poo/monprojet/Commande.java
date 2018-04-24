package fr.uvsq.poo.monprojet;
import java.util.*;

public class Commande {
	Scanner sc;
	String action;
	
	public Commande() {
		this.sc = new Scanner(System.in);
		action = new String();
	}
	
	/*
	 *  Retourne un tableau de mots qui composent la commande
	 *  d'entrée du joueur, si la commande est "move up", split[0] = move, split[1] = up.
	 */
	public String[] getAction() {
		this.action = this.sc.nextLine();
		String split[] = this.action.split(" ");
		return split;
	}
	
	public Joueur analyseCommandeJoueur(Joueur J, UneSalleTemporaire S) {
		String[] action;
		action = getAction();
		String ac;
		if(action[0].equals("move")) {
			return J.Move(action[1]);
		}
		else if(action[0].equals("turn")) {
			return J.Turn(action[1]);
		}
		else if(action[0].equals("pick")) {
			ac = S.WhatsInFrontOfPlayer(J);
			System.out.println(ac);
		}
		else if(action[0].equals("open")) {
			ac = S.WhatsInFrontOfPlayer(J);
			if(ac.equals("porte")) {
				System.out.println("Porte ouverte, salle suivante");
			}
		}
		else System.out.println("erreur de commande");
		return J;
	}
}
