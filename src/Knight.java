import javax.swing.ImageIcon;

public class Knight extends Piece {
	
	private ImageIcon knight;
	
	public Knight() {
		super();
	}
	
	public Knight(String color, String name, int row, int column, int ID) {
		super(color, name, row, column,  ID);
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
	
	public boolean isValidMove(int x, int y) {
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
