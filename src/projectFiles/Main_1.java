package projectFiles;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import databaseTools.databaseManager;
//import filesToFix.DisplayDatabaseTable;


class DisplayDatabaseTable {
	  public static void display(ResultSet rs) {
	    try{

	      ResultSetMetaData metadata = rs.getMetaData(); 
	      int numberOfColumns = metadata.getColumnCount(); 
	      int numberOfRows;

	      try{
	        numberOfRows = rs.getRow();
	      }
	      catch(Exception ex1){
	        numberOfRows = 100;
	      }

	      String ResultSetArray[][] = new String[numberOfRows][numberOfColumns+1];
	      int i=0; 
	      while (rs.next()) { 
	    	  ResultSetArray[i][0] = ""+(i+1);
	        for (int j = 1; j <= numberOfColumns; j++) { 
	          ResultSetArray[i][j] = rs.getString(j); 
	        } 
	        i++; 
	      } 

	      String column[] = new String[numberOfColumns+1];
	      column[0]="S.No";
	      for (int ii = 1; ii <= numberOfColumns; ii++ ) {
	        column[ii] = metadata.getColumnName(ii);
	      }

	      DefaultTableCellRenderer tr = new DefaultTableCellRenderer();
	      tr.setHorizontalAlignment(JLabel.CENTER);
	      JTable jt = new JTable(ResultSetArray,column); 
	      jt.setBounds(30,40,200,300); 
	      jt.setDefaultRenderer(Object.class, tr);
	      JScrollPane sp =new JScrollPane(jt);
	      JButton refresh = new JButton("Refresh Table");

	      JPanel panel = new JPanel();
	      panel.add(sp);
	      panel.add(refresh);
	      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	      Frame f = new JFrame(); 
	      f.add(panel);
	      f.setSize(1650,1080);
	      f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      f.setUndecorated(false);
	      f.setVisible(true);

	      refresh.addActionListener(new ActionListener(){
	          public void actionPerformed(ActionEvent ae){
	            f.invalidate();
	            f.validate();
	            f.repaint();
	          }
	      });
	    }
	    catch(Exception ex){
	      System.out.println(ex);
	    }
	  }
	}

class AdminPanel extends JFrame{
  AdminPanel(){
    super("Admin Panel");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    JButton ticketInfo = new JButton("Ticket Info");
    JButton trainInfo = new JButton("Train Info");
    JButton personalInfo = new JButton("Personal Info");
    JPanel panel = new JPanel();
    JPanel head = new JPanel();
    GridLayout layout = new GridLayout(3,1);
    layout.setVgap(10);
    panel.add(ticketInfo);
    panel.add(trainInfo);
    panel.add(personalInfo);
    JButton seatInfo = new JButton("Seat Availability");
    panel.add(seatInfo);
    JPanel body = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    setLayout(new GridBagLayout());
    head.add(new JLabel("Admin Panel"));
    body.add(head, gbc);
    body.add(panel, gbc);
    body.setLayout(new GridLayout(2,1));
    add(body, gbc);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ticketInfo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
		    try {
			    databaseManager db = new databaseManager();
			    DisplayDatabaseTable.display(db.getAllTicketDetials());
		    }
		    catch(Exception ex) {
			    ex.printStackTrace();
		    }
      }
    });

    trainInfo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
		    try {
			    databaseManager db = new databaseManager();
			    DisplayDatabaseTable.display(db.getAllTrainInfo());
		    }
		    catch(Exception ex) {
			    ex.printStackTrace();
		    }
      }
    });

    personalInfo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
		    try {
			    databaseManager db = new databaseManager();
			    DisplayDatabaseTable.display(db.getAllPersonalDetails());
		    }
		    catch(Exception ex) {
			    ex.printStackTrace();
		    }
      }
    });
    seatInfo.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new test();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});

  }
}

public class Main_1 {
  public static void main(String[] args) {
    new AdminPanel();
  }
}