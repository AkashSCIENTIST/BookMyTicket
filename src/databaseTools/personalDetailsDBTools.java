package databaseTools;

import java.sql.*;


class personalDetailsDBTools {
	Connection connector;
	Statement execute;
	PreparedStatement insert;
	PreparedStatement removeByNumber;
	PreparedStatement removeByAdhar;
	PreparedStatement getByNumber;
	PreparedStatement getByAdhar;
	PreparedStatement removeByProperty;
	PreparedStatement getByProperty;

	personalDetailsDBTools(Connection connect) throws SQLException {
		connector = connect; 
		execute = connector.createStatement();
//		execute.executeUpdate("create table personal_info(name varchar(40),age varchar(40),gender varchar(40),adhar varchar(40),phone_number varchar(40),dob date)");
		insert = connector.prepareStatement("insert into personal_info values (?,?,?,?,?,?)");
		removeByNumber = connector.prepareStatement("delete from personal_info where phone_number = ?");
		removeByAdhar = connector.prepareStatement("delete from personal_info where adhar = ?");
		getByAdhar = connector.prepareStatement("select * from personal_info where adhar=?");
		getByNumber = connector.prepareStatement("select *  from personal_info where phone_number=?");

	}
	public void deleteAll() throws SQLException {
		execute.executeUpdate("delete from personal_info");
	}
	void insertRecord(String[] details) throws SQLException{
		for(int i=1;i<=5;i++) {
			insert.setString(i, details[i-1]);
		}
		insert.setDate(6, Date.valueOf(details[5]));
		insert.executeUpdate();
	}
	void removeRecordByAdhar(String 	id) throws SQLException{
		removeByAdhar.setString(1,id);
		removeByAdhar.executeUpdate();
	}
	void removeRecordByPhoneNumber(String number) throws SQLException {
		removeByNumber.setString(1,number);
		removeByNumber.executeUpdate();
	}
	void removeRecordByProperty(String property,String value) throws SQLException{
		removeByProperty = connector.prepareStatement(String.format("delete from personal_info where %s=?",property));
		removeByProperty.setString(1, value);
		removeByProperty.executeUpdate();
	}
	void getRecordsByProperty(String property,String value) throws SQLException{
		getByProperty = connector.prepareStatement(String.format("select * from personal_info where %s=?",property));
		getByProperty.setString(1, value);
		getByProperty.executeQuery();
	}

	ResultSet getRecordsByAdhar(String id) throws SQLException {
		getByAdhar.setString(1, id);
		return getByAdhar.executeQuery();
	}
	ResultSet getRecordsByPhoneNumber(String number) throws SQLException {
		getByNumber.setString(1, number);
		return getByNumber.executeQuery();
	}
	ResultSet getAllRecords() throws SQLException {
		return execute.executeQuery("select * from personal_info");
	}
	void logRecords() throws SQLException {
		ResultSet result = getAllRecords();
		int i=1;
		while(result.next()) {
			System.out.println("Person : "+i);
			System.out.println();
			i++;
			System.out.println("name : "+result.getString(1));
			System.out.println("age : "+result.getString(2));
			System.out.println("gender: "+result.getString(3));
			System.out.println("adhar: "+result.getString(4));
			System.out.println("phone number : "+result.getString(5));
			System.out.println("DOB : "+result.getDate(6).toString());
		}
	}
	void createTable() throws SQLException {
		execute.executeUpdate("create table personal_info(name varchar(40),age varchar(40),gender varchar(40),adhar varchar(40),phone_number varchar(40),dob date)");
	}
	void deleteTable() throws SQLException {
		execute.executeUpdate("drop table personal_info");
	}
	public static void main(String args[]) throws SQLException {
		Connection con  = DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true");
		personalDetailsDBTools t= new personalDetailsDBTools(con);
//		t.insertRecord(new String[] {"name1","age1","gender1","adhar1","phone number1","2020-05-21"});
//		t.removeRecordByProperty("gender","gender2");
		ResultSet y = t.getAllRecords();
//		y.next();
		ResultSetMetaData rsmd = y.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++) {
			System.out.println(rsmd.getColumnName(i));			
		}

//		t.deleteAll();
	}
	
}