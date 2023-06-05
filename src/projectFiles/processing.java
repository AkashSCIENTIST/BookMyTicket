package projectFiles;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

@SuppressWarnings("serial")
class DemoPanel extends JPanel 
{
    JLabel t;
    private static final Color bg = new Color(216, 245, 211);
    DemoPanel(String s) 
    {
        t= new JLabel(s);
        Font y = new Font("SansSerif", Font.BOLD, 50);
        this.setBackground(bg);
        t.setFont(y);
        add(t);
    }
}

@SuppressWarnings("serial")
public class processing extends JFrame 
{
	private static final Color bg = new Color(216, 245, 211);
    
	processing(frameManager manager)
    {
    	manager.setFrameIcon(this);
        Image image= Toolkit.getDefaultToolkit().getImage("src\\indrail.jpg");  
        this.setIconImage(image);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(bg);
        setSize(800,800);
        setTitle("Processing Page");

        setLayout(new GridBagLayout());

        GridBagConstraints  g = new GridBagConstraints();

        DemoPanel processing = new DemoPanel("Ticket Processing ...");
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 0;
        g.ipadx = 100;
        g.ipady = 20;
        add(processing,g);

        JLabel Buffering = new JLabel(new ImageIcon("src\\buffering.gif"));
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 10;
        add(Buffering,g);

        setVisible(true);   
    }
    public static void main(String args[]) 
    {
        new processing();
    }
}