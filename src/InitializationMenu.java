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
    JRadioButton color_3;
    JRadioButton player_1;
    JRadioButton player_2;
    JRadioButton level_1;
    JRadioButton level_2;
    JRadioButton level_3;
    board chess_gui;
    
    InitializationMenu() {
        cards = new JPanel(new CardLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        color_1 = new JRadioButton("Black");
        color_2 = new JRadioButton("White");
        color_3 = new JRadioButton("Random");
        color_1.addActionListener(this);
        color_2.addActionListener(this);
        color_3.addActionListener(this);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(color_1);
        bg.add(color_2);
        bg.add(color_3);
        
        JPanel card0 = new JPanel();
        card0.add(color_1);
        card0.add(color_2);
        card0.add(color_3);
     
        player_1 = new JRadioButton("Human");
        player_2 = new JRadioButton("AI");
        begin = new JButton("Begin");
        player_1.addActionListener(this);
        player_2.addActionListener(this);
        begin.addActionListener(this);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(player_1);
        bg2.add(player_2);
        
        JPanel card1 = new JPanel();
        card1.add(player_1);
        card1.add(player_2);
        
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
  
        cards.add(card0, "MyPanel0");
        cards.add(card1, "MyPanel1");
        cards.add(card2, "MyPanel2");
        cards.add(card3, "MyPanel3");

        f.add(cards);
        f.setSize(800,800);
        f.setVisible(true);
        f.setLocationRelativeTo(null);  // center in screen
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        if (e.getSource() == color_1 || e.getSource() == color_2 || e.getSource() == color_3) {
            cl.show(cards, "MyPanel1");
        }
        if (e.getSource() == player_1)
            cl.show(cards, "MyPanel2");
        if (e.getSource() == begin) {
			try {
				chess_gui = new board();
			} catch (InterruptedException | IOException e1) {
				e1.printStackTrace();
			}
            chess_gui.getKings()[0].setGrid(chess_gui.getGrid());
    		chess_gui.getKings()[1].setGrid(chess_gui.getGrid());
            f.dispose();
        }
        if (e.getSource() == player_2)
            cl.show(cards, "MyPanel3");
        if (e.getSource() == level_1 || e.getSource() == level_2 || e.getSource() == level_3)
            cl.show(cards, "MyPanel2");
    }

}
