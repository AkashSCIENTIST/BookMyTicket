package databaseTools;
import java.sql.*;
import java.util.*;
class seatDetailsDBTools {
	Connection connector;
	Statement execute;
	PreparedStatement insert;
	PreparedStatement removeById;
	PreparedStatement getById; 
	
	seatDetailsDBTools(Connection con) throws SQLException {
		connector = con;
		execute = connector.createStatement();
		insert = connector.prepareStatement("insert into seat_details values(?,?,?)");
		removeById = connector.prepareStatement("delete from seat_details where ticket_id=?");
		getById = connector.prepareStatement("select * from seat_details where ticket_id=?");
//		removeByProperty = connector.prepareStatement("delete from seat_details where ")
		
	}
	
	void insertRecords(String[] details) throws SQLException {
		for(int i=1;i<=3;i++) {
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
	private ResultSet getAllRecords() throws SQLException {
		return execute.executeQuery("select * from seat_details");
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
	
	void createTable() throws SQLException {
		execute.executeUpdate("create table seat_details(ticket_id varchar(20),seat_number varchar(20),seat_class varchar(20))");
	}
	void deleteAll() throws SQLException {
		execute.executeUpdate("delete from seat_details");
	}
	void dropTable() throws SQLException {
		execute.executeUpdate("drop table seat_details");
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
			System.out.println("ticket_id : "+result.getString(1));
			System.out.println("seat_number : "+result.getString(2));
			System.out.println("seat_class: "+result.getString(3));
		}
	}
	public static void main(String args[]) throws SQLException {
		seatDetailsDBTools t = new seatDetailsDBTools(DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true"));
//		t.createTable();
//		t.insertRecords(new String[] {"Er100Co","23","U"});
		t.logRecords();
//		System.out.println(Arrays.toString(t.getDetailsById("Er100Co")));
		t.closeConnection();
	}
}
