
public class Piece {

	private String color;
	private String name;
	private int row;
	private int column;
	
	public Piece() {
		color = "red";
		name = "none";
		row = 100;
		column = 100;
	}
	
	public Piece(String color, String name, int row, int column) {
		this.color = color;
		this.name = name;
		this.row = row;
		this.column = column;
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
}
