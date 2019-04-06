import javax.swing.ImageIcon;

public class Knight extends Piece {
	
	private ImageIcon knight;
	
	public Knight() {
		super();
	}
	
	public Knight(String color, String name, int row, int column, int ID, boolean alive) {
		super(color, name, row, column, ID, alive);

		if (color == "white") {
			knight = new ImageIcon("white_knight.png");
		}
		else {
			knight = new ImageIcon("black_knight.png");
		}
	}

	public ImageIcon getIcon() {
		return knight;
	}

	public void setIcon(ImageIcon knight) {
		this.knight = knight;
	}
	
	//***FIXME*** need to add (in very isValidMove) a check to see if this move 
	// puts your own king in check, this is an invalid move
	public boolean isValidMove(int x, int y) {
		
		if (row == x && y == column) {
			return false;
		}
		
		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());
		
		if ( (row_diff == 1) && (column_diff == 2)) {
			return true;
		}
		if ( (column_diff == 1) && (row_diff == 2)) {
			return true;
		}
		return false;
	}
	
	
}
