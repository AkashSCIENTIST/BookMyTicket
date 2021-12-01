package databaseTools;
import java.sql.*;
import java.util.Arrays;
import java.util.*;
public class databaseManager {
	private Connection connector;
	private Statement execute;
	private personalDetailsDBTools personal;
	private ticketDetailsDBTools ticket;
	private trainDetailsDBTools train;
	private seatDetailsDBTools seat;
	private availabilityInit availability;
	public databaseManager() throws SQLException {
		connector = DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true");
		execute = connector.createStatement();
		personal = new personalDetailsDBTools(connector);
		ticket = new ticketDetailsDBTools(connector);
		train = new trainDetailsDBTools(connector);
		availability = new availabilityInit(connector);
		seat = new seatDetailsDBTools(connector);
	}
	public ResultSet getSeatInfoForTrain(String trainID) throws SQLException {
		return availability.getRecordsFromTrain(trainID);
	}
	public ResultSet getAllTrainInfo() throws SQLException {
		return train.getAllRecords();
	}
	
	public ResultSet getAllPersonalDetails() throws SQLException {
		return personal.getAllRecords();
	}
	public void initConnection() throws SQLException {
		connector = DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=false");
		execute = connector.createStatement();
		personal = new personalDetailsDBTools(connector);
		ticket = new ticketDetailsDBTools(connector);
		train = new trainDetailsDBTools(connector);

	}
	public ResultSet getAllTicketDetials() throws SQLException {
		return ticket.getAllRecords();
	}
	
//	public ResultSet getAllInfo() throws SQLException {
//		return execute.executeQuery("select * from ticket_info as ticket,personal_info as personal,train_info as train, seat_details as seat, seat_availability as avail where ticket.booking_number=personal.phone_number and "
//				+ "ticket.train_id=train.id and ticket.id=seat.ticket_id and seat.seat_number = avail.seat_number");
//	}
//	
	public String[] getAllInfoByTicketId(String ticketId) throws SQLException {
		String trainId = ticket.getTrainIDFromTicketID(ticketId);
		ResultSet t = execute.executeQuery(String.format("select * from ticket_info as ticket,personal_info as personal,train_info as train, seat_details as seat, seat_availability_%s as avail where ticket.booking_number=personal.phone_number "
				+ "and ticket.train_id=train.id and ticket.id=seat.ticket_id and seat.seat_number = avail.seat_number and ticket.id='%s'",trainId.replaceAll("-", "_"),ticketId));
//		t.next();
//		System.out.println(t);
		ResultSetMetaData rsmd = t.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		String[] details = new String[columnsNumber];
//		int rows = rsmd.get
		System.out.println(columnsNumber);
//		t.next();
//		System.out.println(t.getString(1));
		while (t.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print("\n");
		        String columnValue = i==6||i==13?t.getDate(i).toString():t.getString(i);
		        details[i-1]=columnValue;
		        System.out.print(columnValue + " : " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}
		return details;
	}
	
	public String[] getAllInfoByBookingNumber(String number) throws SQLException {
		String trainID = ticket.getTrainIDFromBookingNumber(number);

		ResultSet t= execute.executeQuery(String.format("select * from ticket_info as ticket,personal_info as personal,train_info as train, seat_details as seat, seat_availability_%s as avail where ticket.booking_number=personal.phone_number and "
				+ "ticket.train_id=train.id and ticket.id=seat.ticket_id and seat.seat_number = avail.seat_number and ticket.booking_number='%s'",trainID.replaceAll("-", "_"),number));
		ResultSetMetaData rsmd = t.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		String[] details = new String[columnsNumber];
//		int rows = rsmd.get
		System.out.println(columnsNumber);
//		t.next();
//		System.out.println(t.getString(1));
		while (t.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print("\n");
		        String columnValue = i==6||i==13?t.getDate(i).toString():t.getString(i);
		        details[i-1]=columnValue;
		        System.out.print(columnValue + " : " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}
		return details;
	}
	
	public HashMap<String, String> getAllInfoByTicketIdAsHashMap(String id) throws SQLException {
		String trainId = ticket.getTrainIDFromTicketID(id);
		ResultSet t = execute.executeQuery(String.format("select * from ticket_info as ticket,personal_info as personal,train_info as train, seat_details as seat, seat_availability_%s as avail where ticket.booking_number=personal.phone_number "
				+ "and ticket.train_id=train.id and ticket.id=seat.ticket_id and seat.seat_number = avail.seat_number and ticket.id='%s'",trainId.replaceAll("-", "_"),id));
//		t.next();
//		System.out.println(t);
		ResultSetMetaData rsmd = t.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		HashMap<String, String> details = new HashMap<String, String>();
//		int rows = rsmd.get
		System.out.println(columnsNumber);
//		t.next();
//		System.out.println(t.getString(1));
		while (t.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print("\n");
		        String columnValue = i==6||i==13?t.getDate(i).toString():t.getString(i);
		        details.put(rsmd.getColumnName(i), columnValue);
		    }
		    System.out.println("");
		}
		return details;
	}
	public HashMap<String, String> getAllInfoByBookingNumberAsHashMap(String number) throws SQLException {
		String trainId = ticket.getTrainIDFromBookingNumber(number);
		ResultSet t= execute.executeQuery(String.format("select * from ticket_info as ticket,personal_info as personal,train_info as train, seat_details as seat, seat_availability_%s as avail where ticket.booking_number=personal.phone_number and "
				+ "ticket.train_id=train.id and ticket.id=seat.ticket_id and seat.seat_number = avail.seat_number and ticket.booking_number='%s'",trainId.replaceAll("-", "_"),number));
//		t.next();
//		System.out.println(t);
		ResultSetMetaData rsmd = t.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		HashMap<String, String> details = new HashMap<String, String>();
//		int rows = rsmd.get
		System.out.println(columnsNumber);
//		t.next();
//		System.out.println(t.getString(1));
		while (t.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print("\n");
		        String columnValue = i==6||i==13?t.getDate(i).toString():t.getString(i);
		        details.put(rsmd.getColumnName(i), columnValue);
		    }
		    System.out.println("");
		}
		return details;
	}

	public void setPersonalInfo(String ...detials) throws SQLException {
		personal.insertRecord(detials);
	}
	
	public void setTrainInfo(String ...details) throws SQLException {
		train.insertRecord(details);
	}
	public void setTicketInfo(String ...details) throws SQLException {
		ticket.insertRecord(details);
	}
	public void setSeatDetailsInfo(String ...details) throws SQLException {
		seat.insertRecords(details);
	}
	public String updateSeatAvailability(String ticketID) throws SQLException {
		String seatNumber;
		String trainID = ticket.getTrainIDFromTicketID(ticketID);
		seatNumber = availability.getAvailableSeat(trainID);
		
		return seatNumber;

	}
	public void setDetails(String[] personal,String[] train,String[] ticket,String seatClass) throws SQLException {
		setTrainInfo(train);
		setPersonalInfo(personal);
		setTicketInfo(ticket);
		String seatNumber = updateSeatAvailability(ticket[0]);
		seat.insertRecords(new String[] {ticket[0],seatNumber,seatClass});
	}
	public void logAllDetails() throws SQLException {
		System.out.println("personal Details :");
		personal.logRecords();
		System.out.println("Train Details :");
		train.logRecords();
		System.out.println("Ticket Details :");
		ticket.logRecords();
	}
	void dropAll() throws SQLException {
		personal.deleteTable();
		train.dropTable();
		ticket.dropTable();
	}
	public void closeConnection() throws SQLException {
		connector.close();
	}
	public void deleteRecord(String id,String number) throws SQLException {
		String[] det=seat.getDetailsById(id);
		availability.freeSeat(ticket.getTrainIDFromTicketID(id),det[1]);
		personal.removeRecordByPhoneNumber(number);
		ticket.removeRecordById(id);
		seat.removeRecordsById(id);
	}
	public void createAll() throws SQLException {
		personal.createTable();
		train.createTable();
		ticket.createTable();
	}
	public void deleteAllRecords() throws SQLException {
		personal.deleteAll();
//		train.deleteAll();
		ticket.deleteAll();
		seat.deleteAll();
	}
	public static void main(String args[]) throws SQLException {
		databaseManager t=new databaseManager();
//		t.logAllDetails();
//		t.setPersonalInfo(new String[] {"name1","age1","gender1","adhar1","booking_number2","2020-05-21"});
//		t.setTrainInfo(new String[] {"train_name1","train_id1","departure2","arrival2"});
//		t.setTicketInfo(new String[] {"id6","booking_number2","train_id1","persons2","11:30 pm","2010-03-31"});
//		t.logAllDetails();
//		t.deleteAllRecords();
		t.deleteAllRecords();
//		t.dropAll();
//		t.createAll();
//		String[] y=t.getAllInfoByBookingNumber("booking_number2");
//		System.out.println();
//		System.out.println(Arrays.toString(y));
		t.closeConnection();
	}
}
