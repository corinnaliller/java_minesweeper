package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import com.sun.org.apache.regexp.internal.recompile;

/** 
 * Die Klasse Daten kann Daten erstellen, einlesen und ausgeben.
 *  Sie aktualisiert die Highscore Textdatei von dem Spiel Minesweeper
 *  und sortiert die Spieler nach Punktestand.
 *  
 *   @author Jacqueline Isabelle Klein (bbm2h17akl)
 *   
 *   @version 1.4
 *   */

public class Daten {

	/**
	 * Attribute
	 */
	
	private ArrayList <Score> spieldaten_anfaenger = new ArrayList<Score>();
	private ArrayList <Score> spieldaten_fortgeschritten = new ArrayList<Score>();
	private ArrayList <Score> spieldaten_profi = new ArrayList<Score>();
	private ArrayList <Score> spieldaten_benutzerdefiniert = new ArrayList<Score>();
	
	/**
	 * Konstruktur zum anlegen von Testdaten
	 */
	public Daten() {
		spieldaten_anfaenger.add(new Score("dummdumm",1,"anfaenger"));
		spieldaten_anfaenger.add(new Score("wooohoooo",20,"anfaenger"));
		spieldaten_anfaenger.add(new Score("ich bin toll",25,"anfaenger"));
		spieldaten_fortgeschritten.add(new Score("loser",0,"fortgeschritten"));
		spieldaten_fortgeschritten.add(new Score("winna",10,"fortgeschritten"));
		spieldaten_fortgeschritten.add(new Score("sowas",8,"fortgeschritten"));
		spieldaten_profi.add(new Score("ich bin zu blöd",2,"profi"));
	}
	
	/** 
	 * Die Textdatei für den Highscore wird angelegt. Sie wird nur angelegt,
	 * Wenn sie nicht vorher schon existierte. In der Datei befinden sich 5 Dummy Daten
	 */
	
	public void erstellen(){
		File[] f = new File[4];
		f[0] = new File("highscore_anfaenger.txt");
		f[1] = new File("highscore_fortgeschritten.txt");
		f[2] = new File("highscore_profi.txt");
		f[3] = new File("highscore_benutzerdefiniert.txt");
		
		for(int i = 0; i < f.length ; i++){
			if(f[i].exists()){
				System.out.println("Datei bereits vorhanden, erstellen nicht nötig");
			}
			else{
				try{
					FileWriter fw = new FileWriter(f[i]);
					fw.write("Spieler 1: 10 Punkte\n"
						+ "Spieler 2: 5 Punkte\n"
						+ "Spieler 3: 2 Punkte\n"
						+ "Spieler 4: 8 Punkte\n"
						+ "Spieler 5: 13 Punkte\n"
						+ "Spieler 6: 10 Punkte\n"
						+ "Spieler 7: 5 Punkte\n"
						+ "Spieler 8: 2 Punkte\n"
						+ "Spieler 9: 8 Punkte\n"
						+ "Spieler 10: 13 Punkte\n");
					fw.close();
				}
				catch(IOException e){
					System.out.println(e);
				}	
			}
		}
	}
	
	/** 
	 * Diese Methode liest die zugewiesenen Werte ein und hängt sie anschließend
	 * in der Highscore Textdatei an. Zusätzlich wird direkt ein neues Objekt in der Array List eingefügt.
	 *
	 *@param spieler - Scoreobjekt (Beinhaltet die Spielerdaten)
	 *
	 */
	
	public void einlesen(Score spieler){
		
		String schwierigkeit = spieler.getSchwierigkeit();
		schwierigkeit.toLowerCase();
		
		try{
			FileWriter fw = new FileWriter("highscore_"+schwierigkeit+".txt", true);
			Score score = new Score(spieler.getName(), spieler.getPunkte());
			
			switch(schwierigkeit){
			case "anfaenger" : spieldaten_anfaenger.add(score);sortArrayList(spieldaten_anfaenger);break;
			case "fortgeschritten" : spieldaten_fortgeschritten.add(score);sortArrayList(spieldaten_fortgeschritten);break;
			case "profi" : spieldaten_profi.add(score);sortArrayList(spieldaten_profi);break;
			case "benutzerdefiniert": spieldaten_benutzerdefiniert.add(score);sortArrayList(spieldaten_benutzerdefiniert);break;
			default: System.out.println("Falsche Eingabe");
			}
			
			fw.write(spieler.getName()+": "+spieler.getPunkte()+"\n");
			fw.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	private void sortArrayList(ArrayList<Score> liste) {
		Collections.sort(liste, new Comparator<Score>() {
			
			@Override
			public int compare(Score o1, Score o2) {
				// TODO Auto-generated method stub
				return o2.getPunkte()-o1.getPunkte();
			}
		});
	}
	
	/** 
	 * Diese Methode liest den Inhalt der Highscore Textdatei aus und gibt sie über die Console aus.
	 *
	 * @param schwierigkeit - Schwierigkeitsgrad des Spieles, anzugeben in: anfaenger, fortgeschritten, profi, benutzerdefiniert)
	 */
	
	public ArrayList<Score> auslesen(String schwierigkeit){
		ArrayList<Score> liste = new ArrayList<Score>();
		try{
			 BufferedReader read = new BufferedReader(new FileReader("highscore_"+schwierigkeit+".txt"));
		      String s = null;
		      
		      while((s = read.readLine()) != null){
		    	  String[] spieler = s.split(":");
		    	  Score score = new Score(spieler[0],Integer.parseInt(spieler[1]));
		    	  liste.add(score);
		      }
		      read.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return liste;
	}
	/**
	 * Übergibt eine gewünschte ArrayList
	 * @param schwierigkeit -Schwierigkeitsgrad des Spieles, anzugeben in: anfaenger, fortgeschritten, profi, benutzerdefiniert)
	 *
	 * @return
	 */
	public ArrayList<Score> gibScores(String schwierigkeit) {
		switch(schwierigkeit) {
		case "anfaenger":return spieldaten_anfaenger;
		case "fortgeschritten": return spieldaten_fortgeschritten;
		case "profi": return spieldaten_profi;
		case "benutzerdefiniert": return spieldaten_benutzerdefiniert;
		default: return null;
		}
		
	}

}
