import javax.swing.JButton;

//import java.awt.Color;

//import javax.swing.JButton;

public class AI {
	final char[] Rook = {'R','r'};
	final char[] Bishop = {'B','b'};
	final char[] Knight = {'K','k'};
	final char[] King = {'M','m'};
	final char[] Queen = {'W','w'};
	final char[] Pawn = {'P','p'};
	private board b;
	private Tile[][] grid = new Tile[8][8];
	private static final long serialVersionUID = -5478665655191131191L;
	String turn;
	Piece selected;
	//boolean select;
	public String team;
	public int maxDepth;
	private MiniMaxAlphaBeta MinMax; //= new MiniMaxAlphaBeta();
	//int x;
	//int y;
	JButton[][] jbuttons;
	char[][] currentPieces = {
			{'P','P','P','P','P','P','P','P'}, // 8 - 15
			{'B','B'}, // 0 1 or 2 3
			{'K','K'}, // 0 1 or 2 3
			{'R','R'}, // 0 1 or 2 3
			{'W'}, // 0
			{'K'} // 0
			};
	int[][][] currentPiecesLocations = {
			{ {1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7} },
			{ {0,2},{0,5} },
			{ {0,1},{0,6} },
			{ {0,0},{0,7} },
			{ {0,4} },
			{ {0,3} }
	};
	char[][] matrixBoard= {
			{'R','K','B','M','W','B','K','R'},
			{'P','P','P','P','P','P','P','P'},
			{'0','0','0','0','0','0','0','0'},
			{'0','0','0','0','0','0','0','0'},
			{'0','0','0','0','0','0','0','0'},
			{'0','0','0','0','0','0','0','0'},
			{'p','p','p','p','p','p','p','p'},
			{'r','k','b','m','w','b','k','r'}
			};
	/**col 0				row7<-row0
	 * col 7
	 */
	public String getTeam()
	{
		return team;
	}
	public void getTurn(String turnIn)
	{
		turn = turnIn;
	}
/*	public void getSelected(boolean selectInput,Piece selectedInput)
	{
		selected = selectedInput;
		select = selectInput;
	}
	void selectChoice(boolean selectInput)
	{
		select = selectInput;
	}*/
	/*public void getXandY(int xIn, int yIn)
	{
		x = xIn;
		y = yIn;
		
	}*/
	/*public boolean checkXandY()
	{
		if((turn == selected.color) && (turn == "black"))
		{
			if(select)
			{
				if(selected.isValidMove(x,y))
				{
					return true;
				}
				
			}
		}
		return false;
	}*/
	/*public void updateBoard()
	{
		if(checkXandY())
		{
			int row = selected.row;
			int col = selected.column;
			//row = 7-0
			char p = matrixBoard[7-row][col];
			matrixBoard[7-row][col] = '0';
			if(captured())
			{
				updateCurrentPieces();
			}
			matrixBoard[7-x][y] = p;
		}
		
	}*/
	public boolean captured()
	{
		if((team == "white") && Character.isUpperCase(matrixBoard[7-b.getX()][b.getY()]))
		{
			return true;
		}
		else if((team == "black") && Character.isLowerCase(matrixBoard[7-b.getX()][b.getY()]))
		{
			return true;
		}
		return false;
	}
	public String getOpponent()
	{
		if(team == "black")
		{
			return "white";
		}
		if(team == "white")
		{
			return "black";
		}
		return null;
	}
	public void updateCurrentPieces()
	{
		int whichPieceType = -1;
		int whichPieceLocation = -1;
		//currentPiecesLocations[whichPieceType][whichPieceLocation][row == 0 || col == 1] - Corresponds to 
			//matrixBoard, not the board grid
		if(matrixBoard[7-b.getX()][b.getY()] == 'P' || matrixBoard[7-b.getX()][b.getY()] == 'p')
		{
			whichPieceType = 0;
		}
		else if(matrixBoard[7-b.getX()][b.getY()] == 'B' || matrixBoard[7-b.getX()][b.getY()] == 'b')
		{
			whichPieceType = 1;
		}
		else if(matrixBoard[7-b.getX()][b.getY()] == 'K' || matrixBoard[7-b.getX()][b.getY()] == 'k')
		{
			whichPieceType = 2;
		}
		else if(matrixBoard[7-b.getX()][b.getY()] == 'R' || matrixBoard[7-b.getX()][b.getY()] == 'r')
		{
			whichPieceType = 3;
		}
		else if(matrixBoard[7-b.getX()][b.getY()] == 'W' || matrixBoard[7-b.getX()][b.getY()] == 'w')
		{
			whichPieceType = 4;
		}
		else if(matrixBoard[7-b.getX()][b.getY()] == 'M' || matrixBoard[7-b.getX()][b.getY()] == 'm')
		{
			whichPieceType = 5;
		}
		int rowMatrix = 7-b.getX();
		int colMatrix = b.getY();
		if(whichPieceType != -1)
		{
			for(int i = 0; i<currentPiecesLocations[whichPieceType].length;++i)
			{
				if(rowMatrix == currentPiecesLocations[whichPieceType][i][0] 
						&& colMatrix == currentPiecesLocations[whichPieceType][i][1])
				{
					whichPieceLocation = i;
					break;
				}
			}
		
			if(whichPieceLocation != -1)
			{
				currentPiecesLocations[whichPieceType][whichPieceLocation][0] = -1;
				currentPiecesLocations[whichPieceType][whichPieceLocation][1] = -1;
			}
		}
	}
	//public Pawn[] pawns;
	public AI(board bin,String AIColor,String difficulty)
	{
		//Pawn[] pawnsIn,Rook[] rooksIn,King[] kingsIn,Queen[] queensIn,Bishop[] bishopsIn,Knight[] knightsIn
		//JButton tiles = new JButton[8][8]
		//private JButton[][] tiles = new JButton[8][8]; // 8*8 grid buttons for tiles
		//private Color maroon = new Color(128, 0, 0); // gigem 
		//private Color white = Color.WHITE;
		//private Tile[][] grid = new Tile[8][8];  // keep track of the board state
		// define bishops
		//select = false;
	
		b =bin;
		maxDepth = determineDifficulty(difficulty);
		team = AIColor;
		//pawns = pawnsIn;
		//rooks = rooksIn;
		//kings = kingsIn;
		//queens = queensIn;
		//bishops = bishopsIn;
		//knights = knightsIn;
		
	}
	int determineDifficulty(String diff)
	{
		if(diff == "easy")
		{
			return 4;
		}
		else if(diff == "medium")
		{
			return 4;
		}
		else if(diff == "hard")
		{
			return 12;
		}
		else
		{
			return 4;
		}
	}

	Move calculateBestMove() {
		// generate all the moves for a given position
		// object newGameMoves = game.ugly_moves();
		// return newGameMoves[Math.floor(Math.random() * newGameMoves.length)];
		MinMax = new MiniMaxAlphaBeta(b, team, maxDepth);
		Move m = MinMax.MM();
		if (m.getX1() >= 0 && m.getX1() <= 7 && m.getY1() >= 0 && m.getY1() <= 7 && m.getX2() >= 0 && m.getX2() <= 7 && m.getY2() >= 0 && m.getY2() <= 7) {
			if (b.grid[m.getX1()][m.getY1()].isEmpty()) {
				return calculateRandomMove(calculateRandomPiece());
			} else if (!b.grid[m.getX1()][m.getY1()].getPiece().isValidMove(m.getX2(), m.getY2())) {
				return calculateRandomMove(calculateRandomPiece());
			} else {

				/*
				 * b.grid[m.getX2()][m.getY2()].setPiece(b.grid[m.getX1()][m.
				 * getY1()].getPiece());
				 * b.grid[m.getX2()][m.getY2()].setEmpty(false);
				 * b.grid[m.getX1()][m.getY1()].setPiece(null);
				 * b.grid[m.getX1()][m.getY1()].setEmpty(true);
				 */
				return m;
			}
		} else {
			return calculateRandomMove(calculateRandomPiece());
		}
	};
	public int calculateRandomPiece()
	{
		
		 int max;// = 5; 
	        int min = 1;
	        if(team.equals("white"))
	        {
	        	max = b.white_pieces.size();
	        }
	        else
	        {
	        	max = b.black_pieces.size();
	        }
	        int range = max - min + 1; 
	        
	        // generate random number within 1 to 10
	            int randPiece = (int)(Math.random() * range) + min; 
	            randPiece = randPiece -1;
	        return randPiece;
	}
	public Move calculateRandomMove(int randomPiece)
	{
		
		int max = 7;
        int min = 0; 
        int range = max - min + 1;
       // getOnBoard(randPieceType,randPiece);
        while(true)
        {
        int randSpotRow = (int)(Math.random() * range) + min;
        int randSpotCol = (int)(Math.random() * range) + min;
      //  getOnBoard(randPieceType,randPiece);
        if(team.equals("white"))
        {
        	selected = b.white_pieces.get(randomPiece);
        }
        else
        {
        	selected = b.black_pieces.get(randomPiece);        	
        }
        if(selected.isValidMove(randSpotRow,randSpotCol));
        {
        	/*int row = selected.getRow();
        	int col = selected.getColumn();
        	jbuttons[7-row][col].setIcon(null);
			jbuttons[7-row][col].setIcon(selected.getIcon());
			grid[7-row][col].setPiece(null);
			grid[7-row][col].setEmpty(true);
			selected.setRow(b.getX());
			selected.setColumn(b.getY());
			grid[7-randSpotRow][randSpotCol].setPiece(selected);
			grid[7-randSpotRow][randSpotCol].setEmpty(false);
			b.grid = grid;
			b.tiles = jbuttons;*/
        	Move out = new Move(selected.getRow(),selected.getColumn(),randSpotRow,randSpotCol);
        	return out;
			
        	
        } 
        
        }
	}
	public void getGrid(Tile[][] gridIn,JButton[][] buttonsIn)
	{
		grid = gridIn;
		jbuttons = buttonsIn;
		
	}
	void processMove()
	{
		
		calculateRandomMove(calculateRandomPiece());
		
	}
	JButton[][] getTile()
	{
		return jbuttons;
	}
	/*public void getOnBoard(int randPT, int randP)
	{
		switch(randPT)
		{
		case 0:
				if(team == "white")
				{
					switch (randP)
					{
					case 0:
					selected = b.pawns[8];
					break;
					case 1:
					selected = b.pawns[9];
					break;
					case 2:
					selected = b.pawns[10];
					break;
					case 3:
					selected = b.pawns[11];
					break;
					case 4:
					selected = b.pawns[12];
					break;
					case 5:
					selected = b.pawns[13];
					break;
					case 6:
					selected = b.pawns[14];
					break;
					case 7:
					selected = b.pawns[15];
					break;
					}
				}
				else if(team == "black")
				{
					switch (randP)
					{
					case 0:
						selected = b.pawns[0];
					break;
					case 1:
						selected = b.pawns[1];
					break;
					case 2:
						selected = b.pawns[2];
					break;
					case 3:
						selected = b.pawns[3];
					break;
					case 4:
						selected = b.pawns[4];
					break;
					case 5:
						selected = b.pawns[5];
					break;
					case 6:
						selected = b.pawns[6];
					break;
					case 7:
						selected = b.pawns[7];
					break;
					}
				}
			break;
			
		case 1:
			if(team == "white")
			{
				switch (randP)
				{
				case 0:
					selected = b.bishops[0];
					break;
				case 1:
					selected = b.bishops[1];
					break;
				}
			}
			else if(team == "black")
			{
				switch (randP)
				{
				case 0:
					selected = b.bishops[3];
					break;
				case 1:
					selected = b.bishops[4];
					break;
				}
			}
			
			break;
		case 2:
			if(team == "white")
			{
				switch (randP)
				{
				case 0:
					selected = b.knights[0];
					break;
				case 1:
					selected = b.knights[1];
					break;
				}
			}
			else if(team == "black")
			{
				switch (randP)
				{
				case 0:
					selected = b.knights[3];
					break;
				case 1:
					selected = b.knights[4];
					break;
				}
			}
			break;
		case 3:
			if(team == "white")
			{
				switch (randP)
				{
				case 0:
					selected = b.rooks[0];
					break;
				case 1:
					selected = b.rooks[1];
					break;
				}
			}
			else if(team == "black")
			{
				switch (randP)
				{
				case 0:
					selected = b.rooks[3];
					break;
				case 1:
					selected = b.rooks[4];
					break;
				}
			
			
			}
			break;
		case 4:
			if(team == "white")
			{
				switch (randP)
				{
				case 0:
					selected = b.queens[0];
					break;
				}
			}
			else if(team == "black")
			{
				switch (randP)
				{
				case 0:
					selected = b.queens[1];
					break;
				
				}
			}
			break;
		case 5:
			if(team == "white")
			{
				switch (randP)
				{
				case 0:
					selected = b.kings[0];
					break;
				}
			}
			else if(team == "black")
			{
				switch (randP)
				{
				case 0:
					selected = b.kings[1];
					break;
				
				}
			}
			break;
		default:
			break;
		}
		
	}*/
	public void updateBoard2(board bin)
	{
		b = bin;
	}
}
/*if(matrixBoard[x][y] == Knight[0] || matrixBoard[x][y] == Knight[1] 
||matrixBoard[x][y] == Rook[0] || matrixBoard[x][y] == Rook[1]
	|| matrixBoard[x][y] == Bishop[0] || matrixBoard[x][y] == Bishop[1]
		|| matrixBoard[x][y] == King[0] || matrixBoard[x][y] == King[1]
			|| matrixBoard[x][y] == Queen[0] || matrixBoard[x][y] == Queen[1]
				|| matrixBoard[x][y] == Pawn[0] || matrixBoard[x][y] == Pawn[1])*/
//	if((turn == selected.color) && (turn == "white"))
//	{

//	}
