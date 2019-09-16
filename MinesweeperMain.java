package minesweeper;

public class MinesweeperMain {

	/**
	 * Dies ist die Minesweeper-Main. Kann ausführen, sonst nichts. Alles andere hat der Controller.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MinesweeperFeldController control = new MinesweeperFeldController();
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
