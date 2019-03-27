import javax.swing.ImageIcon;

public class King extends Piece {
	
	private ImageIcon king;
	private int row;
	private int column;
	
	public King(String color, String name, int row, int column) {
		super(color, name);
		this.row = row;
		this.column = column;
		if (color == "white") {
			king = new ImageIcon("white_king.png");
		}
		else {
			king = new ImageIcon("black_king.png");
		}
	}

	public ImageIcon getIcon() {
		return king;
	}

	public void setRookIcon(ImageIcon king) {
		this.king = king;
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
	
		return false;
	}
	
	
}
