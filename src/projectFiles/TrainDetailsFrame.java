package projectFiles;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
//import DBTools.DBTools;
import java.awt.*;
import java.util.*;

public class TrainDetailsFrame extends JFrame{
	private static final Color bg = new Color(216, 245, 211);
	private class customPanel extends JPanel {
	    JLabel t;
	    customPanel(String s,int fontSize) {
	        t = new JLabel(s);
	        Font font1 = new Font(Font.SANS_SERIF,  Font.BOLD, fontSize);
	        t.setFont(font1);
//	        final Color bgcolor = new Color(245, 223, 223);
	        setBackground(bg);
	        add(t);
	    }
	}
	private class text extends JTextField{
		JTextField tf = this;
		
		text(String s) {
			this(s,BorderFactory.createLineBorder(new Color(108,93,24),2),new Font(Font.SANS_SERIF, Font.BOLD, 17));
			this.addFocusListener(new FocusAdapter() { 
				public void focusLost(FocusEvent f) {
					if(tf.getText().equals("")) {
						tf.setText("*THIS FIELD IS COMPULSORY");
						tf.setForeground(Color.red);
					}
				}
				public void focusGained(FocusEvent f) {
					if(tf.getText().equals("*THIS FIELD IS COMPULSORY")) {
						tf.setText("");
						tf.setForeground(Color.black);
					}
				}
			});
		}
		
		text(String s, Border b, Font f){
			this.setText(s);
			this.setFont(f);
			setBorder(b);
			this.addFocusListener(new FocusAdapter() { 
				public void focusLost(FocusEvent f) {
					if(tf.getText().equals("")) {
						tf.setText("*THIS FIELD IS COMPULSORY");
						tf.setForeground(Color.red);
					}
				}
				public void focusGained(FocusEvent f) {
					if(tf.getText().equals("*THIS FIELD IS COMPULSORY")) {
						tf.setText("");
						tf.setForeground(Color.black);
					}
				}
			});
		}
	}

	
    JLabel reservation_label, train_label, class_label, address_label, date_label, begin_label, end_label;
    JLabel emptyLabel1, emptyLabel2, emptyLabel3;
    JButton nextButton;
    JPanel datePanel,mainContainer;
    Font font1 = new Font(Font.SANS_SERIF,  Font.BOLD, 30);
    Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    JComboBox<String> begin;
    JComboBox<String> end; 
    JComboBox<String> train;
    JComboBox<String> clas; 
    JComboBox<String> date; 
    JComboBox<String> month;
    JComboBox<String> year; 
    final String places[] = new String[]{"","Coimbatore", "Erode", "Salem", "Jolarpet", "Chennai"};
    final String dates[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    final String years[] = {"2021", "2022", "2023", "2024", "2025"};
    final String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    final String trains[] = new String[]{"","Intercity Express", "Rajdhani Express", "Jammu Thavi Express", "Jan Sathapthi Express", "Udhay Express"};
    final String classes[] = new String[]{"","AC 3 Tier", "AC 2 Tier", "Sleeper", "Regular"};

    TrainDetailsFrame(HashMap<String, String> trainDetails,HashMap<String, String> passengerDetails,frameManager m){
        super("Reservation System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        m.setFrameIcon(this);
        mainContainer = new JPanel();
        getContentPane().setBackground(bg);
        begin_label = new JLabel(" Source Station");
        end_label = new JLabel(" Destination Station");
        train_label = new JLabel(" Train Name");
        class_label = new JLabel(" Class");
        date_label = new JLabel(" Date");
        reservation_label = new JLabel(" Train details");
        emptyLabel1 = new JLabel();
        emptyLabel2 = new JLabel();
        emptyLabel3 = new JLabel();

        reservation_label.setFont(font1);
        begin_label.setFont(font2);
        end_label.setFont(font2);
        train_label.setFont(font2);
        class_label.setFont(font2);
        date_label.setFont(font2);
        
        nextButton = new JButton("Next ⏭️");
        nextButton.setFont(font2);

        begin = new JComboBox<>(places);
        begin.setSelectedItem(trainDetails.get("from"));
        end = new JComboBox<>(places);
        end.setSelectedItem(trainDetails.get("to"));
        train = new JComboBox<>(trains);
        train.setSelectedItem(trainDetails.get("train name"));
        clas = new JComboBox<>(classes);
        clas.setSelectedItem(trainDetails.get("train class"));
        date = new JComboBox<>(dates);
        date.setSelectedItem(trainDetails.get("day of booking"));
        month = new JComboBox<>(months);
        month.setSelectedItem(trainDetails.get("month of booking"));
        year = new JComboBox<>(years);
        year.setSelectedItem(trainDetails.get("year of booking"));
        begin.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        end.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        train.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        clas.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        month.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        date.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        year.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        
        
        begin.setFont(font2);
        end.setFont(font2);
        train.setFont(font2);
        clas.setFont(font2);
        date.setFont(font2);
        month.setFont(font2);
        year.setFont(font2);
        
//        begin.setBackground(Color.WHITE);
//        end.setBackground(Color.WHITE);
//        train.setBackground(Color.WHITE);
//        clas.setBackground(Color.WHITE);
//        date.setBackground(Color.WHITE);
//        month.setBackground(Color.WHITE);
//        year.setBackground(Color.WHITE);
//
        datePanel = new JPanel(new GridLayout(1,3));
        datePanel.add(date);
        datePanel.add(month);
        datePanel.add(year);

        //add(reservation_label);
        //add(emptyLabel1);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7,2,15,20));
        mainPanel.add(emptyLabel1);
        mainPanel.add(emptyLabel3);
        mainPanel.add(train_label);
        mainPanel.add(train);
        mainPanel.add(class_label);
        mainPanel.add(clas);
        mainPanel.add(date_label);
        mainPanel.add(datePanel);
        mainPanel.add(begin_label);
        mainPanel.add(begin);
        mainPanel.add(end_label);
        mainPanel.add(end);
        mainPanel.add(emptyLabel2);
        nextButton.setBackground(bg);
        nextButton.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        mainPanel.add(nextButton);
        mainPanel.setBackground(bg);
        GridBagConstraints gb = new GridBagConstraints();
        mainContainer.setLayout(new GridBagLayout());

        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridx=1;
        gb.gridy=1;
        gb.gridwidth = 3;

        customPanel title = new customPanel("Train Details",30);
        mainContainer.add(title,gb);
        gb.gridx=1;
        gb.gridy=2;
        gb.gridwidth = 3;
        gb.gridheight = 3;

        gb.ipadx = 140;
        gb.ipady = 105;
        
        mainContainer.add(mainPanel,gb);
        mainContainer.setBackground(bg);
        mainContainer.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));
        setLayout(new BorderLayout());
        add(mainContainer,BorderLayout.CENTER);
        setVisible(true);
        JFrame frame = this;
        nextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                int start = begin.getSelectedIndex();
                int finish = end.getSelectedIndex();
                String trainClass = classes[clas.getSelectedIndex()];
                String startPlace = places[start];
                String finishPlace = places[finish];
                JFrame f = new JFrame();
                String trainName = trains[train.getSelectedIndex()];
                int bookingDate = date.getSelectedIndex() + 1;
                int bookingMonth = month.getSelectedIndex() + 1;
                int bookingYear = year.getSelectedIndex() + 2021;
                
                if(trainName.equals("") || trainClass.equals("") || startPlace.equals("") || finishPlace.equals("")) {
                	JOptionPane.showMessageDialog(f, "Please fill vaid details", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(start == finish){
                	JOptionPane.showMessageDialog(f, "Starting and Destinaion cannot be same", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else{
                	frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
                    new PassengerDetailsFrame(passengerDetails,getFrameDetails(),m);
                }

            }

        });
    }
    
    public HashMap<String, String> getFrameDetails() {
    	HashMap<String, String> details = new HashMap<>();
    	
    	details.put("train name", (String)train.getSelectedItem());
    	details.put("train class", (String)clas.getSelectedItem());
    	details.put("day of booking", (String)date.getSelectedItem());
    	details.put("month of booking", (String)month.getSelectedItem());
    	details.put("year of booking", (String)year.getSelectedItem());
    	details.put("from", (String)begin.getSelectedItem());
    	details.put("to", (String)end.getSelectedItem());

    	return details;
    }
    
    public static void main(String args[]){
    	HashMap<String, String> trainDetails = new HashMap<>();
    	
    	trainDetails.put("train name", "");
    	trainDetails.put("train class", "");
    	trainDetails.put("day of booking", "1");
    	trainDetails.put("month of booking", "Jan");
    	trainDetails.put("year of booking", "2021");
    	trainDetails.put("from", "");
    	trainDetails.put("to", "");
    	HashMap<String, String> passengerDetails = new HashMap<>();
    	passengerDetails.put("name","");
    	passengerDetails.put("phone number","");
    	passengerDetails.put("adhar", "");
    	passengerDetails.put("email", "");
    	passengerDetails.put("number of adults","1");
    	passengerDetails.put("number of children", "0");
    	passengerDetails.put("gender", "");
    	passengerDetails.put("age", "");   	
    	passengerDetails.put("address", "");
        new TrainDetailsFrame(trainDetails,passengerDetails,new frameManager());
    }
}