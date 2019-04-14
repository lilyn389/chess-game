
public class Tile implements Cloneable{
	
	private Piece piece;  // piece that is on the Tile
	private boolean empty; 
	public Object clone() {
	    try {
	        return (Tile) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return new Tile(this.piece, this.empty);
	    }
	}
	public Tile() {
		empty = true;
		piece = null;
	}
	public Tile(Piece piece, boolean empty) {
		this.piece = piece;
		this.empty = empty;
	}
	public Tile(Tile in)
	{
		if(in.piece instanceof Pawn)
		this.piece = new Pawn((Pawn)in.piece);
		if(in.piece instanceof Bishop)
			this.piece = new Bishop((Bishop)in.piece);
		if(in.piece instanceof King)
			this.piece = new King((King)in.piece);
		if(in.piece instanceof Knight)
			this.piece = new Knight((Knight)in.piece);
		if(in.piece instanceof Queen)
			this.piece = new Queen((Queen)in.piece);
		if(in.piece instanceof Rook)
			this.piece = new Rook((Rook)in.piece);
		this.empty = in.empty;
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
