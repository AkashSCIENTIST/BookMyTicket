package extras;
import java.sql.*;
//import java.util.Arrays;

/*
 * 
 * 
 * 
 * db name : railwaydb
 * table name : ticket_details :
 * table contents :
 * 	ticket_id - varchar(40)(40)
 * 	ticket_booking_number - varchar(40)(40)
 * 	booking_name - varchar(40)(40)
 * 	booking_aadhar - varchar(40)(40)
 * 	booking_mail - varchar(40)(40)
 * 	booking_ gender - varchar(40)(40)
 * 	booking_age - varchar(40)(40)
 * 	train_name - varchar(40)(40)
 * 	train_class - varchar(40)(40)
 *  departure - varchar(40)(40)
 *  arrival - varchar(40)(40)
 *  adults - varchar(40)(40)
 *  children - varchar(40)(40)
 * 	booking_date - date
 * 
 * 
 * 
 * 
 */


/*
 * How to Use :
 * 	* create DBTools object to start connection.
 * 	* call required functions.
 * 	* close the connection using DBTools object.
 *
 * 
 * functions :
 * 	createTable() -  to create table ticket_details
 * 	deleteTable() - to delete table
 *	insertRecord() - to insert into the database [format as arranged above in a string array] date format - yyyy-mm-dd
 *	removeRecordById() - to delete records from db - parameter - id as string.
 *	removeRecordsByBookingNumber() - to delete records from db - parameter - booking_number as string.
 *	getRecordsById() - returns a result set object whose id = string passed as parameter
 *	getRecordsByBookingNumber() - returns a result set object whose booking_number = string passed as arg.
 *  logRecords() -  displays the records in the console.
 */


public class DBTools {
	private Connection connector;
	private Statement execute;
	private PreparedStatement insert;
	private PreparedStatement removeById;
	private PreparedStatement removeByNumber;
	private PreparedStatement getById;
	private PreparedStatement getByNumber;

	public DBTools() throws SQLException {
		connector = DriverManager.getConnection("jdbc:derby:railwaydb;create=true");
		execute = connector.createStatement();
//		deleteTable();
//		createTable();
		insert = connector.prepareStatement("insert into ticket_details values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		removeById = connector.prepareStatement("delete from ticket_details where ticket_id=?");
		removeByNumber = connector.prepareStatement("delete from ticket_details where ticket_booking_number=?");
		getById = connector.prepareStatement("select * from ticket_details where ticket_id=?");
		getByNumber = connector.prepareStatement("select *  from ticket_details where ticket_booking_number=?");
	}
	 void createTable() throws SQLException {
		execute.executeUpdate("create table ticket_details(ticket_id varchar(40),ticket_booking_number varchar(40),booking_name varchar(40),"
				+ "booking_aadhar varchar(40),booking_mail varchar(40),booking_gender varchar(40),booking_age varchar(40),train_name varchar(40),"
				+ "train_class varchar(40),departure varchar(40),arrival varchar(40),adults varchar(40),children varchar(40),booking_date date)");
	}
	void insertRecord(String[] details) throws SQLException{
		for(int i=1;i<=13;i++) {
			insert.setString(i, details[i-1]);
		}
		insert.setDate(14, Date.valueOf(details[13]));
		insert.executeUpdate();
	}
	void removeRecordById(String id) throws SQLException{
		removeById.setString(1,id);
		removeById.executeUpdate();
	}
	void removeRecordByBookingNumber(String number) throws SQLException {
		removeByNumber.setString(1,number);
		removeByNumber.executeUpdate();
	}
	public ResultSet getRecordsById(String id) throws SQLException {
		getById.setString(1, id);
		return getById.executeQuery();
	}
	public ResultSet getRecordsByBookingNumber(String number) throws SQLException {
		getByNumber.setString(1, number);
		return getByNumber.executeQuery();
	}
	public ResultSet getAllRecords() throws SQLException {
		return execute.executeQuery("select * from ticket_details");
	}
	void logRecords() throws SQLException {
		ResultSet records = getRecordsById("wh");
		while(records.next()) {
			System.out.println();
			System.out.println("id : "+records.getString(1)+"\nphone number : "+records.getString(2)+"\nname :  "
		+records.getString(3)+"aadhar number : "+records.getString(4)+"\nemail : "+records.getString(5)+"\ngender : "+records.getString(6)+"\nage : "+records.getString(7)+
		"\ntrain name : "+records.getString(8)+"\nclass : "+records.getString(9)+"\ndeparture : "+records.getString(10)+"arrival : "+records.getString(11)+"adults : "+records.getString(12)
		+"\nchildren :"+records.getString(13)+"\ndate of booking : "+records.getDate(14));
		}
	}
	public void closeConnection() throws SQLException {
		connector.close();
	}
	void deleteTable() throws SQLException {
		execute.executeUpdate("drop table ticket_details");
	}
	public static void main(String args[]) throws SQLException {
		DBTools t= new DBTools();
//		String[] details = new  String[14];
//		Arrays.fill(details, "w");
//		details[13] = "2005-12-08";
////		t.insertRecord(details);
////		t.removeRecordByBookingNumber("w");
		t.logRecords();
		t.closeConnection();
	}
}
