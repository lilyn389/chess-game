

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
		
		board chess_gui = new board(false, true, false, "white", "easy","localhost");
		MiniMaxAlphaBeta a = new MiniMaxAlphaBeta(chess_gui,"white",chess_gui.getAI().maxDepth);
		Move b = a.MM();
		
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
