import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class board extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	// AI declarations 
	protected AI ai;
	private boolean isAI = false; // ***FIXME**** temp var to give AI moves
	protected String AIColor;
	private String AI_difficulty;
	private boolean AI_play;
	private boolean AI_AI;
	
	// for playing the computer locally
	private AIBoard ai_board;
	private Thread ai_thread;
	
	// define the board specifications
	JFrame space = new JFrame("Chess");
	JButton[][] tiles = new JButton[8][8]; // 8*8 grid buttons for tiles
	private Color maroon = new Color(128, 0, 0); // gig'em 
	private Color white = Color.WHITE;
	Tile[][] grid = new Tile[8][8];  // keep track of the board state
	private boolean visible;
	
	// client-server declarations
	private Thread thread;
	private Socket s;
	private PrintWriter pr;
	private InputStreamReader in;
	private BufferedReader bf;
	private String server_msg;
	protected boolean network_play = false;
	protected String my_color;	
	protected String IP;
	
	// move helper variables
	protected String turn = "white";
	protected Piece selected;
	private boolean select = false;
	Vector<Piece> white_pieces;  // vector to keep track of white pieces
	Vector<Piece> black_pieces;  // vector to keep track of black pieces
	private int numOfQueens = 2;
	protected int row_moved_from;
	protected int column_moved_from;
	
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
	
	// Menu declarations
	private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem c1, c2, c3;
    
	/* Default Constructor, this constructor sets up the board,
	 * places all pieces, and connects to the server. */
	public board(boolean _AI_AI, boolean _AI_play, boolean _network_play, String _AIColor, String _AI_difficulty, String _IP, boolean _visible) throws InterruptedException, IOException {    
		
		// initialize game configuration set by user in the menu
		AI_AI = _AI_AI;  // true if in 'computer vs. computer' mode.
		AI_play = _AI_play; // true if in 'human vs computer' mode.
		// **NOTE for Joshua** comment this (network_play) out if you dont want to connect to the network. 
		network_play = _network_play; // currently always true, meaning that we will always connect to the network
		AIColor = _AIColor; // determines color of AI
		AI_difficulty = _AI_difficulty; // difficulty level set by user { easy | medium | hard }
		
		// set visibility 
		visible = _visible;
		
		// IP address for network_play
		IP = _IP;
		
		// initialize menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        
        // menu choice: concede
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
		
		// build the grid
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new Tile();
			}
		}
		
		// initialize piece vectors 
		white_pieces = new Vector<Piece>();
		black_pieces = new Vector<Piece>();
		
		// Initialize all pieces and place them on the grid
		// Knights
		knights[0] = new Knight(grid, "white", "knight", 0, 1, 0, true);
		grid[0][1] = new Tile(knights[0], false);  // set them on the tile
		knights[1] = new Knight(grid, "white", "knight", 0, 6, 1, true);
		grid[0][6] = new Tile(knights[1], false);
		white_pieces.addElement(knights[0]);   // add them to the vector
		white_pieces.addElement(knights[1]);
		knights[2] = new Knight(grid, "black", "knight", 7, 1, 2, true);
		grid[7][1] = new Tile(knights[2], false);
		knights[3] = new Knight(grid, "black", "knight", 7, 6, 3, true);
		grid[7][6] = new Tile(knights[3], false);
		black_pieces.addElement(knights[2]);
		black_pieces.addElement(knights[3]);
		
		// Rooks
		rooks[0] = new Rook(grid, "white", "rook", 0, 0, 0, true);
		grid[0][0] = new Tile(rooks[0], false);
		rooks[1] = new Rook(grid, "white", "rook", 0, 7, 1, true);
		grid[0][7] = new Tile(rooks[1], false);
		white_pieces.addElement(rooks[0]);
		white_pieces.addElement(rooks[1]);
		rooks[2] = new Rook(grid, "black", "rook", 7, 0, 2, true);
		grid[7][0] = new Tile(rooks[2], false);
		rooks[3] = new Rook(grid, "black", "rook", 7, 7, 3, true);
		grid[7][7] = new Tile(rooks[3], false);
		black_pieces.addElement(rooks[2]);
		black_pieces.addElement(rooks[3]);
		
		// Bishops
		bishops[0] = new Bishop(grid, "white", "bishop", 0, 2, 0, true);
		grid[0][2] = new Tile(bishops[0], false);
		bishops[1] = new Bishop(grid, "white", "bishop", 0, 5, 1, true);
		grid[0][5] = new Tile(bishops[1], false);
		white_pieces.addElement(bishops[0]);
		white_pieces.addElement(bishops[1]);
		bishops[2] = new Bishop(grid, "black", "bishop", 7, 2, 2, true);
		grid[7][2] = new Tile(bishops[2], false);
		bishops[3] = new Bishop(grid, "black", "bishop", 7, 5, 3, true);
		grid[7][5] = new Tile(bishops[3], false);
		black_pieces.addElement(bishops[2]);
		black_pieces.addElement(bishops[3]);

		// Kings
		kings[0] = new King(grid, "white", "king", 0, 3, 0, true);
		grid[0][3] = new Tile(kings[0], false);
		white_pieces.addElement(kings[0]);
		kings[1] = new King(grid, "black", "king", 7, 3, 1, true);
		grid[7][3] = new Tile(kings[1], false);
		black_pieces.addElement(kings[1]);
		
		// Queens
		queens[0] = new Queen(grid, "white", "queen", 0, 4, 0, true);
		grid[0][4] = new Tile(queens[0], false);
		white_pieces.addElement(queens[0]);
		queens[1] = new Queen(grid, "black", "queen", 7, 4, 1, true);
		grid[7][4] = new Tile(queens[1], false);
		black_pieces.addElement(queens[1]);
		
		// Black pawns
		pawns[0] = new Pawn(grid, "black", "pawn", 6, 0, 0, true);
		grid[6][0] = new Tile(pawns[0], false);
		pawns[1] = new Pawn(grid, "black", "pawn", 6, 1, 1, true);
		grid[6][1] = new Tile(pawns[1], false);
		pawns[2] = new Pawn(grid, "black", "pawn", 6, 2, 2, true);
		grid[6][2] = new Tile(pawns[2], false);
		pawns[3] = new Pawn(grid, "black", "pawn", 6, 3, 3, true);
		grid[6][3] = new Tile(pawns[3], false);
		pawns[4] = new Pawn(grid, "black", "pawn", 6, 4, 4, true);
		grid[6][4] = new Tile(pawns[4], false);
		pawns[5] = new Pawn(grid, "black", "pawn", 6, 5, 5, true);
		grid[6][5] = new Tile(pawns[5], false);
		pawns[6] = new Pawn(grid, "black", "pawn", 6, 6, 6, true);
		grid[6][6] = new Tile(pawns[6], false);
		pawns[7] = new Pawn(grid, "black", "pawn", 6, 7, 7, true);
		grid[6][7] = new Tile(pawns[7], false);
		black_pieces.addElement(pawns[0]);
		black_pieces.addElement(pawns[1]);
		black_pieces.addElement(pawns[2]);
		black_pieces.addElement(pawns[3]);
		black_pieces.addElement(pawns[4]);
		black_pieces.addElement(pawns[5]);
		black_pieces.addElement(pawns[6]);
		black_pieces.addElement(pawns[7]);
		
		// White pawns
		pawns[8] = new Pawn(grid, "white", "pawn", 1, 0, 8, true);
		grid[1][0] = new Tile(pawns[8], false);
		pawns[9] = new Pawn(grid, "white", "pawn", 1, 1, 9, true);
		grid[1][1] = new Tile(pawns[9], false);
		pawns[10] = new Pawn(grid, "white", "pawn", 1, 2, 10, true);
		grid[1][2] = new Tile(pawns[10], false);
		pawns[11] = new Pawn(grid, "white", "pawn", 1, 3, 11, true);
		grid[1][3] = new Tile(pawns[11], false);
		pawns[12] = new Pawn(grid, "white", "pawn", 1, 4, 12, true);
		grid[1][4] = new Tile(pawns[12], false);
		pawns[13] = new Pawn(grid, "white", "pawn", 1, 5, 13, true);
		grid[1][5] = new Tile(pawns[13], false);
		pawns[14] = new Pawn(grid, "white", "pawn", 1, 6, 14, true);
		grid[1][6] = new Tile(pawns[14], false);
		pawns[15] = new Pawn(grid, "white", "pawn", 1, 7, 15, true);
		grid[1][7] = new Tile(pawns[15], false);
		white_pieces.addElement(pawns[8]);
		white_pieces.addElement(pawns[9]);
		white_pieces.addElement(pawns[10]);
		white_pieces.addElement(pawns[11]);
		white_pieces.addElement(pawns[12]);
		white_pieces.addElement(pawns[13]);
		white_pieces.addElement(pawns[14]);
		white_pieces.addElement(pawns[15]);

		// Associate the pieces with their king
		// This is for the check & checkmate functions
		kings[0].setPieces(white_pieces);
		kings[0].setEnemyPieces(black_pieces);
		kings[1].setPieces(black_pieces);
		kings[1].setEnemyPieces(white_pieces);
		kings[0].setGrid(grid);
		kings[1].setGrid(grid);

		
		//space = getContentPane();
		space.setLayout(new GridLayout (8, 8));
		
		// Event handler
		ButtonHandler handleClick = new ButtonHandler();
		
		// Build the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tiles[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					tiles[i][j].setBackground(maroon); // gigem
				}
				else {
					tiles[i][j].setBackground(white);
				}
				space.add(tiles[i][j]);
				
				// Associate the action listener with each tile
				tiles[i][j].addActionListener(handleClick);
			}
		}
				
		// Place all pieces on the board
		// Knights
		tiles[knights[0].getRow()][knights[0].getColumn()].setIcon(knights[0].getIcon()); 
		tiles[knights[1].getRow()][knights[1].getColumn()].setIcon(knights[1].getIcon());  
		tiles[knights[2].getRow()][knights[2].getColumn()].setIcon(knights[2].getIcon()); 
		tiles[knights[3].getRow()][knights[3].getColumn()].setIcon(knights[3].getIcon()); 
		
		// Rooks
		tiles[rooks[0].getRow()][rooks[0].getColumn()].setIcon(rooks[0].getIcon());  
		tiles[rooks[1].getRow()][rooks[1].getColumn()].setIcon(rooks[1].getIcon()); 
		tiles[rooks[2].getRow()][rooks[2].getColumn()].setIcon(rooks[2].getIcon()); 
		tiles[rooks[3].getRow()][rooks[3].getColumn()].setIcon(rooks[3].getIcon());
		
		// Bishops
		tiles[bishops[0].getRow()][bishops[0].getColumn()].setIcon(bishops[0].getIcon());  
		tiles[bishops[1].getRow()][bishops[1].getColumn()].setIcon(bishops[1].getIcon()); 
		tiles[bishops[2].getRow()][bishops[2].getColumn()].setIcon(bishops[2].getIcon()); 
		tiles[bishops[3].getRow()][bishops[3].getColumn()].setIcon(bishops[3].getIcon());
		
		// Queens
		tiles[queens[0].getRow()][queens[0].getColumn()].setIcon(queens[0].getIcon());  
		tiles[queens[1].getRow()][queens[1].getColumn()].setIcon(queens[1].getIcon());
		
		// Kings
		tiles[kings[0].getRow()][kings[0].getColumn()].setIcon(kings[0].getIcon());  
		tiles[kings[1].getRow()][kings[1].getColumn()].setIcon(kings[1].getIcon());
		
		// White pawns
		tiles[pawns[0].getRow()][pawns[0].getColumn()].setIcon(pawns[0].getIcon());
		tiles[pawns[1].getRow()][pawns[1].getColumn()].setIcon(pawns[1].getIcon());
		tiles[pawns[2].getRow()][pawns[2].getColumn()].setIcon(pawns[2].getIcon());
		tiles[pawns[3].getRow()][pawns[3].getColumn()].setIcon(pawns[3].getIcon());
		tiles[pawns[4].getRow()][pawns[4].getColumn()].setIcon(pawns[4].getIcon());
		tiles[pawns[5].getRow()][pawns[5].getColumn()].setIcon(pawns[5].getIcon());
		tiles[pawns[6].getRow()][pawns[6].getColumn()].setIcon(pawns[6].getIcon());
		tiles[pawns[7].getRow()][pawns[7].getColumn()].setIcon(pawns[7].getIcon());
		
		// Black pawns
		tiles[pawns[8].getRow()][pawns[8].getColumn()].setIcon(pawns[8].getIcon());
		tiles[pawns[9].getRow()][pawns[9].getColumn()].setIcon(pawns[9].getIcon());
		tiles[pawns[10].getRow()][pawns[10].getColumn()].setIcon(pawns[10].getIcon());
		tiles[pawns[11].getRow()][pawns[11].getColumn()].setIcon(pawns[11].getIcon());
		tiles[pawns[12].getRow()][pawns[12].getColumn()].setIcon(pawns[12].getIcon());
		tiles[pawns[13].getRow()][pawns[13].getColumn()].setIcon(pawns[13].getIcon());
		tiles[pawns[14].getRow()][pawns[14].getColumn()].setIcon(pawns[14].getIcon());
		tiles[pawns[15].getRow()][pawns[15].getColumn()].setIcon(pawns[15].getIcon());
		
		// Set board specifications
		space.setJMenuBar(menuBar);
        space.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		space.setSize(600, 600);
		space.setResizable(true);
		space.setLocationRelativeTo(null);  // centers window
		space.setVisible(visible);

		// Connect to the server
		if (network_play && !AI_AI)
		{
			thread = new Thread(this, "board");
			thread.start();
		}
	}
	
  
	/* This function connects to the server and to the other player */
	protected boolean connectToServer(String IP) throws InterruptedException, IOException
	{
		// connect to server
		s = new Socket(IP, 9998);

		// connect output stream and send message to server
		pr = new PrintWriter(s.getOutputStream());
		
		// connect input stream and wait for message from server
		in = new InputStreamReader(s.getInputStream());
		bf = new BufferedReader(in);
		// wait for welcome message from server
		server_msg = bf.readLine();
		System.out.println("server : " + server_msg);
		
		// wait for info message from server
		server_msg = bf.readLine();
		
		Scanner scan = new Scanner(server_msg);
		String command = scan.next();
		String time = scan.next();
		my_color = scan.next();
		
		if (command.equals("INFO") && (my_color.equals("white") || my_color.equals("black")) )
		{
			// send ready message to server
			pr.println("READY");
			pr.flush();
			
			// let user know how much time they have
			if (time.equals("0"))
			{
				JOptionPane.showMessageDialog(space, "No time limit for moves");				
			}
			else 
			{
				int move_time = Integer.parseInt(time) / 1000;
				JOptionPane.showMessageDialog(space, move_time + " seconds per move");
			}
		}
		else 
		{
			System.out.println("Error: bad welcome message from server");
			return false;
		}
		
		if (my_color.equals("white")) 
		{
			// wait for player 2 to connect
			JOptionPane.showMessageDialog(space, "You are white, move first");
		}
		else 
		{
			System.out.println("Player 1 connected");
			JOptionPane.showMessageDialog(space, "You are black, player 1 is connected, you move second");			
		}
		return true;
	}
	
	/* This function is called when someone clicks on one of the pieces, regardless of state.
	 * If no piece is selected, it will run processSelection() until a valid pieces is selected.
	 * If a piece is selected, it will move the piece. */
	private void processClick(int x, int y)
	{	
		if (select == false) {
			processSelection(x, y);
			return;
		}
        else processMove(x, y);

	}
	
	/* this function is called when a player selects a piece they want to move,
	 * if the location is empty, it will do nothing, if it is full, it will select 
	 * the piece and return, this will be the piece that is moved in the next click.
	 * Also, during network play, you can only select the piece that is your color */
	private int processSelection(int x, int y) 
	{	
		if (grid[x][y].isEmpty()) 
		{	
			return -1;
		}
		else if (network_play && grid[x][y].getPiece().getColor().equals(my_color))
		{
			selected = grid[x][y].getPiece();
			select = true;
			return 0;
		}
		else if (!network_play)
		{
			selected = grid[x][y].getPiece();
			select = true;
			return 0;
		}
		else return -1;
	}
	
	/* This function checks the location that the player is trying to move their piece to
	 * it returns true if the location is either empty, or an enemy */
	private boolean checkMoveLocation(int x, int y)
	{
		if (grid[x][y].isEmpty())
		{
			return true;
		}
		else if (grid[x][y].getPiece().getColor() == turn)
		{
			return false;
		}
		else if (selected.isValidMove(x, y) && selected.getColor().equals(turn))
		{
			grid[x][y].getPiece().setIsAlive(false); // kill piece
			grid[x][y].setEmpty(true); // empty space
			
			// remove piece from the vector of pieces
			if (grid[x][y].getPiece().getColor().equals("white"))
			{
				for (int i = 0; i < white_pieces.size(); i++)
				{
					if (white_pieces.get(i).getName() == grid[x][y].getPiece().getName() && white_pieces.get(i).getID() == grid[x][y].getPiece().getID())
					{
						white_pieces.remove(i);
					}
				}
			}
			else 
			{
				for (int i = 0; i < black_pieces.size(); i++)
				{
					if (black_pieces.get(i).getName() == grid[x][y].getPiece().getName() && black_pieces.get(i).getID() == grid[x][y].getPiece().getID())
					{
						black_pieces.remove(i);
					}
				}
			}
			
			return true;
		}
		else return false;
	}
	
	// This function checks the validity of the move for the purpose of the server
	public boolean serverCheck(String move)
	{
		String from = "";
		String to = "";
		int from_row = 0;
		int from_column = 0;
		int to_row = 0;
		int to_column = 0;
		
		if (move.length() != 5) 
		{
			System.out.println("Error: Incorrect move message sent from server");
			return false;
		}
		else 
		{
			// parse
			from = move.substring(0, 2);
			to = move.substring(3, 5);
		}
		
		// Convert rows
		int row_digit_1 = from.charAt(1) - '0';
		from_row = 8 - row_digit_1;
		int row_digit_2 = to.charAt(1) - '0';
		to_row = 8 - row_digit_2;
		 
		// Convert the columns
		switch (from.charAt(0)) {
		case 'A':
			from_column = 0;
			break;
		case 'B':
			from_column = 1;
			break;
		case 'C':
			from_column = 2;
			break;
		case 'D':
			from_column = 3;
			break;
		case 'E':
			from_column = 4;
			break;
		case 'F':
			from_column = 5;
			break;
		case 'G':
			from_column = 6;
			break;
		case 'H':
			from_column = 7;
			break;
		default:
			System.out.println("Error: wrong move message from server");
		}

		switch (to.charAt(0)) {
		case 'A':
			to_column = 0;
			break;
		case 'B':
			to_column = 1;
			break;
		case 'C':
			to_column = 2;
			break;
		case 'D':
			to_column = 3;
			break;
		case 'E':
			to_column = 4;
			break;
		case 'F':
			to_column = 5;
			break;
		case 'G':
			to_column = 6;
			break;
		case 'H':
			to_column = 7;
			break;
		default:
			System.out.println("Error: wrong move message from server");
		}		
		
		if (!grid[from_row][from_column].isEmpty())
		{
			selected = grid[from_row][from_column].getPiece();
			processMove(to_row, to_column);
			return true;
		}
		else return false;
	}
	
	/* This is the move piece function, it first updates the grid, then checks if the desired move 
	 * path is valid. Then, it checks the location of the move to be sure that it can move there.
	 * After the move, it checks for pawn promotion conditions, check & checkmate conditions, and 
	 * switches the turn. */
	public void processMove(int x, int y) 
	{
		boolean moved = false;
		
      	selected.updateGrid(grid);
		if (selected.isValidMove(x, y) && checkMoveLocation(x, y) && selected.getColor().equals(turn))
		{
			// move piece
			row_moved_from = selected.getRow();
			column_moved_from = selected.getColumn();
			tiles[selected.getRow()][selected.getColumn()].setIcon(null);
			tiles[x][y].setIcon(selected.getIcon());
			grid[selected.getRow()][selected.getColumn()].setPiece(null);
			grid[selected.getRow()][selected.getColumn()].setEmpty(true);
			selected.setRow(x);
			selected.setColumn(y);
			grid[x][y].setPiece(selected);
			grid[x][y].setEmpty(false);
			moved = true;
			// Pawn promotion
			if (selected.getName().equals("pawn") && (x == 0 || x == 7))
			{
				queens[numOfQueens] = new Queen(grid, selected.getColor(), "queen", x, y, numOfQueens, true);
				tiles[x][y].setIcon(null);
				tiles[x][y].setIcon(queens[numOfQueens].getIcon());
				grid[x][y].getPiece().setIsAlive(false);
				grid[x][y].setPiece(queens[numOfQueens]);
				
				numOfQueens++;
				
				// add to vector of pieces
				if (selected.getColor().equals("white"))
				{
					white_pieces.addElement(queens[numOfQueens - 1]);
				}
				else 
				{
					black_pieces.addElement(queens[numOfQueens - 1]);
				}
			}
		} 
	        	

		if (moved)
		{
			// run isCheck & isCheckMate
			if (kings[0].getColor() != turn)
			{
				if (kings[0].isCheck())
				{
					if (kings[0].isCheckMate())
					{
						// game over
						gameOver(kings[0].getColor());
					} else
					{
						// warn the other team that they are in check
						JOptionPane.showMessageDialog(space, "White King is in check!");
					}
				}
			} else if (kings[1].getColor() != turn)
			{
				if (kings[1].isCheck())
				{
					if (kings[1].isCheckMate())
					{
						// game over
						gameOver(kings[1].getColor());
					} else
					{
						// warn the other team that they are in check
						JOptionPane.showMessageDialog(space, "Black King is in check!");
					}
				}
			}
			
			// change turns
			if (turn.equals("white")) turn = "black";
			else turn = "white";
			
			// if playing over network, send move to server
			if (network_play && !turn.equals(my_color))
			{
				sendMove(x, y);
			}
	
		}
		select = false;
	}
	
	private void gameOver(String winner) 
	{
		JOptionPane.showMessageDialog(space, winner + "is the winner!");
	}
	
	public void sendMove(int x, int y) 
	{
		String svr_msg = "";
		String from, to, move_msg;
		int from_row, to_row;
		String from_column, to_column;
		from_column = null;
		to_column = null;
		
		// Convert the rows
		from_row = 8 - row_moved_from;
		to_row = 8 - x;
		
		// Convert the columns
		switch (column_moved_from) {
		case 0:
			from_column = "A";
			break;
		case 1:
			from_column = "B";
			break;
		case 2:
			from_column = "C";
			break;
		case 3:
			from_column = "D";
			break;
		case 4:
			from_column = "E";
			break;
		case 5:
			from_column = "F";
			break;
		case 6:
			from_column = "G";
			break;
		case 7:
			from_column = "H";
			break;
		default:
			// code block
		}

		switch (y) {
		case 0:
			to_column = "A";
			break;
		case 1:
			to_column = "B";
			break;
		case 2:
			to_column = "C";
			break;
		case 3:
			to_column = "D";
			break;
		case 4:
			to_column = "E";
			break;
		case 5:
			to_column = "F";
			break;
		case 6:
			to_column = "G";
			break;
		case 7:
			to_column = "H";
			break;
		default:
			// code block
		}
		
		// concatenate & build message
		from = from_column.concat(Integer.toString(from_row));
		to = to_column.concat(Integer.toString(to_row));
		from = from.concat(" ");
		move_msg = from.concat(to);
		
		// send move to server
		pr.println(move_msg);
		pr.flush();

	}
	
	protected boolean getMove() 
	{
		// get move from server
		String move_msg = null;
		int from_row = -1;
		int to_row = -1;
		int from_column = -1;
		int to_column = -1;
		
		try
		{
			move_msg = bf.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Recieved: " + move_msg);
		
		if (move_msg.equals("OK"))
		{
			try
			{
				move_msg = bf.readLine();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Recieved: " + move_msg);
			if (move_msg.equals("WINNER"))
			{
				JOptionPane.showMessageDialog(space, "You win!!");
			}
		}
		else if (move_msg.equals("TIME"))
		{
			JOptionPane.showMessageDialog(space, "Time violation, you lose");
		}
		
		else if (move_msg.equals("WINNER"))
		{
			JOptionPane.showMessageDialog(space, "You Won!!");
		}
		
		else if (move_msg.equals("ILLEGAL"))
		{
			JOptionPane.showMessageDialog(space, "Illegal move, you lose");
		}	
		
		String from, to;
		
		if (move_msg.length() != 5) 
		{
			System.out.println("Error: Incorrect move message sent from server");
			return false;
		}
		else 
		{
			// parse
			from = move_msg.substring(0, 2);
			to = move_msg.substring(3, 5);
		}
		
		// Convert rows
		int row_digit_1 = from.charAt(1) - '0';
		from_row = 8 - row_digit_1;
		int row_digit_2 = to.charAt(1) - '0';
		to_row = 8 - row_digit_2;
		 
		// Convert the columns
		switch (from.charAt(0)) {
		case 'A':
			from_column = 0;
			break;
		case 'B':
			from_column = 1;
			break;
		case 'C':
			from_column = 2;
			break;
		case 'D':
			from_column = 3;
			break;
		case 'E':
			from_column = 4;
			break;
		case 'F':
			from_column = 5;
			break;
		case 'G':
			from_column = 6;
			break;
		case 'H':
			from_column = 7;
			break;
		default:
			System.out.println("Error: wrong move message from server");
		}

		switch (to.charAt(0)) {
		case 'A':
			to_column = 0;
			break;
		case 'B':
			to_column = 1;
			break;
		case 'C':
			to_column = 2;
			break;
		case 'D':
			to_column = 3;
			break;
		case 'E':
			to_column = 4;
			break;
		case 'F':
			to_column = 5;
			break;
		case 'G':
			to_column = 6;
			break;
		case 'H':
			to_column = 7;
			break;
		default:
			System.out.println("Error: wrong move message from server");
		}		
		
		if (!grid[from_row][from_column].isEmpty())
		{
			selected = grid[from_row][from_column].getPiece();
			processMove(to_row, to_column);
			return true;
		}
		else return false;
	}
	
	/* This is the listener for the button clicks, this function 
	 */
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

	@Override
	public void run() 
	{
		try
		{
			if(connectToServer(IP)) System.out.println("Succesfully connected to server and other player");
		} catch (InterruptedException | IOException e)
		{
			e.printStackTrace();
		}
			
		if (my_color.equals("black"))
		{
			// wait for white to move first
			boolean first_move = getMove(); 
			if (first_move)
			{
				System.out.println("White moved");
			}
		}
		while (true) 
		{
				getMove();
		}
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
	public AI getAI()
	{
		return ai;
	}
}
	
