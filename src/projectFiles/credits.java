package projectFiles;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

class CreditPanel extends JPanel 
{
    JLabel t;
    CreditPanel(String s) 
    {
        t= new JLabel(s);
        Font y = new Font("SansSerif", Font.BOLD, 30);
        this.setBackground(new Color(178, 214, 41));
        t.setFont(y);
        add(t);
    }
}
public class credits extends JFrame
{
    JPanel panel1,panel2;
    public credits(frameManager manager)
    {
//    	this.setIconImage();
//    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image image= Toolkit.getDefaultToolkit().getImage("<IMAGE_FOLDER>\\icon.png");  
        this.setIconImage(image);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(178, 214, 41));
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent E) {
        		new FeedBack();
        	}
		});
        setLayout(new GridLayout(2,1));

        setSize(2000,2000);
        setTitle("Thank You");

        panel1 = new JPanel(new GridLayout(1,1));

        panel1.setBackground(new Color(178, 214, 41));

        JLabel thankyou = new JLabel(new ImageIcon("<IMAGE_FOLDER>\\thankyou.jpg"));
        panel1.add(thankyou);

        panel2 = new JPanel();
        panel2.setBackground(new Color(178, 214, 41));

        BoxLayout layout = new BoxLayout(panel2,BoxLayout.Y_AXIS);

        panel2.setLayout(layout);

        CreditPanel credit2 = new CreditPanel("Done By:");
        credit2.setMaximumSize(new Dimension(Integer.MAX_VALUE, credit2.getMinimumSize().height));
        panel2.add(credit2);

        CreditPanel credit3 = new CreditPanel("A Mohammed Aswir");
        credit3.setMaximumSize(new Dimension(Integer.MAX_VALUE, credit3.getMinimumSize().height));
        panel2.add(credit3);

        CreditPanel credit4 = new CreditPanel("Akash S P");
        credit4.setMaximumSize(new Dimension(Integer.MAX_VALUE, credit4.getMinimumSize().height));
        panel2.add(credit4);

        CreditPanel credit5 = new CreditPanel("Atheeithan T A");
        credit5.setMaximumSize(new Dimension(Integer.MAX_VALUE, credit5.getMinimumSize().height));
        panel2.add(credit5);

        CreditPanel credit6 = new CreditPanel("B Srivathsan");
        credit6.setMaximumSize(new Dimension(Integer.MAX_VALUE, credit6.getMinimumSize().height));
        panel2.add(credit6);

        CreditPanel credit7 = new CreditPanel("Logeshwaran C");
        credit7.setMaximumSize(new Dimension(Integer.MAX_VALUE, credit7.getMinimumSize().height));
        panel2.add(credit7);

        add(panel1);
        add(panel2);

        setVisible(true);   
    }
    public static void main(String args[]) 
    {
//        new credits();
    }
}
