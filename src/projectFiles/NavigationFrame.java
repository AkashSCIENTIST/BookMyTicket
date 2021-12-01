package projectFiles;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.border.Border;

import databaseTools.databaseManager;
//import filesToFix.DisplayDatabaseTable;

import java.io.*;
import javax.imageio.ImageIO;

public class NavigationFrame extends JFrame {
    JPanel MainPanel,MainPanel0,MainPanel1;
    
    NavigationFrame(frameManager manager)
    {
    	
        Image image = Toolkit.getDefaultToolkit().getImage("<IMAGE_FOLDER>\\bg_train.png"); 
        Image imagebg = Toolkit.getDefaultToolkit().getImage("<IMAGE_FOLDER>\\bg_train.png");  
        this.setIconImage(image);
        setContentPane(new JLabel(new ImageIcon(imagebg)));
        setSize(2000,2000);
        setTitle("BookMyTicket Application");
        JLabel welcome = new JLabel("Welcome To",SwingConstants.CENTER);
        welcome.setFont(new Font("Vani", Font.BOLD, 35));
        welcome.setForeground(new Color(255,255,255));
        JLabel title = new JLabel("BookMyTicket Application",SwingConstants.CENTER);
        title.setFont(new Font("Vani", Font.BOLD, 35));
        title.setForeground(new Color(255,255,255));

        JLabel space1 = new JLabel(" ");
        space1.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space2 = new JLabel(" ");
        space2.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space3 = new JLabel(" ");
        space3.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space4 = new JLabel(" ");
        space4.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space5 = new JLabel(" ");
        space5.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space6 = new JLabel(" ");
        space6.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space7 = new JLabel(" ");
        space7.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space8 = new JLabel(" ");
        space8.setFont(new Font("Vani", Font.BOLD, 100));

        JLabel space9 = new JLabel(" ");
        space9.setFont(new Font("Vani", Font.BOLD, 100));

        setLayout(new GridBagLayout());
        GridBagConstraints  g = new GridBagConstraints();


        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 0;

        g.ipadx = 100;
        g.ipady = 0;
        add(welcome,g);
        
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(title,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 2;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space1,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 3;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space2,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 4;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space3,g);
        
        JButton reserve = new JButton("Ticket Reservation 🚂");
        reserve.setBackground(new Color(104, 189, 223));
        g.fill = GridBagConstraints.NONE;

        reserve.setFont(new Font("SansSerif", Font.BOLD, 18));

        g.gridx = 1;
        g.gridy = 5;
        g.ipadx = 100;
        g.ipady = 40;
        add(reserve,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space4,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space5,g);
        
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 2;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space6,g);

        JButton cancel = new JButton("Ticket Cancellation ❌");
        cancel.setBackground(new Color(104, 189, 223));
        cancel.setFont(new Font("SansSerif", Font.BOLD, 18));

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 3;
        g.ipadx = 100;
        g.ipady = 40;
        add(cancel,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 2;
        g.gridy = 0;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space7,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 2;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space8,g);

        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 2;
        g.gridy = 2;
        g.ipadx = 100;
        g.ipady = 0;
        
        add(space9,g);

        JButton status = new JButton("Ticket Status ℹ️");
        status.setBackground(new Color(104, 189, 223));
        g.fill = GridBagConstraints.HORIZONTAL;

        status.setFont(new Font("SansSerif", Font.BOLD, 18));

        g.gridx = 2;
        g.gridy = 3;
        g.ipadx = 100;
        g.ipady = 40;
        add(status,g);
        
        NavigationFrame f = this;
        reserve.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		f.setVisible(false);
        		manager.hideFrame(f);
        		manager.initiateReservation();
//        		f.setVisible(true);
        	}
        });
        
        cancel.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		manager.hideFrame(f);
        		manager.initiateCancellation();
        	}
        });

        status.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manager.hideFrame(f);
        		manager.initiateStatusCheck();
        	}
        });
        
        status.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e)
            {
                status.setBackground(Color.WHITE);
                status.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent e)
            {
                status.setBackground(new Color(104, 189, 223));
                status.setForeground(Color.BLACK);
            }
        });

        cancel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e)
            {
                cancel.setBackground(Color.WHITE);
                cancel.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent e)
            {
                cancel.setBackground(new Color(104, 189, 223));
                cancel.setForeground(Color.BLACK);
            }
        });

        reserve.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e)
            {
                reserve.setBackground(Color.WHITE);
                reserve.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent e)
            {
                reserve.setBackground(new Color(104, 189, 223));
                reserve.setForeground(Color.BLACK);
            }
        });

        
        //window config :
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent w) {
        		manager.updateFile();
        		new credits(manager);
        	}
		});
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);   
    }

//    public static void main(String args[]) {
//    	
//        new xxx();
//    }
}



// import java.awt.*;
// import javax.swing.*;
// import java.awt.event.*;
// import java.awt.Color;

// class customPanel extends JPanel {
//     JLabel t;
//     customPanel(String s) {
//         t= new JLabel(s);
//         add(t);
//         setBackground(Color.YELLOW);
//     }
// }

// class Mainframe extends JFrame
// {
//     JPanel MainPanel,MainPanel0,MainPanel1;
//     Mainframe()
//     {
//         setTitle("MainFrame");
//         JLabel welcome = new JLabel("Ticket Reservation (Mini Project)");
//         JLabel welcome1 = new JLabel("WELCOME");
//         MainPanel0 = new JPanel();
//         MainPanel0.setLayout(new GridLayout(2,1));
//         MainPanel0.add(welcome);
//         MainPanel0.add(welcome1);

//         GridLayout x =new GridLayout(1,3);
//         x.setHgap(30);
//         MainPanel1 = new JPanel();
//         MainPanel1.setLayout(x);
//         JButton tbooking = new JButton("Ticket Booking");
//         JButton tcancelling = new JButton("Ticket Cancelling");
//         JButton tQuery = new JButton("Ticket Query");
//         tbooking.setForeground(Color.white);
//         tcancelling.setForeground(Color.white);
//         tQuery.setForeground(Color.BLACK);
//         tbooking.setBackground(Color.BLUE);
//         tcancelling.setBackground(Color.RED);
//         tQuery.setBackground(Color.GREEN);
//         MainPanel1.add(tbooking);
//         MainPanel1.add(tcancelling);
//         MainPanel1.add(tQuery);

//         MainPanel = new JPanel();
//         MainPanel0.setBackground(Color.GRAY);
//         MainPanel.setBackground(Color.black);
//         MainPanel1.setBackground(Color.white);
//         MainPanel.setLayout(new BorderLayout());
//         MainPanel.setVisible(true);
//         MainPanel.add(MainPanel0,BorderLayout.NORTH);
//         MainPanel.add(MainPanel1,BorderLayout.CENTER);
//         add(MainPanel);
//         setSize(4000,2000);
//         setVisible(true);

//         welcome.setFont(new Font("SansSerif", Font.BOLD, 30));
//         welcome1.setFont(new Font("SansSerif", Font.BOLD, 30));
//         tbooking.setFont(new Font("Verdana", Font.BOLD, 40));
//         tcancelling.setFont(new Font("Verdana", Font.BOLD, 40));
//         tQuery.setFont(new Font("Verdana", Font.BOLD, 40));

//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//     }

//     public static void main(String[] args)
//     {
//         new Mainframe();
//     }
// }