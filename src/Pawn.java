import javax.swing.ImageIcon;

public class Pawn extends Piece {
	
	private ImageIcon pawn;
	
	public Pawn(String color, String name, int row, int column) {
		super(color, name, row, column);
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
	
	public boolean isValidMove(int x, int y) {
	
		return true;
	}
	
	
}
