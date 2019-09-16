package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

/**
 * Der Controller holt sich alle Klassen, die er braucht und bei den GUIs auch Zugriff auf Labels und Knöpfe.
 * Außerdem verfügt er über einen eigenen ActionListener und MouseListener (innere Klassen)
 * @author bbm2h17mli
 *
 */
public class MinesweeperFeldController {
	
	/**
	 * Attribute
	 */
	private MinesweeperGUI spielFenster;
	private Optionen startFenster;
	private Highscore highscores;
	private CustomFeld benutzerdef;
	private JButton anfaenger, fortgeschritten, profi, benutzer, smiley, neustarten, custom;
	private JTextField bomben,time;
	private MyOwnButton[][] buttons;
	private FeldModel model;
	private int breite, hoehe, minenZahl, aufgedeckt,mineMarkiert,maxPunkte;
	private KnopfLauscher knoepfe;
	private MausLauscher maus;
	private long startZeit, endZeit;
	private Daten schreiber;
	private boolean spielende;
	
	/**
	 * Am Anfang öffnet der Controller das Fenster, mit dem der User den Schwierigkeitsgrad bestimmen kann.
	 * Hilfsklassen werden bereits angelegt, weitere Fenster bleiben erst einmal unsichtbar bis sie gebraucht
	 * werden.
	 * 
	 */
	public MinesweeperFeldController() {
		schreiber = new Daten();
		startFenster = new Optionen();
		startFenster.setLocationRelativeTo(null);
		knoepfe = new KnopfLauscher();
		maus = new MausLauscher();
		anfaenger = startFenster.getBtnAnfnger();
		fortgeschritten = startFenster.getBtnFortgeschritten();
		profi = startFenster.getBtnProfi();
		benutzer = startFenster.getBtnBenutzerdefiniert();
		anfaenger.addActionListener(knoepfe);
		fortgeschritten.addActionListener(knoepfe);
		profi.addActionListener(knoepfe);
		benutzer.addActionListener(knoepfe);
		highscores = new Highscore();
		neustarten = highscores.getNeustarten();
		neustarten.addActionListener(knoepfe);
		benutzerdef = new CustomFeld();
		custom = benutzerdef.getStart();
		custom.addActionListener(knoepfe);
	}
	/**
	 * Wird aufgerufen, wenn der User sich für einen Schwierigkeitsgrad entschieden hat. Das Spielfenster wird dann nach
	 * den betreffenden Parametern aufgerufen.
	 * @param breite
	 * @param hoehe
	 * @param minenZahl
	 * @param schwierigkeit
	 */
	public void neuesSpiel(int breite, int hoehe, int minenZahl, String schwierigkeit) {
		spielende = false;
		startZeit = System.currentTimeMillis();
		endZeit = 0;
		mineMarkiert = 0;
		this.breite = breite;
		this.hoehe = hoehe;
		this.minenZahl = minenZahl;
		aufgedeckt = 0;
		maxPunkte = minenZahl*1000;
		model = new FeldModel(this.breite, this.hoehe, this.minenZahl, schwierigkeit);
		spielFenster = new MinesweeperGUI(this.breite, this.hoehe);
		spielFenster.getFrmMinesweeper().setLocationRelativeTo(null);
		bomben = spielFenster.getBombs();
		bomben.setText(Integer.toString(minenZahl));
		time = spielFenster.getTime();
		smiley = spielFenster.getSmiley();
		smiley.addActionListener(knoepfe);
		buttons = spielFenster.getButtonFelder();
		buttonsVerbinden();
		buttonsEinsschalten();
		//minenAnzeigen();
		sekundenZaehlen();
	}
	/**
	 * Der Buttonarray wird mit dem MouseListener verbunden. Die Dimensionen entsprechen dem Spielfeld-Array.
	 */
	private void buttonsVerbinden() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				buttons[i][j].addMouseListener(maus);
			}
		}
	}
	
	/**
	 * Diese Methode dient dem Entwickler zum Überprüfen der Gewinn-Methode. Die Minen werden sofort markiert.
	 */
	private void minenAnzeigen() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				if(model.getSpielFeld()[i][j].isMine()) {
					spielFenster.bildWechsel(i, j, 11);
				}
			}
		}
	}
	/**
	 * Diese Methode deckt ein angeklicktes Feld auf. Dabei werden Fehlübertragungen erst einmal abgefangen.
	 * Nur ein geschlossenes Feld kann geöffnet werden - sonst passiert nichts. Beim Aufdecken wird das Icon
	 * des korrespondierenden Buttons angepasst und zeigt die Zahl der Nachbarn - oder eine Mine. Wenn eine
	 * Mine getroffen wurde, ist das Spiel verloren.
	 * Wenn ein freies Feld ohne benachbarte Minen aufgedeckt wird, werden in einer Kaskade alle benachbarten
	 * Felder ohne Minen aufgedeckt. Dies geschieht durch rekursiven Aufruf der Methode selbst, wenn der Status
	 * des Feldes 0 ist.
	 * Nach jedem Aufdecken wird überprüft, ob das Spielziel (alle Felder außer den verminten aufdecken) erreicht
	 * wurde.
	 * @param x
	 * @param y
	 */
	public void feldAufdecken(int x, int y) {
		if (x < breite && y < hoehe) {
			if (x != -1 && y != -1) {
				boolean schonOffen = model.getSpielFeld()[x][y].isOffen();
				if (!schonOffen) {
					model.getSpielFeld()[x][y].setOffen(true);
					aufgedeckt++;
					int nummer = model.getSpielFeld()[x][y].getStatus();
					if (nummer < 9) {
						if (nummer == 0) {
							int[][] nachbarn = model.getSpielFeld()[x][y].getNachbarn();
							spielFenster.bildWechsel(x, y, nummer);
							for (int i = 0; i < nachbarn.length; i++) {
								int nx = nachbarn[i][0];
								int ny = nachbarn[i][1];
								boolean geoeffnet = model.getSpielFeld()[nx][ny].isOffen();
								int check = model.getSpielFeld()[nx][ny].getStatus();
								if (!geoeffnet && check != 9) {
									feldAufdecken(nx, ny);
									//System.out.println("Rekursion für"+nx+","+ny);
								}
							}
						}
						else {
							spielFenster.bildWechsel(x, y, nummer);
						}
					}
					else {
						spielFenster.bildWechsel(x, y, 9);
						youLose(x, y);
					}
				}
			}
			winCheck();
		}
	}
	/**
	 * Überprüft, ob das Spielziel erreicht wurde und beendet das Spiel, wenn dem so ist.
	 */
	private void winCheck() {
		if (aufgedeckt >= (breite*hoehe-minenZahl)) {
			spielFenster.smileyWechsel(2);
			endZeit = System.currentTimeMillis();
			spielende = true;
			int sekunden = (int) (endZeit - startZeit)/1000;
			System.out.println(sekunden+" s");
			int punkte = maxPunkte / sekunden;
			System.out.println(punkte+" Punkte");
			minenAufdecken(-1,-1);
			buttonsAusschalten();
			spielZuende(punkte);
		}
	}
	/**
	 * Wird bei Spielende (gewonnen oder verloren) aufgerufen, damit der Spieler seinen Namen eingeben kann und dieser
	 * gespeichert werden kann.
	 * Das Exception-Handling verhindert ein Abstürzen des Programms, wenn der User bei dem JOptionPane auf "Abbrechen"
	 * klickt.
	 * @param punkte
	 */
	private void spielZuende(int punkte) {
		String spielername = "";
		try {
			do {
				spielername = JOptionPane.showInputDialog("Sie haben "+punkte+" Punkte erreicht.\nBitte geben Sie Ihren Namen ein", null);
			}
			while (spielername.equals(""));
		}
		catch (Exception e) {
			spielername = "leer";
		}
		finally {
			Score neu = new Score(spielername,punkte,model.getSchwierigkeit());
			schreiber.einlesen(neu);
			spielFenster.getFrmMinesweeper().setVisible(false);
			spielFenster.getFrmMinesweeper().dispose();
			highscores.setPlatzierungen(schreiber.gibScores(model.getSchwierigkeit()));
			highscores.setVisible(true);
		}
		
	}
	/**
	 * Zu spielende werden alle Minen angezeigt. Wenn das Spiel verloren ist, wird die Mine, auf die geklickt wurde
	 * weiterhin mit rotem Hintergrund gezeigt - alle anderen mit grauem. Wenn das Spiel gewonnen ist, werden alle
	 * mit grauem Hintergrund gezeigt. (Gewinnfunktion übergibt beim Aufruf einfach (-1,-1), Verlierfunktion übergibt
	 * die Koordinaten der ausgelösten Mine.)
	 * @param x
	 * @param y
	 */
	private void minenAufdecken(int x, int y) {
		int[][] minenPositionen = model.getMinenPositionen();
		for (int[] position : minenPositionen) {
			if (position[0] != x && position[1] != y ) {
				spielFenster.bildWechsel(position[0], position[1], 10);
			}
		}
	}
	/**
	 * Wird aufgerufen, wenn eine Mine angeklickt wurde.
	 * @param x
	 * @param y
	 */
	private void youLose(int x, int y) {
		minenAufdecken(x, y);
		buttonsAusschalten();
		spielFenster.smileyWechsel(3);
		endZeit = System.currentTimeMillis();
		spielende = true;
		int sekunden = (int) (endZeit - startZeit)/10;
		int punkte = maxPunkte / sekunden;
		spielZuende(punkte);
	}
	/**
	 * Zu Spielende werden alle Buttons disabled.
	 */
	private void buttonsAusschalten() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				buttons[i][j].setEnabled(false);
			}
		}
	}
	/**
	 * Um alle Buttons wieder zu enablen.
	 */
	private void buttonsEinsschalten() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < hoehe; j++) {
				buttons[i][j].setEnabled(true);
			}
		}
	}
	
	/**
	 * Diese Methode ändert die Sekundenanzeige
	 */
	public void sekundenZaehlen(){
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(!spielende) {
					time.setText(Integer.toString(messen()));
				}
				else {
					cancel();
				}
			}
		}, 0, 1000);
		
	}
	
	/** Diese Methode misst die aktuelle Spielzeit*/
	public int messen(){
		long gerade = System.currentTimeMillis();
		int spielzeit = (int)(gerade-startZeit)/1000;
		return spielzeit;
	}
	
	/**
	 * Der ActionListener horcht auf die Events der Buttons
	 * @author bbm2h17mli
	 *
	 */
	class KnopfLauscher implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == anfaenger) {
				//System.out.println("Anfänger");
				neuesSpiel(10, 10, 15, "anfaenger");
				startFenster.setVisible(false);
			}
			else if (e.getSource() == fortgeschritten) {
				//System.out.println("Fortgeschritten");
				neuesSpiel(15, 15, 35, "fortgeschritten");
				startFenster.setVisible(false);
			}
			else if (e.getSource() == profi) {
				//System.out.println("Profi");
				neuesSpiel(20, 20, 70, "profi");
				startFenster.setVisible(false);
			}
			else if (e.getSource() == benutzer) {
				startFenster.setVisible(false);
				benutzerdef.getFrmCustom().setVisible(true);
			}
			if (e.getSource() == smiley) {
				spielFenster.getFrmMinesweeper().setVisible(false);
				spielFenster.getFrmMinesweeper().dispose();
				startFenster.setVisible(true);
				
			}
			if (e.getSource() == neustarten) {
				spielFenster.getFrmMinesweeper().setVisible(false);
				spielFenster.getFrmMinesweeper().dispose();
				highscores.setVisible(false);
				startFenster.setVisible(true);
			}
			/**
			 * Benutzerdefiniert. Der User kann selbst ein Feld beliebiger Größe eingeben, solange es mindestens
			 * 5x5 (5 Minen) und höchstens 40x70 Felder groß ist. Die Minenanzahl muss zwischen 10% und 90% der
			 * Felder liegen.
			 * Das Exception-Handling fängt Fehleingaben des Users ab.
			 */
			if (e.getSource() == custom) {
				boolean okay = false;
				int hoehe = 10;
				int breite = 10;
				int minen = 20;
				try {
					hoehe = Integer.parseInt(benutzerdef.getZeilen().getText());
					breite = Integer.parseInt(benutzerdef.getSpalten().getText());
					minen = Integer.parseInt(benutzerdef.getMinen().getText());
					if (minen > (hoehe*breite)) {
						benutzerdef.getLblFehler().setVisible(true);
						benutzerdef.getLblFehler().setText("Wie soll das denn gehen???");
					}
					else if (hoehe > 40) {
						benutzerdef.getLblFehler().setVisible(true);
						benutzerdef.getLblFehler().setText("Feld zu hoch!");
					}
					else if (breite > 70) {
						benutzerdef.getLblFehler().setVisible(true);
						benutzerdef.getLblFehler().setText("Feld zu breit!");
					}
					else if (minen > (hoehe*breite)*0.9) {
						benutzerdef.getLblFehler().setVisible(true);
						benutzerdef.getLblFehler().setText("Zu viele Minen");
					}
					else if (minen < (hoehe*breite)*0.1) {
						benutzerdef.getLblFehler().setVisible(true);
						benutzerdef.getLblFehler().setText("Zu wenige Minen. Mehr Mut!");
					}
					else if (minen < 5 || hoehe < 5 || breite < 5) {
						benutzerdef.getLblFehler().setVisible(true);
						benutzerdef.getLblFehler().setText("Mindestgröße: 5x5 mit 5 Minen");
					}
					else {
						okay = true;
					}
					if(okay) {
						neuesSpiel(breite, hoehe, minen, "benutzerdefiniert");
						benutzerdef.getFrmCustom().setVisible(false);
					}		
				}
				catch (NumberFormatException ex) {
					benutzerdef.getLblFehler().setVisible(true);
					benutzerdef.getLblFehler().setText("Geben Sie anständige Zahlen ein!");
				}
				catch(Exception excep) {
					benutzerdef.getLblFehler().setVisible(true);
					benutzerdef.getLblFehler().setText("Was machen Sie da???");
				}
			}
		}
		
	}
	
	/**
	 * Der MouseListener bedient die Buttons, die zum Spielfeld gehören. Als erstes holt sich der MouseListener
	 * den Button und extrahiert dann die x- und y-Koordinaten.
	 * @author bbm2h17mli
	 *
	 */
	class MausLauscher implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			MyOwnButton geklickt = (MyOwnButton) e.getSource();
			int px = geklickt.getPosX();
			int py = geklickt.getPosY();
			
			if (SwingUtilities.isLeftMouseButton(e)) {
				feldAufdecken(px, py);
			}
			else if (SwingUtilities.isRightMouseButton(e)) {
				if (!model.getSpielFeld()[px][py].isMarkiert()) {
					model.feldMarkieren(px, py);
					spielFenster.bildWechsel(px, py, 11);
					mineMarkiert++;
					bomben.setText(Integer.toString(minenZahl-mineMarkiert));
				}
				else {
					model.getSpielFeld()[px][py].setMarkiert(false);
					spielFenster.bildWechsel(px, py, 12);
					mineMarkiert--;
					bomben.setText(Integer.toString(minenZahl-mineMarkiert));
				}
				
			}
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			spielFenster.smileyWechsel(1);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			spielFenster.smileyWechsel(0);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
