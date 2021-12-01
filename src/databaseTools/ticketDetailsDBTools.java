package databaseTools;

import java.sql.*;


class ticketDetailsDBTools {
	Connection connector;
	Statement execute;
	PreparedStatement insert;
	PreparedStatement removeByNumber;
	PreparedStatement removeById;
	PreparedStatement getByNumber;
	PreparedStatement getById;
	PreparedStatement removeByProperty;
	PreparedStatement getByProperty;
	ticketDetailsDBTools(Connection connect) throws SQLException {
		connector = connect;
		execute = connector.createStatement();
		insert = connector.prepareStatement("insert into ticket_info values (?,?,?,?,?,?,?)");
		removeByNumber = connector.prepareStatement("delete from ticket_info where booking_number= ?");
		removeById= connector.prepareStatement("delete from ticket_info where id = ?");
		getById= connector.prepareStatement("select * from ticket_info where id=?");
		getByNumber = connector.prepareStatement("select *  from ticket_info where booking_number=?");
	}
	public void deleteAll() throws SQLException {
		execute.executeUpdate("delete from ticket_info");
	}

	public void createTable() throws SQLException {
		execute.executeUpdate("create table ticket_info(id varchar(40),booking_number varchar(40),train_id varchar(40),persons varchar(40),departure_time varchar(40),dob date,payment_status varchar(40))");
	}
	void insertRecord(String[] details) throws SQLException{
		for(int i=1;i<=5;i++) {
			insert.setString(i, details[i-1]);
		}
		insert.setDate(6, Date.valueOf(details[5]));
		insert.setString(7,details[6]);
		insert.executeUpdate();
	}
	void removeRecordById(String id) throws SQLException{
		removeById.setString(1,id);
		removeById.executeUpdate();
	}
	void removeRecordByProperty(String property,String value) throws SQLException{
		removeByProperty = connector.prepareStatement(String.format("delete from ticket_info where %s=?",property));
		removeByProperty.setString(1, value);
		removeByProperty.executeUpdate();
	}
	void getRecordsByProperty(String property,String value) throws SQLException{
		getByProperty = connector.prepareStatement(String.format("select * from ticket_info where %s=?",property));
		getByProperty.setString(1, value);
		getByProperty.executeQuery();
	}

	void removeRecordByBookingNumber(String number) throws SQLException {
		removeByNumber.setString(1,number);
		removeByNumber.executeUpdate();
	}
	ResultSet getRecordsById(String id) throws SQLException {
		getById.setString(1, id);
		return getById.executeQuery();
	}
	ResultSet getRecordsByBookingNumber(String number) throws SQLException {
		getByNumber.setString(1, number);
		return getByNumber.executeQuery();
	}
	void logRecords() throws SQLException {
		ResultSet result = getAllRecords();
		int i=1;
		while(result.next()) {
			System.out.println("Ticket : "+i);
			System.out.println();
			i++;
			System.out.println("id : "+result.getString(1));
			System.out.println("booking_number : "+result.getString(2));
			System.out.println("train_id "+result.getString(3));
			System.out.println("persons : "+result.getString(4));
			System.out.println("departure time : "+result.getString(5));
			System.out.println("departure date : "+result.getDate(6).toString());
			System.out.println("status : "+result.getString(7));
		}
	}
	public void dropTable() throws SQLException {
		execute.executeUpdate("drop table ticket_info");
	}
	ResultSet getAllRecords() throws SQLException {
		return execute.executeQuery("select * from ticket_info");
	}
	public void closeConnection() throws SQLException {
		connector.close();
	}
	String getTrainIDFromTicketID(String ticketID) throws SQLException {
		ResultSet ts = execute.executeQuery(String.format("select * from ticket_info where id = '%s'", ticketID));
		if(ts.next()) {
			System.out.println(ts.getString("train_id"));
			return ts.getString("train_id");
		}
		return null;
	}
	String getTrainIDFromBookingNumber(String number) throws SQLException {
		ResultSet ts = execute.executeQuery(String.format("select * from ticket_info where booking_number = '%s'", number));
		if(ts.next()) {
			return ts.getString("train_id");
		}
		return null;
	}

	public static void main(String args[]) throws SQLException {
		Connection con  = DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true");
		ticketDetailsDBTools t= new ticketDetailsDBTools(con);
//		t.deleteAll();
//		t.insertRecord(new String[] {"id2","booking_number2","train_id2","persons2","12:00am","2001-02-21","unpaid"});
//		t.removeRecordByProperty("persons","persons2");
//		ResultSet y = t.getAllRecords();
//		y.next();
//		ResultSetMetaData rsmd = y.getMetaData();
//		for(int i=1;i<=rsmd.getColumnCount();i++) {
//			System.out.println(rsmd.getColumnName(i));			
//		}
//		t.logRecords();
		System.out.println(t.getTrainIDFromTicketID("Sa200Ch"));
		t.closeConnection();
	}
}
