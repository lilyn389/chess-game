import javax.swing.ImageIcon;

public class Queen extends Piece {
	
	private ImageIcon queen;
	
	public Queen(String color, String name, int row, int column) {
		super(color, name, row, column);
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
	
		return true;
	}
	
	
}
