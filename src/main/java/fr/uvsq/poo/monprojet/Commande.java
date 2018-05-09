package fr.uvsq.poo.monprojet;
import java.util.*;

public class Commande {
	protected Scanner sc;
	protected String action;
	
	public Commande() {
		this.sc = new Scanner(System.in);
		action = new String();
	}
	
	/*
	 *  Retourne un tableau de mots qui composent la commande
	 *  d'entr�e du joueur, si la commande est "move up", split[0] = move, split[1] = up.
	 */
	public String[] getAction() {
		this.action = this.sc.nextLine();
		String split[] = this.action.split(" ");
		return split;
	}
	
	public int analyseCommandeFichier(String Message) {
		String[] action;
		action = getAction();
		if(Message.equals("loadgame")) {
			if(action[0].equals("y")) {
				System.out.println("Vous souhaitez continuer d'une ancienne sauvegarde");
				return 1;
			}
			else if(action[0].equals("n")){
				System.out.println("Vous souhaitez faire un nouveau jeu");
				return 0;
			}
			else {
				System.err.println("Entrez y/n en minuscules");
				return -1;
			}
		}
		System.out.println("Aucune information valide");
		return -1;
	}
	
	
	public void analyseCommandeJoueur(Salle S) {
		String[] action;
		action = getAction();
		String ac;

		if(action[0].equals("move")) {
			S.joueur.Move(S,action[1]);
		}
		else if(action[0].equals("turn")) {
			S.joueur.Turn(action[1]);
		}
		else if(action[0].equals("see")) {
			ac = S.WhatsInFrontOfPlayer(S.joueur);
			System.out.println(ac);
		}
		else if(action[0].equals("open")) {
			S = S.ChangeRoom();
		}
		else if(action[0].equals("take")) {
			S = S.getObjetFromFloor();
		}
		else if(action[0].equals("inventory")) {
			S.joueur.CheckStatsAndInventory();
		}
		else System.out.println("erreur de commande");
		S.Update();
	}
}
