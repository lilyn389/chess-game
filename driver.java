package chess_proj;

public class driver {

	public static void main(String[] args) {
		
		Pawn pawn1 = new Pawn();
		
		Queen queen = new Queen();

		Tile tile1 = new Tile(1, 1);
		
		System.out.println(tile1.isEmpty());
		
		tile1.addPiece(pawn1);
		
		System.out.println(tile1.isEmpty());
		
		System.out.println(tile1.getPiece().getID());
	
		tile1.removePiece();
		
		System.out.println(tile1.isEmpty());
		
		tile1.addPiece(queen);
		
		System.out.println(tile1.getPiece().getID());
	}

}
