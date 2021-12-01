package uselessFiles;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import projectFiles.*;

public class homeFrame extends JFrame {
	JPanel mainPanel;
	private class customPanel extends JPanel {
	    JLabel t;
	    customPanel(String s) {
	        t= new JLabel(s);
	        Font y = new Font("SansSerif", Font.BOLD, 30);
	        this.setBackground(new Color(245, 223, 223));
	        t.setForeground(new Color(114,87,30));
	        t.setFont(y);
	        add(t);
	        //setBackground(Color.YELLOW);
	    }
	}
	private class customButton extends JButton {
	    customButton(String s) {
	    	this.setText(s);
	        this.setBackground(new Color(80,228,160));
	        this.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),3));
	        //setBackground(Color.YELLOW);
	    }
	}
    JPanel MainPanel,MainPanel0,MainPanel1;
    homeFrame()
    {
    	HashMap<String, String> trainDetails = new HashMap<>();
//    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	trainDetails.put("train name", "");
    	trainDetails.put("train class", "");
    	trainDetails.put("day of booking", "1");
    	trainDetails.put("month of booking", "Jan");
    	trainDetails.put("year of booking", "2021");
    	trainDetails.put("from", "");
    	trainDetails.put("to", "");
    	trainDetails.put("status", "unpaid");
    	HashMap<String, String> passengerDetails = new HashMap<>();
    	passengerDetails.put("name","");
    	passengerDetails.put("phone number","");
    	passengerDetails.put("adhar", "");
    	passengerDetails.put("email", "");
    	passengerDetails.put("number of adults","1");
    	passengerDetails.put("number of children", "0");
    	passengerDetails.put("gender", "");
    	passengerDetails.put("age", "");   	
    	passengerDetails.put("address", "");
    	frameManager manager = new frameManager();
    	mainPanel = new JPanel();
    	mainPanel.setLayout(new GridBagLayout());
        Image image= Toolkit.getDefaultToolkit().getImage("<IMAGE_FOLDER>\\train.jpg");  
        this.setIconImage(image);
        ImageIcon s = new ImageIcon(Toolkit.getDefaultToolkit().getImage("<IMAGE_FOLDER>\\icon.png"));
        this.getContentPane().setBackground(new Color(245, 223, 223));
//        245, 223, 223/ 80,228,160 /108, 189, 186
        mainPanel.setBackground(new Color(245, 223, 223));
//        setSize(800,800);
        this.setExtendedState(MAXIMIZED_BOTH);
        setTitle("Ticket Reservation Application");
        customPanel title = new customPanel("Ticket Reservation System");
        customPanel welcome = new customPanel("WELCOME");
        setLayout(new BorderLayout(0,0));
        GridBagConstraints  g = new GridBagConstraints();
        // id : Er100Jo
        // number : 32153
        JButton i = new JButton();
        i.setPreferredSize(new Dimension(150,150));
//        i.setEnabled(false);
        i.setBackground(new Color(245, 223, 223));

        Border emptyBorder = BorderFactory.createEmptyBorder();
        i.setBorder(emptyBorder);
//        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0;
        g.gridy = 0;
        g.ipadx = 100;
        g.ipady = 40;
        mainPanel.add(i,g);

        JButton j = new JButton(new ImageIcon("<IMAGE_FOLDER>\\indrail.jpg"));
        j.setBackground(new Color(245, 223, 223));
        j.setBorder(emptyBorder);
//        j.setEnabled(false);
//        g.fill = GridBagConstraint.HORIZONTAL;

        g.gridx = 2;
        g.gridy = 0;
        g.ipadx = 100;
        g.ipady = 40;
        mainPanel.add(j,g);


//        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 0;

        g.ipadx = 100;
        g.ipady = 240;
        mainPanel.add(title,g);
        
//        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 250;
        
        mainPanel.add(welcome,g);
        
        customButton reserve = new customButton("Ticket Reservation");
        reserve.setBackground(new Color(241, 215, 98));
//        g.fill = GridBagConstraints.HORIZONTAL;
        homeFrame f = this;
        reserve.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		f.setVisible(false);
        		manager.hideFrame(f);
        		manager.initiateReservation();
//        		f.setVisible(true);
        	}
        });

        reserve.setFont(new Font("SansSerif", Font.BOLD, 20));

        g.gridx = 1;
        g.gridy = 2;
        g.ipadx = 100;
        g.ipady = 40;
        mainPanel.add(reserve,g);

        customButton cancel = new customButton("Ticket Cancellation");
        cancel.setBackground(new Color(241, 215, 98));
        cancel.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        cancel.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		manager.hideFrame(f);
        		manager.initiateCancellation();
        	}
        });
//        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 40;
        mainPanel.add(cancel,g);

        customButton status = new customButton("Ticket Status");
        status.setBackground(new Color(241, 215, 98));
//        g.fill = GridBagConstraints.HORIZONTAL;

        status.setFont(new Font("SansSerif", Font.BOLD, 20));

        g.gridx = 2;
        g.gridy = 1;
        g.ipadx = 100;
        g.ipady = 40;
        JFrame f1 =this;
        status.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manager.hideFrame(f1);
        		manager.initiateStatusCheck();
        	}
        });
        mainPanel.add(status,g);
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent w) {
        		new credits(manager);
        	}
		});
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));
        add(mainPanel,BorderLayout.CENTER);
        setVisible(true);   
    }
    public static void main(String args[]) {
        new homeFrame();
    }
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