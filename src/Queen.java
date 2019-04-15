import javax.swing.ImageIcon;

public class Queen extends Piece implements Cloneable{
	
	private ImageIcon queen;
	
	public Queen(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		super(gridIn, color, name, row, column, ID, alive);
		if (color.equals("white")) {
			queen = new ImageIcon("white_queen.png");
		}
		else {
			queen = new ImageIcon("black_queen.png");
		}
	}
	public Queen(Queen in)
	{
		super(in);
		if (color.equals("white")) {
			queen = new ImageIcon("white_queen.png");
		}
		else {
			queen = new ImageIcon("black_queen.png");
		}
	}
	public Object clone() {
	    try {
	        return (Queen) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return new Queen(this.grid, this.color,this.name,this.row,this.column,this.ID,this.is_alive);
	    }
	}
	public ImageIcon getIcon() {
		return queen;
	}

	public void setIcon(ImageIcon queen) {
		this.queen = queen;
	}
	
	public boolean isValidMove(int x, int y) {
		
		if(x >= 0 && x <= 7 && y >= 0 && y <= 7)
		{
			if (row == x && y == column) {
				return false;
			}
	
		//Check for path obstructions
		
			if(validQueenMove(x, y))
			{
				if(!grid[x][y].isEmpty())
				{
					if(grid[x][y].getPiece().color.equals(color))
					{
						return false;
					}
				}
				else
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean validQueenMove(int x, int y) {

		return orthagonalMove(x, y) || diagonalMove(x, y);
	}
	public int getValue()
	{
		return 90;
	}
}
