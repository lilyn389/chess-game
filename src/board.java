import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class board extends JFrame {

	// define the board specifications
	JFrame space = new JFrame("Chess");
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
	JMenuBar menuBar;
    	JMenu menu;
    	JMenuItem c1, c2, c3;
	
	public board() {    // constructor
        //menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        
        //menu choice: concede
        c1 = new JMenuItem("Concede");
        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Exit", "Main menu"};
                int n = JOptionPane.showOptionDialog(space, "Final Board State", "End Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == JOptionPane.YES_OPTION)
                    System.exit(NORMAL);
                else {
                    new Menu();
                    space.dispose();
                }
            }
        });
        
        //menu choice: draw
        c2 = new JMenuItem("Draw");
        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(space, "Draw?", "Draw", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.YES_OPTION) {
                    Object[] options = {"Exit", "Main menu"};
                    int n = JOptionPane.showOptionDialog(space, "Final Board State", "End Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (n == JOptionPane.YES_OPTION)
                        System.exit(NORMAL);
                    else {
                        new Menu();
                        space.dispose();
                    }
                }
            }
        });
        
        //menu choice: exit game
        c3 = new JMenuItem("Exit");
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(space, "Are you sure you want to exit the application?", "Warning", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.YES_OPTION)
                    System.exit(NORMAL);
            }
        });
        menu.add(c1);
        menu.add(c2);
        menu.add(c3);
        menuBar.add(menu);
        
		
		// build board manager 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new Tile();
			}
		}
		
		// define knights and place them on grid
		knights[0] = new Knight("white", "knight", 0, 1, 0);
		grid[0][1] = new Tile(knights[0], false);
		knights[1] = new Knight("white", "knight", 0, 6, 1);
		grid[0][6] = new Tile(knights[1], false);
		knights[2] = new Knight("black", "knight", 7, 1, 2);
		grid[7][1] = new Tile(knights[2], false);
		knights[3] = new Knight("black", "knight", 7, 6, 3);
		grid[7][6] = new Tile(knights[3], false);
		// define rooks
		rooks[0] = new Rook("white", "rook", 0, 0, 0);
		grid[0][0] = new Tile(rooks[0], false);
		rooks[1] = new Rook("white", "rook", 0, 7, 1);
		grid[0][7] = new Tile(rooks[1], false);
		rooks[2] = new Rook("black", "rook", 7, 0, 2);
		grid[7][0] = new Tile(rooks[2], false);
		rooks[3] = new Rook("black", "rook", 7, 7, 3);
		grid[7][7] = new Tile(rooks[3], false);
		// define bishops
		bishops[0] = new Bishop("white", "bishop", 0, 2, 0);
		grid[0][2] = new Tile(bishops[0], false);
		bishops[1] = new Bishop("white", "bishop", 0, 5, 1);
		grid[0][5] = new Tile(bishops[1], false);
		bishops[2] = new Bishop("black", "bishop", 7, 2, 2);
		grid[7][2] = new Tile(bishops[2], false);
		bishops[3] = new Bishop("black", "bishop", 7, 5, 3);
		grid[7][5] = new Tile(bishops[3], false);
		// define kings
		kings[0] = new King("white", "king", 0, 3, 0);
		grid[0][3] = new Tile(kings[0], false);
		kings[1] = new King("black", "king", 7, 3, 1);
		grid[7][3] = new Tile(kings[1], false);
		// define queens
		queens[0] = new Queen("white", "queen", 0, 4, 0);
		grid[0][4] = new Tile(queens[0], false);
		queens[1] = new Queen("black", "queen", 7, 4, 1);
		grid[7][4] = new Tile(queens[1], false);
		// define black pawns
		pawns[0] = new Pawn("black", "pawn", 6, 0, 0);
		grid[6][0] = new Tile(pawns[0], false);
		pawns[1] = new Pawn("black", "pawn", 6, 1, 1);
		grid[6][1] = new Tile(pawns[1], false);
		pawns[2] = new Pawn("black", "pawn", 6, 2, 2);
		grid[6][2] = new Tile(pawns[2], false);
		pawns[3] = new Pawn("black", "pawn", 6, 3, 3);
		grid[6][3] = new Tile(pawns[3], false);
		pawns[4] = new Pawn("black", "pawn", 6, 4, 4);
		grid[6][4] = new Tile(pawns[4], false);
		pawns[5] = new Pawn("black", "pawn", 6, 5, 5);
		grid[6][5] = new Tile(pawns[5], false);
		pawns[6] = new Pawn("black", "pawn", 6, 6, 6);
		grid[6][6] = new Tile(pawns[6], false);
		pawns[7] = new Pawn("black", "pawn", 6, 7, 7);
		grid[6][7] = new Tile(pawns[7], false);
		// define white pawns
		pawns[8] = new Pawn("white", "pawn", 1, 0, 8);
		grid[1][0] = new Tile(pawns[8], false);
		pawns[9] = new Pawn("white", "pawn", 1, 1, 9);
		grid[1][1] = new Tile(pawns[9], false);
		pawns[10] = new Pawn("white", "pawn", 1, 2, 10);
		grid[1][2] = new Tile(pawns[10], false);
		pawns[11] = new Pawn("white", "pawn", 1, 3, 11);
		grid[1][3] = new Tile(pawns[11], false);
		pawns[12] = new Pawn("white", "pawn", 1, 4, 12);
		grid[1][4] = new Tile(pawns[12], false);
		pawns[13] = new Pawn("white", "pawn", 1, 5, 13);
		grid[1][5] = new Tile(pawns[13], false);
		pawns[14] = new Pawn("white", "pawn", 1, 6, 14);
		grid[1][6] = new Tile(pawns[14], false);
		pawns[15] = new Pawn("white", "pawn", 1, 7, 15);
		grid[1][7] = new Tile(pawns[15], false);
		
		index = 100;
	//	selected = new Piece();
		selectedKnight = new Knight();
		select = false;
		turn = "white";
		
		//space = getContentPane();
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
		
		
        space.setJMenuBar(menuBar);
        space.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		space.setSize(900, 900);
		space.setResizable(false);
		space.setLocationRelativeTo(null);  // centers window
		space.setVisible(true);
	}
	
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
	
	private void processClick(int x, int y) {
		// check if there is a piece selected. 
		if (select == false) {
			index = processSelection(x, y);
			return;
		}
		
		// move a knight
		if (selected.getColor() == turn & selected.getName() == "knight") {
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
					select = false;
					if (turn == "white") {
						turn = "black";
					}
					else {
						turn = "white";
					}
				}
				return;
			}
		}
		
		// move a rook
		if (selected.getColor() == turn & selected.getName() == "rook") {
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
					select = false;
					if (turn == "white") {
						turn = "black";
					}
					else {
						turn = "white";
					}
				}
				return;
			}
		}
		
		// move a bishop
		if (selected.getColor() == turn & selected.getName() == "bishop") {
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
		}
		
		// move a queen
		if (selected.getColor() == turn & selected.getName() == "queen") {
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
		}
		
		// move a king
		if (selected.getColor() == turn & selected.getName() == "king") {
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
		}
		
		// move a pawn
		if (selected.getColor() == turn & selected.getName() == "pawn") {
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
		}
		select = false;  // reset 
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
				return true;
			}
		}
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
