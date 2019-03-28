import javax.swing.ImageIcon;

public class Bishop extends Piece {
	
	private ImageIcon bishop;
	
	public Bishop(String color, String name, int row, int column) {
		super(color, name, row, column);
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
	
	public boolean isValidMove(int x, int y) {
	
		return true;
	}
	
	
}
