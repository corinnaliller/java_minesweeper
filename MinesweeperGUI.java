package minesweeper;

/**
 * Die GUI für Minesweeper. Kann in unterschiedlichen Größen angelegt werden.
 * Die Felden werden hierbei komfortabel in einer for-Schleife angelegt.
 * 
 * @author Jacqueline Isabelle Klein (bbm2h17akl), Chris Bauer (bbm2h17aba)
 * 
 * @version 1.3
 */

import java.awt.Dimension;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

public class MinesweeperGUI {

	/**
	 * Attribute
	 */
	
	private JFrame frmMinesweeper;
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	private GridBagConstraints[][] gridC;
	
	private JTextField time = new JTextField(3);
	private JTextField bombs = new JTextField(3);
	private JLabel lblBomben = new JLabel("Bomben");
	private JLabel lblTime = new JLabel("Zeit");
	private MyOwnButton[][] buttonFelder;
	private JButton smiley;
	
	private int xFelderAnzahl, yFelderAnzahl;
	private String path = "H:\\bbm2h17a\\public\\Semester 2\\Projekte\\JAVA-SWE\\Gruppe EPIC SHARPS\\Production\\Projekt\\";
	
	private Component horizontalStrut;
	
	/**
	 * Der Konstruktor, welcher das GUI sichtbar macht und die Initialisierung aufruft.
	 * Übergeben werden die X und Y Werte des gewünschten Spielfeldes.
	 * 
	 * @param xFelderAnzahl - Anzahl der Felder auf der X - Achse
	 * @param yFelderAnzahl - Anzahl der Felder auf der Y - Achse
	 */
	
	public MinesweeperGUI(int xFelderAnzahl, int yFelderAnzahl) {
		this.xFelderAnzahl = xFelderAnzahl;
		this.yFelderAnzahl = yFelderAnzahl;
		initialize();
		frmMinesweeper.setVisible(true);
	}

	/**
	 * Initialisierung des GUIs
	 */
	
	private void initialize() {		
		frmMinesweeper = new JFrame();
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinesweeper.setBounds(0, 0, (int)(300+(xFelderAnzahl*25)), (int)(200+(yFelderAnzahl*25)));
		frmMinesweeper.getContentPane().setLayout(gridBagLayout);
		
		buttonFelder = new MyOwnButton[xFelderAnzahl][yFelderAnzahl];
		gridC = new GridBagConstraints[xFelderAnzahl][yFelderAnzahl];
		
		for(int x = 0; x < xFelderAnzahl; x++){
			for(int y = 0; y<yFelderAnzahl; y++){
				buttonFelder[x][y] = new MyOwnButton(x,y);
				gridC[x][y] = new GridBagConstraints();
				gridC[x][y].insets = new Insets(0,0,5,5);
				gridC[x][y].anchor = GridBagConstraints.WEST;
				gridC[x][y].gridx = x+2;
				gridC[x][y].gridy = y+3;
				buttonFelder[x][y].setPreferredSize(new Dimension(20,20));
				buttonFelder[x][y].setIcon(new ImageIcon(path+"tile.png"));
				frmMinesweeper.getContentPane().add(buttonFelder[x][y], gridC[x][y]);
			}
		}
		
		c.anchor = GridBagConstraints.WEST;
		
		smiley = new JButton("");
		GridBagConstraints gbc_smiley = new GridBagConstraints();
		gbc_smiley.gridwidth = 3;
		gbc_smiley.gridheight = 3;
		gbc_smiley.anchor = GridBagConstraints.CENTER;
		gbc_smiley.insets = new Insets(0, 0, 5, 0);
		gbc_smiley.gridx = xFelderAnzahl/2;
		gbc_smiley.gridy = 0;
		smiley.setPreferredSize(new Dimension(40, 40));
		smiley.setIcon(new ImageIcon(path+"happy.png"));
		frmMinesweeper.getContentPane().add(smiley, gbc_smiley);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		c.insets = new Insets(0, 0, 5, 5);
		c.gridx = xFelderAnzahl+2;
		c.gridy = 0+(int)(yFelderAnzahl/4);
		frmMinesweeper.getContentPane().add(horizontalStrut, c);
		
		c.insets = new Insets(0, 0, 5, 5);
		c.gridx = xFelderAnzahl+3;
		c.gridy = 0+(int)(yFelderAnzahl/2.7);
		frmMinesweeper.getContentPane().add(lblBomben, c);
		
		bombs.setEditable(false);
		bombs.setText("0");
		c.insets = new Insets(0, 0, 5, 0);
		c.gridx = xFelderAnzahl+4;
		c.gridy = 0+(int)(yFelderAnzahl/2.7);
		frmMinesweeper.getContentPane().add(bombs, c);
		
		c.insets = new Insets(0, 0, 5, 5);
		c.gridx = xFelderAnzahl+3;
		c.gridy = 1+(int)(yFelderAnzahl/2.7);
		frmMinesweeper.getContentPane().add(lblTime, c);
		
		time.setText("0");
		time.setEditable(false);
		c.insets = new Insets(0, 0, 5, 0);
		c.gridx = xFelderAnzahl+4;
		c.gridy = 1+(int)(yFelderAnzahl/2.7);
		frmMinesweeper.getContentPane().add(time, c);
		
	}

	/**
	 * Übergeben werden X und Y Werte des Spielfeldes, sowie der jeweilige Status des Feldes.
	 * Über ein Switch wird der Status abgefragt und das Image des Feldes wird je nach Koordinate gewechselt.
	 *
	 *@param x - X-Wert des Spielfeldes
	 *@param y - Y-Wert des Spielfeldes
	 *@param status - Status des Spielfeldes
	 */
	
	public void bildWechsel(int x, int y, int status){
		switch(status){
		case 0:buttonFelder[x][y].setIcon(new ImageIcon(path+"empty.png"));break;
		case 1:buttonFelder[x][y].setIcon(new ImageIcon(path + "one.png"));break;
		case 2:buttonFelder[x][y].setIcon(new ImageIcon(path + "two.png"));break;
		case 3:buttonFelder[x][y].setIcon(new ImageIcon(path + "three.png"));break;
		case 4:buttonFelder[x][y].setIcon(new ImageIcon(path + "four.png"));break;
		case 5:buttonFelder[x][y].setIcon(new ImageIcon(path + "five.png"));break;
		case 6:buttonFelder[x][y].setIcon(new ImageIcon(path + "six.png"));break;
		case 7:buttonFelder[x][y].setIcon(new ImageIcon(path + "seven.png"));break;
		case 8:buttonFelder[x][y].setIcon(new ImageIcon(path + "eight.png"));break;
		case 9:buttonFelder[x][y].setIcon(new ImageIcon(path + "bomb_red.png"));break;
		case 10:buttonFelder[x][y].setIcon(new ImageIcon(path + "bomb.png"));break;
		case 11:buttonFelder[x][y].setIcon(new ImageIcon(path + "flag.png"));break;
		case 12:buttonFelder[x][y].setIcon(new ImageIcon(path + "tile.png"));break;
		}
	}
	
	/**
	 * Übergeben wird der Status des Smileys.
	 * Je nach Status wird mittels Switch das jeweilige Icon neugesetzt.
	 * 
	 * @param status - Status des Smileys
	 */
	
	public void smileyWechsel(int status){
		switch(status){
		case 0:smiley.setIcon(new ImageIcon(path + "happy.png"));break;
		case 1:smiley.setIcon(new ImageIcon(path + "wow.png"));break;
		case 2:smiley.setIcon(new ImageIcon(path + "cool.png"));break;
		case 3:smiley.setIcon(new ImageIcon(path + "dead.png"));break;
		}
	}
	
	/**
	 * Die Getter / Setter Methoden
	 */
	
	public JButton getSmiley() {
		return smiley;
	}
	
	public MyOwnButton[][] getButtonFelder() {
		return buttonFelder;
	}
	
	public JTextField getBombs() {
		return bombs;
	}

	public void setBombs(JTextField bombs) {
		this.bombs = bombs;
	}

	public JTextField getTime() {
		return time;
	}

	public void setTime(JTextField time) {
		this.time = time;
	}

	public int getxFelderAnzahl() {
		return xFelderAnzahl;
	}

	public void setxFelderAnzahl(int xFelderAnzahl) {
		this.xFelderAnzahl = xFelderAnzahl;
	}

	public int getyFelderAnzahl() {
		return yFelderAnzahl;
	}

	public void setyFelderAnzahl(int yFelderAnzahl) {
		this.yFelderAnzahl = yFelderAnzahl;
	}

	public void setSmiley(JButton smiley) {
		this.smiley = smiley;
	}
	public JFrame getFrmMinesweeper() {
		return frmMinesweeper;
	}

	public void setFrmMinesweeper(JFrame frmMinesweeper) {
		this.frmMinesweeper = frmMinesweeper;
	}
}
