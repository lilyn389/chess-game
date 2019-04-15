import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AIBoard extends board implements Runnable {
	//public AI ai;


	// public MiniMaxAlphaBeta tree;
	public AIBoard(boolean _AI_AI, boolean _AI_play, boolean _network_play, String _AIColor, String _AI_difficulty,
			String _IP, boolean _visible) throws InterruptedException, IOException {
		super(_AI_AI, _AI_play, _network_play, _AIColor, _AI_difficulty, _IP, _visible);
		ai = new AI(this, _AIColor, _AI_difficulty);
		Thread t = new Thread(this,"AI_board");
		t.start();
		/*
		 * private int processSelection(int x, int y) { if
		 * (grid[x][y].isEmpty()) { return -1; } else if (network_play &&
		 * grid[x][y].getPiece().getColor().equals(my_color)) { selected =
		 * grid[x][y].getPiece(); select = true; return 0; } else if
		 * (!network_play) { selected = grid[x][y].getPiece(); select = true;
		 * return 0; } else return -1; } checkMoveLocation()
		 */
		// tree = new MiniMaxAlphaBeta(this,_AIColor,)
	}

	/*
	 * void processMove(int x, int y) { //super.processMove(x,y);
	 * 
	 * }
	 */
	/*protected void sendMove(int x, int y) {

	}

	protected boolean getMove() {

	}
*/
	public void run() {
		// compute primes larger than minPrime
		try {
			connectToServer(IP);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		while (true) {
			if (ai.team == "white") {
				// builds the tree and calculates move
				Move out = ai.calculateBestMove();
				// replaces it in the board
				Piece p = grid[out.getX1()][out.getY1()].getPiece();
				tiles[out.getX1()][out.getY1()].setIcon(null);
				tiles[out.getX1()][out.getY1()].setIcon(p.getIcon());
				grid[p.getRow()][p.getColumn()].setPiece(null);
				grid[p.getRow()][p.getColumn()].setEmpty(true);
				p.setRow(out.getX2());
				p.setColumn(out.getY2());
				grid[out.getX2()][out.getY2()].setPiece(selected);
				grid[out.getX2()][out.getY2()].setEmpty(false);
				// moved = true;
				// sends it
				sendMove(out.getX2(), out.getY2());
				// receives and updates move
				getMove();
			} else if (ai.team == "black") {
				getMove();
				// builds the tree and calculates move
				Move out = ai.calculateBestMove();
				// replaces it in the board
				Piece p = grid[out.getX1()][out.getY1()].getPiece();
				tiles[out.getX1()][out.getY1()].setIcon(null);
				tiles[out.getX1()][out.getY1()].setIcon(p.getIcon());
				grid[p.getRow()][p.getColumn()].setPiece(null);
				grid[p.getRow()][p.getColumn()].setEmpty(true);
				p.setRow(out.getX2());
				p.setColumn(out.getY2());
				grid[out.getX2()][out.getY2()].setPiece(selected);
				grid[out.getX2()][out.getY2()].setEmpty(false);
				// moved = true;
				// sends it
				sendMove(out.getX2(), out.getY2());
				// receives and updates move

			}
		}
	}
	/*
	 * void processMove(int x, int y) { //super.processMove(x,y);
	 * 
	 * 
	 * }
	 */
	/*
	 * selected = grid[from_row][from_column].getPiece(); processMove(to_row,
	 * to_column);
	 */
	/*
	 * private int processSelection(int x, int y) { if (grid[x][y].isEmpty()) {
	 * return -1; } else if (network_play &&
	 * grid[x][y].getPiece().getColor().equals(my_color)) { selected =
	 * grid[x][y].getPiece(); select = true; return 0; } else if (!network_play)
	 * { selected = grid[x][y].getPiece(); select = true; return 0; } else
	 * return -1; }
	 */
	/*
	 * private class ButtonHandler implements ActionListener {
	 * 
	 * public void actionPerformed(ActionEvent event) { // find which tile was
	 * clicked, pass that location as a param to processClick() Object source =
	 * event.getSource(); for (int i = 0; i < 8; i++) { for (int j = 0; j < 8;
	 * j++) { if (source == tiles[i][j]) { processClick(i, j); return; } } } } }
	 */
}
