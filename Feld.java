package minesweeper;
/**
 * Die einzelnen Spielfelder
 * @author bbm2h17mli
 *
 */

public class Feld {

	/**
	 * Attribute: Status, Position, Feldgröße;
	 * offen, markiert, vermint;
	 * Positionen der Nachbarn;
	 */
	private int status, posX, posY, feldBreite, feldHoehe;
	private boolean offen, markiert, mine;
	private int[][] nachbarn;
	
	/**
	 * Der Konstruktor
	 * @param x - x-Position
	 * @param y - y-Position
	 * @param breite - Feldbreite in Feldern
	 * @param hoehe - Feldhoehe in Feldern
	 */
	public Feld(int x, int y, int breite, int hoehe) {
		posX = x;
		posY = y;
		status = 0;
		feldBreite = breite;
		feldHoehe = hoehe;
		offen = false;
		markiert = false;
		mine = false;
		nachbarn = nachbarnBerechnen();
	}
	
	/**
	 * Diese Methode ermittelt die Anzahl und x-/y-Koordinaten der benachbarten Felder
	 * in Abhängigkeit der Position des Feldes auf dem Feld
	 * @return Ein Array von [x,y]-Positionen
	 */
	private int[][] nachbarnBerechnen() {
		int[][] nachbarn;
		if (posX == 0) {
			if (posY == 0) {
				nachbarn = new int[3][];
				nachbarn[0] = new int[] {posX+1,posY};
				nachbarn[1] = new int[] {posX,posY+1};
				nachbarn[2] = new int[] {posX+1,posY+1};
			}
			else if (posY == (feldHoehe-1)) {
				nachbarn = new int[3][];
				nachbarn[0] = new int[] {posX,posY-1};
				nachbarn[1] = new int[] {posX+1,posY-1};
				nachbarn[2] = new int[] {posX+1,posY};
			}
			else {
				nachbarn = new int[5][];
				nachbarn[0] = new int[] {posX,posY-1};
				nachbarn[1] = new int[] {posX+1,posY-1};
				nachbarn[2] = new int[] {posX+1,posY};
				nachbarn[3] = new int[] {posX,posY+1};
				nachbarn[4] = new int[] {posX+1,posY+1};
			}
		}
		else if (posX == (feldBreite-1)) {
			if (posY == 0) {
				nachbarn = new int[3][];
				nachbarn[0] = new int[] {posX-1,posY};
				nachbarn[1] = new int[] {posX-1,posY+1};
				nachbarn[2] = new int[] {posX,posY+1};
			}
			else if (posY == (feldHoehe-1)) {
				nachbarn = new int[3][];
				nachbarn[0] = new int[] {posX-1,posY-1};
				nachbarn[1] = new int[] {posX,posY-1};
				nachbarn[2] = new int[] {posX-1,posY};
			}
			else {
				nachbarn = new int[5][];
				nachbarn[0] = new int[] {posX-1,posY-1};
				nachbarn[1] = new int[] {posX,posY-1};
				nachbarn[2] = new int[] {posX-1,posY};
				nachbarn[3] = new int[] {posX-1,posY+1};
				nachbarn[4] = new int[] {posX,posY+1};
			}
		}
		else {
			if (posY == 0) {
				nachbarn = new int[5][];
				nachbarn[0] = new int[] {posX-1,posY};
				nachbarn[1] = new int[] {posX+1,posY};
				nachbarn[2] = new int[] {posX-1,posY+1};
				nachbarn[3] = new int[] {posX,posY+1};
				nachbarn[4] = new int[] {posX+1,posY+1};
			}
			else if (posY == (feldHoehe-1)) {
				nachbarn = new int[5][];
				nachbarn[0] = new int[] {posX-1,posY-1};
				nachbarn[1] = new int[] {posX,posY-1};
				nachbarn[2] = new int[] {posX+1,posY-1};
				nachbarn[3] = new int[] {posX-1,posY};
				nachbarn[4] = new int[] {posX+1,posY};
			}
			else {
				nachbarn = new int[8][];
				nachbarn[0] = new int[] {posX-1,posY-1};
				nachbarn[1] = new int[] {posX,posY-1};
				nachbarn[2] = new int[] {posX+1,posY-1};
				nachbarn[3] = new int[] {posX-1,posY};
				nachbarn[4] = new int[] {posX+1,posY};
				nachbarn[5] = new int[] {posX-1,posY+1};
				nachbarn[6] = new int[] {posX,posY+1};
				nachbarn[7] = new int[] {posX+1,posY+1};
			}
		}
		return nachbarn;
	}
	
	/**
	 * Diese Methode bekommt von dem Modell die von diesem ausgewürfelten Positionen der Minen übergeben.
	 * Wenn das Feld seine eigene Position in der Liste findet, setzt es sich als Mine. Wenn es eine Mine
	 * auf der Position eines Nachbarfeldes vorfindet, zählt es diese. Am Ende bekommt es (wenn es selbst
	 * keine Mine ist) die Anzahl der Minen-Nachbarn als Status. (Default: 0)
	 * @param minenPositionen
	 */
	public void minenCheck(int[][] minenPositionen) {
		int minenNah = 0;
		for (int i =0; i < minenPositionen.length; i++) {
			if (posX == minenPositionen[i][0] && posY == minenPositionen[i][1]) {
				status = 9;
				mine = true;
				break;
			}
			else {
				int mx = minenPositionen[i][0];
				int my = minenPositionen[i][1];
				
				if (mx <= (posX+1)&& mx >= (posX-1)) {
					if (my <= (posY+1)&& my >= (posY-1)) {
						minenNah++;
					}
				}
			}
		}
		if (minenNah != 0 && status != 9 && !mine) {
			status = minenNah;
		}
	}

	/**
	 * Getter und Setter die das Modell braucht
	 * @return
	 */
	public boolean isOffen() {
		return offen;
	}

	public void setOffen(boolean offen) {
		this.offen = offen;
	}

	public boolean isMarkiert() {
		return markiert;
	}

	public void setMarkiert(boolean markiert) {
		this.markiert = markiert;
	}

	public int getStatus() {
		return status;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isMine() {
		return mine;
	}

	public int[][] getNachbarn() {
		return nachbarn;
	}
	
}
