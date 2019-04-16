import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Timer extends JFrame{
    Timer(String t) {
        JFrame frame = new JFrame("Chess Timer");
        frame.setLayout(null);
        frame.setSize(300,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        long seconds = Long.parseLong(t) / 1000;
        
        JPanel container = new JPanel();
        //lblTime.setHorizontalAlignment(SwingsConstants.CENTER);
        frame.setContentPane(container);
        JLabel lblTime = new JLabel(Long.toString(seconds) + " seconds left");
        container.add(lblTime);
        frame.setVisible(true);
        
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + (seconds * 1000);
        long temp = startTime + 1000 ;
        while (startTime < endTime) {
            if (startTime == temp) {
                seconds--;
                lblTime.setText(Long.toString(seconds) + " seconds left");
                temp += 1000;
            }
            startTime = System.currentTimeMillis();
        }
        System.out.println("TIME");
        System.exit(0);
    }
    
    public static void main(String[] args) {
        new Timer(args[0]);
    }
}
