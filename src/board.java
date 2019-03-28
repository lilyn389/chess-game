import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class board extends JFrame {

	// define the board specs
	private Container space;
	private JButton[][] tiles = new JButton[8][8]; // 8*8 grid buttons for tiles
	private Color maroon = new Color(128, 0, 0); // gigem 
	private Color white = Color.WHITE;
	private Tile[][] grid = new Tile[8][8];  // keep track of the board state
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
	// declare knights
	private Knight[] knights = new Knight[4];
	// declare rooks
	private Rook[] rooks = new Rook[4];
	// declare bishops
	private Bishop[] bishops = new Bishop[4];
	// declare Queens
	private Queen[] queens = new Queen[2];
	// declare Kings
	private King[] kings = new King[2];
	// declare black pawns
	private Pawn[] pawns = new Pawn[16];
	
	public board() {    // constructor
		super("Chess Board");
		
		// define knights
		knights[0] = new Knight("white", "knight", 0, 1);
		knights[1] = new Knight("white", "knight", 0, 6);
		knights[2] = new Knight("black", "knight", 7, 1);
		knights[3] = new Knight("black", "knight", 7, 6);
		// define rooks
		rooks[0] = new Rook("white", "rook", 0, 0);
		rooks[1] = new Rook("white", "rook", 0, 7);
		rooks[2] = new Rook("black", "rook", 7, 0);
		rooks[3] = new Rook("black", "rook", 7, 7);
		// define bishops
		bishops[0] = new Bishop("white", "bishop", 0, 2);
		bishops[1] = new Bishop("white", "bishop", 0, 5);
		bishops[2] = new Bishop("black", "bishop", 7, 2);
		bishops[3] = new Bishop("black", "bishop", 7, 5);
		// define kings
		kings[0] = new King("white", "king", 0, 3);
		kings[1] = new King("black", "king", 7, 3);
		// define queens
		queens[0] = new Queen("white", "queen", 0, 4);
		queens[1] = new Queen("black", "queen", 7, 4);
		// define black pawns
		pawns[0] = new Pawn("black", "pawn", 6, 0);
		pawns[1] = new Pawn("black", "pawn", 6, 1);
		pawns[2] = new Pawn("black", "pawn", 6, 2);
		pawns[3] = new Pawn("black", "pawn", 6, 3);
		pawns[4] = new Pawn("black", "pawn", 6, 4);
		pawns[5] = new Pawn("black", "pawn", 6, 5);
		pawns[6] = new Pawn("black", "pawn", 6, 6);
		pawns[7] = new Pawn("black", "pawn", 6, 7);
		// define white pawns
		pawns[8] = new Pawn("white", "pawn", 1, 0);
		pawns[9] = new Pawn("white", "pawn", 1, 1);
		pawns[10] = new Pawn("white", "pawn", 1, 2);
		pawns[11] = new Pawn("white", "pawn", 1, 3);
		pawns[12] = new Pawn("white", "pawn", 1, 4);
		pawns[13] = new Pawn("white", "pawn", 1, 5);
		pawns[14] = new Pawn("white", "pawn", 1, 6);
		pawns[15] = new Pawn("white", "pawn", 1, 7);
		
		index = 100;
		selected = new Piece();
		selectedKnight = new Knight();
		select = false;
		turn = "white";
		
		space = getContentPane();
		space.setLayout(new GridLayout (8, 8));
		
		// event handler
		ButtonHandler handleClick = new ButtonHandler();
		
		// build the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tiles[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					tiles[i][j].setBackground(maroon);
				}
				else {
					tiles[i][j].setBackground(white);
				}
				space.add(tiles[i][j]);
				
				tiles[i][j].addActionListener(handleClick);
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
		
		setSize(900, 900);
		setResizable(false);
		setLocationRelativeTo(null);  // centers window
		setVisible(true);
	}
	
	private int processSelection(int x, int y) {
		
		// check if there is a knight here
		for (int i = 0; i < 4; i++) {
			if ( (knights[i].getRow() == x) & (knights[i].getColumn() == y) ) {
				selected = knights[i];
				selectedKnight = knights[i];
				select = true;
				return i;
			}
		}

		// check if there is a rook here
		for (int i = 0; i < 4; i++) {
			if ( (rooks[i].getRow() == x) & (rooks[i].getColumn() == y) ) {
				selected = rooks[i];
				selectedRook = rooks[i];
				select = true;
				return i;
			}
		}	
		
		// check if there is a bishop here
		for (int i = 0; i < 4; i++) {
			if ( (bishops[i].getRow() == x) & (bishops[i].getColumn() == y) ) {
				selected = bishops[i];
				selectedBishop = bishops[i];
				select = true;
				return i;
			}
		}

		// check if there is a king here
		for (int i = 0; i < 2; i++) {
			if ( (kings[i].getRow() == x) & (kings[i].getColumn() == y) ) {
				selected = kings[i];
				selectedKing = kings[i];
				select = true;
				return i;
			}
		}
		
		// check if there is a queen here
		for (int i = 0; i < 2; i++) {
			if ( (queens[i].getRow() == x) & (queens[i].getColumn() == y) ) {
				selected = queens[i];
				selectedQueen = queens[i];
				select = true;
				return i;
			}
		}
		
		// check if there is a pawn here
		for (int i = 0; i < 16; i++) {
			if ( (pawns[i].getRow() == x) & (pawns[i].getColumn() == y) ) {
				selected = pawns[i];
				selectedPawn = pawns[i];
				select = true;
				return i;
			}
		}
		return -1;
	}
	
	private void processClick(int x, int y) {
		// check if there is a piece selected. 
		if (select == false) {
			index = processSelection(x, y);
			return;
		}
		
		// check if a knight is on this tile
		for (int i = 0; i < 4; i++) {
			if ( (knights[i].getRow() == x) & (knights[i].getColumn() == y) ) {
				select = false;  // empty the current piece selection. 
				return;
			}
		}
		
		// move a knight
		if (selected.getColor() == turn & selected.getName() == "knight") {
			if (selectedKnight.isValidMove(x, y)) {
				tiles[selectedKnight.getRow()][selectedKnight.getColumn()].setIcon(null);
				tiles[x][y].setIcon(selectedKnight.getIcon());
				knights[index].setRow(x);
				knights[index].setColumn(y);
				select = false;
				if (turn == "white") {
					turn = "black";
				}
				else {
					turn = "white";
				}
				return;
			}
		}
		
		// move a rook
		if (selected.getColor() == turn & selected.getName() == "rook") {
			if (selectedRook.isValidMove(x, y)) {
				tiles[selectedRook.getRow()][selectedRook.getColumn()].setIcon(null);
				tiles[x][y].setIcon(selectedRook.getIcon());
				rooks[index].setRow(x);
				rooks[index].setColumn(y);
				select = false;
				if (turn == "white") {
					turn = "black";
				}
				else {
					turn = "white";
				}
				return;
			}
		}
		
		// move a bishop
		if (selected.getColor() == turn & selected.getName() == "bishop") {
			if (selectedBishop.isValidMove(x, y)) {
				tiles[selectedBishop.getRow()][selectedBishop.getColumn()].setIcon(null);
				tiles[x][y].setIcon(selectedBishop.getIcon());
				bishops[index].setRow(x);
				bishops[index].setColumn(y);
				select = false;
				if (turn == "white") {
					turn = "black";
				}
				else {
					turn = "white";
				}
				return;
			}
		}
		
		// move a queen
		if (selected.getColor() == turn & selected.getName() == "queen") {
			if (selectedQueen.isValidMove(x, y)) {
				tiles[selectedQueen.getRow()][selectedQueen.getColumn()].setIcon(null);
				tiles[x][y].setIcon(selectedQueen.getIcon());
				queens[index].setRow(x);
				queens[index].setColumn(y);
				select = false;
				if (turn == "white") {
					turn = "black";
				}
				else {
					turn = "white";
				}
				return;
			}
		}
		
		// move a queen
		if (selected.getColor() == turn & selected.getName() == "king") {
			if (selectedKing.isValidMove(x, y)) {
				tiles[selectedKing.getRow()][selectedKing.getColumn()].setIcon(null);
				tiles[x][y].setIcon(selectedKing.getIcon());
				kings[index].setRow(x);
				kings[index].setColumn(y);
				select = false;
				if (turn == "white") {
					turn = "black";
				}
				else {
					turn = "white";
				}
				return;
			}
		}
		
		// move a pawn
		if (selected.getColor() == turn & selected.getName() == "pawn") {
			if (selectedPawn.isValidMove(x, y)) {
				tiles[selectedPawn.getRow()][selectedPawn.getColumn()].setIcon(null);
				tiles[x][y].setIcon(selectedPawn.getIcon());
				pawns[index].setRow(x);
				pawns[index].setColumn(y);
				select = false;
				if (turn == "white") {
					turn = "black";
				}
				else {
					turn = "white";
				}
				return;
			}
		}
		select = false;  // reset
	}
	
	
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
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
	

