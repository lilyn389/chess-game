import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class InitializationMenu implements ActionListener {
    JFrame f = new JFrame("Chess");
    JPanel cards;
    JButton begin;
    JRadioButton color_1;
    JRadioButton color_2;
    JRadioButton player_1;
    JRadioButton player_2;
    JRadioButton player_3;
    JRadioButton level_1;
    JRadioButton level_2;
    JRadioButton level_3;
    JRadioButton get_ip;
    board chess_gui;
    String AIColor;
    String AI_difficulty;
    String IP;
    boolean network_play;
    boolean AI_play;
    boolean AI_AI;
boolean visible = true;
    
    InitializationMenu() {
    	
    	// initialize variables to false and null values
    	AIColor = "";
    	AI_difficulty = "";
    	network_play = false;
    	AI_play = false;
    	AI_AI = false;
    	IP = "localhost";
    	
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
        begin = new JButton("Begin");
        player_1.addActionListener(this);
        player_2.addActionListener(this);
        player_3.addActionListener(this);
        begin.addActionListener(this);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(player_1);
        bg2.add(player_2);
        bg2.add(player_3);
        
        JPanel card1 = new JPanel();
        card1.add(player_1);
        card1.add(player_2);
        card1.add(player_3);
        
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
			IP = JOptionPane.showInputDialog("Enter IP Address");
			cl.show(cards, "MyPanel2");
		}
		if (e.getSource() == player_2) // player_2 = Play computer
		{
			AI_play = true;
			network_play = true;
			IP = JOptionPane.showInputDialog("Enter IP Address");
			cl.show(cards, "MyPanel0");
		}
		if (e.getSource() == player_3) // player_3 = Play human
		{
			network_play = true;
			IP = JOptionPane.showInputDialog("Enter IP Address");
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
					chess_gui_ai = new AIBoard(AI_AI, AI_play, network_play, AIColor, AI_difficulty, IP,false);
				}
				else
				{
					chess_gui = new board(AI_AI, AI_play, network_play, AIColor, AI_difficulty, IP,visible);
				}
			} catch (InterruptedException | IOException e1)
			{
				e1.printStackTrace();
			}
			chess_gui.getKings()[0].setGrid(chess_gui.getGrid());
			chess_gui.getKings()[1].setGrid(chess_gui.getGrid());
			f.dispose();
		}
	}

}
