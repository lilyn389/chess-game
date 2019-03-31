
public class Piece {

	protected String color;
	protected String name;
	protected int row;
	protected int column;
	protected int ID;
	private Tile[][] grid;
	public Piece() {
		color = "red";
		name = "none";
		row = 100;
		column = 100;
	}
	
	public Piece(String color, String name, int row, int column, int ID) {
		this.color = color;
		this.name = name;
		this.row = row;
		this.column = column;
		this.ID = ID;
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
	protected boolean diagonalMove(int x, int y) {
		
		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());
		
		if (row_diff == 0) {
			
			return false;
		}
		
		if (row_diff == column_diff) {
			
			int minRow;
			int minCol;
			
			if (x < this.getRow()) {
				
				minRow = x + 1;
			}
			else {
				
				minRow = this.getRow() + 1;
			}
			
			if (y < this.getColumn()) {
				
				minCol = y + 1;
			}
			else {
				
				minCol = this.getColumn() + 1;
			}
		
			for (int i = 0; i < row_diff - 1; i++) {
				
				// Checking for a clear path
				
				if (!grid[minRow][minCol].isEmpty()) {
					
					return false;	//Path is not clear
				}
				
				minCol++;
				minRow++;
			}
			
			// Path is clear
			return true;
		}
		else {
			
			return false;
		}
	}
	
	protected boolean orthagonalMove(int x, int y) {
		
		int row_diff = Math.abs(x - this.getRow());
		int column_diff = Math.abs(y - this.getColumn());
		///*
		// Checking path
		int minIndex;
		int maxIndex;
		
		if (row_diff == 0) {
		
			if (y < this.getColumn()) {
				
				minIndex = y + 1;
				maxIndex = this.getColumn();
			}
			else {
				
				minIndex = this.getColumn() + 1;
				maxIndex = y;
			}
			
			//Searching for obstructions
			for (int i = minIndex; i < maxIndex; i++) {
				
				if (!grid[x][i].isEmpty()) {
					
					return false;
				}
			}
			
			//No obstruction found
			return true;
		}
		else if (column_diff == 0) {
			
			if (x < this.getRow()) {
				
				minIndex = x + 1;
				maxIndex = this.getRow();
			}
			else {
				
				minIndex = this.getRow() + 1;
				maxIndex = x;
			}		
			
			//Searching for obstructions
			for (int i = minIndex; i < maxIndex; i++) {
				
				if (!grid[i][y].isEmpty()) {
					
					return false;
				}
			}
			
			//No obstruction found
			return true;
		}
		else {
		
			return false;
		}
		
		//*/
	}
}
