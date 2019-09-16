package minesweeper;

/** Hier wird ein neues Objekt der Klasse Score definiert. Es beinhaltet die Daten des Spielers von Minesweeper.
 * 	Zu den Daten gehören Spielername, Punktestand so wie Schwierigkeitsgrad des Spieles.
 * 
 *  @author Jacqueline Isabelle Klein (bbm2h17akl)
 *  
 *  @version 1.0*/

public class Score {
	/**
	 * Attribute
	 */
	private String name, schwierigkeit;
	private int punkte;
	
	/**
	 * Konstruktoren
	 * @param name - Spielername
	 * @param punkte - Punktestand des Spielers
	 * @param schwierigkeit - Schwierigkeitsgrad des Spieles, anzugeben in: anfaenger, fortgeschritten, profi, benutzerdefiniert
	 */
	public Score(String name, int punkte) {
		this.name = name;
		this.punkte = punkte;
		schwierigkeit = "n.d.";
	}
	public Score(String name, int punkte, String schwierigkeit){
		this.name = name;
		this.punkte = punkte;
		this.schwierigkeit = schwierigkeit;
	}

	/**
	 * Zum Vergleichen zweier Scores, damit diese in der ArrayList geordnet werden können.
	 * @param vergleich - Score Objekt wird übergeben - dessen Punktestand wird dann verglichen
	 * @return
	 */
	public int compareTo(Score vergleich) {
		int vergleichen = ((Score)vergleich).getPunkte();
		return vergleichen - punkte;
	}
	
	/**
	 * Getter und Setter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public String getSchwierigkeit() {
		return schwierigkeit;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return name + ": " + punkte+ " Punkte, "+schwierigkeit+"\n";
	}
	
	
}
