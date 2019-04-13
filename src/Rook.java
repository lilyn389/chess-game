import javax.swing.ImageIcon;

public class Rook extends Piece {
	
	private ImageIcon rook;

	
	public Rook(Tile[][] grid, String color, String name, int row, int column, int ID, boolean alive) {
		super(grid, color, name, row, column, ID, alive);
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
	
	public boolean isValidMove(int x, int y) {
		
		if (row == x && y == column) {
			return false;
		}
	
		//Check for path obstruction
	
	
		return validRookMove(x, y);
	}
	
	private boolean validRookMove(int x, int y) {
		
		return orthagonalMove(x, y);
	}
}
