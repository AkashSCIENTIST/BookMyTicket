package filesToFix;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import databaseTools.databaseManager;


class DisplayDatabaseTable{
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

	      JTable jt = new JTable(ResultSetArray,column); 
	      jt.setBounds(30,40,200,300); 
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