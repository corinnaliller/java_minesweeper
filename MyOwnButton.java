package minesweeper;

import javax.swing.JButton;
/**
 * Eine Ableitung von JButton, damit diesem seine Koordinaten zugewiesen werden können, auf die dann
 * der Controller zugreifen kann.
 * @author bbm2h17mli
 *
 */
public class MyOwnButton extends JButton {

	private int posX, posY;
	
	public MyOwnButton(int x, int y) {
		super();
		posX = x;
		posY = y;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
}
