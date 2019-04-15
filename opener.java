package chess;

import java.util.LinkedList;
import java.util.Random;

import java.util.Vector;

public class opener {
	
	LinkedList<Integer> position;
	LinkedList<Integer> move;
	
	opener(String colour) {
		
		if (colour.equals("white")) {
			
			whiteOpener();
		}
		else if (colour.equals("black")) {
			
			blackOpener();
		}
		else {
			
			System.out.println("No opener");
		}
	}
	
	
	private void whiteOpener() {
		
		Random rand = new Random();
		
		int opnr = rand.nextInt(4);
		
		if (opnr == 0) {
			
			//1e4
			position.add(1);
			position.add(3);
			
			move.add(3);
			move.add(3);
			
		}
		else if (opnr == 1) {
			
			//1d4
			position.add(1);
			position.add(4);
			
			move.add(3);
			move.add(4);
		}
		else if (opnr == 2) {
			
			//1c4
			position.add(1);
			position.add(5);
			
			move.add(3);
			move.add(5);
		}
		else if (opnr == 3) {
			
			position.add(0);
			position.add(1);
			
			move.add(2);
			move.add(2);
		}
		else {
			
			System.out.println("No opener");
		}
	}
	
	private void blackOpener() {
		
Random rand = new Random();
		
		int opnr = rand.nextInt(4);
		
		if (opnr == 0) {
			
			//1c5
			position.add(6);
			position.add(5);
			
			move.add(4);
			move.add(5);
		}
		else if (opnr == 1) {
			
			//1e6
			position.add(6);
			position.add(3);
			
			move.add(5);
			move.add(3);
		}
		else if (opnr == 2) {
			
			//1e5
			position.add(6);
			position.add(3);
			
			move.add(4);
			move.add(3);
		}
		else if (opnr == 3) {
			
			//1c6
			position.add(6);
			position.add(5);
			
			move.add(5);
			move.add(5);
		}
		else {
			
			System.out.println("No opener");
		}
	}
	
	//Not to be in this class
	public int evalBoard(String colour) {
		
		int sum = 0;
		int pawns;
		int bishops = 0;
		int rooks = 0;
		int queen = 0;
		
		if (colour.equals("white")) {
			
			//Summing pieces on board
			for(int i = 0; i < 7; i++) {
				
				for (int j = 0; j < 7; j++) {
					
					/*if (!grid[i][j].isEmpty()) {
					 * 
					 * if(grid[i][j].getPiece().color.equals("white")) {
					 * 
					 * 		sum += grid[i][j].getPiece().val;
					 * 	
						 *  Rewarding center control
						 * 	if(i >= 3 && i < 5 && j >= 3 && j < 5) {
						 * 
						 * 		sum += grid[i][j].getPiece().val;
						 * 	}
						 * 
						 * Rewarding beneficial combos for late game
						 * 	if (grid[i][j].getPiece().name.equals("bishop") {
						 * 
						 * 		bishops++;
						 * 	}
						 * 	else if (grid[i][j].getPiece().name.equals("rooks") {
						 * 
						 * 		rooks++;
						 * 	}
						 * 	else if (grid[i][j].getPiece().name.equals("queen") {
						 * 		
						 * 		queen++;
						 * 	}
						 *  else if (grid[i][j].getPiece().name.equals("pawn") && i >= 5) {
						 * 		
						 * 		sum += pawn.val;
						 * 	}
					 * 	}
					 * }
					 */
				}
			}
			
			//Punishing stacked pawns
			for (int i = 0; i < 7; i++) {
				
				pawns = 0;
				
				for (int j = 0; j < 7; j++) {
					
					/*if (!grid[j][i].isEmpty()) {
					 * 
					 * if(grid[j][i].getPiece().color.equals("white") && grid[j][i].getPiece().name.equals("pawn")) {
					 * 
					 * 		pawn++;
					 * 	}
					 * }
					 */
				}
				
				if (pawns > 1) {
					
					//sum -= pawn.val * (pawns - 1);
				}
			}
			
			if (bishops == 2) {
				
				//sum += bishops.val * 2;
			}
			
			if (rooks == 2) {
				
				//sum += rooks.val * 2;
			}
			
			//sum += queen.val * queen;
			
			//Reward placing opponent in check
			/*if (blackKing.isCheck()) {
			 * 
			 * 	sum += 100;
			 *}
			 */
			
			//Reward placing opponent in checkmate
			/*if (blackKing.isCheckMate()) {
			 * 
			 * 	sum += 10000;
			 *}
			 */
		}
		else if (colour.equals("black")) {
			
			//Summing pieces on board
			for(int i = 0; i < 7; i++) {
				
				for (int j = 0; j < 7; j++) {
					
					/*if (!grid[i][j].isEmpty()) {
					 * 
					 * if(grid[i][j].getPiece().color.equals("black")) {
					 * 
					 * 		sum += grid[i][j].getPiece().val;
					 * 	
						 *  Rewarding center control
						 * 	if(i >= 3 && i < 5 && j >= 3 && j < 5) {
						 * 
						 * 		sum += grid[i][j].getPiece().val;
						 * 	}
						 * 
						 * Rewarding beneficial combos for late game
						 * 	if (grid[i][j].getPiece().name.equals("bishop") {
						 * 
						 * 		bishops++;
						 * 	}
						 * 	else if (grid[i][j].getPiece().name.equals("rooks") {
						 * 
						 * 		rooks++;
						 * 	}
						 * 	else if (grid[i][j].getPiece().name.equals("queen") {
						 * 		
						 * 		queen++;
						 * 	}
						 *  else if (grid[i][j].getPiece().name.equals("pawn") && i >= 5) {
						 * 		
						 * 		sum += pawn.val;
						 * 	}
					 * 	}
					 * }
					 */
				}
			}
			
			//Punishing stacked pawns
			for (int i = 0; i < 7; i++) {
				
				pawns = 0;
				
				for (int j = 0; j < 7; j++) {
					
					/*if (!grid[j][i].isEmpty()) {
					 * 
					 * if(grid[j][i].getPiece().color.equals("black") && grid[j][i].getPiece().name.equals("pawn")) {
					 * 
					 * 		pawn++;
					 * 	}
					 * }
					 */
				}
				
				if (pawns > 1) {
					
					//sum -= pawn.val * (pawns - 1);
				}
			}
			
			if (bishops == 2) {
				
				//sum += bishops.val * 2;
			}
			
			if (rooks == 2) {
				
				//sum += rooks.val * 2;
			}
			
			//sum += queen.val * queen;
			
			//Reward placing opponent in check
			/*if (whiteKing.isCheck()) {
			 * 
			 * 	sum += 100;
			 *}
			 */
			
			//Reward placing opponent in checkmate
			/*if (whiteKing.isCheckMate()) {
			 * 
			 * 	sum += 10000;
			 *}
			 */
		}
		
		return sum;
	}
}
