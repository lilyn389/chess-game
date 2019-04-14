import javax.swing.ImageIcon;

public abstract class Piece {

	protected String color;
	protected String name;
	protected int row;
	protected int column;
	protected int ID;
	protected boolean is_alive;  // true if the piece is alive and on the board
	protected Tile[][] grid;
  
	public Piece() {
		color = "red";
		name = "none";
		row = 100;
		column = 100;
	}
	
	public Piece(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
		this.color = color;
		this.name = name;
		this.row = row;
		this.column = column;
		this.ID = ID;
		is_alive = alive;
		grid = gridIn;
	}
	public Piece(Piece in)
	{
		//Tile[][] in2 = deepCopy(in.grid);
		this.grid = in.grid;
		this.color = in.color;
		this.name = in.name;
		this.row = in.row;
		this.column = in.column;
		this.ID = in.ID;
		this.is_alive = in.is_alive;
	}
	public Tile[][] deepCopy(Tile[][] in)
	{
		if(in.length >0)
		{
			Tile[][] out = new Tile[in.length][in[0].length]; 
			for(int i =0;i<in.length;++i)
			{
				for(int j =0;j<in[0].length;++j)
				{
					out[i][j] = new Tile(in[i][j]);
				}
			}
			return out;
		}
		Tile[][] out = {{}};
		return out;
	}
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean isAlive() {
		return is_alive;
	}

	public void setIsAlive(boolean is_alive) {
		this.is_alive = is_alive;
	}

	public void empty() {
		color = "red";
		name = "none";
	}
	
	public boolean isEmpty() {
		if (name == "none") {
			return true;
		}
		return false;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
  
	protected void updateGrid(Tile[][] gridIn)
	{
		grid = gridIn;
	}
	
	// abstract function defined in inherited clas
	public abstract ImageIcon getIcon();
	
	public abstract void setIcon(ImageIcon icon);

	public abstract boolean isValidMove(int x, int y);

	protected boolean diagonalMove(int x, int y)
	{

		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());

		if (row_diff == 0)
		{
			System.out.println(row);
			System.out.println(column);
			System.out.println("error1");
			return false;
		}

		if (row_diff == column_diff)
		{

			int row = 0;
			int col = 0;

			if (x < this.getRow() && y < this.getColumn())
			{

				row = this.getRow() - 1;
				col = this.getColumn() - 1;

				for (int i = 0; i < row_diff - 1; i++)
				{

					// Checking for a clear path

					if (!grid[row][col].isEmpty())
					{

						return false; // Path is not clear
					}

					col--;
					row--;
				}
			}
			else if (x < this.getRow() && y > this.getColumn())
			{

				row = this.getRow() - 1;
				col = this.getColumn() + 1;

				for (int i = 0; i < row_diff - 1; i++)
				{

					if (!grid[row][col].isEmpty())
					{

						return false;
					}

					col++;
					row--;
				}
			}
			else if (x > this.getRow() && y < this.getColumn())
			{

				row = this.getRow() + 1;
				col = this.getColumn() - 1;

				for (int i = 0; i < row_diff - 1; i++)
				{

					if (!grid[row][col].isEmpty())
					{

						return false;
					}

					col--;
					row++;
				}
			}
			else if (x > this.getRow() && y > this.getColumn())
			{

				row = this.getRow() + 1;
				col = this.getColumn() + 1;

				for (int i = 0; i < row_diff - 1; i++)
				{

					if (!grid[row][col].isEmpty())
					{

						return false;
					}

					col++;
					row++;
				}
			}

			// Path is clear
			return true;
		}
		else
		{

			System.out.println("error2");
			return false;
		}
	}

	protected boolean orthagonalMove(int x, int y)
	{
		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());
		/// *
		// Checking path
		int minIndex;
		int maxIndex;

		if (row_diff == 0)
		{

			if (y < this.getColumn())
			{
				minIndex = y + 1;
				maxIndex = this.getColumn();
			}
			else
			{
				minIndex = this.getColumn() + 1;
				maxIndex = y;
			}

			// Searching for obstructions
			for (int i = minIndex; i < maxIndex; i++)
			{
				if (!grid[x][i].isEmpty())
				{
					return false;
				}
			}

			if (!grid[x][y].isEmpty())
			{
				if (grid[x][y].getPiece().getColor() == this.color)
				{
					return false;
				}
			}
			
			// No obstruction found
			return true;
		}
		else if (column_diff == 0)
		{

			if (x < this.getRow())
			{

				minIndex = x + 1;
				maxIndex = this.getRow();
			}
			else
			{

				minIndex = this.getRow() + 1;
				maxIndex = x;
			}

			// Searching for obstructions
			for (int i = minIndex; i < maxIndex; i++)
			{

				if (!grid[i][y].isEmpty())
				{

					return false;
				}
			}
			
			if (!grid[x][y].isEmpty())
			{
				if (grid[x][y].getPiece().getColor() == this.color)
				{
					return false;
				}
			}

			// No obstruction found
			return true;
		}
		else
		{
			return false;
		}
	}
	public abstract int getValue();
}
