import javax.swing.ImageIcon;

public class Rook extends Piece {
	
	private ImageIcon rook;
	private int row;
	private int column;
	
	public Rook(String color, String name, int row, int column) {
		super(color, name);
		this.row = row;
		this.column = column;
		if (color == "white") {
			rook = new ImageIcon("white_castle.png");
		}
		else {
			rook = new ImageIcon("black_castle.png");
		}
	}

	public ImageIcon getIcon() {
		return rook;
	}

	public void setIcon(ImageIcon rook) {
		this.rook = rook;
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
