package fr.uvsq.poo.monprojet;

import java.io.*;

public abstract class FileManager {
	File f;
	FileReader Fread;
	BufferedReader Br;
	FileWriter Fwrite;
	BufferedWriter Bw;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public boolean ExistingFile(String nameoffile) {
		f = new File(nameoffile);
		try {
			Fread = new FileReader(f);
			Br = new BufferedReader(Fread);
		}catch(FileNotFoundException e) {
			System.err.println("Fichier de Sauvegarde inexistant");
			return true;
		}
		try {
			Fread.close();
			Br.close();
		}catch(IOException e) {
			System.err.println("Erreur IO fichier");
		}
		return false;
	}
	
	public Donjon LoadGame(String nameoffile) {
 		Donjon D = new Donjon();
 		// lire fichier
 		try {
 			ois = new ObjectInputStream(
 		              new BufferedInputStream(
 		                new FileInputStream(
 		                  new File(nameoffile))));
			for(int k = 0; k < Variables.Nb_etages; k++) {
		    	  for(int i=0;i<Variables.Nb_largeur_salles;i++) {
		  			for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
		  				D.getNiveau(k).SetGrille(i,j,((Salle)ois.readObject()));
		  			}
		    	  }
		      }
			D.SetSallex(ois.readInt());
			D.SetSalley(ois.readInt());
			D.SetNumEtage(ois.readInt());
	    System.out.println("jeu chargé avec succès !");
 		 } catch (IOException e) {
			 System.err.println("Une erreur de lecture, le jeu n'a pas pu être chargé correctement");
 		 } catch (ClassNotFoundException e) {
			 System.err.println("problème rencontré dans l'écriture de la classe salle");
 		 } finally {
 		    try {	
 		       if (ois != null)	
 		          ois.close();	
 		    } catch (IOException e) {
		    	System.err.println("Le fichier de sauvegarde n'a pas pu être fermé correctement");
 		       //e.printStackTrace();
 		    }
 		 }
 		return D;
 	}
 	
 	public void SaveGame(String nameoffile, Donjon D){
 		try {
 		      oos = new ObjectOutputStream(
 		              new BufferedOutputStream(
 		                new FileOutputStream(
 		                  new File(nameoffile))));
		      for(int k = 0; k < Variables.Nb_etages; k++) {
		    	  for(int i=0;i<Variables.Nb_largeur_salles;i++) {
		  			for(int j=0;j<Variables.Nb_hauteur_salles;j++) {
		  					oos.writeObject(D.getNiveau(k).GetSalle(i,j));
		  			}
		    	  }
		      }
		      oos.writeInt(D.GetSallex());
		      oos.writeInt(D.GetSalley());
		      oos.writeInt(D.GetNumEtage());
		      System.out.println("jeu sauvegardé !");
 		 } catch (IOException e) {
			 System.err.println("Une erreur d'écriture, le jeu n'a pas pu sauvegardé correctement");
 		 } finally {
 		    try {	
 		       if (oos != null)	
 		          oos.close();	
 		    } catch (IOException e) {
		    	System.err.println("Le fichier de sauvegarde n'a pas pu être fermé correctement");
 		       //e.printStackTrace();
 		    }
 		 }
	}
}
