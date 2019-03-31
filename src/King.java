import javax.swing.ImageIcon;

public class King extends Piece {
	
	private ImageIcon king;
	
	private boolean firstMove = true;
	
	public King(String color, String name, int row, int column, int ID) {
		super(color, name, row, column, ID);
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
	
		if (firstMove) {
			
			if (validKingMove(x, y) || validCastle(x, y)) {
				
				firstMove = false;
				return true;
			}
			else {
				
				return false;
			}
		}
	
		return validKingMove(x, y);
	}
	
	private boolean validKingMove(int x, int y) {
		
		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());
		
		//FUTURE FIX: invalidates moves into check
		
		if (row_diff == 1 && column_diff == 1) {
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	//Future feature
	private boolean validCastle(int x, int y) {
		
		return false;
	}
}
