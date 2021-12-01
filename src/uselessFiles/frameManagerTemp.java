package uselessFiles;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import extras.DBTools;
class displayFrame extends JFrame {
	/**
	 * 
	 */
	private class customPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JLabel label;
		customPanel(String content) {
			label = new JLabel(content);
			label.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
//			setBackground(new Color(80,228,160));
			add(label);
		}
	}
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	customPanel ticketId;
	customPanel ticketNumber;
	customPanel name;
	customPanel aadhar;
	customPanel mail;
	customPanel gender;
	customPanel age;
	customPanel trainName;
	customPanel trainClass;
	customPanel departure;
	customPanel arrival;
	customPanel adults;
	customPanel children;
	customPanel bookingDate;
	
	customPanel dticketId;
	customPanel dticketNumber;
	customPanel dname;
	customPanel daadhar;
	customPanel dmail;
	customPanel dgender;
	customPanel dage;
	customPanel dtrainName;
	customPanel dtrainClass;
	customPanel ddeparture;
	customPanel darrival;
	customPanel dadults;
	customPanel dchildren;
	customPanel dbookingDate;

	customPanel title;
	
	JButton close;
	
	displayFrame(ResultSet ticketDetails) throws SQLException {
		setLayout(new BorderLayout(0,0));
		setSize(700,700);
		//CLOSE BUTTON
		
		close = new JButton("Close");
		close.setPreferredSize(new Dimension(200,40));
		
		// ADDING EVENT HANDLER TO CLOSE BUTTON
		
		JFrame frame = this;
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
			}
		});
		
		add(close,BorderLayout.SOUTH);
		
		// TITLE
		
		title = new customPanel("TICKET DETAILS");
		title.setPreferredSize(new Dimension(200,40));
		add(title,BorderLayout.NORTH);
		
		// CONFIGURING MAIN PANEL:
		
		mainPanel = new JPanel();
		mainPanel.setSize(500,700);
		
		ticketId= new customPanel("TICKET ID");
		ticketNumber= new customPanel("PHONE NUMBER");
		name = new customPanel("NAME");
		aadhar = new customPanel("AADHAR");
		mail = new customPanel("MAIL");
		gender = new customPanel("GENDER");
		age = new customPanel("AGE");
		trainName = new customPanel("TRAIN NAME");
		trainClass = new customPanel("TRAIN CLASS");
		departure = new customPanel("DEPARTURE");
		arrival = new customPanel("ARRIVAL");
		adults = new customPanel("ADULTS");
		children = new customPanel("CHILDREN");
		bookingDate = new customPanel("BOOKING DATE");
		
		ticketDetails.next();
		
		dticketId= new customPanel(ticketDetails.getString(1));
		dticketNumber= new customPanel(ticketDetails.getString(2));
		dname = new customPanel(ticketDetails.getString(3));
		daadhar = new customPanel(ticketDetails.getString(4));
		dmail = new customPanel(ticketDetails.getString(5));
		dgender = new customPanel(ticketDetails.getString(6));
		dage = new customPanel(ticketDetails.getString(7));
		dtrainName = new customPanel(ticketDetails.getString(8));
		dtrainClass = new customPanel(ticketDetails.getString(9));
		ddeparture = new customPanel(ticketDetails.getString(10));
		darrival = new customPanel(ticketDetails.getString(11));
		dadults = new customPanel(ticketDetails.getString(12));
		dchildren = new customPanel(ticketDetails.getString(13));
		dbookingDate = new customPanel(ticketDetails.getDate(14).toString());
		mainPanel.setLayout(new GridLayout(14,2,10,10));
		
		mainPanel.add(ticketId);
		mainPanel.add(dticketId);
		mainPanel.add(ticketNumber);
		mainPanel.add(dticketNumber);
		mainPanel.add(name);
		mainPanel.add(dname);
		mainPanel.add(aadhar);
		mainPanel.add(daadhar);
		mainPanel.add(mail);
		mainPanel.add(dmail);
		mainPanel.add(gender);
		mainPanel.add(dgender);
		mainPanel.add(age);
		mainPanel.add(dage);
		mainPanel.add(trainName);
		mainPanel.add(dtrainName);
		mainPanel.add(trainClass);
		mainPanel.add(dtrainClass);
		mainPanel.add(departure);
		mainPanel.add(ddeparture);
		mainPanel.add(arrival);
		mainPanel.add(darrival);
		mainPanel.add(adults);
		mainPanel.add(dadults);
		mainPanel.add(children);
		mainPanel.add(dchildren);
		mainPanel.add(bookingDate);
		mainPanel.add(dbookingDate);
		
		
		// ADDING MAIN PANEL TO FRAME
		
		add(mainPanel,BorderLayout.CENTER);
		
		setVisible(true);
		
	}
}
public class frameManagerTemp {
	
	public static void main(String args[]) throws SQLException {
		
		displayTicketDetailsById("wh");
	}
	
	public static void displayTicketDetailsById(String ticketId) throws SQLException {
		DBTools tool = new DBTools();
		new displayFrame(tool.getRecordsById(ticketId));
		tool.closeConnection();
	}
	public static void displayTiketDetialsByBookingNumber(String number) throws SQLException {
		DBTools tool = new DBTools();
		new displayFrame(tool.getRecordsByBookingNumber(number));
		tool.closeConnection();
	}
	
	
}
