package databaseTools;

import java.sql.*;


class trainDetailsDBTools {
	Connection connector;
	Statement execute;
	PreparedStatement insert;
	PreparedStatement removeById;
	PreparedStatement removeByName;
	PreparedStatement getById;
	PreparedStatement getByName;
	PreparedStatement removeByProperty;
	PreparedStatement getByProperty;

	trainDetailsDBTools(Connection connect) throws SQLException {
		connector = connect;
		execute = connector.createStatement();
//		dropTable();
//		execute.executeUpdate("create table train_info(name varchar(40),id varchar(40))"); //dob - date of booking
		insert = connector.prepareStatement("insert into train_info values (?,?,?,?)");
		removeByName= connector.prepareStatement("delete from train_info where name = ?");
		removeById= connector.prepareStatement("delete from train_info where id = ?");
		getByName= connector.prepareStatement("select * from train_info where name=?");
		getById= connector.prepareStatement("select *  from train_info where id=?");

	}
	public void deleteAll() throws SQLException {
		execute.executeUpdate("delete from train_info");
	}

	public void createTable() throws SQLException {
		execute.executeUpdate("create table train_info(name varchar(40),id varchar(40),departure varchar(40),arrival varchar(40))");
	}
	void insertRecord(String[] details) throws SQLException{
		for(int i=1;i<=4;i++) {
			insert.setString(i, details[i-1]);
		}
		insert.executeUpdate();
	}
	void removeRecordByName(String name) throws SQLException{
		removeByName.setString(1,name);
		removeByName.executeUpdate();
	}
	void removeRecordById(String id) throws SQLException {
		removeById.setString(1,id);
		removeById.executeUpdate();
	}
	void removeRecordByProperty(String property,String value) throws SQLException{
		removeByProperty = connector.prepareStatement(String.format("delete from train_info where %s=?",property));
		removeByProperty.setString(1, value);
		removeByProperty.executeUpdate();
	}
	void getRecordsByProperty(String property,String value) throws SQLException{
		getByProperty = connector.prepareStatement(String.format("select * from train_info where %s=?",property));
		getByProperty.setString(1, value);
		getByProperty.executeQuery();
	}

	ResultSet getRecordsById(String id) throws SQLException {
		getById.setString(1, id);
		return getById.executeQuery();
	}
	ResultSet getRecordsByName(String name) throws SQLException {
		getByName.setString(1, name);
		return getByName.executeQuery();
	}
	ResultSet getAllRecords() throws SQLException {
		return execute.executeQuery("select * from train_info");
	}
	void closeConnection() throws SQLException {
		connector.close();
	}
	void logRecords() throws SQLException {
		ResultSet result = getAllRecords();
		int i=1;
		while(result.next()) {
			System.out.println();
			System.out.println("Train : "+i);
			i++;
			System.out.println("name : "+result.getString(1));
			System.out.println("id: "+result.getString(2));
			System.out.println("departure: "+result.getString(3));
			System.out.println("arrival: "+result.getString(4));
//			System.out.println("time of departure : "+result.getString(3));
//			System.out.println("date of booking: "+result.getDate(3).toString());
		}
	}
	void dropTable() throws SQLException {
		execute.executeUpdate("drop table train_info");
	}
	public static void main(String args[]) throws SQLException {
		trainDetailsDBTools t = new trainDetailsDBTools(DriverManager.getConnection("jdbc:derby:ticketreservationdb;create=true"));
//		t.insertRecord(new String[] {"name1","id1","11:30 pm","2010-03-31"});
//		t.removeRecordByProperty("departure_time","11:30 pm");
//		ResultSet y = t.getAllRecords();
//		y.next();
//		ResultSetMetaData rsmd = y.getMetaData();
//		for(int i=1;i<=rsmd.getColumnCount();i++) {
//			System.out.println(rsmd.getColumnName(i));			
//		}

		t.logRecords();
		t.closeConnection();
	}
}