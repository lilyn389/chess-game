import javax.swing.ImageIcon;

public class Knight extends Piece implements Cloneable{
	
	private ImageIcon knight;
	
	public Knight() {
		super();
	}
	
	public Knight(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		super(gridIn, color, name, row, column, ID, alive);

		if (color == "white") {
			knight = new ImageIcon("white_knight.png");
		}
		else {
			knight = new ImageIcon("black_knight.png");
		}
	}
	public Knight(Knight in)
	{
		super(in);
		if (color == "white") {
			knight = new ImageIcon("white_knight.png");
		}
		else {
			knight = new ImageIcon("black_knight.png");
		}
	}
	public Object clone() {
	    try {
	        return (Knight) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return new Knight(this.grid, this.color,this.name,this.row,this.column,this.ID,this.is_alive);
	    }
	}
	public ImageIcon getIcon() {
		return knight;
	}

	public void setIcon(ImageIcon knight) {
		this.knight = knight;
	}
	
	//***FIXME*** need to add (in very isValidMove) a check to see if this move 
	// puts your own king in check, this is an invalid move
	public boolean isValidMove(int x, int y) {
		if(x < 0 || y < 0 || x > 7 || y > 7)
		{
			return false;
		}
		else
		{
			if (row == x && y == column) {
				return false;
			}
		
			int row_diff = Math.abs(x - this.getRow());
			int column_diff = Math.abs(y - this.getColumn());
			
			if ( (row_diff == 1) && (column_diff == 2)) 
			{
				if(!grid[x][y].isEmpty())
				{
					if(grid[x][y].getPiece().color == color)
					{
						return false;
					}
				}
				else
				{
					return true;
				}
			}
			if ( (column_diff == 1) && (row_diff == 2)) 
			{
				if(!grid[x][y].isEmpty())
				{
					if(grid[x][y].getPiece().color == color)
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
	public int getValue()
	{
		return 30;
	}

	@Override
	public boolean isCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCheckMate() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
