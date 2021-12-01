package FrameContents;

import java.awt.*;

import javax.swing.*;



public class firstFrame extends JFrame {
	private class customPanel extends JPanel{
		JLabel t;
		customPanel(String s) {
			t= new JLabel(s);
			add(t);
		}
	}

	customPanel title;
	customPanel welcome;
	JPanel buttonContainer1;
	JPanel buttonContainer2;
	JPanel buttonContainer3;
	JPanel mainPanel;
	
	JPanel filler1;
	JPanel filler2;
	
	JButton toReserve;
	JButton toCancel;
	JButton toCheckStatus;
	
	firstFrame() {
		setSize(600,600);
		
		mainPanel = new JPanel();
		customPanel title = new customPanel("Train Ticket Reservation System");
		customPanel welcome = new customPanel("Welcome");
        JFrame.setDefaultLookAndFeelDecorated(true);
        filler1 = new JPanel();
        filler2 = new JPanel();
        JPanel filler6 = new JPanel();
        JPanel filler7 = new JPanel();

		JButton toReserve = new JButton("Reserve Tickets");
		JButton toCancel = new JButton("Cancel Tickets");
		JButton toCheckStatus = new JButton ("Check Ticket Status");
		JPanel filler3 = new JPanel();
		JPanel filler4 = new JPanel();
		buttonContainer1 = new JPanel();
		buttonContainer1.setLayout(new BorderLayout(0,0));
		buttonContainer1.setAlignmentY(BOTTOM_ALIGNMENT);
//		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toReserve.setPreferredSize(new Dimension(40,100));
		buttonContainer1.add(toReserve,BorderLayout.WEST);
		toCheckStatus.setPreferredSize(new Dimension(40,100));
		buttonContainer1.add(toCheckStatus,BorderLayout.EAST);
		buttonContainer1.add(welcome,BorderLayout.CENTER);
		buttonContainer2 = new JPanel();
		buttonContainer2.setLayout(new BorderLayout(0,0));
		buttonContainer2.add(toCancel,BorderLayout.CENTER);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.add(title);
		mainPanel.add(buttonContainer1);
		mainPanel.add(buttonContainer2);
		mainPanel.setPreferredSize(new Dimension(400,400));
		setLayout(new BorderLayout(0,0));
		add(mainPanel,BorderLayout.CENTER);
		Dimension filler_x = new Dimension(50,100);
		Dimension filler_y = new Dimension(100,50);
		filler1.setPreferredSize(filler_x);
		add(filler1,BorderLayout.WEST);
		filler2.setPreferredSize(filler_x);
		add(filler2,BorderLayout.EAST);
		filler3.setPreferredSize(filler_y);
		add(filler3,BorderLayout.NORTH);
		filler4.setPreferredSize(filler_y);
		add(filler4,BorderLayout.SOUTH);
		setVisible(true);
	}
	public static void main(String args[]) {
		new firstFrame();
	}
}
