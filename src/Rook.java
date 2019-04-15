import javax.swing.ImageIcon;

public class Rook extends Piece implements Cloneable{
	
	private ImageIcon rook;

	
	public Rook(Tile[][] grid, String color, String name, int row, int column, int ID, boolean alive) {
		super(grid, color, name, row, column, ID, alive);
		if (color == "white") {
			rook = new ImageIcon("white_castle.png");
		}
		else {
			rook = new ImageIcon("black_castle.png");
		}
	}
	public Rook(Rook in)
	{
		super(in);
		if (color == "white") {
			rook = new ImageIcon("white_castle.png");
		}
		else {
			rook = new ImageIcon("black_castle.png");
		}
	}
	public Object clone() {
	    try {
	        return (Rook) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return new Rook(this.grid, this.color,this.name,this.row,this.column,this.ID,this.is_alive);
	    }
	}
	public ImageIcon getIcon() {
		return rook;
	}

	public void setIcon(ImageIcon rook) {
		this.rook = rook;
	}
	
	public boolean isValidMove(int x, int y) {
		
		if (row == x && y == column) {
			return false;
		}
	
		//Check for path obstruction
	
	
		return validRookMove(x, y);
	}
	
	private boolean validRookMove(int x, int y) {
		if(x >= 0 && x <= 7 && y >= 0 && y<= 7)
		{
			if(!grid[x][y].isEmpty())
				if(grid[x][y].getPiece().color == color)
					return false;
			
					return orthagonalMove(x, y);
		}
		return false;
	}
	public int getValue()
	{
		return 50;
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
