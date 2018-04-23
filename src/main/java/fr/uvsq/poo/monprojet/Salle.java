
public class Salle {
	String grille[][] = new String[Variables.largeur_salle][Variables.hauteur_salle];
	boolean Porte_N;
	boolean Porte_E;
	boolean Porte_S;
	boolean Porte_W;
	int Nb_portes;
	int Type;
	
	
	
	public Salle Init_salle() {
		Salle S = new Salle();
		
		for(int i = 0; i < Variables.largeur_salle; i++) {
			for(int j = 0; j < Variables.hauteur_salle; j++) {
				S.grille[i][j] = null;
			}
		}
		
		S.Porte_N = true;
		S.Porte_E = true;
		S.Porte_S = true;
		S.Porte_W = true;
		S.Nb_portes = 4;
		S.Type = -1;
		
		return S;
	}
}
