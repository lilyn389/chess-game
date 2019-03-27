import javax.swing.ImageIcon;

public class Pawn extends Piece {
	
	private ImageIcon pawn;
	private int row;
	private int column;
	
	public Pawn(String color, String name, int row, int column) {
		super(color, name);
		this.row = row;
		this.column = column;
		if (color == "white") {
			pawn = new ImageIcon("white_pawn.png");
		}
		else {
			pawn = new ImageIcon("black_pawn.png");
		}
	}

	public ImageIcon getIcon() {
		return pawn;
	}

	public void setRookIcon(ImageIcon pawn) {
		this.pawn = pawn;
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
