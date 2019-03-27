
public class Piece {

	private String color;
	private String name;
	
	public Piece(String color, String name) {
		this.color = color;
		this.name = name;
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
}
