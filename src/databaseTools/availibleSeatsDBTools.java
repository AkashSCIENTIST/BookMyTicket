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

class availibleSeatsDBTools {
	Connection connector;
	Statement execute;
	PreparedStatement insert;
	PreparedStatement removeById;
	PreparedStatement getById; 
	
	availibleSeatsDBTools(Connection con) throws SQLException {
		connector = con;
		execute = connector.createStatement();
		insert = connector.prepareStatement("insert into seat_availability values(?,?)");
		removeById = connector.prepareStatement("delete from seat_availability where seat_number=?");
		getById = connector.prepareStatement("select * from seat_availability where seat_number=?");
//		removeByProperty = connector.prepareStatement("delete from seat_details where ")
		
	}
	
	void insertRecords(String[] details) throws SQLException {
		for(int i=1;i<=2;i++) {
			insert.setString(i, details[i-1]);
		}
		insert.executeUpdate();
	}
	
	void removeRecordsById(String id) throws SQLException {
		removeById.setString(1, id);
		removeById.executeUpdate();
	}
	
	public String[] getDetailsById(String id) throws SQLException {
		getById.setString(1, id);
		ResultSet r = getById.executeQuery();
		ResultSetMetaData rsmd =r.getMetaData();
		r.next();
		String[] details=new String[rsmd.getColumnCount()];
		
		for(int i=1;i<=rsmd.getColumnCount();i++) {
			details[i-1] = r.getString(i);
		}
		
		
		return details;
	}
	public ResultSet getAllRecords() throws SQLException {
		return execute.executeQuery("select * from seat_availability");
	}
	public HashMap<String, String> getDetailsByIdAsHashMap(String id) throws SQLException {
		getById.setString(1, id);
		ResultSet r = getById.executeQuery();
		ResultSetMetaData rsmd =r.getMetaData();
		HashMap<String, String> details=new HashMap<String, String>();
		
		for(int i=1;i<=rsmd.getColumnCount();i++) {
			details.put(rsmd.getColumnName(i), r.getString(i));
		}
		
		
		return details;
	}
	public void updateSeatAvailability(String seatNumber,String status) throws SQLException {
		execute.executeUpdate(String.format("update seat_availability set availability='%s' where seat_number = '%s'",status,seatNumber));
	}
	void createTable() throws SQLException {
		execute.executeUpdate("create table seat_availability(seat_number varchar(20),availability varchar(20))");
	}
	void dropTable() throws SQLException {
		execute.executeUpdate("drop table seat_availability");
	}
	void deleteAll() throws SQLException {
		execute.executeUpdate("delete from seat_availability");
	}
	void closeConnection() throws SQLException {
		connector.close();
	}
	void logRecords() throws SQLException {
		ResultSet result = getAllRecords();
		int i=1;
		while(result.next()) {
			System.out.println("Seat  : "+i);
			System.out.println();
			i++;
			System.out.println("seat_number : "+result.getString(1));
			System.out.println("availability : "+result.getString(2));
//			System.out.println("seat_class: "+result.getString(3));
		}
	}
	void initDB() throws SQLException {
		for(int i=1;i<=70;i++) {
			insertRecords(new String[] {Integer.toString(i),"true"});
		}
		logRecords();
	}
	public static void main(String args[]) throws SQLException {
		availibleSeatsDBTools t = new availibleSeatsDBTools(DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true"));
//		t.createTable();
//		t.insertRecords(new String[] {"23","true"});
//		t.logRecords();
//		System.out.println(Arrays.toString(t.getDetailsById("23")));
//		t.dropTable();
//		t.deleteAll();
//		t.initDB();
		t.closeConnection();
	}

}
