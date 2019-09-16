package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

/**
 * Eine Highscore GUI der besten 10 Spieler.
 * Die Spielerliste wird mittels einer for-Schleife angelegt.
 * Die Rangliste wird über eine Methode aktualisiert.
 * 
 * @author Jacqueline Isabelle Klein (bbm2h17akl)
 * 
 * @version 1.4
 *
 */
public class Highscore extends JFrame {
	
	/**
	 * Attribute
	 */
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	private JLabel lblHighscore = new JLabel("Highscore");
	private JButton neustarten = new JButton ("Neustarten");
	private JLabel[][] platzierungen = new JLabel[10][3];  
	
	/**
	 * Der Konstruktorm welcher das Fenster erstellt.
	 */
	public Highscore() {
		setTitle("Minesweeper - Highscore");
		setResizable(false);
		setBounds(400, 100, 350,370);
		getContentPane().setLayout(gridBagLayout);
		
		// Top 10 angelegen
		for(int i = 0; i < 10; i++){
			platzierungen[i][0] = new JLabel(1+i+".");
			platzierungen[i][0].setFont(new Font("Tahoma", Font.BOLD, 14));
			c.insets = new Insets(0,0,5,0);
			c.gridx = 0;
			c.gridy = 2+i;
			getContentPane().add(platzierungen[i][0], c);
			
			platzierungen[i][1] = new JLabel(" - ");
			platzierungen[i][1].setFont(new Font("Tahoma", Font.PLAIN, 14));
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 1;
			c.gridy = 2+i;
			getContentPane().add(platzierungen[i][1], c);
			
			platzierungen[i][2] = new JLabel(" - ");
			platzierungen[i][2].setFont(new Font("Tahoma", Font.BOLD, 14));
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 2;
			c.gridy = 2+i;
			getContentPane().add(platzierungen[i][2], c);
		}
		
		// Highscore Label
		lblHighscore.setFont(new Font("Tahoma", Font.BOLD, 18));
		c.gridwidth = 7;
		c.insets = new Insets(0, 0, 5, 5);
		c.gridx = 0;
		c.gridy = 1;
		getContentPane().add(lblHighscore, c);
		
		c.gridx = 0;
		c.gridy = 13;
		getContentPane().add(neustarten,c);
		
	}
	
	/**
	 * Mit dieser Methode werden die Platzierungen geschrieben.
	 * Das ganze geschieht über eine zugewiesene ArrayList vom Typ Score.
	 * 
	 * @param liste - Übergeben wird eine ArrayListe vom Typ Score, welche die Spielerdaten enthält
	 */
	public void setPlatzierungen(ArrayList<Score> liste){
		
		for(int i = 0; i < liste.size() ; i++){
			if(i > 10){
				break;
			}
			else{
				platzierungen[i][1].setText(liste.get(i).getName());
				platzierungen[i][2].setText(liste.get(i).getPunkte()+ " Punkte");	
			}
		}
	}
	
	/**
	 * Die Getter und Setter Methoden
	 */
	
	public JLabel[][] getPlatzierungen() {
		return platzierungen;
	}

	public JButton getNeustarten() {
		return neustarten;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Highscore h = new Highscore();
	}

}
