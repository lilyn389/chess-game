import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
public class MiniMaxAlphaBeta {
	board b;
	String color;
	int maxDepth;
	//float alpha;
	//float beta;
	MiniMaxAlphaBeta(board bin,String teamIn,int maxDepthIn)
	{
		b = bin;
  		color = teamIn;
		maxDepth = maxDepthIn;
		
	}
/*	private float maxValue(ArrayList<Move> state,float alpha,float beta,int depth)
	{
		if(depth > maxDepth)
		{
			
		}
		return depth;
	}
	private float minValue(ArrayList<Move> state,float alpha,float beta,int depth)
	{
		return depth;
	}
	private Move MMAlphaBeta(ArrayList<Move> potentialMoves,int maxDepth)
	{
		Tile[][] temp = b.grid.clone();
		int depth = 1;
		potentialMoves = getAllMoves(b.getAI().getTeam(),temp);
		float alpha = Float.NEGATIVE_INFINITY;
		float beta = Float.POSITIVE_INFINITY;
		if(state.size() == 0)
			return null;
		Move m = new Move(0,0,0,0);
		m.setValue
		Move mCheck = new Move(0,0,0,0);
		for(int i = 0;i<state.size();++i)
		{
			Tile[][] temp2 = b.grid.clone();
			int row = potentialMoves.get(i).getX1();
			int col = potentialMoves.get(i).getY1();
			temp2[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setEmpty(false);
			temp2[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setPiece(temp[row][col].getPiece());
			temp2[row][col].setEmpty(true);
			temp2[row][col].setPiece(null);
			MMAlphaBeta(potentialMoves.get(i),temp2,maxDepth,depth+1,alpha,beta);
		}
		
	}
	private Move MMAlphaBeta(Move init,Tile[][] currentState,int maxDepth,int depth,float alpha,float beta)
	{
		ArrayList<Move> potentialMoves = getAllMoves(b.getAI().getOpponent(),gridIn);
		if(state.size() == 0)
			return null;
		for(int i = 0;i<state.size();++i)
		{
			if(depth == maxDepth)
			{
				
			}
			else
			{
				
			}
		}
	}*/
	public void setMaxDepth(int maxIn)
	{
		maxDepth = maxIn;
	}
	public Move MM()
	{
		if(maxDepth > 0)
		{
		Tile[][] gridC = b.grid.clone();
		//alpha = Float.NEGATIVE_INFINITY;
		//beta = Float.POSITIVE_INFINITY;
		float alpha = Float.NEGATIVE_INFINITY;
		float beta = Float.POSITIVE_INFINITY;
		Vector<Float> teamScores = new Vector<Float>();
		ArrayList<Move> potentialMoves = getAllMoves(b.getAI().getTeam(),gridC);
		int depth = 1;
		//float[] teamScores = new float[potentialMoves.size()];
		for(int i = 0; i<potentialMoves.size();++i)
		{
			Tile[][] gridCnew = new Tile[8][8];
			for(int j = 0;j<8;++j)
			{
				for(int k = 0;k<8;++k)
				{
					/*if(in.piece instanceof Pawn)
						gridCnew[j][k] = new Tile(new Pawn((Pawn)in.piece);
					if(in.piece instanceof Bishop)
							this.piece = new Bishop((Bishop)in.piece);
					if(in.piece instanceof King)
							this.piece = new King((King)in.piece);
					if(in.piece instanceof Knight)
							this.piece = new Knight((Knight)in.piece);
					if(in.piece instanceof Queen)
							this.piece = new Queen((Queen)in.piece);
					if(in.piece instanceof Rook)
							this.piece = new Rook((Rook)in.piece);
						this.empty = in.empty;*/
					gridCnew[j][k] = new Tile(b.grid[j][k]);
				}
			}
			//Tile[][] gridCnew = b.grid.clone();
			int row = potentialMoves.get(i).getX1();
			int col = potentialMoves.get(i).getY1();
			gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setEmpty(false);
			gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setPiece(gridC[row][col].getPiece());
			gridCnew[row][col].setEmpty(true);
			gridCnew[row][col].setPiece(null);
			teamScores.add(MM2(potentialMoves.get(i),gridCnew,maxDepth,depth,alpha,beta,b.getAI().getOpponent()));
			if(alpha <= teamScores.get(i))
			{
				alpha = teamScores.get(i);
			}
		}
		int moveLocation = -1;
		float maxValue = Float.NEGATIVE_INFINITY;
		for(int i = 0; i < potentialMoves.size();++i)
		{
			if(maxValue < teamScores.get(i))
			{
				maxValue = teamScores.get(i); 
				moveLocation = i;
			}
		}
		if(moveLocation>= 0)
		{
		return potentialMoves.get(moveLocation);
		}
		return null;
		}
		else
		{
			//calculate a random move
			return new Move(0,0,0,0);
		}
		
	}
	private float MM2(Move init,Tile[][] currentState,int maxDepth,int depth,float alpha,float beta,String colorIn)
	{
		++depth;
		//Vector<Float> v = new Vector<Float>();
		
		ArrayList<Move> potentialMoves = getAllMoves(colorIn,currentState);
		//Float a[potentialMoves.size()] = new Float[potentialMoves.size()]();
		//float[] teamScores = new float[potentialMoves.size()];
		Vector<Float> teamScores = new Vector<Float>();
		if(depth == maxDepth)
		{
			//even first is to minimize
			//odd first is to maximize
			if((depth % 2) == 0)
			{
				for(int i = 0; i<potentialMoves.size();++i)
				{
					//Tile[][] gridCnew = currentState.clone();
					Tile[][] gridCnew = new Tile[8][8];
					for(int j = 0;j<8;++j)
					{
						for(int k = 0;k<8;++k)
						{
							gridCnew[j][k] = new Tile(b.grid[j][k]);
						}
					}
					int row = potentialMoves.get(i).getX1();
					int col = potentialMoves.get(i).getY1();
					gridCnew[row][col].getPiece().setRow(potentialMoves.get(i).getX2());
					gridCnew[row][col].getPiece().setColumn(potentialMoves.get(i).getY2());
					gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setEmpty(false);
					gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setPiece(gridCnew[row][col].getPiece());
					gridCnew[row][col].setEmpty(true);
					gridCnew[row][col].setPiece(null);
					teamScores.add(calculatePieces(b.getAI().getTeam(),gridCnew));
					 
				}
				float min = Float.POSITIVE_INFINITY;
				int minLocation = -1;
				for(int i = 0; i<potentialMoves.size();++i)
				{
					if(min > teamScores.get(i))
					{
						min = teamScores.get(i);
						minLocation = i;
					}
				}
				 return min;
				
			}
			else if((depth %2) == 1)
			{
				for(int i = 0; i<potentialMoves.size();++i)
				{
					//Tile[][] gridCnew = currentState.clone();
					Tile[][] gridCnew = new Tile[8][8];
					for(int j = 0;j<8;++j)
					{
						for(int k = 0;k<8;++k)
						{
							gridCnew[j][k] = new Tile(b.grid[j][k]);
						}
					}
					int row = potentialMoves.get(i).getX1();
					int col = potentialMoves.get(i).getY1();
					gridCnew[row][col].getPiece().setRow(potentialMoves.get(i).getX2());
					gridCnew[row][col].getPiece().setColumn(potentialMoves.get(i).getY2());
					gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setEmpty(false);
					gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setPiece(gridCnew[row][col].getPiece());
					gridCnew[row][col].setEmpty(true);
					gridCnew[row][col].setPiece(null);
					teamScores.add(calculatePieces(b.getAI().getTeam(),gridCnew));
					 
				}
				float max = Float.NEGATIVE_INFINITY;
				int maxLocation = -1;
				for(int i = 0; i<potentialMoves.size();++i)
				{
					if(max < teamScores.get(i))
					{
						max = teamScores.get(i);
						maxLocation = i;
					}
				}
				 return max;
			}
		}
		else
		{ // Calculate from given recursive values
			for(int i = 0; i<potentialMoves.size();++i)
			{
				float bestVal = Float.NEGATIVE_INFINITY;
				if(beta <= alpha)
				{
					break;
				}
				//Tile[][] gridCnew = currentState.clone();
				Tile[][] gridCnew = new Tile[8][8];
				for(int j = 0;j<8;++j)
				{
					for(int k = 0;k<8;++k)
					{
						gridCnew[j][k] = new Tile(b.grid[j][k]);
					}
				}
				int row = potentialMoves.get(i).getX1();
				int col = potentialMoves.get(i).getY1();
				gridCnew[row][col].getPiece().setRow(potentialMoves.get(i).getX2());
				gridCnew[row][col].getPiece().setColumn(potentialMoves.get(i).getY2());
				gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setEmpty(false);
				gridCnew[potentialMoves.get(i).getX2()][potentialMoves.get(i).getY2()].setPiece(gridCnew[row][col].getPiece());
				gridCnew[row][col].setEmpty(true);
				gridCnew[row][col].setPiece(null);
				if(colorIn == "white")
				{
					teamScores.add(MM2(potentialMoves.get(i),gridCnew,maxDepth,depth,alpha,beta,"black"));
				}
				else if(colorIn == "black")
				{
					teamScores.add(MM2(potentialMoves.get(i),gridCnew,maxDepth,depth,alpha,beta,"white"));
				}
				 //MM2(Move init,Tile[][] currentState,int maxDepth,int depth,float alpha,float beta,String colorIn)
				if((depth%2) == 1)
				{
					if(teamScores.get(i) <= beta)
					{
						beta = teamScores.get(i);
					}
				}
				if((depth%2) == 0)
				{
					if(teamScores.get(i) >= alpha)
					{
						alpha = teamScores.get(i);
					}
				}
			}
			if((depth%2) == 1)
			{
				//maximize
				float max = Float.NEGATIVE_INFINITY;
				int maxLocation = -1;
				for(int i = 0; i<teamScores.size();++i)
				{
					if(max < teamScores.get(i))
					{
						max = teamScores.get(i);
						maxLocation = i;
					}
				}
				/*if(alpha <= max)
				{
					alpha = max;
				}*/
				 return max;
			}
			else if((depth % 2) == 0)
			{
				//minimizen m
				float min = Float.POSITIVE_INFINITY;
				int minLocation = -1;
				for(int i = 0; i<teamScores.size();++i)
				{
					if(min > teamScores.get(i))
					{
						min = teamScores.get(i);
						minLocation = i;
					}
				}
				/*if(beta >= min)
				{
					beta = min;
				}*/
				 return min;
			}
		}
		return Float.NEGATIVE_INFINITY;
		
	}
	private float calculatePieces(String color,Tile[][] grid)
	{
		//Calculates pieces(color) - pieces(other team)
		float colorsPieces = 0;
		float otherColorsPieces = 0;
		int colorQueens = 0;
		int otherColorQueens = 0;
		int colorBishops = 0;
		int otherColorBishops = 0;
		int colorRooks = 0;
		int otherColorRooks = 0;
		Piece colorKing = grid[0][0].getPiece();
		Piece otherColorKing = grid[0][0].getPiece();
		
		for(int i = 0;i<8;++i)
		{
			for(int j = 0;j<8;++j)
			{
				Piece p = grid[i][j].getPiece();
				if(grid[i][j].isEmpty())
				{
					continue;
				}
				else
				{
					
					if(p.color == color)
					{
						//Summing value for all pieces alive
						colorsPieces = colorsPieces + p.getValue();
						
						//Rewarding control of center tiles according to strength of piece
						if(i >= 3 && i < 5 && j >= 3 && j < 5) 
						{
							colorsPieces += p.getValue();
						}
						
						//Tracking potential strong piece combos
						if (p.getName().equals("bishop")) 
						{
							colorBishops++;
						}
						else if (p.getName().equals("rooks")) 
						{							 
							colorRooks++;
						}
						else if (p.getName().equals("queen")) 
						{
							colorQueens++;
						}
						else if (p.getName().equals("pawn") && i >= 5) 
						{
							colorsPieces += p.getValue();
						}
						else if (p.getName().equals("king")) {
							
							colorKing = p;
						}

					}
					else
					{
						otherColorsPieces = otherColorsPieces + p.getValue();
						
						if(i >= 3 && i < 5 && j >= 3 && j < 5) 
						{
							otherColorsPieces += p.getValue();
						}
						
						if (p.getName().equals("bishop")) 
						{
							otherColorBishops++;
						}
						else if (p.getName().equals("rooks")) 
						{
							 otherColorRooks++;
						}
						else if (p.getName().equals("queen"))
						{
							otherColorQueens++;
						}
						else if (p.getName().equals("pawn") && i >= 5)
						{
							otherColorsPieces += p.getValue();
						}
						else if (p.getName().equals("king")) {
							
							otherColorKing = p;
						}
					}
				}
			}
		}
		
		//Rewarding beneficial combos
		if (colorBishops == 2) {
			
			colorsPieces += 30 * 2;
		}
		
		if (colorRooks == 2) {
			
			colorsPieces += 30 * 2;
		}
		
		colorsPieces += 90 * colorQueens;
		
		if (otherColorBishops == 2) {
			
			otherColorsPieces += 30 * 2;
		}
		
		if (otherColorRooks == 2) {
			
			otherColorsPieces += 30 * 2;
		}
		
		otherColorsPieces += 90 * otherColorQueens;
		
		
		//Rewarding check/mate
		if (otherColorKing.isCheck()) 
		{
			colorsPieces += 100;
		}
		
		if (colorKing.isCheck()) 
		{
			otherColorsPieces += 100;
		}
		
		if (otherColorKing.isCheckMate()) 
		{
			colorsPieces += 10000;
		}
		else if (colorKing.isCheckMate()) 
		{
			otherColorsPieces += 10000;
		}
		
		return colorsPieces - otherColorsPieces;
	}
	/*public ArrayList<Move> getAllMovesAfterAI(String color,Tile[][] grid)
	{
		
	}*/
	
	public ArrayList<Move> getAllMoves(String color,Tile[][] grid)
	{
		ArrayList<Move> output = new ArrayList<Move>();
		for(int i = 0; i<8;++i)
		{
			for(int j = 0; j<8;++j)
			{
				if(!grid[i][j].isEmpty())
				{
					if(grid[i][j].getPiece().color == color)
					{
						output.addAll(getMoves(color,grid[i][j]));
					}
				}
			}
		}
		//listOne.addAll(listTwo);  
		return output;
	}
	public ArrayList<Move> getMoves(String color,Tile currentSpot)
	{
		ArrayList<Move> output = new ArrayList<Move>();
		if(color == currentSpot.getPiece().color)
		{
			if(currentSpot.getPiece() instanceof Pawn)
			{
				output = pawnMoves(color, currentSpot);
			}
			else if(currentSpot.getPiece() instanceof Knight)
			{
				output = knightMoves(color,currentSpot);
			}
			else if(currentSpot.getPiece() instanceof Bishop)
			{
				output = bishopMoves(color,currentSpot);
			}
			else if(currentSpot.getPiece() instanceof Rook)
			{
				output = rookMoves(color,currentSpot);
			}
			else if(currentSpot.getPiece() instanceof Queen)
			{
				output = queenMoves(color,currentSpot);
			}
			else if(currentSpot.getPiece() instanceof King)
			{
				output = kingMoves(color,currentSpot);
			}
		}
		return output;
		
	}
	public ArrayList<Move> pawnMoves(String color,Tile currentSpot)
	{
		Pawn p = (Pawn)currentSpot.getPiece();
		int row = currentSpot.getPiece().getRow();
		int col = currentSpot.getPiece().getColumn();
		ArrayList<Move> output = new ArrayList<Move>();
		int newRo[]={0,0};
		int newCo[]={0,0,0};
		if(color == "white" && row != 8)
		{
			newRo[0] = row+1;
			newRo[1] = row+2;
			newCo[0] = col-1;
			newCo[1] = col+1;
			
		}
		else if((color == "black") && row != 8)
		{
			newRo[0] = row-1;
			newRo[1] = row-2;
			newCo[0] = col-1;
			newCo[1] = col-2;
		}
		for(int i = 0;i<2;++i)
		{
			if(p.isValidMove(newRo[0],newCo[i]))
					output.add(new Move(row,col,newRo[0],newCo[i]));
		}
		if(p.isValidMove(newRo[1],col))
		{
			output.add(new Move(row,col,newRo[1],col));
		}
		if(p.isValidMove(newRo[0],col))
		{
			output.add(new Move(row,col,newRo[0],col));
		}
		return output;
	}
	public ArrayList<Move> rookMoves(String color,Tile currentSpot)
	{
		Rook p = (Rook)currentSpot.getPiece();
		int row = currentSpot.getPiece().getRow();
		int col = currentSpot.getPiece().getColumn();
		ArrayList<Move> output = new ArrayList<Move>();
		if(color == "white")
		{
			for(int i = 0;i<8;++i)
			{
				if(row == i)
				{
					
				}
				else
				{
					if(p.isValidMove(i,col))
					{
						output.add(new Move(row,col,i,col));
					}
				}
			}
			for(int i = 0;i<col;++i)
			{
				if(col == i)
				{
					
				}
				else
				{
					if(p.isValidMove(row,i))
					{
						output.add(new Move(row,col,row,i));
					}
				}
			}
		}
		return output;
	}
	public ArrayList<Move> knightMoves(String color,Tile currentSpot)
	{
		Knight p = (Knight)currentSpot.getPiece();
		int row = currentSpot.getPiece().getRow();
		int col = currentSpot.getPiece().getColumn();
		ArrayList<Move> output= new ArrayList<Move>();
		int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; 
	    int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; 
	  
	    int count = 0; 
	  
	    // Check if each possible move is valid or not 
	    for (int i = 0; i < 8; i++) 
	    { 
	  
	        // Position of knight after move 
	        int x = row + X[i]; 
	        int y = col + Y[i]; 
	        if(p.isValidMove(x,y))
	        {
	        	output.add(new Move(row,col,x,y));
	        }
	        
	    }
			
		
		return output;
	}
	public ArrayList<Move> bishopMoves(String color,Tile currentSpot)
	{
		Bishop p = (Bishop)currentSpot.getPiece();
		int row = currentSpot.getPiece().getRow();
		int col = currentSpot.getPiece().getColumn();
		ArrayList<Move> output = new ArrayList<Move>();
		int i = row;
		int j = col;
		while((i <= 7) && (j >= 0))
		{
			// RIGHT DOWN, left side down diagonal
			/*
			 * 0 0 0 0 0 0 0 0
			 * 0 0 0 0 P 0 0 0
			 * 0 0 0 H 0 0 0 0
			 * 0 0 H 0 0 0 0 0
			 * 0 H 0 0 0 0 0 0
			 */
			++i;
			--j;
			if(p.isValidMove(i,j))
			{
				output.add(new Move(row,col,i,j));
			}
		}
		i = row;
		j = col;
		while((i >= 0) && (j <= 7))
		{
			//RIGHT UP, right side up diagonal
			/*
			 * 0 0 0 0 0 H 0 0
			 * 0 0 0 0 H 0 0 0
			 * 0 0 0 H 0 0 0 0
			 * 0 0 P 0 0 0 0 0
			 * 0 0 0 0 0 0 0 0
			 */
			--i;
			++j;
			if(p.isValidMove(i,j))
			{
				output.add(new Move(row,col,i,j));
			}
			
		}
		i = row;
		j = col;
		while((i >= 0) && (j >= 0))
		{
			//LEFT UP, left side up diagonal
			/*
			 * 0 0 H 0 0 0 0 0
			 * 0 0 0 H 0 0 0 0
			 * 0 0 0 0 H 0 0 0
			 * 0 0 0 0 0 P 0 0
			 * 0 0 0 0 0 0 0 0
			 */
			--i;
			--j;
			if(p.isValidMove(i,j))
			{
				output.add(new Move(row,col,i,j));
			}
		}
		i = row;
		j = col;
		while((i <= 7) && (j <= 7))
		{
			//RIGHT UP, right side up diagonal
			/*
			 * 0 0 0 0 0 H 0 0
			 * 0 0 0 0 H 0 0 0
			 * 0 0 0 H 0 0 0 0
			 * 0 0 P 0 0 0 0 0
			 * 0 0 0 0 0 0 0 0
			 */
			++i;
			++j;
			if(p.isValidMove(i,j))
			{
				output.add(new Move(row,col,i,j));
			}
		}
			return output;
		/*	//row/col  4 7      0 0 0 0 0 0 0 0
					7 4		//	0 0 0 0 0 0 0 0
							//	0 0 0 0 0 0 0 0
						//		0 0 0 0 0 0 0 0
						//		0 0 0 0 0 0 0 0
					//			0 0 0 0 0 0 0 0
				//				0 0 0 0 0 0 0 0
			//					0 0 0 0 0 0 0 0
		*/}
	public ArrayList<Move> kingMoves(String color,Tile currentSpot)
	{
		King p = (King)currentSpot.getPiece();
		int row = currentSpot.getPiece().getRow();
		int col = currentSpot.getPiece().getColumn();
		ArrayList<Move> output = new ArrayList<Move>();
		int newRow[] = {row+1,row-1};
		int newCol[] = {col+1,col-1};
		if(p.isValidMove(row+1,col))
		{
			output.add(new Move(row,col,row+1,col));
		}
		if(p.isValidMove(row-1,col))
		{
			output.add(new Move(row,col,row-1,col));
		}
		if(p.isValidMove(row,col-1))
		{
			output.add(new Move(row,col,row,col-1));
		}
		if(p.isValidMove(row,col+1))
		{
			output.add(new Move(row,col,row,col+1));
		}
		return output;
	}
	public ArrayList<Move> queenMoves(String color,Tile currentSpot)
	{
		Queen p = (Queen)currentSpot.getPiece();
		int row = currentSpot.getPiece().getRow();
		int col = currentSpot.getPiece().getColumn();
		ArrayList<Move> output = new ArrayList<Move>();
		output = checkDirection(p,row+1,col);
		output.addAll(checkDirection(p,row-1,col));
		output.addAll(checkDirection(p,row,col+1));
		output.addAll(checkDirection(p,row,col-1));
		output.addAll(checkDirection(p,row+1,col+1));
		output.addAll(checkDirection(p,row+1,col-1));
		output.addAll(checkDirection(p,row-1,col+1));
		output.addAll(checkDirection(p,row-1,col-1));
		return output;
	}
	boolean range(int x, int y) 
	{ 
	  return (x <= 8 && x > 0 && y <= 8 && y > 0); 
	}
	boolean isObstacle(int x,int y)
	{
		if(range(x,y))
		{
		if(b.grid[x][y].isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
		}
		return true;
	}
	ArrayList<Move> checkDirection(Piece p,int newRow,int newCol)
	{
		int row = p.getRow();
		int col = p.getColumn();
		ArrayList<Move> output = new ArrayList<Move>();
		int directionRow = newRow-row;
		int directionCol = newCol-col;
		newRow = row+directionRow;
		newCol = col+directionCol;
		while((newRow >= 0 && newRow<= 7 && newCol >= 0 && newCol <= 7) && (!isObstacle(newRow,newCol)))
		{
			if(p.isValidMove(newRow,newCol))
			{
				output.add(new Move(row,col,newRow,newCol));
			}
			directionRow = newRow-row;
			directionCol = newCol-col;
		}
		if((newRow >= 0 && newRow<= 7 && newCol >= 0 && newCol <= 7) && p.isValidMove(newRow,newCol))
		{
			output.add(new Move(row,col,newRow,newCol));
		}
		return output;
	}
//volatile int a
	//implements runnable
	//void run()
}

