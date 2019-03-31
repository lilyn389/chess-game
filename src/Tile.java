
public class Tile {
	
	private Piece piece;  // piece that is on the Tile
	private boolean empty; 
	
	public Tile() {
		empty = true;
	}
	
	public Tile(Piece piece, boolean empty) {
		this.piece = piece;
		this.empty = empty;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
}
