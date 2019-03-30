import javax.swing.ImageIcon;

public class Rook extends Piece {
	
	private ImageIcon rook;

	
	public Rook(String color, String name, int row, int column, int ID) {
		super(color, name, row, column, ID);
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
	
		return true;
	}
	
	
}
