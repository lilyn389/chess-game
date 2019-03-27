package chess_proj;

public class Tile {
	
	private int row;
	
	private int col;

	private Pieces piece = null;
	
	
	Tile(int r, int c) {
		
		row = r;
		col = c;
	}
	
	
	public int getRow() {
		
		return row;
	}

	public int getCol() {
		
		return col;
	}
	
	
	public boolean isEmpty() {
		
		return piece == null;
	}
	
	public void addPiece(Pieces p) {
		
		piece = p;
	}
	
	public void removePiece() {
		
		piece = null;
	}
	
	public Pieces getPiece() {
		
		return piece;
	}
}
