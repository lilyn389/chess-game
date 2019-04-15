

import java.io.IOException;

import javax.swing.JFrame;

public class testMiniMax{
	//AI_AI, AI_play, network_play, AIColor, AI_difficulty, IP
	testMiniMax()
	{
		
	}
	void doStuff()
	{
	try
	{
		Tile c = new Tile(new Pawn(),false);
		Tile d = (Tile)c.clone();
		Tile e = c;
		c.setEmpty(true);
		c.setPiece(new Pawn());
		c.getPiece().setColumn(2000);
		board chess_gui = new board(false, true, false, "white", "easy","localhost",true);
		MiniMaxAlphaBeta a = new MiniMaxAlphaBeta(chess_gui,"white",chess_gui.getAI().maxDepth);
		Move b = a.MM();
		/*Piece p = chess_gui.grid[b.getX1()][b.getY1()].getPiece();
		int x = chess_gui.grid[b.getX1()][b.getY1()].getPiece().getRow();
		int y = chess_gui.grid[b.getX1()][b.getY1()].getPiece().getColumn();
		chess_gui.tiles[x][y].setIcon(null);
		chess_gui.tiles[x][y].setIcon(p.getIcon());
		chess_gui.grid[p.getRow()][p.getColumn()].setPiece(null);
		chess_gui.grid[p.getRow()][p.getColumn()].setEmpty(true);
		p.setRow(b.getX2());
		p.setColumn(b.getY2());
		chess_gui.grid[b.getX1()][b.getY2()].setEmpty(false);*/
		//processSelected(b.getX1(),b.getY1());
		Move f = a.MM();
	} catch (InterruptedException | IOException e1)
	{
		e1.printStackTrace();
	}
	}
	//board chess_gui = new board(false, true, false, "white", "easy","localhost");
	//chess_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//MiniMaxAlphaBeta tree = new MiniMaxAlphaBeta(chess_gui,"white",3);
	//Move a = tree.MM();
}
