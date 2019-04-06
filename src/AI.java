//import java.awt.Color;

//import javax.swing.JButton;

public class AI {
	final char[] Rook = {'R','r'};
	final char[] Bishop = {'B','b'};
	final char[] Knight = {'K','k'};
	final char[] King = {'M','m'};
	final char[] Queen = {'W','w'};
	final char[] Pawn = {'P','p'};
	private static final long serialVersionUID = -5478665655191131191L;
	String turn;
	Piece selected;
	boolean select;
	String team;
	char[][] currentPieces = {
			{'P','P','P','P','P','P','P','P'},
			{'B','B'},
			{'K','K'},
			{'R','R'},
			{'W'},
			{'K'}
			};
	int[][][] currentPiecesLocations = {
			{ {1,0},{1,1},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1} },
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
	public void getTurn(String turnIn)
	{
		turn = turnIn;
	}
	public void getSelected(boolean selectInput,Piece selectedInput)
	{
		selected = selectedInput;
		select = selectInput;
	}
	void selectChoice(boolean selectInput)
	{
		select = selectInput;
	}
	int x;
	int y;
	public void getXandY(int xIn, int yIn)
	{
		x = xIn;
		y = yIn;
		
	}
	public boolean checkXandY()
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
	}
	public void updateBoard()
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
		
	}
	public boolean captured()
	{
		if((team == "white") && Character.isUpperCase(matrixBoard[7-x][y]))
		{
			return true;
		}
		else if((team == "black") && Character.isLowerCase(matrixBoard[7-x][y]))
		{
			return true;
		}
		return false;
	}
	public void updateCurrentPieces()
	{
		int whichPieceType = -1;
		int whichPieceLocation = -1;
		
			//currentPiecesLocations[whichPieceType][whichPieceLocation][row == 0 || col == 1] - Corresponds to 
			//matrixBoard, not the board grid
		if(matrixBoard[7-x][y] == 'P' || matrixBoard[7-x][y] == 'p')
		{
			whichPieceType = 0;
		}
		else if(matrixBoard[7-x][y] == 'B' || matrixBoard[7-x][y] == 'b')
		{
			whichPieceType = 1;
		}
		else if(matrixBoard[7-x][y] == 'K' || matrixBoard[7-x][y] == 'k')
		{
			whichPieceType = 2;
		}
		else if(matrixBoard[7-x][y] == 'R' || matrixBoard[7-x][y] == 'r')
		{
			whichPieceType = 3;
		}
		else if(matrixBoard[7-x][y] == 'W' || matrixBoard[7-x][y] == 'w')
		{
			whichPieceType = 4;
		}
		else if(matrixBoard[7-x][y] == 'M' || matrixBoard[7-x][y] == 'm')
		{
			whichPieceType = 5;
		}
		int row = 7-x;
		int col = y;
		if(whichPieceType != -1)
		{
			for(int i = 0; i<currentPiecesLocations[whichPieceType].length;++i)
			{
				if(row == currentPiecesLocations[whichPieceType][i][0] 
						&& col == currentPiecesLocations[whichPieceType][i][1])
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
	public AI()
	{
		//JButton tiles = new JButton[8][8]
		//private JButton[][] tiles = new JButton[8][8]; // 8*8 grid buttons for tiles
		//private Color maroon = new Color(128, 0, 0); // gigem 
		//private Color white = Color.WHITE;
		//private Tile[][] grid = new Tile[8][8];  // keep track of the board state
		// define bishops
		select = false;
		team = "white";
		
	}
	void calculateBestMove(){
	    //generate all the moves for a given position
	   // object newGameMoves = game.ugly_moves();
	    //return newGameMoves[Math.floor(Math.random() * newGameMoves.length)];
	};
	void calculateRandomPiece()
	{
		
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