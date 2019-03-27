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
	// knights
	private Knight wKnight = new Knight("white", "knight", 0, 1);
	private Knight wKnight1 = new Knight("white", "knight", 0, 6);
	private Knight bKnight = new Knight("black", "knight", 7, 1);
	private Knight bKnight1 = new Knight("black", "knight", 7, 6);
	// rooks
	private Rook wRook = new Rook("white", "rook", 0, 0);
	private Rook wRook1 = new Rook("white", "rook", 0, 7);
	private Rook bRook = new Rook("black", "rook", 7, 0);
	private Rook bRook1 = new Rook("black", "rook", 7, 7);
	// bishops
	private Bishop wBishop = new Bishop("white", "bishop", 0, 2);
	private Bishop wBishop1 = new Bishop("white", "bishop", 0, 5);
	private Bishop bBishop = new Bishop("black", "bishop", 7, 2);
	private Bishop bBishop1 = new Bishop("black", "bishop", 7, 5);
	// Queens
	private Queen wQueen = new Queen("white", "queen", 0, 4);
	private Queen bQueen = new Queen("black", "queen", 7, 4);
	// Kings
	private King wKing = new King("white", "king", 0, 3);
	private King bKing = new King("black", "king", 7, 3);
	// black pawns
	private Pawn bPawn0 = new Pawn("black", "pawn", 6, 0);
	private Pawn bPawn1 = new Pawn("black", "pawn", 6, 1);
	private Pawn bPawn2 = new Pawn("black", "pawn", 6, 2);
	private Pawn bPawn3 = new Pawn("black", "pawn", 6, 3);
	private Pawn bPawn4 = new Pawn("black", "pawn", 6, 4);
	private Pawn bPawn5 = new Pawn("black", "pawn", 6, 5);
	private Pawn bPawn6 = new Pawn("black", "pawn", 6, 6);
	private Pawn bPawn7 = new Pawn("black", "pawn", 6, 7);
	// white pawns
	private Pawn wPawn0 = new Pawn("white", "pawn", 1, 0);
	private Pawn wPawn1 = new Pawn("white", "pawn", 1, 1);
	private Pawn wPawn2 = new Pawn("white", "pawn", 1, 2);
	private Pawn wPawn3 = new Pawn("white", "pawn", 1, 3);
	private Pawn wPawn4 = new Pawn("white", "pawn", 1, 4);
	private Pawn wPawn5 = new Pawn("white", "pawn", 1, 5);
	private Pawn wPawn6 = new Pawn("white", "pawn", 1, 6);
	private Pawn wPawn7 = new Pawn("white", "pawn", 1, 7);
	
	public board() {    // constructor
		super("Chess Board");
		
		space = getContentPane();
		space.setLayout(new GridLayout (8, 8));
		
		// event handlers
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
		tiles[wKnight.getRow()][wKnight.getColumn()].setIcon(wKnight.getIcon()); 
		tiles[bKnight.getRow()][bKnight.getColumn()].setIcon(bKnight.getIcon());  
		tiles[wKnight1.getRow()][wKnight1.getColumn()].setIcon(wKnight1.getIcon()); 
		tiles[bKnight1.getRow()][bKnight1.getColumn()].setIcon(bKnight1.getIcon()); 
		// set rooks
		tiles[wRook.getRow()][wRook.getColumn()].setIcon(wRook.getIcon());  
		tiles[bRook.getRow()][bRook.getColumn()].setIcon(bRook.getIcon()); 
		tiles[wRook1.getRow()][wRook1.getColumn()].setIcon(wRook1.getIcon()); 
		tiles[bRook1.getRow()][bRook1.getColumn()].setIcon(bRook1.getIcon());
		// set bishops
		tiles[wBishop.getRow()][wBishop.getColumn()].setIcon(wBishop.getIcon());  
		tiles[bBishop.getRow()][bBishop.getColumn()].setIcon(bBishop.getIcon()); 
		tiles[wBishop1.getRow()][wBishop1.getColumn()].setIcon(wBishop1.getIcon()); 
		tiles[bBishop1.getRow()][bBishop1.getColumn()].setIcon(bBishop1.getIcon());
		// set Queens
		tiles[wQueen.getRow()][wQueen.getColumn()].setIcon(wQueen.getIcon());  
		tiles[bQueen.getRow()][bQueen.getColumn()].setIcon(bQueen.getIcon());
		// set Kings
		tiles[wKing.getRow()][wKing.getColumn()].setIcon(wKing.getIcon());  
		tiles[bKing.getRow()][bKing.getColumn()].setIcon(bKing.getIcon());
		// set white pawns
		tiles[wPawn0.getRow()][wPawn0.getColumn()].setIcon(wPawn1.getIcon());
		tiles[wPawn1.getRow()][wPawn1.getColumn()].setIcon(wPawn2.getIcon());
		tiles[wPawn2.getRow()][wPawn2.getColumn()].setIcon(wPawn3.getIcon());
		tiles[wPawn3.getRow()][wPawn3.getColumn()].setIcon(wPawn4.getIcon());
		tiles[wPawn4.getRow()][wPawn4.getColumn()].setIcon(wPawn5.getIcon());
		tiles[wPawn5.getRow()][wPawn5.getColumn()].setIcon(wPawn6.getIcon());
		tiles[wPawn6.getRow()][wPawn6.getColumn()].setIcon(wPawn7.getIcon());
		tiles[wPawn7.getRow()][wPawn7.getColumn()].setIcon(wPawn7.getIcon());
		// set black pawns
		tiles[bPawn0.getRow()][bPawn0.getColumn()].setIcon(bPawn0.getIcon());
		tiles[bPawn1.getRow()][bPawn1.getColumn()].setIcon(bPawn1.getIcon());
		tiles[bPawn2.getRow()][bPawn2.getColumn()].setIcon(bPawn2.getIcon());
		tiles[bPawn3.getRow()][bPawn3.getColumn()].setIcon(bPawn3.getIcon());
		tiles[bPawn4.getRow()][bPawn4.getColumn()].setIcon(bPawn4.getIcon());
		tiles[bPawn5.getRow()][bPawn5.getColumn()].setIcon(bPawn5.getIcon());
		tiles[bPawn6.getRow()][bPawn6.getColumn()].setIcon(bPawn6.getIcon());
		tiles[bPawn7.getRow()][bPawn7.getColumn()].setIcon(bPawn7.getIcon());
		
		setSize(900, 900);
		setResizable(false);
		setLocationRelativeTo(null);  // centers window
		setVisible(true);
	}
	
	
	private void processClick(int x, int y) {
		// implement
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
	

