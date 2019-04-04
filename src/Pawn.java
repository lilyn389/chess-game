import javax.swing.ImageIcon;

public class Pawn extends Piece {
	
	private ImageIcon pawn;
	
	private boolean firstMove = true;
	
	public Pawn(String color, String name, int row, int column, int ID) {
		super(color, name, row, column, ID);
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
		
		if (firstMove) {
			
			int row_diff = Math.abs(x - this.getRow());
			int column_diff = Math.abs(y - this.getColumn());
			
			if (column_diff == 0 && row_diff == 2) {
				
				firstMove = false;
				return true;
			}			
		}
		
		if (validPawnMove(x, y)) {
			
			firstMove = false;
			return true;
		}
		else {
			
			return false;
		}
	}
	
	private boolean validPawnMove(int x, int y) {
		
		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());
		
		if (column_diff == 0 && row_diff == 1) {
			
			return true;
		}
		else if (column_diff == 1 && row_diff == 1) {
			if(color == "white")
			{
				if(x > row)
					return true;
			}
			if(color == "black")
			{
				if(x < row)
					return true;
			}
			//Needs checks to ensure it only moves forward and check for path obstructions
			
			return false;
		}
		else {
			
			return false;
		}
	}
}
