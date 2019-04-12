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
        //frame
        JFrame frame = new JFrame("Chess");
        frame.setLayout(null);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // center in the screen
        
        //background image
        ImageIcon bg_img = new ImageIcon("gradient.png");
        JLabel background = new JLabel("", bg_img, JLabel.CENTER);
        background.setBounds(0,0,800,800);
        frame.add(background);
        //background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        
        //transparent panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(450,350);
        panel.setBackground(new Color(0,0,0,60));
        panel.setBounds(175,175,450,350);
        background.add(panel);
        
        JLabel title = new JLabel("Chess");
        title.setFont(new Font("Serif", Font.BOLD, 60));
        //title.setForeground(Color.white);
        title.setBounds(150,7,450,70);
        panel.add(title);
        
        JLabel show_image = new JLabel("");
        show_image.setIcon(new ImageIcon("image1.png"));
        show_image.setBounds(160,75,150,150);
        panel.add(show_image);
        
        JLabel label1 = new JLabel("Team 27");
        label1.setFont(new Font("Serif", Font.BOLD, 25));
        //label1.setForeground(Color.white);
        label1.setBounds(185,230,450,30);
        panel.add(label1);
        
        JLabel label2 = new JLabel("Lucas Crockett, Grant Singleton, and");
        label2.setFont(new Font(Font.SERIF, Font.PLAIN, 13));
        //label2.setForeground(Color.white);
        label2.setBounds(132,254,450,20);
        panel.add(label2);
        
        JLabel label3 = new JLabel("Lily Nguyen, Joshua Anderson");
        label3.setFont(new Font(Font.SERIF, Font.PLAIN, 13));
        //label3.setForeground(Color.white);
        label3.setBounds(150,267,450,20);
        panel.add(label3);

        JButton button_1 = new JButton("Start Game");
        //button_1.setBackground(new Color(160,182,45));
        button_1.setBounds(183,290,100,40);
        
        panel.add(button_1);
        button_1.addActionListener(new ActionListener() {         //Clicking on button will switch screens
            @Override
            public void actionPerformed(ActionEvent e) {
                new InitializationMenu();
                frame.dispose();
            }
        });
        
        frame.setVisible(true);
    }
    
}
