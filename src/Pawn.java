import javax.swing.ImageIcon;

public class Pawn extends Piece {
	
	private ImageIcon pawn;
	
	private boolean firstMove = true;
	public Pawn()
	{
		super();
	}
	public Pawn(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		super(gridIn, color, name, row, column, ID, alive);
		if (color == "white") {
			pawn = new ImageIcon("white_pawn.png");
		}
		else {
			pawn = new ImageIcon("black_pawn.png");
		}
	}
	public Pawn(Pawn in)
	{
		super(in);
		if (color == "white") {
			pawn = new ImageIcon("white_pawn.png");
		}
		else {
			pawn = new ImageIcon("black_pawn.png");
		}
	}
	public Object clone() {
	    try {
	        return (Pawn) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return new Pawn(this.grid, this.color,this.name,this.row,this.column,this.ID,this.is_alive);
	    }
	}
	public ImageIcon getIcon() {
		return pawn;
	}

	public void setIcon(ImageIcon pawn) {
		this.pawn = pawn;
	}
	
	public boolean isValidMove(int x, int y) {
		
		if (row == x && y == column) {
			return false;
		}
    
		if (firstMove) {
			if(x >= 0 && x <= 7 && y >= 0 && y<= 7)
			{
				int row_diff = Math.abs(x - this.getRow());
				int column_diff = Math.abs(y - this.getColumn());
			
				if (column_diff == 0 && row_diff == 2 && grid[x][y].isEmpty()) {
				
					if(color == "white")
					{
					
						if (!grid[this.getRow() + 1][y].isEmpty()) {
						
							return false;
						}
					}
					else if(color == "black")
					{
					
						if (!grid[this.getRow() - 1][y].isEmpty()) {
						
							return false;
						}
					}
				
				firstMove = false;
				return true;
				}			
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
		if(x >= 0 && x <= 7 && y >= 0 && y<= 7)
		{
			int row_diff = Math.abs(x - this.getRow());
			int column_diff = Math.abs(y - this.getColumn());
		
			//Normal move
			if (column_diff == 0 && row_diff == 1) {
			
				//Piece blocking move
				if (!grid[x][y].isEmpty()) {
				
					return false;
				}
		
				//Ensuring only forward moves
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
			
			return false;
			}
			//Attack condition
			else if (column_diff == 1 && row_diff == 1) {
			
				//No attack taking place
				if (grid[x][y].isEmpty()) {
				
					return false;
				}
				//Ensuring only forward attacks
				if(color == "white")
				{
					if(x > row)
					{
						if(grid[x][y].getPiece().color != color)
						{
							return true;
						}
					}
				}
				if(color == "black")
				{
					if(x < row)
					{
						if(grid[x][y].getPiece().color != color)
						{
							return true;
						}
					}
				}
			//Needs checks to ensure it only moves forward and check for path obstructions
			
			return false;
			}
			else {
			
				return false;
			}
		}
		return false;
	}
	public int getValue()
	{
		return 10;
	}
}
