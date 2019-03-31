//
//  main.cpp
//  Project2
//
//  Created by Oanh Nguyen on 3/26/19.
//  Copyright © 2019 Oanh Nguyen. All rights reserved.
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame{
    Menu() {
        JFrame frame = new JFrame("Chess");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon bg_img = new ImageIcon("gradient.png");
        JLabel background = new JLabel(bg_img);
        frame.add(background);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Chess");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        background.add(title);
        
        JLabel label1 = new JLabel("Team 27");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        background.add(label1);
        
        JLabel label2 = new JLabel("Lucas Crockett, Grant Singleton, and");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font(Font.SERIF, Font.PLAIN, 13));
        background.add(label2);
        
        JLabel label3 = new JLabel("Lily Nguyen, Joshua Anderson");
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setFont(new Font(Font.SERIF, Font.PLAIN, 13));
        background.add(label3);
        
        JButton button_1 = new JButton("Start Game");
        button_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(button_1);
        button_1.addActionListener(new ActionListener() {         //Clicking on button will switch screens
            @Override
            public void actionPerformed(ActionEvent e) {
                new InitializationMenu();
                frame.dispose();
            }
        });
        
        //Display the window.
        frame.setSize(800,800);
        frame.setVisible(true);
    }
    
}
