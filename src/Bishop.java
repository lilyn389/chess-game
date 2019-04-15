import javax.swing.ImageIcon;

public class Bishop extends Piece implements Cloneable{
	
	private ImageIcon bishop;
	
	public Bishop(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		super(gridIn, color, name, row, column, ID, alive);
		if (color.equals("white")) {
			bishop = new ImageIcon("white_bishop.png");
		}
		else {
			bishop = new ImageIcon("black_bishop.png");
		}
	}
	public Bishop(Bishop in)
	{
		super(in);
		if (color.equals("white")) {
			bishop = new ImageIcon("white_bishop.png");
		}
		else {
			bishop = new ImageIcon("black_bishop.png");
		}
	}
	public Object clone() {
	    try {
	        return (Bishop) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return new Bishop(this.grid, this.color,this.name,this.row,this.column,this.ID,this.is_alive);
	    }
	}
	public ImageIcon getIcon() {
		return bishop;
	}

	public void setIcon(ImageIcon bishop) {
		this.bishop = bishop;
	}
	
	public boolean isValidMove(int x, int y) {
		
		if ((row == x && y == column)) {
			return false;
		}
		if(x < 0 || x > 7 || y < 0 || y > 7)
		{
			return false;
		}
		if(grid[x][y].getPiece() != null)
		{
			if(grid[x][y].getPiece().color.equals(this.color))
			{
				return false;
			}
		}
		
		return diagonalMove(x, y);
	}
	public int getValue()
	{
		return 10;
	}
}
