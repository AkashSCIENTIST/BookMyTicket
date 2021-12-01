package databaseTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;

public class availabilityInit {
	Connection connector;
	Statement execute;
	trainDetailsDBTools train;
	PreparedStatement insert;
	PreparedStatement removeById;
	PreparedStatement getById; 
	
	availabilityInit(Connection con) throws SQLException {
		connector = con;
		train = new trainDetailsDBTools(con);
		execute = connector.createStatement();
	}
	void insertRecords(String[] details,String trainId) throws SQLException {
		execute.executeUpdate(String.format("insert into seat_availability_%s values('%s','%s')",trainId.replaceAll("-", "_"),details[0],details[1]));
	}
	ResultSet getRecordsFromTrain(String trainId) throws SQLException {
		return execute.executeQuery(String.format("select * from seat_availability_%s",trainId.replaceAll("-", "_")));
	}
	void createTable(String trainId) throws SQLException {
		execute.executeUpdate(String.format("create table seat_availability_%s(seat_number varchar(20),availability varchar(20))",trainId.replaceAll("-", "_")));
	}
	void dropTable(String trainId) throws SQLException {
		execute.executeUpdate(String.format("drop table seat_availability_%s",trainId.replaceAll("-", "_")));
	}
	void deleteAll(String trainID) throws SQLException {
		execute.executeUpdate(String.format("delete from seat_availability_%s",trainID.replaceAll("-", "_")));
	}
	void closeConnection() throws SQLException {
		connector.close();
	}
	void logRecords() throws SQLException {
		ResultSet r = train.getAllRecords();
		while(r.next()) {
			String id = r.getString("id");
			ResultSet rs = getRecordsFromTrain(id);
			System.out.println("\n\n"+id+" : ");
			while(rs.next()) {
				System.out.println(Arrays.toString(new String[] {rs.getString(1),rs.getString(2)}));
			}
		}
	}
	void initDBWithTrainID(String trainID) throws SQLException {
		for(int i=0;i<30;i++) {
			insertRecords(new String[]{(i+1)+"","true"}, trainID);
		}
	}
	void initDB() throws SQLException {
		ResultSet r = train.getAllRecords();
		while(r.next()) {
			String id = r.getString("id");
//			createTable(id);
			initDBWithTrainID(id);
		}
	}
	public void freeSeat(String trainID, String seatNumber) throws SQLException {
		updateAvailability(trainID, seatNumber, "true");
	}
	public void freeAllSeats() throws SQLException {
		ResultSet rs = train.getAllRecords();
		while(rs.next()) {
			String id = rs.getString("id");
			deleteAll(id);
		}
	}
	public String getAvailableSeat(String trainID) throws SQLException {
		ResultSet r = getRecordsFromTrain(trainID);
		String seatNumber=null;
		while(r.next()) {
			if(r.getString(2).equals("true")) {
				seatNumber = r.getString(1);
				updateAvailability(trainID, seatNumber, "false");
				return seatNumber;
			}
		}
		return seatNumber;
	}
	public void updateAvailability(String trainID,String seatNumber,String availability) throws SQLException {
		execute.executeUpdate(String.format("update seat_availability_%s set availability = '%s' where seat_number = "
				+ "'%s'",trainID.replaceAll("-", "_"),availability,seatNumber));
	}
	public static void main(String args[]) throws SQLException {
		availabilityInit t = new availabilityInit(DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true"));
//		t.createTable();
//		t.insertRecords(new String[] {"23","true"});
//		t.logRecords();
//		System.out.println(Arrays.toString(t.getDetailsById("23")));
//		t.dropTable();
//		t.deleteAll();
//		t.initDB();
//		t.freeAllSeats();
		t.logRecords();
//		t.updateAvailability("Jo-Udhay-Express-Ch", "24", "true");
//		t.logRecords();
//		t.initDB();
		t.closeConnection();
	}

}
