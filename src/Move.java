import java.util.ArrayList;

public class Move {
	
	private int x1, y1, x2, y2;
	private boolean castling = false;
	private float value;
	/**
	 * 
	 */
	public Move(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.value = 0;
	}
	
	public Move(int x1, int y1, int x2, int y2, boolean castling) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.castling = castling;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public boolean isCastling() {
		return castling;
	}
	
	public String toString(){ // TODO change to a1 to b4 etc
		//return x1 + " " + y1 + " " + x2 + " " + y2;
		//return (char)('A'+x1) + "" + (y1+1) + " " + (char)('A'+x2) + "" + (y2+1);
		
		String from, to, move_msg;
		int from_row, to_row;
		String from_column, to_column;
		from_column = null;
		to_column = null;
		
		// Convert the rows
		from_row = 8 - x1;
		to_row = 8 - x2;
		
		// Convert the columns
		switch (y1) {
		case 0:
			from_column = "A";
			break;
		case 1:
			from_column = "B";
			break;
		case 2:
			from_column = "C";
			break;
		case 3:
			from_column = "D";
			break;
		case 4:
			from_column = "E";
			break;
		case 5:
			from_column = "F";
			break;
		case 6:
			from_column = "G";
			break;
		case 7:
			from_column = "H";
			break;
		default:
			// code block
		}

		switch (y2) {
		case 0:
			to_column = "A";
			break;
		case 1:
			to_column = "B";
			break;
		case 2:
			to_column = "C";
			break;
		case 3:
			to_column = "D";
			break;
		case 4:
			to_column = "E";
			break;
		case 5:
			to_column = "F";
			break;
		case 6:
			to_column = "G";
			break;
		case 7:
			to_column = "H";
			break;
		default:
			// code block
		}
		
		// concatenate & build message
		from = from_column.concat(Integer.toString(from_row));
		to = to_column.concat(Integer.toString(to_row));
		from = from.concat(" ");
		move_msg = from.concat(to);
		
		return move_msg;
	}
	
	public boolean equals(Object o){
		Move op = (Move) o;
		
		if(op.getX1() == x1 && op.getY1() == y1 && op.getX2() == x2 && op.getY2() == y2){
			return true;
		}
		else
			return false;
	}
	public void setValue(float set)
	{
		value = set;
	}
	public float getValue()
	{
		return value;
	}
}