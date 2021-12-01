package projectFiles;
//import javax.swing.*;
//import java.util.*;
//import javax.swing.border.Border;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import java.util.Arrays;
//public class cancellationFrame {
//
//}



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import databaseTools.*;
import java.sql.*;
import java.util.*;


class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {
		// TODO Auto-generated constructor stub
		super("Record Not Found!");
	}
}

class InvalidDetailsException extends Exception {
	public InvalidDetailsException() {
		// TODO Auto-generated constructor stub
		super("Please Enter Correct Credentials!");
	}
}

class NullFieldsException extends Exception {
	public NullFieldsException() {
		// TODO Auto-generated constructor stub
		super("Empty Fields Not Allowed!");
	}
}

public class cancellationFrame {
	String[] trainDetails;
  cancellationFrame(frameManager manager) {
    JFrame frame = new JFrame("Cancellation");
    manager.setFrameIcon(frame);
    final Color bg = new Color(216, 245, 211);
    frame.setSize(500, 250);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setResizable(false);
    frame.setLayout(new BorderLayout(50, 50));
    //ImageIcon img = new ImageIcon("indian railway.png");
    //frame.setIconImage(img.getImage());
    frame.getContentPane().setBackground(bg);

    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(70, 70));
    //panel.setBackground(Color.gray);

    JTextField txt = new JTextField();
    JTextField txt1 = new JTextField(10);
    JButton button = new JButton("Confirm");

    txt.setSize(30, 30);
    txt1.setSize(30, 30);
    button.setEnabled(true);

    panel.setLayout(new GridLayout(6, 4));
    panel.add(new JLabel("TICKET ID"));
    panel.add(txt);
    panel.add(new JLabel());
    panel.add(new JLabel());
    panel.add(new JLabel("Phone Number"));
    panel.add(txt1);
    panel.add(new JLabel());
    panel.add(new JLabel());
    panel.add(new JLabel());
    panel.add(button);
    panel.setBackground(bg);

    JPanel east = new JPanel();
    east.setBackground(bg);
    JPanel west = new JPanel();
    west.setBackground(bg);
    JPanel north = new JPanel();
    north.setBackground(bg);
    frame.add(east, BorderLayout.EAST);
    frame.add(west, BorderLayout.WEST);
    frame.add(north, BorderLayout.NORTH);
    //frame.add(new JPanel(), BorderLayout.SOUTH);

    frame.add(panel, BorderLayout.CENTER);
    frame.setVisible(true);

    

    button.addActionListener(new ActionListener()
      {  
        public void actionPerformed(ActionEvent e)
        {   
            String s1 = txt.getText();
            String s2 = txt1.getText(); 
           try{
        	   	if(s1.equals("")|| s2.equals("")) {
        	   		throw new NullFieldsException();
        	   	}
                Long.parseLong(s2);
//              Integer.parseInt(s1);
//              if(s2.length()<10 || s2.length()>10)
//              {
//                throw new Exception();
//              }
//
              databaseManager tool = new databaseManager();
              HashMap<String, String> details = tool.getAllInfoByTicketIdAsHashMap(s1);
              tool.closeConnection();
              if(details.isEmpty()) {
            	  throw new RecordNotFoundException();
              }
              if(!details.get("BOOKING_NUMBER").equals(s2)) {
           		  throw new InvalidDetailsException();
              }
              //check the database

              /*if ticket no doesnt in the database*/

              /*JOptionPane.showMessageDialog(null, "Invalid","ERROR",JOptionPane.ERROR_MESSAGE);
              */

              /*if ticket is there then*/

              Confirm_frame(s1,details,manager);
              txt.setText("");
              txt1.setText("");


              }
          catch(NumberFormatException  e1) {
        	  e1.printStackTrace();
        	  JOptionPane.showMessageDialog(frame, "Enter Correct Phone Number","ERROR",JOptionPane.ERROR_MESSAGE);
        	  return;
          }
          catch (RecordNotFoundException ex) {
        	  JOptionPane.showMessageDialog(frame, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
          }	catch (InvalidDetailsException ex) {
        	  JOptionPane.showMessageDialog(frame, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
          } catch (NullFieldsException ex) {
        	  JOptionPane.showMessageDialog(frame, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
          } catch (Exception ex) {
        	  JOptionPane.showMessageDialog(frame, "Record Not Found!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
          }
        }  
      }
      );  

    

  }

  static void Confirm_frame(String s1,HashMap<String ,String> details, frameManager manager) {
        JFrame f = new JFrame("Confirm Cancellation");
            final Color bg = new Color(216, 245, 211);
            f.setSize(500, 500);
//            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setLayout(new BorderLayout(50, 50));
            //ImageIcon img1 = new ImageIcon("indian railway.png");
            //f.setIconImage(img1.getImage());
            f.getContentPane().setBackground(bg);
            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(70, 70));
            panel2.setBackground(bg);

            JTextField[] text = new JTextField[4];
            text[0]=new JTextField(s1);
            text[1]=new JTextField(details.get("DEPARTURE"));
            text[2]=new JTextField(details.get("ARRIVAL"));
            text[3]=new JTextField(Integer.toString(Integer.parseInt(details.get("PERSONS"))*200));
            JButton button2 = new JButton("YES");

            button2.setEnabled(true);
            JButton button3 = new JButton("BACK");

            button3.setEnabled(true);
//            text[0].setText(s1);

            
            GridLayout layout = new GridLayout(10, 4);
            layout.setHgap(25);
            panel2.setLayout(layout);
            panel2.add(new JLabel("TICKET ID:"));
            panel2.add(text[0]);
            panel2.add(new JLabel());
            panel2.add(new JLabel());
            panel2.add(new JLabel("FROM:"));
            panel2.add(text[1]);
            panel2.add(new JLabel());
            panel2.add(new JLabel());
            panel2.add(new JLabel("TO:"));
            panel2.add(text[2]);
            panel2.add(new JLabel());
            panel2.add(new JLabel());
            panel2.add(new JLabel("COST:"));
            panel2.add(text[3]);
            panel2.add(new JLabel());
            panel2.add(new JLabel());
            panel2.add(button3);
            panel2.add(button2);
          
            JPanel east = new JPanel();
            east.setBackground(bg);
            JPanel west = new JPanel();
            west.setBackground(bg);
            JPanel north = new JPanel();
            north.setBackground(bg);

            
            f.add(east, BorderLayout.EAST);
            f.add(west, BorderLayout.WEST);
            f.add(north, BorderLayout.NORTH);

            f.add(new JLabel("NOTE: Only 50% of total money is refundable"),BorderLayout.SOUTH);
            f.add(panel2, BorderLayout.CENTER);
            for(int i=0;i<4;i++)
            {text[i].setEditable(false);}
            f.setVisible(true);


      button2.addActionListener(new ActionListener()
      {  
        public void actionPerformed(ActionEvent e)
        {        
        int input = JOptionPane.showConfirmDialog(f, "Do you want to Proceed??\n*Note:Once Cancelled Cannot be Reverted","",JOptionPane.YES_NO_OPTION);
        
        if(input==0)
        {
        	try {
        		databaseManager tool = new databaseManager();
        		tool.deleteRecord(s1,details.get("BOOKING_NUMBER"));
        		tool.logAllDetails();
        		tool.closeConnection();
        	}
        	catch(SQLException t1) {
        		t1.printStackTrace();
        	}
        	
          
          JOptionPane.showMessageDialog(f,"Your Reservation has been cancelled Successfully");
          f.setVisible(false);
          manager.makeHomeFrameVisible();
        }
        }  
      }
      );
      button3.addActionListener(new ActionListener()
      {  
        public void actionPerformed(ActionEvent e)
        {        f.setVisible(false);
        }  
      }
      );
  }

//  public static void main(String args[]) {
//	  new cancellationFrame();
//  }


  

}
