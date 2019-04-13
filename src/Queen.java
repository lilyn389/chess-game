import javax.swing.ImageIcon;

public class Queen extends Piece {
	
	private ImageIcon queen;
	
	public Queen(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		super(gridIn, color, name, row, column, ID, alive);
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

	public void setIcon(ImageIcon queen) {
		this.queen = queen;
	}
	
	public boolean isValidMove(int x, int y) {
		
		if (row == x && y == column) {
			return false;
		}
	
		//Check for path obstructions
		
		return validQueenMove(x, y);
	}
	
	private boolean validQueenMove(int x, int y) {

		return orthagonalMove(x, y) || diagonalMove(x, y);
	}
	
}
