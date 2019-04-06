import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board chess_gui = new board();
		chess_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// pass the grid to the kings for the check mate function
		chess_gui.getKings()[0].setGrid(chess_gui.getGrid());
		chess_gui.getKings()[1].setGrid(chess_gui.getGrid());
	}

}

