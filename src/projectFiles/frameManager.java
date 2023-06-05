package projectFiles;
import databaseTools.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import emailConnector.emailConnector;
/*
 * 	TICKET DETAILS
 *
 *  id1 : ID
 * 	booking_number2 : BOOKING_NUMBER
 *  train_id1 : TRAIN_ID
 * 	departure2 : DEPARTURE
 * 	arrival2 : ARRIVAL
 * 	persons2 : PERSONS
 * 
 *	// PERSONAL DETAILS
 *
 * 	name1 : NAME
 * 	age1 : AGE
 * 	gender1 : GENDER
 * 	adhar1 : ADHAR
 * 	booking_number2 : PHONE_NUMBER
 * 	2020-05-21 : DOB
 * 
 * 	// TRAIN DETAILS 
 * 
 * 	train_name1 : NAME
 * 	train_id1 : ID
 * 	11:30 pm : DEPARTURE_TIME
 * 	2010-03-31 : DOB
 * 
 * 
 *  passengerDetails.put("name","");
 *  passengerDetails.put("phone number","");
 *  passengerDetails.put("adhar", "");
 *  passengerDetails.put("email", "");
 *  passengerDetails.put("number of adults","1");
 *  passengerDetails.put("number of children", "0");
 *  passengerDetails.put("gender", "");
 *  passengerDetails.put("age", "");
 *  passengerDetails.put("address", "");
 *  
 *  trainDetails.put("train name", "");
 *  trainDetails.put("train class", "");
 *  trainDetails.put("day of booking", "1");
 *  trainDetails.put("month of booking", "Jan");
 *  trainDetails.put("year of booking", "2021");
 *  trainDetails.put("from", "");
 *  trainDetails.put("to", "");
 */
//    final String places[] = new String[]{"","Coimbatore", "Erode", "Salem", "Jolarpet", "Chennai"};
//    final String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


public class frameManager {
	
	private class thread extends Thread {
		private String email;
		processing f;
		frameManager manager;
		thread(String email, processing t,frameManager manager) {
			this.email = email;
			this.f=t;
			this.manager = manager;
		}
		public void run() {
			
			try {
				Thread.sleep(1000);
				emailConnector.sendEmail(getFromDBAsHashMapById(), email);
				f.dispose();
				manager.initReceiptFrame(manager.getLink());
			} catch (SQLException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	private HashMap<String, String> months = new HashMap<>();
	private JFrame mainFrame;
	private static String ticketID;
	HashMap<String, String> allDetails;
	String[] checkValues; 
	String[] cancelValues;
	String[] personalValues,/*trainValues,*/ticketValues;
	public frameManager() {
		String month[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		int i=1;
		
		File file = new File("src\\lastTicket.txt");
		
		try {
			FileReader fread = new FileReader(file);
			char[] data = new char[100];
			fread.read(data);
//			System.out.println(Arrays.toString(data));
			ticketID = new String(data).trim();
//			System.out.println(ticketID);
//			System.out.println(Integer.parseInt(ticketID.trim()));
			fread.close();
		}
		catch(Exception except) {
			except.printStackTrace();
		}
		
		
		
		for(String mon : month) {
			months.put(mon, i<10?("0"+i):(""+i));
		}
		allDetails = new HashMap<>();
		personalValues = new String[6];
//		trainValues = new String[4];
		ticketValues = new String[7];
		checkValues = new String[3];
		cancelValues = new String[2];
	}
	public void updateFile() {
		try {
			FileWriter fout = new FileWriter("src\\lastTicket.txt");
			fout.write(ticketID);
			fout.close();
		}
		catch(Exception except) {
			except.printStackTrace();
		}
	}
	public void init(HashMap<String, String> personalDetails,HashMap<String, String> trainDetails) {
		for(String t : personalDetails.keySet()) {
			allDetails.put(t, personalDetails.get(t));
		}
		for(String t : trainDetails.keySet()) {
			allDetails.put(t, trainDetails.get(t));
		}
		System.out.println(allDetails.toString());
		toValues();
	}
	public void pushToDB() throws SQLException {
		databaseManager tool = new databaseManager();
		tool.setPersonalInfo(personalValues);
		tool.setTicketInfo(ticketValues);
		String seat = tool.updateSeatAvailability(ticketValues[0]);
		tool.setSeatDetailsInfo(new String[] {ticketValues[0],seat,allDetails.get("train class")});
		tool.logAllDetails();
		tool.closeConnection();
	}
	public HashMap<String, String> getLink() {
		HashMap<String, String> details = new HashMap<>();
		details.put("id", ticketValues[0]);
		details.put("phone number", ticketValues[1]);
		details.put("adhar", personalValues[3]);
		return details;
	}
	private void toValues(HashMap<String , String> details,String type) {
		if(type.equals("check")) {
			checkValues[0] = details.get("id");
			checkValues[1] = details.get("phone number");
			checkValues[2] = details.get("adhar");
		}
		else if (type.equals("cancel")) {
			
		}
	}
	private void toValues() {
		
		String ticketId = allDetails.get("from").substring(0,2)+ticketID+allDetails.get("to").substring(0,2);
		ticketID = Integer.toString(Integer.parseInt(ticketID)+1);
		
		LocalDate now = LocalDate.now();
		
		String dob = String.join("-",(new String[] {Integer.toString(now.getYear()-Integer.parseInt(allDetails.get("age"))),Integer.toString(now.getMonthValue()),(now.getDayOfMonth()<10?"0"+now.getDayOfMonth():Integer.toString(now.getDayOfMonth()))}));
		int day = Integer.parseInt(allDetails.get("day of booking"));
		String booking_date = String.join("-",(new String[] {allDetails.get("year of booking"),months.get(allDetails.get("month of booking")),(day<10?"0"+day:""+day)}));
		
		String trainId = String.join("-", new String[] {allDetails.get("from").substring(0,2),allDetails.get("train name").replace(" ", "-"),allDetails.get("to").substring(0,2)});
		String numberOfPerson = Integer.toString(Integer.parseInt(allDetails.get("number of children"))+Integer.parseInt(allDetails.get("number of adults")));
		
		personalValues[0] = allDetails.get("name");
		personalValues[1] = allDetails.get("age");
		personalValues[2] = allDetails.get("gender");
		personalValues[3] = allDetails.get("adhar");
		personalValues[4] = allDetails.get("phone number");
		personalValues[5] = dob;
		LocalDateTime now1= LocalDateTime.now();
//		trainValues[0] = allDetails.get("train name");
//		trainValues[1] = trainId;
//		trainValues[2] = (now1.plusHours(8).getHour()<10?("0"+now1.plusHours(8).getHour()):(now1.plusHours(8).getHour()))+":"+(now1.getMinute()<10?"0"+now1.getMinute():now1.getMinute());
//		trainValues[3] = booking_date;
//		
		ticketValues[0] = ticketId;
		ticketValues[1] = allDetails.get("phone number");
		ticketValues[2] = trainId;
		ticketValues[3] = numberOfPerson;	
		ticketValues[4] = (now1.plusHours(2).getHour()<10?("0"+now1.plusHours(2).getHour()):(now1.plusHours(2).getHour()))+":00";
		ticketValues[5] = booking_date;
		ticketValues[6] = allDetails.get("status");
//		printValues();
		
	}
	public void printValues() {
		for(String t : personalValues) {
			System.out.println(t);
		}
		System.out.println();
//		for(String t : trainValues) {
//			System.out.println(t);
//		}
//		System.out.println();
		for(String t : ticketValues) {
			System.out.println(t);
		}
		
	}
	
	void initTrainDB() throws SQLException {
		databaseManager tool=new databaseManager();
		String trains[] = new String[]{"Intercity Express", "Rajdhani Express", "Jammu Thavi Express", "Jan Sathapthi Express", "Udhay Express"};
		String places[] = new String[]{"Coimbatore", "Erode", "Salem", "Jolarpet", "Chennai"};

		for(String train : trains) {
			for(String place1:places) {
				for(String place2:places) {
					if(!place1.equals(place2)) {
						String[]  details = new String[] {train,(place1.substring(0,2)+"-"+train.replace(" ", "-")+"-"+place2.substring(0,2)),place1,place2};
						tool.setTrainInfo(details);
					}
				}
			}
		}
		tool.logAllDetails();
		tool.closeConnection();
	}
	private String[] getFromDB() throws SQLException {
		databaseManager tool = new databaseManager();
		return tool.getAllInfoByTicketId(checkValues[0]);
	}
	public HashMap<String, String> getFromDBAsHashMapById() throws SQLException {
		checkValues[0] = ticketValues[0];
		return getFromDBAsHashMap();
	}
	public HashMap<String, String> getFromDBAsHashMap() throws SQLException {
		HashMap<String, String> details = new HashMap<>();
		
		String[] det = getFromDB();
		
		//ticket details : 
		
		details.put("TICKET_ID", det[0]);
		details.put("BOOKING_NUMBER", det[1]);
		details.put("TRAIN_ID", det[2]);
		details.put("PERSONS", det[3]);
		details.put("DEPARTURE_TIME", det[4]);
		details.put("DATE_OF_BOOKING",det[5]);
		details.put("PAYMENT_STATUS", det[6]);
		
		// personal details :
		details.put("NAME", det[7]);
		details.put("AGE", det[8]);
		details.put("GENDER", det[9]);
		details.put("ADHAR",det[10]);
//		details.put("PHONE_NUMBER", det[10]);
		details.put("DATE_OF_BIRTH", det[12]);
		
		
		// train details :
		
		details.put("TRAIN_NAME", det[13]);
//		details.put("TRAIN_ID", )
		details.put("FROM", det[15]);
		details.put("TO", det[16]);
		details.put("SEAT_NUMBER", det[18]);
		details.put("SEAT_CLASS", det[19]);
		return details;
	}
	private void printDetails(HashMap<String, String> details) {
		for(String detail: details.keySet()) {
			System.out.println(detail+" : "+details.get(detail));
		}
	}
	
	
	
	public void setFrameIcon(JFrame f)  {
		f.setIconImage(new ImageIcon("src\\icon.png").getImage());
	}
	public void initReceiptFrame(HashMap<String, String> details) throws SQLException, Exception {
		System.out.println("\n\n details : "+details);
		toValues(details,"check");
		HashMap<String, String> det= getFromDBAsHashMap();
		System.out.println(det);
		if(det.isEmpty()) {
			throw new Exception();
		}

		printDetails(det);
		new displayDetailsFrame(det,this);
		
	}

	public void initiateCancellation() {
		new cancellationFrame(this);
	}
	public void initProject() {
		new NavigationFrame(this);
	}
	public synchronized void initProcess(String email) throws SQLException {
		thread t1 = new thread(email,new processing(this),this);
		t1.start();
		
	}
	public void hideFrame(JFrame f) {
		mainFrame = f;
		f.setVisible(false);
	}
	public void makeFrameVisible() {
		mainFrame.setVisible(true);
	}
	public void hideHomeFrame() {
		mainFrame.setVisible(false);
	}
	public void makeHomeFrameVisible() {
		mainFrame.setVisible(true);
	}
	public void initiateReservation() {
    	HashMap<String, String> trainDetails = new HashMap<>();
    	trainDetails.put("train name", "");
    	trainDetails.put("train class", "");
    	trainDetails.put("day of booking", "1");
    	trainDetails.put("month of booking", "Jan");
    	trainDetails.put("year of booking", "2021");
    	trainDetails.put("from", "");
    	trainDetails.put("to", "");
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
        new TrainDetailsFrame(trainDetails,passengerDetails,this);
    }
	public void initiateStatusCheck() {
		new pageFrame(this);
	}
	public static void main(String args[]) throws SQLException {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
//    	HashMap<String, String> passengerDetails = new HashMap<>();
//    	
//    	
//    	passengerDetails.put("name","argsrg");
//    	passengerDetails.put("phone number","56345");
//    	passengerDetails.put("adhar", "45563546");
//    	passengerDetails.put("email", "gsrhghsb");
//    	passengerDetails.put("number of adults","1");
//    	passengerDetails.put("number of children", "0");
//    	passengerDetails.put("gender", "m");
//    	passengerDetails.put("age", "18");   	
//    	passengerDetails.put("address", "thetyne");
//    	
//    	HashMap<String, String> trainDetails = new HashMap<>();
//    	trainDetails.put("train name", "shadapthi");
//    	trainDetails.put("train class", "sss");
//    	trainDetails.put("day of booking", "5");
//    	trainDetails.put("month of booking", "Jan");
//    	trainDetails.put("year of booking", "2021");
//    	trainDetails.put("from", "panipet");
//    	trainDetails.put("to", "gujarat");
//    	frameManager s = new frameManager();
//    	s.init(passengerDetails, trainDetails);
//    	s.printValues();
		frameManager t = new frameManager();
//		t.printValues();
//		t.initTrainDB();
		t.initProject();
	}
}
