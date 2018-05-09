package fr.uvsq.poo.monprojet;
import java.io.*;

public abstract class FileManager {
	File f;
	FileReader Fread;
	BufferedReader Br;
	FileWriter Fwrite;
	BufferedWriter Bw;
	
	public boolean ExistingFile(String nameoffile) {
		f = new File(nameoffile);
		try {
			Fread = new FileReader(f);
			Br = new BufferedReader(Fread);
		}catch(FileNotFoundException e) {
			System.err.println("Fichier de Sauvegarde inexistant");
			return false;
		}
		try {
			Fread.close();
			Br.close();
		}catch(IOException e) {
			System.err.println("Erreur IO fichier");
		}
		return true;
	}
	
	public void LoadGame(File f) {
		
	}
	
	public void SaveGame(String nameoffile, Etage E){
		f = new File(nameoffile);
		if(!ExistingFile(nameoffile)) {
			try{
				f.createNewFile();
				Fwrite = new FileWriter(f);
				Bw = new BufferedWriter(Fwrite);
			}catch(IOException e){
				System.err.println("Erreur création de fichier");
			}
		}
		else {
			try {
				Fwrite = new FileWriter(f);
				Bw = new BufferedWriter(Fwrite);
			}catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
		
		// faire les instructions pour sauvegarder un étage
		// salle par salle
		// exemple sauvegarder 1 salle
		// type x y vie puissance liste d'objets
		// PJ   4 5 3   50        surin lime 
		// PNJ  3 3     35        cle		
		// si objet
		// Objet type x y
		// Objet cle  5 6
		// exemple complet
		// PJ 4 5 3 50 surin lime cle
		// PNJ 3 4 30 cle
		// PNJ 3 3 10 pistol
		// Objet cle 4 4
		// Objet pistol 5 7
	}
}
