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
		
		ai.team = AIColor;

		
		if (network_play)
		{
			Thread t = new Thread(this,"AI_board");
			t.start();
		}
		else 
		{
			Thread t = new Thread(this, "local");
			t.start();
		}
	}

	public void run() {
		if (network_play)
		{
			// compute primes larger than minPrime
			try {
				connectToServer(IP);
				ai.team = my_color;
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
			while (true) {
				if (ai.team.equals("white")) {
					// builds the tree and calculates move
					Move out = ai.calculateBestMove();
					// replaces it in the board
					Piece p = grid[out.getX1()][out.getY1()].getPiece();
					selected = p;
					tiles[out.getX1()][out.getY1()].setIcon(null);
					tiles[out.getX2()][out.getY2()].setIcon(p.getIcon());
					grid[p.getRow()][p.getColumn()].setPiece(null);
					grid[p.getRow()][p.getColumn()].setEmpty(true);
					row_moved_from = selected.getRow();
					column_moved_from = selected.getColumn();
					p.setRow(out.getX2());
					p.setColumn(out.getY2());
					grid[out.getX2()][out.getY2()].setPiece(selected);
					grid[out.getX2()][out.getY2()].setEmpty(false);				// change turns
					if (turn.equals("white")) turn = "black";
					else turn = "white";
					// moved = true;
					// sends it
					sendMove(out.getX2(), out.getY2());
					// receives and updates move
					getMove();
				} else if (ai.team.equals("black")) {
					//recieves and updates move
					getMove();
					// builds the tree and calculates move
					Move out = ai.calculateBestMove();
					// replaces it in the board
					Piece p = grid[out.getX1()][out.getY1()].getPiece();
					selected = p;
					tiles[out.getX1()][out.getY1()].setIcon(null);
					tiles[out.getX2()][out.getY2()].setIcon(p.getIcon());
					grid[p.getRow()][p.getColumn()].setPiece(null);
					grid[p.getRow()][p.getColumn()].setEmpty(true);
					row_moved_from = selected.getRow();
					column_moved_from = selected.getColumn();
					p.setRow(out.getX2());
					p.setColumn(out.getY2());
					grid[out.getX2()][out.getY2()].setPiece(selected);
					grid[out.getX2()][out.getY2()].setEmpty(false);				// change turns
					if (turn.equals("white")) turn = "black";
					else turn = "white";
					// moved = true;
					// sends it
					sendMove(out.getX2(), out.getY2());
					// receives and updates move
				}
			}
		}
		else 
		{
			while (true)
			{
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				if (ai.team.equals(turn))
				{
					AIMove();
				}
			}
		}
	}
	
	public void AIMove()
	{
		Move out = ai.calculateBestMove();
		// replaces it in the board
		Piece p = grid[out.getX1()][out.getY1()].getPiece();
		selected = p;
		tiles[out.getX1()][out.getY1()].setIcon(null);
		tiles[out.getX2()][out.getY2()].setIcon(p.getIcon());
		grid[p.getRow()][p.getColumn()].setPiece(null);
		grid[p.getRow()][p.getColumn()].setEmpty(true);
		row_moved_from = selected.getRow();
		column_moved_from = selected.getColumn();
		p.setRow(out.getX2());
		p.setColumn(out.getY2());
		grid[out.getX2()][out.getY2()].setPiece(selected);
		grid[out.getX2()][out.getY2()].setEmpty(false);
	}
}
