import javax.swing.ImageIcon;

public class King extends Piece {
	
	private ImageIcon king;
	
	public King(String color, String name, int row, int column) {
		super(color, name, row, column);
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
	
	public boolean isValidMove(int x, int y) {
	
		return true;
	}
	
	
}
