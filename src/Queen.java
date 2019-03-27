import javax.swing.ImageIcon;

public class Queen extends Piece {
	
	private ImageIcon queen;
	private int row;
	private int column;
	
	public Queen(String color, String name, int row, int column) {
		super(color, name);
		this.row = row;
		this.column = column;
		if (color == "white") {
			queen = new ImageIcon("white_queen.png");
		}
		else {
			queen = new ImageIcon("black_queen.png");
		}
	}

	public ImageIcon getIcon() {
		return queen;
	}

	public void setRookIcon(ImageIcon bishop) {
		this.queen = queen;
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
