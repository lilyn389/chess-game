package chess_proj;

public class validateMove {

	private static int white = 0;
	
	private static int black = 1;
	
	public int validMove() {
		
		return validatePawn(1, 1, 1, 1, 1);
	}
	
	private int validatePawn(int iRow, int iCol, int fRow, int fCol, int color) {
		
		if (color == white) {
			
			int validRow = iRow + 1;
			
			//Move with no capture
			if (iCol == fCol) {
				
				//Pawn's first move allowing it to move 2 spaces
				if (iRow == 2) {
					
					if (fRow == validRow || fRow == validRow + 1) {
						//Valid Move
						return 0;
					}
					else {
						//Invalid square code
						return -1;
					}
				}
				
				//Maximum initial row position for a pawn is 7.  At 8, it is promoted.
				else if (iRow < 8 && iRow > 1) {
					
					if (fRow == validRow) {
						
						return 0;
					}
					else {
						//Invalid square code
						return -1;
					}
				}
				
				else {
					//Impossible iRow
					//Display Error
					return 98;
				}
			}
			
			//else if () {
				
			//}
			return 1;
		}
		
		else if(color == black) {
			
			return 1;
		}
		
		else {
			//Invalid color code
			//Display error
			return 99; 
		}
	}
}
