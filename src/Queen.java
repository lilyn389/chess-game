import javax.swing.ImageIcon;

public class Queen extends Piece {
	
	private ImageIcon queen;
	
	public Queen(String color, String name, int row, int column, int ID) {
		super(color, name, row, column, ID);
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

	public void setRookIcon(ImageIcon queen) {
		this.queen = queen;
	}
	
	public boolean isValidMove(int x, int y) {
	
		//Check for path obstructions
		
		return validQueenMove(x, y);
	}
	
	private boolean validQueenMove(int x, int y) {

		return orthagonalMove(x, y) || diagonalMove(x, y);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
