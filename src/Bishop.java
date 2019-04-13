import javax.swing.ImageIcon;

public class Bishop extends Piece {
	
	private ImageIcon bishop;
	
	public Bishop(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		super(gridIn, color, name, row, column, ID, alive);
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

	public void setIcon(ImageIcon bishop) {
		this.bishop = bishop;
	}
	
	public boolean isValidMove(int x, int y) {
		
		if (row == x && y == column) {
			return false;
		}
	
		return diagonalMove(x, y);
	}
}
