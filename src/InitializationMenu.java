import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class InitializationMenu implements ActionListener, Runnable {
    private JFrame f = new JFrame("Chess");
    private JPanel cards;
    private JButton begin;
    private JRadioButton color_1;
    private JRadioButton color_2;
    private JRadioButton player_1;
    private JRadioButton player_2;
    private JRadioButton player_3;
    private JRadioButton player_4;
    private JRadioButton level_1;
    private JRadioButton level_2;
    private JRadioButton level_3;
    private JRadioButton get_ip;
    private board chess_gui;
    private AIBoard chess_gui_ai;
    private String AIColor;
    private String AI_difficulty;
    private String IP;
    private boolean network_play;
    private boolean AI_play;
    private boolean AI_AI;
    private boolean visible;
    private Thread server_thread;
    private ChessServer server;
    
    InitializationMenu() {
    	
    	// initialize variables to false and null values
    	AIColor = "black";
    	AI_difficulty = "medium";
    	network_play = false;
    	AI_play = false;
    	AI_AI = true;
    	IP = "localhost";
    	visible = true;
    	
        cards = new JPanel(new CardLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        color_1 = new JRadioButton("Black");
        color_2 = new JRadioButton("White");
        color_1.addActionListener(this);
        color_2.addActionListener(this);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(color_1);
        bg.add(color_2);
        
        JPanel card0 = new JPanel();
        card0.add(color_1);
        card0.add(color_2);
     
        player_1 = new JRadioButton("Computer vs. Computer");
        player_2 = new JRadioButton("Play the Computer");
        player_3 = new JRadioButton("Play a Human");
        player_4 = new JRadioButton("Local Game");
        begin = new JButton("Begin");
        player_1.addActionListener(this);
        player_2.addActionListener(this);
        player_3.addActionListener(this);
        player_4.addActionListener(this);
        begin.addActionListener(this);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(player_1);
        bg2.add(player_2);
        bg2.add(player_3);
        bg2.add(player_4);
        
        JPanel card1 = new JPanel();
        card1.add(player_1);
        card1.add(player_2);
        card1.add(player_3);
        card1.add(player_4);
        
        JPanel card2 = new JPanel();
        card2.add(begin);
        
        level_1 = new JRadioButton("Easy");
        level_2 = new JRadioButton("Medium");
        level_3 = new JRadioButton("Hard");
        level_1.addActionListener(this);
        level_2.addActionListener(this);
        level_3.addActionListener(this);
        
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(level_1);
        bg3.add(level_2);
        bg3.add(level_3);
        
        JPanel card3 = new JPanel();
        card3.add(level_1);
        card3.add(level_2);
        card3.add(level_3);
  
        cards.add(card1, "MyPanel1");
        cards.add(card0, "MyPanel0");
        cards.add(card2, "MyPanel2");
        cards.add(card3, "MyPanel3");

        f.add(cards);
        f.setSize(800,800);
        f.setVisible(true);
        f.setLocationRelativeTo(null);  // center in screen
    }
    
    @Override
	public void actionPerformed(ActionEvent e)
	{
		CardLayout cl = (CardLayout) (cards.getLayout());
		
		// choose mode of play
		if (e.getSource() == player_1) // player_1 = comp vs. comp,
		{
			AI_AI = true;
			network_play = true;
			AI_play = false;
			int reply = JOptionPane.showConfirmDialog(null, "Would you like to host the server?", "Server", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION)
			{
				server_thread = new Thread(this, "server");
				server_thread.start();
			}
			else 
			{
				IP = JOptionPane.showInputDialog("Enter IP Address");
			}
			cl.show(cards, "MyPanel2");
		}
		if (e.getSource() == player_2) // player_2 = Play computer
		{
			AI_play = true;
			network_play = false;
			AI_AI = false;
			visible = true;
			cl.show(cards, "MyPanel0");
		}
		if (e.getSource() == player_3) // player_3 = Play human
		{
			AI_play = false;
			AI_AI = false;
			network_play = true;
			int reply = JOptionPane.showConfirmDialog(null, "Would you like to host the server?", "Server", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION)
			{
				server_thread = new Thread(this, "server");
				server_thread.start();
			}
			else 
			{
				IP = JOptionPane.showInputDialog("Enter IP Address");
			}
			cl.show(cards, "MyPanel2");
		}
		if (e.getSource() == player_4) // player_3 = Play local game
		{
			AI_play = false;
			AI_AI = false;
			network_play = false;
			cl.show(cards, "MyPanel2");
		}
		
		// choose piece color (only in "Play computer" mode)
		if (e.getSource() == color_1 || e.getSource() == color_2) // color1 = black, color2 = white
		{ 
			if (e.getSource() == color_1)
			{
				AIColor = "white";
				cl.show(cards, "MyPanel3");
			}
			else if (e.getSource() == color_2)
			{
				AIColor = "black";
				cl.show(cards, "MyPanel3");
			}
			else
			{
				AIColor = "black";
				cl.show(cards, "MyPanel3");
			}
		}
		
		// set difficulty level (only in "Play computer" mode)
		if (e.getSource() == level_1 || e.getSource() == level_2 || e.getSource() == level_3) 
		{
			if (e.getSource() == level_1)
			{
				AI_difficulty = "easy";
				cl.show(cards, "MyPanel2");
			}
			else if (e.getSource() == level_2)
			{
				AI_difficulty = "medium";
				cl.show(cards, "MyPanel2");
			}
			else 
			{
				AI_difficulty = "hard";
				cl.show(cards, "MyPanel2");
			}
		}
		
		//begin the game
		if (e.getSource() == begin)
		{
			try
			{
				if(AI_AI)
				{
					chess_gui_ai = new AIBoard(AI_AI, AI_play, network_play, AIColor, AI_difficulty, IP,visible);
				}
				else if (AI_play)
				{
					chess_gui_ai = new AIBoard(AI_AI, AI_play, network_play, AIColor, AI_difficulty, IP,visible);
				}
				else
				{
					chess_gui = new board(AI_AI, AI_play, network_play, AIColor, AI_difficulty, IP,visible);
				}
			} catch (InterruptedException | IOException e1)
			{
				e1.printStackTrace();
			}
			if(AI_AI)
			{
				//chess_gui_ai.getKings()[0].setGrid(chess_gui.getGrid());
				//chess_gui_ai.getKings()[1].setGrid(chess_gui.getGrid());
				f.dispose();
			}
			else
			{
				//chess_gui.getKings()[0].setGrid(chess_gui.getGrid());
				//chess_gui.getKings()[1].setGrid(chess_gui.getGrid());
				f.dispose();
			}
			
		}
	}

	@Override
	public void run()
	{
		try
		{
			server = new ChessServer();
		} catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
