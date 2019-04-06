import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class board extends JFrame {

	// define the board specifications
	private Container space;
	private JButton[][] tiles = new JButton[8][8]; // 8*8 grid buttons for tiles
	private Color maroon = new Color(128, 0, 0); // gig'em 
	private Color white = Color.WHITE;
	protected Tile[][] grid = new Tile[8][8];  // keep track of the board state
	
	// move helper variables
	private String turn;
	private Piece selected;
	private Knight selectedKnight;
	private Rook selectedRook;
	private Bishop selectedBishop;
	private King selectedKing;
	private Queen selectedQueen;
	private Pawn selectedPawn;
	private boolean select;
	private int index;
	private Vector<Piece> white_pieces;  // vector to keep track of white pieces
	private Vector<Piece> black_pieces;  // vector to keep track of black pieces
	private int numOfQueens = 2;
	// declare knights
	private Knight[] knights = new Knight[4];
	// declare rooks
	private Rook[] rooks = new Rook[4];
	// declare bishops
	private Bishop[] bishops = new Bishop[4];
	// declare Queens
	private Queen[] queens = new Queen[18];
	// declare Kings
	private King[] kings = new King[2];
	// declare black pawns
	private Pawn[] pawns = new Pawn[16];
	
	public board() {    // constructor
		super("Chess Board");
		
		// build board manager 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new Tile();
			}
		}
		
		// initialize piece vectors 
		white_pieces = new Vector<Piece>();
		black_pieces = new Vector<Piece>();
		
		// define knights and place them on grid
		knights[0] = new Knight("white", "knight", 0, 1, 0, true);
		grid[0][1] = new Tile(knights[0], false);  // set them on the tile
		knights[1] = new Knight("white", "knight", 0, 6, 1, true);
		grid[0][6] = new Tile(knights[1], false);
		white_pieces.addElement(knights[0]);   // add them to the vector
		white_pieces.addElement(knights[1]);
		knights[2] = new Knight("black", "knight", 7, 1, 2, true);
		grid[7][1] = new Tile(knights[2], false);
		knights[3] = new Knight("black", "knight", 7, 6, 3, true);
		grid[7][6] = new Tile(knights[3], false);
		black_pieces.addElement(knights[2]);
		black_pieces.addElement(knights[3]);
		// define rooks
		rooks[0] = new Rook("white", "rook", 0, 0, 0, true);
		grid[0][0] = new Tile(rooks[0], false);
		rooks[1] = new Rook("white", "rook", 0, 7, 1, true);
		grid[0][7] = new Tile(rooks[1], false);
		white_pieces.addElement(rooks[0]);
		white_pieces.addElement(rooks[1]);
		rooks[2] = new Rook("black", "rook", 7, 0, 2, true);
		grid[7][0] = new Tile(rooks[2], false);
		rooks[3] = new Rook("black", "rook", 7, 7, 3, true);
		grid[7][7] = new Tile(rooks[3], false);
		black_pieces.addElement(rooks[2]);
		black_pieces.addElement(rooks[3]);
		// define bishops
		bishops[0] = new Bishop("white", "bishop", 0, 2, 0, true);
		grid[0][2] = new Tile(bishops[0], false);
		bishops[1] = new Bishop("white", "bishop", 0, 5, 1, true);
		grid[0][5] = new Tile(rooks[1], false);
		white_pieces.addElement(bishops[0]);
		white_pieces.addElement(bishops[1]);
		bishops[2] = new Bishop("black", "bishop", 7, 2, 2, true);
		grid[7][2] = new Tile(rooks[2], false);
		bishops[3] = new Bishop("black", "bishop", 7, 5, 3, true);
		grid[7][5] = new Tile(rooks[3], false);
		black_pieces.addElement(bishops[2]);
		black_pieces.addElement(bishops[3]);

		// define kings
		kings[0] = new King("white", "king", 0, 3, 0, true);
		grid[0][3] = new Tile(kings[0], false);
		white_pieces.addElement(kings[0]);
		kings[1] = new King("black", "king", 7, 3, 1, true);
		grid[7][3] = new Tile(kings[1], false);
		black_pieces.addElement(kings[1]);
		// define queens
		queens[0] = new Queen("white", "queen", 0, 4, 0, true);
		grid[0][4] = new Tile(queens[0], false);
		white_pieces.addElement(queens[0]);
		queens[1] = new Queen("black", "queen", 7, 4, 1, true);
		grid[7][4] = new Tile(queens[1], false);
		black_pieces.addElement(queens[1]);
		// define black pawns
		pawns[0] = new Pawn("black", "pawn", 6, 0, 0, true);
		grid[6][0] = new Tile(pawns[0], false);
		pawns[1] = new Pawn("black", "pawn", 6, 1, 1, true);
		grid[6][1] = new Tile(pawns[1], false);
		pawns[2] = new Pawn("black", "pawn", 6, 2, 2, true);
		grid[6][2] = new Tile(pawns[2], false);
		pawns[3] = new Pawn("black", "pawn", 6, 3, 3, true);
		grid[6][3] = new Tile(pawns[3], false);
		pawns[4] = new Pawn("black", "pawn", 6, 4, 4, true);
		grid[6][4] = new Tile(pawns[4], false);
		pawns[5] = new Pawn("black", "pawn", 6, 5, 5, true);
		grid[6][5] = new Tile(pawns[5], false);
		pawns[6] = new Pawn("black", "pawn", 6, 6, 6, true);
		grid[6][6] = new Tile(pawns[6], false);
		pawns[7] = new Pawn("black", "pawn", 6, 7, 7, true);
		grid[6][7] = new Tile(pawns[7], false);
		black_pieces.addElement(pawns[0]);
		black_pieces.addElement(pawns[1]);
		black_pieces.addElement(pawns[2]);
		black_pieces.addElement(pawns[3]);
		black_pieces.addElement(pawns[4]);
		black_pieces.addElement(pawns[5]);
		black_pieces.addElement(pawns[6]);
		black_pieces.addElement(pawns[7]);
		// define white pawns
		pawns[8] = new Pawn("white", "pawn", 1, 0, 8, true);
		grid[1][0] = new Tile(pawns[8], false);
		pawns[9] = new Pawn("white", "pawn", 1, 1, 9, true);
		grid[1][1] = new Tile(pawns[9], false);
		pawns[10] = new Pawn("white", "pawn", 1, 2, 10, true);
		grid[1][2] = new Tile(pawns[10], false);
		pawns[11] = new Pawn("white", "pawn", 1, 3, 11, true);
		grid[1][3] = new Tile(pawns[11], false);
		pawns[12] = new Pawn("white", "pawn", 1, 4, 12, true);
		grid[1][4] = new Tile(pawns[12], false);
		pawns[13] = new Pawn("white", "pawn", 1, 5, 13, true);
		grid[1][5] = new Tile(pawns[13], false);
		pawns[14] = new Pawn("white", "pawn", 1, 6, 14, true);
		grid[1][6] = new Tile(pawns[14], false);
		pawns[15] = new Pawn("white", "pawn", 1, 7, 15, true);
		grid[1][7] = new Tile(pawns[15], false);
		white_pieces.addElement(pawns[8]);
		white_pieces.addElement(pawns[9]);
		white_pieces.addElement(pawns[10]);
		white_pieces.addElement(pawns[11]);
		white_pieces.addElement(pawns[12]);
		white_pieces.addElement(pawns[13]);
		white_pieces.addElement(pawns[14]);
		white_pieces.addElement(pawns[15]);

		// associate the pieces with their king
		kings[0].setPieces(white_pieces);
		kings[0].setEnemyPieces(black_pieces);
		kings[1].setPieces(black_pieces);
		kings[1].setEnemyPieces(white_pieces);
		
		index = 100; 
		select = false; // true if a valid piece has been clicked on by a player
		turn = "white";  // white moves first
		
		space = getContentPane();
		space.setLayout(new GridLayout (8, 8));
		
		// event handler
		ButtonHandler handleClick = new ButtonHandler();
		
		// build the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tiles[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					tiles[i][j].setBackground(maroon);  // color the maroon pieces
				}
				else {
					tiles[i][j].setBackground(white);  // color the white pieces 
				}
				space.add(tiles[i][j]);  // place it on the board
				
				tiles[i][j].addActionListener(handleClick);  // set listener for each tile button
			}
		}
				
		// place knights
		tiles[knights[0].getRow()][knights[0].getColumn()].setIcon(knights[0].getIcon()); 
		tiles[knights[1].getRow()][knights[1].getColumn()].setIcon(knights[1].getIcon());  
		tiles[knights[2].getRow()][knights[2].getColumn()].setIcon(knights[2].getIcon()); 
		tiles[knights[3].getRow()][knights[3].getColumn()].setIcon(knights[3].getIcon()); 
		// place rooks
		tiles[rooks[0].getRow()][rooks[0].getColumn()].setIcon(rooks[0].getIcon());  
		tiles[rooks[1].getRow()][rooks[1].getColumn()].setIcon(rooks[1].getIcon()); 
		tiles[rooks[2].getRow()][rooks[2].getColumn()].setIcon(rooks[2].getIcon()); 
		tiles[rooks[3].getRow()][rooks[3].getColumn()].setIcon(rooks[3].getIcon());
		// place bishops
		tiles[bishops[0].getRow()][bishops[0].getColumn()].setIcon(bishops[0].getIcon());  
		tiles[bishops[1].getRow()][bishops[1].getColumn()].setIcon(bishops[1].getIcon()); 
		tiles[bishops[2].getRow()][bishops[2].getColumn()].setIcon(bishops[2].getIcon()); 
		tiles[bishops[3].getRow()][bishops[3].getColumn()].setIcon(bishops[3].getIcon());
		// place Queens
		tiles[queens[0].getRow()][queens[0].getColumn()].setIcon(queens[0].getIcon());  
		tiles[queens[1].getRow()][queens[1].getColumn()].setIcon(queens[1].getIcon());
		// place Kings
		tiles[kings[0].getRow()][kings[0].getColumn()].setIcon(kings[0].getIcon());  
		tiles[kings[1].getRow()][kings[1].getColumn()].setIcon(kings[1].getIcon());
		// place white pawns
		tiles[pawns[0].getRow()][pawns[0].getColumn()].setIcon(pawns[0].getIcon());
		tiles[pawns[1].getRow()][pawns[1].getColumn()].setIcon(pawns[1].getIcon());
		tiles[pawns[2].getRow()][pawns[2].getColumn()].setIcon(pawns[2].getIcon());
		tiles[pawns[3].getRow()][pawns[3].getColumn()].setIcon(pawns[3].getIcon());
		tiles[pawns[4].getRow()][pawns[4].getColumn()].setIcon(pawns[4].getIcon());
		tiles[pawns[5].getRow()][pawns[5].getColumn()].setIcon(pawns[5].getIcon());
		tiles[pawns[6].getRow()][pawns[6].getColumn()].setIcon(pawns[6].getIcon());
		tiles[pawns[7].getRow()][pawns[7].getColumn()].setIcon(pawns[7].getIcon());
		// place black pawns
		tiles[pawns[8].getRow()][pawns[8].getColumn()].setIcon(pawns[8].getIcon());
		tiles[pawns[9].getRow()][pawns[9].getColumn()].setIcon(pawns[9].getIcon());
		tiles[pawns[10].getRow()][pawns[10].getColumn()].setIcon(pawns[10].getIcon());
		tiles[pawns[11].getRow()][pawns[11].getColumn()].setIcon(pawns[11].getIcon());
		tiles[pawns[12].getRow()][pawns[12].getColumn()].setIcon(pawns[12].getIcon());
		tiles[pawns[13].getRow()][pawns[13].getColumn()].setIcon(pawns[13].getIcon());
		tiles[pawns[14].getRow()][pawns[14].getColumn()].setIcon(pawns[14].getIcon());
		tiles[pawns[15].getRow()][pawns[15].getColumn()].setIcon(pawns[15].getIcon());
		
		
		
		setSize(900, 900);  // size of the board
		setResizable(true); 
		setLocationRelativeTo(null);  // centers window
		setVisible(true);  // we need to see it
	}
	
	
	public Vector<Piece> getWhitePieces() {
		return white_pieces;
	}


	public void setWhitePieces(Vector<Piece> white_pieces) {
		this.white_pieces = white_pieces;
	}


	public Vector<Piece> getBlackPieces() {
		return black_pieces;
	}


	public void setBlackPieces(Vector<Piece> black_pieces) {
		this.black_pieces = black_pieces;
	}

	public Tile[][] getGrid() {
		return grid;
	}


	public void setGrid(Tile[][] grid) {
		this.grid = grid;
	}

	public King[] getKings() {
		return kings;
	}


	public void setKings(King[] kings) {
		this.kings = kings;
	}


	// this function is called when a player selects a piece they want to move
	// it validates the selection
	private int processSelection(int x, int y) {
		
		if (grid[x][y].isEmpty()) {
			return -1;
		}
		else {
			if (grid[x][y].getPiece().getName() == "knight" & grid[x][y].getPiece().getColor() == turn) {
				selected = knights[grid[x][y].getPiece().getID()];
				selectedKnight = knights[grid[x][y].getPiece().getID()];
				select = true;
				return grid[x][y].getPiece().getID();
			}
			
			if (grid[x][y].getPiece().getName() == "rook" & grid[x][y].getPiece().getColor() == turn) {
				selected = rooks[grid[x][y].getPiece().getID()];
				selectedRook = rooks[grid[x][y].getPiece().getID()];
				select = true;
				return grid[x][y].getPiece().getID();
			}
			
			if (grid[x][y].getPiece().getName() == "bishop" & grid[x][y].getPiece().getColor() == turn) {
				selected = bishops[grid[x][y].getPiece().getID()];
				selectedBishop = bishops[grid[x][y].getPiece().getID()];
				select = true;
				return grid[x][y].getPiece().getID();
			}
			
			if (grid[x][y].getPiece().getName() == "queen" & grid[x][y].getPiece().getColor() == turn) {
				selected = queens[grid[x][y].getPiece().getID()];
				selectedQueen = queens[grid[x][y].getPiece().getID()];
				select = true;
				return grid[x][y].getPiece().getID();
			}
			
			if (grid[x][y].getPiece().getName() == "king" & grid[x][y].getPiece().getColor() == turn) {
				selected = kings[grid[x][y].getPiece().getID()];
				selectedKing = kings[grid[x][y].getPiece().getID()];
				select = true;
				return grid[x][y].getPiece().getID();
			}
			
			if (grid[x][y].getPiece().getName() == "pawn" & grid[x][y].getPiece().getColor() == turn) {
				selected = pawns[grid[x][y].getPiece().getID()];
				selectedPawn = pawns[grid[x][y].getPiece().getID()];
				select = true;
				return grid[x][y].getPiece().getID();
			}
		}
		return -1; 
	}
	
	// this function is called when someone clicks on one of the pieces, regardless of state
	private void processClick(int x, int y) {
		
		// variable for keeping track of whether we moved or not
		boolean moved = false;
		
		// check if there is a piece selected. 
		// if there is not, then process the selection
		if (select == false) {
			index = processSelection(x, y);
			return;
		}
		// if a piece is selected, then check if the move is valid 
		
		// move a knight
		else if (selected.getColor() == turn & selected.getName() == "knight") {
			selectedKnight.updateGrid(grid);
			if (selectedKnight.isValidMove(x, y)) {
				if (checkMoveLocation(x, y)) {
					tiles[selectedKnight.getRow()][selectedKnight.getColumn()].setIcon(null);
					tiles[x][y].setIcon(selectedKnight.getIcon());
					grid[knights[index].getRow()][knights[index].getColumn()].setPiece(null);
					grid[knights[index].getRow()][knights[index].getColumn()].setEmpty(true);
					knights[index].setRow(x);
					knights[index].setColumn(y);
					grid[x][y].setPiece(knights[index]);
					grid[x][y].setEmpty(false);
					moved = true;
				}
			}
		}
		
		// move a rook
		else if (selected.getColor() == turn & selected.getName() == "rook") {
			selectedRook.updateGrid(grid);
			if (selectedRook.isValidMove(x, y)) {
				if (checkMoveLocation(x, y)) {
					tiles[selectedRook.getRow()][selectedRook.getColumn()].setIcon(null);
					tiles[x][y].setIcon(selectedRook.getIcon());
					grid[rooks[index].getRow()][rooks[index].getColumn()].setPiece(null);
					grid[rooks[index].getRow()][rooks[index].getColumn()].setEmpty(true);
					rooks[index].setRow(x);
					rooks[index].setColumn(y);
					grid[x][y].setPiece(rooks[index]);
					grid[x][y].setEmpty(false);
					moved = true;
			  }
		   }
		}
		
		// move a bishop
		else if (selected.getColor() == turn & selected.getName() == "bishop") {
			selectedBishop.updateGrid(grid);
			if (selectedBishop.isValidMove(x, y)) {				
				if (checkMoveLocation(x, y)) {
					tiles[selectedBishop.getRow()][selectedBishop.getColumn()].setIcon(null);
					tiles[x][y].setIcon(selectedBishop.getIcon());
					grid[bishops[index].getRow()][bishops[index].getColumn()].setPiece(null);
					grid[bishops[index].getRow()][bishops[index].getColumn()].setEmpty(true);
					bishops[index].setRow(x);
					bishops[index].setColumn(y);
					grid[x][y].setPiece(bishops[index]);
					grid[x][y].setEmpty(false);
					moved = true;
				}
			}
		}
		
		// move a queen
		else if (selected.getColor() == turn & selected.getName() == "queen") {
			selectedQueen.updateGrid(grid);
			if (selectedQueen.isValidMove(x, y)) {
				if (checkMoveLocation(x, y)) {
					tiles[selectedQueen.getRow()][selectedQueen.getColumn()].setIcon(null);
					tiles[x][y].setIcon(selectedQueen.getIcon());
					grid[queens[index].getRow()][queens[index].getColumn()].setPiece(null);
					grid[queens[index].getRow()][queens[index].getColumn()].setEmpty(true);
					queens[index].setRow(x);
					queens[index].setColumn(y);
					grid[x][y].setPiece(queens[index]);
					grid[x][y].setEmpty(false);
					moved = true;
				}
			}
		}
		
		// move a king
		else if (selected.getColor() == turn & selected.getName() == "king") {
			selectedKing.updateGrid(grid);
			if (selectedKing.isValidMove(x, y)) {
				if (checkMoveLocation(x, y)) {
					tiles[selectedKing.getRow()][selectedKing.getColumn()].setIcon(null);
					tiles[x][y].setIcon(selectedKing.getIcon());
					grid[kings[index].getRow()][kings[index].getColumn()].setPiece(null);
					grid[kings[index].getRow()][kings[index].getColumn()].setEmpty(true);
					kings[index].setRow(x);
					kings[index].setColumn(y);
					grid[x][y].setPiece(kings[index]);
					grid[x][y].setEmpty(false);
					moved = true;
				}
			}
		}
		
		// move a pawn
		else if (selected.getColor() == turn & selected.getName() == "pawn") {
			selectedPawn.updateGrid(grid);
			if (selectedPawn.isValidMove(x, y)) {
				if (checkMoveLocation(x, y)) {
					tiles[selectedPawn.getRow()][selectedPawn.getColumn()].setIcon(null);
					tiles[x][y].setIcon(selectedPawn.getIcon());
					grid[pawns[index].getRow()][pawns[index].getColumn()].setPiece(null);
					grid[pawns[index].getRow()][pawns[index].getColumn()].setEmpty(true);
					pawns[index].setRow(x);
					pawns[index].setColumn(y);
					grid[x][y].setPiece(pawns[index]);
					grid[x][y].setEmpty(false);
					moved = true;
          //Pawn promotion
					if (y == 0 || y = 7) {
						
						queens[numOfQueens] = new Queen(selectedPawn.getColor(), selectedPawn.getName(), x, y, numOfQueens);
						grid[x][y].setPiece(queens[numOfQueens]);
						tiles[x][y].setIcon(queens[numOfQueens].getIcon());
						
						numOfQueens++;
					}
				}
			}
		}
		
		if (moved) {
			select = false;  // reset
			// run isCheck & isCheckMate
			if (kings[0].getColor() != turn) {
				if (kings[0].isCheck()) {
					if (kings[0].isCheckMate()) {
						// game over
						gameOver(kings[0].getColor());
					}
					else {
						// warn the other team that they are in check
						JOptionPane.showMessageDialog(space, "White King is in check!");
					}
				}
			}
			else if (kings[1].getColor() != turn) {
				if (kings[1].isCheck()) {
					if (kings[1].isCheckMate()) {
						// game over
						gameOver(kings[1].getColor());
					}
					else {
						// warn the other team that they are in check
						JOptionPane.showMessageDialog(space, "Black King is in check!");
					}
				}
			}
	
			if (turn == "white") {
				turn = "black";
			}
			else {
				turn = "white";
			}
		}
		return;
	}
	
	// This function checks the location that the player is trying to move their piece to
	private boolean checkMoveLocation(int x, int y) {
		if (grid[x][y].isEmpty()) {  // if it is an empty tile, move there
			return true;
		}
		else {
			if( grid[x][y].getPiece().getColor() == turn) {  // if it is the same color as moving piece, dont move there
				return false;
			}
			else {  // if it is an enemy piece, move there and kill the piece
				grid[x][y].setEmpty(true);  // empty piece from grid
				grid[x][y].getPiece().setIsAlive(false); // kill the piece
				return true;
			}
		}
	}
	
	private void gameOver(String winner) {
		JOptionPane.showMessageDialog(space, winner + "is the winner!");
	}
	
	
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			// find which tile was clicked, pass that location as a param to processClick()
			Object source = event.getSource();	
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (source == tiles[i][j]) {
						processClick(i, j);
						return;
					}
				}
			}
		}
	}
}
	

