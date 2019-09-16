package minesweeper;

import java.util.Random;
/**
 * Model, das die Spielfelder verwaltet.
 * @author bbm2h17mli
 *
 */

public class FeldModel {

	/**
	 * Attribute
	 */
	private Feld[][] spielFeld;
	private int[][] minenPositionen;
	private Random zufall;
	private int breite, hoehe, minenZahl;
	private String schwierigkeit;
	
	/**
	 * Konstruktor
	 * @param breite
	 * @param hoehe
	 * @param minenZahl
	 * @param schwierigkeit
	 */
	public FeldModel(int breite, int hoehe, int minenZahl, String schwierigkeit) {
		this.breite = breite;
		this.hoehe = hoehe;
		this.minenZahl = minenZahl;
		this.schwierigkeit = schwierigkeit;
		zufall = new Random();
		spielFeld = spielfeldSetzen();
		minenPositionen = minenWuerfeln();
		minenSetzen();
	}
	
	/**
	 * Ein neues Spielfeld in der festgelegten Größe wird angelegt.
	 * @return
	 */
	private Feld[][] spielfeldSetzen() {
		Feld[][] brett = new Feld[breite][hoehe];
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				brett[i][j] = new Feld(i,j,breite,hoehe);
			}
		}
		return brett;
	}
	/**
	 * Die Positionen der Minen werden ausgewürfelt und in einen Array gepackt. Beim Auswürfeln wird überprüft, ob
	 * diese Position bereits im Array vorhanden ist und in diesem Fall noch einmal neu gewürfelt.
	 * @return
	 */
	private int[][] minenWuerfeln() {
		int maxX = breite;
		int maxY = hoehe;
		int[][] minen = new int[minenZahl][];
		for (int i = 0; i < minenZahl; i++) {
			int zX = zufall.nextInt(maxX);
			int zY = zufall.nextInt(maxY);
			if (i == 0) {
				minen[0] = new int[] {zX, zY};
				//System.out.println("Mine:"+(i+1)+zX+","+zY);
			}
			else {
				boolean schonDrin = false;
				for (int j = 0; j < i; j++) {
					int mx = minen[j][0];
					int my = minen[j][1];
					if (mx == zX && my == zY) {
						schonDrin = true;
					}
				}
				if (schonDrin) {
					i--;
				}
				else {
					minen[i] = new int[] {zX, zY};
					//System.out.println("Mine:"+(i+1)+zX+","+zY);
				}
			}
		}
		return minen;
	}
	/**
	 * Den Feldern des Spielfelds wird der Array mit Minenpositionen übergeben, damit sie feststellen können,
	 * wie viele Minen sich in ihrer Umgebung befinden.
	 */
	private void minenSetzen() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				spielFeld[i][j].minenCheck(minenPositionen);
			}
		}
	}
	/**
	 * Minenfeld markieren
	 */
	public void feldMarkieren(int x, int y) {
		if (!spielFeld[x][y].isMarkiert()) {
			spielFeld[x][y].setMarkiert(true);
		}
		else {
			spielFeld[x][y].setMarkiert(false);
		}
	}
		
	/**
	 * Getter und Setter (auch für Zugriff auf das Spielfeld), die der Controller braucht.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean feldMarkiert(int x, int y) {
		return spielFeld[x][y].isMarkiert();
	}
	public int feldStatus(int x, int y) {
		return spielFeld[x][y].getStatus();
	}
	public int[][] welcheNachbarn(int x, int y) {
		return spielFeld[x][y].getNachbarn();
	}

	public Feld[][] getSpielFeld() {
		return spielFeld;
	}

	public int[][] getMinenPositionen() {
		return minenPositionen;
	}

	public int getBreite() {
		return breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public int getMinenZahl() {
		return minenZahl;
	}

	public String getSchwierigkeit() {
		return schwierigkeit;
	}
	
}
