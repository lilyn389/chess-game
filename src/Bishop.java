import javax.swing.ImageIcon;

public class Bishop extends Piece {
	
	private ImageIcon bishop;
	private int row;
	private int column;
	
	public Bishop(String color, String name, int row, int column) {
		super(color, name);
		this.row = row;
		this.column = column;
		if (color == "white") {
			bishop = new ImageIcon("white_bishop.png");
		}
		else {
			bishop = new ImageIcon("black_bishop.png");
		}
	}

	public ImageIcon getIcon() {
		return bishop;
	}

	public void setRookIcon(ImageIcon bishop) {
		this.bishop = bishop;
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
