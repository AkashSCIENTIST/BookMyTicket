package uselessFiles;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

class Mainframe extends JFrame
{
    JPanel MainPanel,MainPanel0,MainPanel1,MainPanel2,MainPanel3,MainPanel4;
    Mainframe()
    {
        setTitle("MainFrame");
        JLabel welcome = new JLabel("                                        Ticket Reservation (Mini Project)");
        JLabel groupmembers = new JLabel("           Created By:                                                                                                               ");
        JLabel akash = new JLabel("           Akash S P (20I306)");
        JLabel srivathsan = new JLabel("           B Srivathsan (20I312)");
        JLabel MAswir = new JLabel("           A Mohammed Aswir (20I302)");
        JLabel atheeithan = new JLabel("           Atheeithan T A (20I311)");
        JLabel logesh = new JLabel("           Logeshwaran C (20I329)");
        JLabel empty1 = new JLabel("                                  ");
        JLabel empty2 = new JLabel("                                  ");
        JLabel empty3 = new JLabel("                                  ");
        JLabel empty4 = new JLabel("                                  ");
        JLabel empty5 = new JLabel("                                  ");
        JLabel empty6 = new JLabel("                                  ");
        JLabel empty7 = new JLabel("                                  ");
        JLabel empty8 = new JLabel("           WELCOME");
        JLabel empty9 = new JLabel(" ");
        MainPanel0 = new JPanel();
        MainPanel0.setLayout(new GridLayout(2,1));
        MainPanel0.add(welcome);
        MainPanel0.add(empty7);
        GridLayout y =new GridLayout(8,1);
        y.setVgap(0);
        MainPanel1 = new JPanel();
        MainPanel1.setLayout(y);
        MainPanel1.add(empty8);
        MainPanel1.add(groupmembers);
        MainPanel1.add(MAswir);
        MainPanel1.add(akash);
        MainPanel1.add(atheeithan);
        MainPanel1.add(srivathsan);
        MainPanel1.add(logesh);
        MainPanel1.add(empty9);
        MainPanel1.setVisible(true);
        GridLayout x =new GridLayout(3,1);
        x.setVgap(30);
        MainPanel2 = new JPanel();
        MainPanel2.setLayout(x);
        JButton tbooking = new JButton("Ticket Booking");
        JButton tcancelling = new JButton("Ticket Cancelling");
        JButton tQuery = new JButton("Ticket Query");
        tbooking.setForeground(Color.white);
        tcancelling.setForeground(Color.white);
        tQuery.setForeground(Color.BLACK);
        tbooking.setBackground(Color.BLUE);
        tcancelling.setBackground(Color.RED);
        tQuery.setBackground(Color.GREEN);
        MainPanel2.add(tbooking);
        MainPanel2.add(tcancelling);
        MainPanel2.add(tQuery);
        MainPanel3 = new JPanel();
        MainPanel3.setLayout(new GridLayout(3,1));
        MainPanel3.add(empty1);
        MainPanel3.add(empty2);
        MainPanel3.add(empty3);
        MainPanel4 = new JPanel();
        MainPanel4.setLayout(new GridLayout(3,1));
        MainPanel4.add(empty4);
        MainPanel4.add(empty5);
        MainPanel4.add(empty6);
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());
        MainPanel.setVisible(true);
        MainPanel.add(MainPanel0,BorderLayout.NORTH);
        MainPanel.add(MainPanel1,BorderLayout.WEST);
        MainPanel.add(MainPanel2,BorderLayout.CENTER);
        MainPanel.add(MainPanel3,BorderLayout.SOUTH);
        MainPanel.add(MainPanel4,BorderLayout.EAST);
        add(MainPanel);
        setSize(4000,2000);
        setVisible(true);

        welcome.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty1.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty2.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty3.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty4.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty5.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty6.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty7.setFont(new Font("SansSerif", Font.BOLD, 50));
        empty8.setFont(new Font("SansSerif", Font.BOLD, 45));
        empty9.setFont(new Font("SansSerif", Font.BOLD, 200));
        groupmembers.setFont(new Font("Verdana", Font.BOLD, 18));
        akash.setFont(new Font("Verdana", Font.BOLD, 18));
        srivathsan.setFont(new Font("Verdana", Font.BOLD, 18));
        atheeithan.setFont(new Font("Verdana", Font.BOLD, 18));
        MAswir.setFont(new Font("Verdana", Font.BOLD, 18));
        logesh.setFont(new Font("Verdana", Font.BOLD, 18));
        tbooking.setFont(new Font("Verdana", Font.BOLD, 40));
        tcancelling.setFont(new Font("Verdana", Font.BOLD, 40));
        tQuery.setFont(new Font("Verdana", Font.BOLD, 40));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args)
    {
        new Mainframe();
    }
}