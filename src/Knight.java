import javax.swing.ImageIcon;

public class Knight extends Piece {
	
	private ImageIcon knight;
	private int row;
	private int column;
	
	public Knight(String color, String name, int row, int column) {
		super(color, name);
		this.row = row;
		this.column = column;
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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public boolean isValidMove(int x, int y) {
		int row_diff = Math.abs(x - this.row);
		int column_diff = Math.abs(y - this.column);
		
		if ( (row_diff == 1) && (column_diff == 2)) {
			return true;
		}
		if ( (column_diff == 1) && (row_diff == 2)) {
			return true;
		}
		return false;
	}
	
	
}
