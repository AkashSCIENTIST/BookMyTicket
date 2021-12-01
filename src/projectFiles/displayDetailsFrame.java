package projectFiles;
import java.sql.*;
import printTools.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
public class displayDetailsFrame extends JFrame {
	private static final Color bg = new Color(216, 245, 211);
	private class customLabel extends JPanel {
		JLabel x;
		customLabel(String s, int fontSize) {
			x= new JLabel(s);
			x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
			setBackground(bg);
			add(x);
		}
	}
	private class customPanel extends JPanel {
		customLabel t;
		text x;
		customPanel(String s,String value, int fontSize) {
			setPreferredSize(new Dimension(500,50));
			t = new customLabel(s,fontSize);
//			t.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
			x=new text(value);
			x.setHorizontalAlignment(JTextField.CENTER);
			x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
			setLayout(new GridLayout(1,2,20,10));
			add(t);
			add(x);
			setBackground(bg);
		}
	}
	private class text extends JTextField {
		text(String s) {
			this.setText(s);
			this.setBorder(BorderFactory.createLineBorder(new Color(43, 40, 28),1));
			this.setEditable(false);
		}		
	}
	
	
	//components :
	
	JPanel personalDetailsContainer;
	JPanel trainDetailsContainer;
	JPanel ticketDetailsContainer;
	
	
	//components for personalDetailsContainer :
	customPanel name;
	customPanel age;
	customPanel gender;
	customPanel adhar;
	customPanel phoneNumber;
	customPanel dob;
	
	
	// components for trainDetailsContainer :
	
	customPanel trainId;
	customPanel from;
	customPanel to;
	customPanel trainName;
	customPanel seatNumber;
	customPanel seatClass;
	
	
	// components for ticketDetailsContainer :
	
	customPanel ticketId;
	customPanel numberOfTravellers;
	customPanel departureTime;
	customPanel departureDate;
	customPanel paymentStatus;
	customPanel price;
	customLabel title;
	
	customLabel personalTitle;
	customLabel trainTitle;
	customLabel ticketTitle;
	
	Box titleContainer;
	
	
	JButton pay;
	
	Box detailsContainer;
	
	displayDetailsFrame(HashMap<String, String> details,frameManager manager) {
		manager.setFrameIcon(this);
		JPanel mainPanel = new JPanel(new GridBagLayout());
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		detailsContainer = new Box(BoxLayout.X_AXIS);
//		setSize(500,500);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 

//		pay.setVisible(false);
		JPanel titlePanel  = new JPanel(new BorderLayout(10,10));
		JButton printDetails = new JButton(new ImageIcon("<IMAGE_FOLDER>\\downloadSymbol.png"));
		title = new customLabel("Ticket Status",30);
		name = new customPanel("Name : ",details.get("NAME"),15);
		age = new customPanel("Age : ",details.get("AGE"),15);
		gender = new customPanel("Gender : ",details.get("GENDER"),15);
		adhar = new customPanel("Adhar : ",details.get("ADHAR"),15);
		dob = new customPanel("Date Of Birth : ",details.get("DATE_OF_BIRTH"),15);
		phoneNumber = new customPanel("Phone Number : ",details.get("BOOKING_NUMBER"),15);
		personalDetailsContainer = new JPanel();
		
		personalDetailsContainer.setLayout(new GridLayout(6,1,5,15));
		personalDetailsContainer.add(name);
		personalDetailsContainer.add(age);
		personalDetailsContainer.add(gender);
		personalDetailsContainer.add(phoneNumber);
		personalDetailsContainer.add(adhar);
		personalDetailsContainer.add(dob);
		personalDetailsContainer.setBackground(getForeground());
//		personalDetailsContainer.setPreferredSize(getPreferredSize());
//		personalDetailsContainer.setBounds(0,0,300,200);
		detailsContainer.add(personalDetailsContainer);
		
		
		ticketTitle = new customLabel("Ticket Details",20);
		trainTitle = new customLabel("Train Details",20);
		personalTitle = new customLabel("Personal Details",20);
		
		titleContainer = new Box(BoxLayout.X_AXIS);
		
		titleContainer.add(personalTitle);
		titleContainer.add(Box.createGlue());
		titleContainer.add(trainTitle);
		titleContainer.add(Box.createGlue());
		titleContainer.add(ticketTitle);
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				manager.makeHomeFrameVisible();
			}
		});
		
		
		trainId = new customPanel("Train ID : ",details.get("TRAIN_ID"),15);
		from = new customPanel("Departure  : ",details.get("FROM"),15);
		to = new customPanel("Arrival : ",details.get("TO"),15);
		trainName = new customPanel("Train Name : ",details.get("TRAIN_NAME"),15);
		seatNumber = new customPanel("Seat Number : ",details.get("SEAT_NUMBER"),15);
		seatClass = new customPanel("Seat Class : ",details.get("SEAT_CLASS"),15);
		trainDetailsContainer = new JPanel(new GridLayout(6,1,5,15));
		detailsContainer.add(Box.createGlue());
		
		trainDetailsContainer.add(trainName);
		trainDetailsContainer.add(trainId);
		trainDetailsContainer.add(from);
		trainDetailsContainer.add(to);
		trainDetailsContainer.add(seatNumber);
		trainDetailsContainer.add(seatClass);
		trainDetailsContainer.setBackground(getForeground());
		
		detailsContainer.add(trainDetailsContainer);
		detailsContainer.add(Box.createGlue());
		ticketDetailsContainer = new JPanel(new GridLayout(6,1,5,15));
		
		ticketId = new customPanel("Ticket ID : ",details.get("TICKET_ID"),15);
		numberOfTravellers = new customPanel("Number of Persons : ",details.get("PERSONS"),15);
		departureTime = new customPanel("Departure Time : ",details.get("DEPARTURE_TIME"),15);
		departureDate = new customPanel("Departure Date : ",details.get("DATE_OF_BOOKING"),15);
		paymentStatus = new customPanel("Payment Status : ",details.get("PAYMENT_STATUS"),15);
		price = new customPanel("Price : ",Double.toString(Integer.parseInt(details.get("PERSONS"))*1.25*200),15);
		ticketDetailsContainer.add(ticketId);
		ticketDetailsContainer.add(departureTime);
		ticketDetailsContainer.add(departureDate);
		ticketDetailsContainer.add(numberOfTravellers);
		ticketDetailsContainer.add(price);
		ticketDetailsContainer.add(paymentStatus);
		ticketDetailsContainer.setBackground(getForeground());
		
		detailsContainer.add(ticketDetailsContainer);
//		detailsContainer.setBackground(Color.white);
		//setLayout(new GridBagLayout());
		setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
//		g.fill = GridBagConstraints.HORIZONTAL;
		g.gridx = 4;
		g.gridy = 0;
		g.ipadx=1250;
		g.ipady =50;
		g.gridwidth = 4;
		g.gridheight = 1;
//		add(title,g);
		
//		customPanel t = new customPanel("asdas","ASf",20);
//		add(t,g);
//		add(detailsContainer,g);
		titlePanel.add(title,BorderLayout.CENTER);
		titlePanel.setBackground(bg);
		printDetails.setPreferredSize(new Dimension(50,30));
		printDetails.setBorder(null);
		printDetails.setBackground(bg);
//		);
		titlePanel.add(printDetails,BorderLayout.EAST);
		mainPanel.add(titlePanel,g);
		

		g.gridx = 0;
		g.gridy = 1;
		g.ipadx=930;
		g.gridheight = 2;
		g.gridwidth = 10;
//		titleContainer.setPreferredSize(new Dimension(500,100));
//		titleContainer.setBackground(Color.white);
		mainPanel.add(titleContainer,g);
		
		//g.fill = GridBagConstraints.NONE;
		g.ipadx=0;
		g.ipady =0;
		g.gridx = 0;
		g.gridy = 3;
		g.gridwidth = 15;
		g.gridheight=8;
		
		mainPanel.add(detailsContainer,g);
		mainPanel.setBackground(bg);
//		add()
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));
//		mainPanel.add(detailsContainer,g);
		
		setLayout(new BorderLayout(0,0));
		add(mainPanel,BorderLayout.CENTER);
		getContentPane().setBackground(bg);
//		add(detailsContainer);
		setVisible(true);
		
		printDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				PrintTool.JPanelToPdf(mainPanel, "D:\\project downloads", details.get("TICKET_ID"));
			}
		});
		
	}
	public static void main(String args[]) {
//		HashMap<String, String> det = new HashMap<>();
//		new displayDetailsFrame(det);
	}
}
