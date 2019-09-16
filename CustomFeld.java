package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;

//Chris Bauer bbm2h17a 4.7.2018
//Die GUI für ein Feld in dem die Werte übergeben werden für ein benutzerdefiniertes Minesweeperfeld.

public class CustomFeld {

	public JFrame getFrmCustom() {
		return frmCustom;
	}

	private JFrame frmCustom;			//Initialisierung der Variablen
	private JTextField zeilen;
	private JTextField spalten;
	private JTextField minen;
	private JLabel lblMinen, lblzeilen;
	private JButton start;
	private JLabel lblFehler;			
	private Component horizontalStrut;
	private Component verticalStrut;

	/**
	 * Create the application.
	 */
	public CustomFeld() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCustom = new JFrame();
		frmCustom.setTitle("Custom");
		frmCustom.setBounds(100, 100, 440, 220);
		frmCustom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmCustom.setResizable(false);
		frmCustom.setLocationRelativeTo(null);
		frmCustom.getContentPane().setLayout(gridBagLayout);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 0;
		frmCustom.getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblspalten = new JLabel("Spalten");
		GridBagConstraints gbc_lblspalten = new GridBagConstraints();
		gbc_lblspalten.gridwidth = 4;
		gbc_lblspalten.insets = new Insets(0, 0, 5, 5);
		gbc_lblspalten.gridx = 1;
		gbc_lblspalten.gridy = 1;
		frmCustom.getContentPane().add(lblspalten, gbc_lblspalten);
		
		lblMinen = new JLabel("Minen");
		GridBagConstraints gbc_lblMinen = new GridBagConstraints();
		gbc_lblMinen.gridwidth = 2;
		gbc_lblMinen.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinen.gridx = 6;
		gbc_lblMinen.gridy = 1;
		frmCustom.getContentPane().add(lblMinen, gbc_lblMinen);
		
		lblzeilen = new JLabel("Zeilen");
		GridBagConstraints gbc_lblzeilen = new GridBagConstraints();
		gbc_lblzeilen.gridwidth = 2;
		gbc_lblzeilen.insets = new Insets(0, 0, 5, 0);
		gbc_lblzeilen.gridx = 9;
		gbc_lblzeilen.gridy = 1;
		frmCustom.getContentPane().add(lblzeilen, gbc_lblzeilen);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 2;
		frmCustom.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		spalten = new JTextField();
		GridBagConstraints gbc_spalten = new GridBagConstraints();
		gbc_spalten.gridwidth = 4;
		gbc_spalten.insets = new Insets(0, 0, 5, 5);
		gbc_spalten.fill = GridBagConstraints.HORIZONTAL;
		gbc_spalten.gridx = 1;
		gbc_spalten.gridy = 2;
		frmCustom.getContentPane().add(spalten, gbc_spalten);
		spalten.setColumns(10);
		
		lblFehler = new JLabel("Fehler!!!");
		lblFehler.setForeground(Color.RED);
		lblFehler.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblFehler = new GridBagConstraints();
		gbc_lblFehler.insets = new Insets(0, 0, 0, 5);
		gbc_lblFehler.gridheight = 2;
		gbc_lblFehler.gridwidth = 11;
		gbc_lblFehler.gridx = 0;
		gbc_lblFehler.gridy = 4;
		lblFehler.setVisible(false);
		
		minen = new JTextField();
		GridBagConstraints gbc_minen = new GridBagConstraints();
		gbc_minen.gridwidth = 2;
		gbc_minen.insets = new Insets(0, 0, 5, 5);
		gbc_minen.fill = GridBagConstraints.HORIZONTAL;
		gbc_minen.gridx = 6;
		gbc_minen.gridy = 2;
		frmCustom.getContentPane().add(minen, gbc_minen);
		minen.setColumns(10);
		
		zeilen = new JTextField();
		GridBagConstraints gbc_zeilen = new GridBagConstraints();
		gbc_zeilen.gridwidth = 2;
		gbc_zeilen.insets = new Insets(0, 0, 5, 0);
		gbc_zeilen.fill = GridBagConstraints.HORIZONTAL;
		gbc_zeilen.gridx = 9;
		gbc_zeilen.gridy = 2;
		frmCustom.getContentPane().add(zeilen, gbc_zeilen);
		zeilen.setColumns(10);
		
		start = new JButton("Start");
		GridBagConstraints gbc_start = new GridBagConstraints();
		gbc_start.insets = new Insets(0, 0, 5, 5);
		gbc_start.gridwidth = 10;
		gbc_start.gridx = 1;
		gbc_start.gridy = 3;
		frmCustom.getContentPane().add(start, gbc_start);
		frmCustom.getContentPane().add(lblFehler, gbc_lblFehler);
	}
	
	//Initialisierung von gettern
	public JTextField getZeilen() {
		return zeilen;
	}

	public JLabel getLblFehler() {
		return lblFehler;
	}

	public JTextField getSpalten() {
		return spalten;
	}

	public JTextField getMinen() {
		return minen;
	}

	public JButton getStart() {
		return start;
	}

}
