package minesweeper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;


/**
 * Ein Grafisches User Interface für die Schwierigkeitsoptionen von Minesweeper. 
 * Durch klicken auf den Buttons kann die Schwierigkeitsstufe eingestellt werden.
 * 
 * @author Jacqueline Isabelle Klein (bbm2h17akl)
 * 
 * @version 1.0
 */

public class Optionen extends JFrame {

	/**
	 * Attribute
	 */
	private JPanel contentPane;
	private JLabel lblOptionen = new JLabel("Optionen");
	
	private GridBagLayout gbl_contentPane = new GridBagLayout();
	private GridBagConstraints gbc_btnAnfnger = new GridBagConstraints();
	private GridBagConstraints gbc_btnProfi = new GridBagConstraints();
	private GridBagConstraints gbc_btnFortgeschritten = new GridBagConstraints();
	private GridBagConstraints gbc_lblOptionen = new GridBagConstraints();
	private GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
	private GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
	private GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
	private GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
	private GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
	private GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
	private GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
	private GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
	private GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
	
	private JButton btnAnfnger = new JButton("Anf\u00E4nger");
	private JButton btnFortgeschritten = new JButton("Fortgeschritten");
	private JButton btnProfi = new JButton("Profi");
	private JButton btnBenutzerdefiniert = new JButton("Benutzerdefiniert");
	
	private final Component horizontalStrut = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_2 = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_3 = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_4 = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_5 = Box.createHorizontalStrut(20);
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final Component verticalStrut_2 = Box.createVerticalStrut(20);
	

	/**
	 * Optionen Fenster wird erstellt
	 */
	public Optionen() {
		setTitle("Minesweeper - Optionen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 5;
		gbc_verticalStrut.gridy = 0;
		contentPane.add(verticalStrut, gbc_verticalStrut);
		
		gbc_horizontalStrut_4.gridwidth = 5;
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_4.gridx = 0;
		gbc_horizontalStrut_4.gridy = 1;
		contentPane.add(horizontalStrut_4, gbc_horizontalStrut_4);
		
		lblOptionen.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc_lblOptionen.insets = new Insets(0, 0, 5, 5);
		gbc_lblOptionen.gridx = 5;
		gbc_lblOptionen.gridy = 1;
		contentPane.add(lblOptionen, gbc_lblOptionen);
		
		gbc_horizontalStrut_5.gridwidth = 6;
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_5.gridx = 6;
		gbc_horizontalStrut_5.gridy = 1;
		contentPane.add(horizontalStrut_5, gbc_horizontalStrut_5);
		
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 5;
		gbc_verticalStrut_1.gridy = 2;
		contentPane.add(verticalStrut_1, gbc_verticalStrut_1);
		
		gbc_horizontalStrut.gridwidth = 2;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 3;
		contentPane.add(horizontalStrut, gbc_horizontalStrut);
		
		gbc_btnAnfnger.gridwidth = 2;
		gbc_btnAnfnger.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnfnger.gridx = 2;
		gbc_btnAnfnger.gridy = 3;
		contentPane.add(btnAnfnger, gbc_btnAnfnger);
		
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 4;
		gbc_horizontalStrut_2.gridy = 3;
		contentPane.add(horizontalStrut_2, gbc_horizontalStrut_2);
		gbc_btnFortgeschritten.fill = GridBagConstraints.HORIZONTAL;
		
		gbc_btnFortgeschritten.insets = new Insets(0, 0, 5, 5);
		gbc_btnFortgeschritten.gridx = 5;
		gbc_btnFortgeschritten.gridy = 3;
		contentPane.add(btnFortgeschritten, gbc_btnFortgeschritten);
		
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 6;
		gbc_horizontalStrut_3.gridy = 3;
		contentPane.add(horizontalStrut_3, gbc_horizontalStrut_3);
		gbc_btnProfi.gridwidth = 3;
		gbc_btnProfi.insets = new Insets(0, 0, 5, 5);
		gbc_btnProfi.gridx = 7;
		gbc_btnProfi.gridy = 3;
		contentPane.add(btnProfi, gbc_btnProfi);
		
		gbc_horizontalStrut_1.gridwidth = 2;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 10;
		gbc_horizontalStrut_1.gridy = 3;
		contentPane.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		GridBagConstraints gbc_btnBenutzerdefiniert = new GridBagConstraints();
		gbc_btnBenutzerdefiniert.insets = new Insets(0, 0, 5, 5);
		gbc_btnBenutzerdefiniert.gridx = 5;
		gbc_btnBenutzerdefiniert.gridy = 4;
		contentPane.add(btnBenutzerdefiniert, gbc_btnBenutzerdefiniert);
		
		gbc_verticalStrut_2.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_2.gridx = 5;
		gbc_verticalStrut_2.gridy = 5;
		contentPane.add(verticalStrut_2, gbc_verticalStrut_2);
		setVisible(true);
	}

	/**
	 * Getter für die Buttons, damit der Controller darauf zugreifen kann
	 * @return
	 */
	public JButton getBtnAnfnger() {
		return btnAnfnger;
	}

	public JButton getBtnFortgeschritten() {
		return btnFortgeschritten;
	}

	public JButton getBtnProfi() {
		return btnProfi;
	}
	public JButton getBtnBenutzerdefiniert() {
		return btnBenutzerdefiniert;
	}
}
