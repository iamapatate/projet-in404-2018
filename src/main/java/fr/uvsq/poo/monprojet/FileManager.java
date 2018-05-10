package fr.uvsq.poo.monprojet;
import java.io.*;

public abstract class FileManager {
	File f;
	FileReader Fread;
	BufferedReader Br;
	FileWriter Fwrite;
	BufferedWriter Bw;
	ObjectOutputStream oos;
	
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
	
	public Donjon LoadGame(String nameoffile) {
		Donjon D = new Donjon();
		// lire fichier
		return D;
	}
	
	public void SaveGame(String nameoffile, Donjon D){
		try {
		      oos = new ObjectOutputStream(
		              new BufferedOutputStream(
		                new FileOutputStream(
		                  new File(nameoffile))));
		      oos.writeObject(D);
	    System.out.println("jeu sauvegardé !");
		 } catch (IOException e) {
			 System.out.println("Une erreur d'écriture, le jeu n'a pas pu sauvegardé correctement");
		 } finally {
		    try {	
		       if (oos != null)	
		          oos.close();	
		    } catch (IOException e) {
		    	System.out.println("Le fichier de sauvegarde n'a pas pu être fermé correctement");
		       e.printStackTrace();
		    }
		 }
	}
}
