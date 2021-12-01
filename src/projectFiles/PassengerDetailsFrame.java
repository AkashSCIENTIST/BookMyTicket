package projectFiles;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.regex.*;
import java.awt.event.*;//import DBTools;
import java.sql.*;
import java.awt.*;
import java.util.*;

class validPhoneNumber{
	public static boolean isValid(String input) {
		return Pattern.matches("\\d{10}", input);
	}
}

class validAdhaarNumber{
	public static boolean isValid(String input) {
		return Pattern.matches("\\d{12}", input);
	}
}

class validEMail{
	public static boolean isValid(String input) {
		return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", input);
	}
}

class GroupButtonUtils {
	
    public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}

public class PassengerDetailsFrame extends JFrame{
	private static final Color bg = new Color(216, 245, 211);
	private class text extends JTextField {
		text(String s) {
			this(s,BorderFactory.createLineBorder(new Color(108,93,24),2),new Font(Font.SANS_SERIF, Font.BOLD, 17));
		}
		text(String s,Border b,Font f) {
			this.setText(s);
			this.setFont(f);
			setBorder(b);
		}
	}
	
	private class customPanel extends JPanel {
		JLabel t;
		customPanel(String s,int fontSize) {
			t = new JLabel(s);
			Font font1 = new Font(Font.SANS_SERIF,  Font.BOLD, fontSize);
			t.setFont(font1);
//			final Color bgcolor = new Color(48,206,136);
			setBackground(bg);
			add(t);
		}
	}


	frameManager m;
    JLabel reservation_label, adults, age_label, adhaar_label, childs, name_label, phoneNumber_label, email_label, address_label, gender_label;
    JLabel emptyLabel1, emptyLabel2, emptyLabel3, emptyLabel4;
    JButton bookButton;
    JRadioButton male, female;
    ButtonGroup gender;
    JPanel genderPanel, datePanel, buttonPanel;
    text name, email, phoneNumber, address, adhaar_number, age;

    Font font1;
    Font font2;

    JButton info1;
    JButton info2;
    
    JPanel adultsPanel;
    JPanel childrenPanel;
    
    final String numbers[] = new String[]{"1","2","3","4"};
    final String numbers2[] = new String[]{"0","1","2","3","4"};
    
    JComboBox<String> nAdults;
    JComboBox<String> nChildren;
    
    PassengerDetailsFrame(HashMap<String, String> details, HashMap<String, String> details1,frameManager manager) {
        super("Reservation System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        font1 = new Font(Font.SANS_SERIF,  Font.BOLD, 30);
        font2 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
        manager.setFrameIcon(this);
        adults = new JLabel("\tNo. of Adults");
        childs = new JLabel("\tNo. of Children");
        name_label = new JLabel("\tName");
        phoneNumber_label = new JLabel("\tPhone Number");
        adhaar_label = new JLabel("\tAdhaar Number");
        email_label = new JLabel("\tE-mail");
        age_label = new JLabel("\tAge");
        address_label = new JLabel("\tAddress");
        gender_label = new JLabel("\tGender");
        reservation_label = new JLabel("Reserve Tickets");
        emptyLabel1 = new JLabel();
        emptyLabel2 = new JLabel();
        emptyLabel3 = new JLabel();
        emptyLabel4 = new JLabel();

        info1 = new JButton("i");
        info1.setBorder(BorderFactory.createLineBorder(Color.black));
        info1.setPreferredSize(new Dimension(80,80));
        
        
        info2 = new JButton("i");
        info2.setBorder(BorderFactory.createLineBorder(Color.black));
        info2.setPreferredSize(new Dimension(80,80));
        
        info1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new fareDetailsFrame();
        	}
        });
        
        info2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new fareDetailsFrame();
        	}
        });

        reservation_label.setFont(font1);
        adults.setFont(font2);
        childs.setFont(font2);
        age_label.setFont(font2);
        name_label.setFont(font2);
        phoneNumber_label.setFont(font2);
        adhaar_label.setFont(font2);
        email_label.setFont(font2);
        address_label.setFont(font2);
        gender_label.setFont(font2);
        
        bookButton = new JButton("Book Ticket");
        bookButton.setFont(font2);

        nAdults = new JComboBox(numbers);
        nChildren = new JComboBox(numbers2);

        nAdults.setSelectedItem(details.get("number of adults"));
        nChildren.setSelectedItem(details.get("number of children"));
        nAdults.setFont(font2);
        nChildren.setFont(font2);
        nAdults.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        nChildren.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
        name = new text(details.get("name"));
        email = new text(details.get("email"));
        phoneNumber = new text(details.get("phone number"));
        address = new text(details.get("address"));
        adhaar_number = new text(details.get("adhar"));
        age = new text(details.get("age"));

        
//        name.setFont(font2);
//        email.setFont(font2);
//        phoneNumber.setFont(font2);
//        address.setFont(font2);
//        adhaar_number.setFont(font2);
//        age.setFont(font2);
        

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        gender = new ButtonGroup();
        gender.add(female);
        gender.add(male);
        if(details.get("gender")!=null) {

        if(details.get("gender").equals("Male")) {
        	male.setSelected(true);
        }
        else if(details.get("gender").equals("Female")){
        	female.setSelected(true);
        }
        }
        male.setFont(font2);
        female.setFont(font2);
        male.setBackground(bg);
        female.setBackground(bg);
        genderPanel = new JPanel(new GridLayout(1,2));
        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.setBackground(bg);
        buttonPanel = new JPanel(new GridLayout(1,2));
//        buttonPanel.add(emptyLabel2);
        buttonPanel.add(bookButton);
        buttonPanel.setBackground(bg);
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),3));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11,2,15,15));
        mainPanel.add(emptyLabel3);
        mainPanel.add(emptyLabel4);
        mainPanel.add(name_label);
        mainPanel.add(name);
        mainPanel.add(phoneNumber_label);
        mainPanel.add(phoneNumber);
        mainPanel.add(adhaar_label);
        mainPanel.add(adhaar_number);
        mainPanel.add(email_label);
        mainPanel.add(email);
        mainPanel.add(gender_label);
        mainPanel.add(genderPanel);
        mainPanel.add(age_label);
        mainPanel.add(age);
        mainPanel.add(address_label);
        mainPanel.add(address);
        mainPanel.add(adults);
        
        adultsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints y = new GridBagConstraints();
        adultsPanel.setBackground(bg);
        y.gridx = 0;
        y.gridy = 0;
        y.gridwidth = 3;
        y.ipadx = 100;
        y.ipady = 0;
        
        JPanel filler = new JPanel();
        filler.setBackground(bg);
        JPanel filler2 = new JPanel();
        filler2.setBackground(bg);
        adultsPanel.add(nAdults,y);
        
        y.gridx = 3;
        y.gridy = 0;
        y.gridwidth = 1;
        y.ipadx = 20;
        y.ipady = 0;

        adultsPanel.add(filler,y);
        
        y.gridx = 4;
        y.gridy = 0;
        y.gridwidth = 1;
        y.ipadx = 30;
        y.ipady = 20;
        
        adultsPanel.add(info1,y);
        
        
        mainPanel.add(adultsPanel);
        mainPanel.add(childs);
        
        childrenPanel = new JPanel(new GridBagLayout());
        childrenPanel.setBackground(bg);
        y.gridx = 0;
        y.gridy = 0;
        y.gridwidth = 3;
        y.ipadx = 100;
        y.ipady = 0;

        childrenPanel.add(nChildren,y);
        
        y.gridx = 3;
        y.gridy = 0;
        y.gridwidth = 1;
        y.ipadx = 20;
        y.ipady = 0;

        childrenPanel.add(filler2,y);


        y.gridx = 4;
        y.gridy = 0;
        y.gridwidth = 1;
        y.ipadx = 30;
        y.ipady = 20;

        childrenPanel.add(info2,y);
        
        mainPanel.add(childrenPanel);
//        mainPanel.add(emptyLabel2);
        JButton back = new JButton("Back");
//        JPanel backPanel = new JPanel();
//        backPanel.add(back);
        //back.setBackground(bg);
        back.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),3));
        back.setPreferredSize(getPreferredSize());
        back.setFont(font2);
        JFrame f = this;
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
        		new TrainDetailsFrame(details1,getFrameDetails(),m);
        	}
        });
        mainPanel.add(back);
        mainPanel.add(buttonPanel);
        //final Color panelcolor = new Color(125,227,147);
        //mainPanel.setBackground(panelcolor);
        mainPanel.setBackground(bg);
        this.getContentPane().setBackground(bg);
        customPanel title = new customPanel("Personal Details",30);
        setLayout(new GridBagLayout());
        setBackground(Color.blue);

        GridBagConstraints gb = new GridBagConstraints();

        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridx=1;
        gb.gridy=1;
        gb.gridwidth = 3;

        add(title,gb);

        gb.gridx=1;
        gb.gridy=2;
        gb.gridwidth =3;
        gb.gridheight = 3;

        gb.ipadx = 300;
        gb.ipady = 100;

        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));
        add(mainPanel,gb);
        
        setVisible(true);
        //setSize(700, 800);
        final Color bgcolor = new Color(211,245,206);
        //this.getContentPane().setBackground(Color.white);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        m = manager;
        JFrame t = this;
        bookButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	System.out.println((String)nAdults.getSelectedItem());
                JFrame f = new JFrame();
                String bookingName = name.getText().trim();
                String bookingEMail = email.getText().trim();
                String bookingGender;

                if(male.isSelected()){
                    bookingGender = "Male";
                }
                else if(female.isSelected()){
                    bookingGender = "Female";
                }
                else{
                    bookingGender = "";
                }
                
                try{
                	
                	if(validPhoneNumber.isValid(phoneNumber.getText()) == false || validAdhaarNumber.isValid(adhaar_number.getText()) == false || validEMail.isValid(email.getText()) == false){
                		throw new Exception("Invalid details");
                	}
                	
                    String bookingPhoneNumber = phoneNumber.getText().trim();
                    String bookingAdhaar = adhaar_number.getText().trim();
                    String bookingAge = "" + Integer.parseInt(age.getText().trim());
                    String bookingAddress = address.getText().trim();

                    if(bookingName.equals("") || bookingEMail.equals("") || bookingAddress.equals("") || bookingGender.equals("")){
                        JOptionPane.showMessageDialog(f, "Please Enter a valid / Complete Details ... ", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                    	new CardDetails(manager,getFrameDetails(),details1);
                    	t.dispatchEvent(new WindowEvent(t,WindowEvent.WINDOW_CLOSING));
                    }
                }
                catch(Exception except){
                    JOptionPane.showMessageDialog(f, "Please Enter valid  Details", "Error", JOptionPane.WARNING_MESSAGE);
                    except.printStackTrace();
                }
            }
        });
    }
    
    public HashMap<String, String> getFrameDetails() {
    	GroupButtonUtils tool = new GroupButtonUtils();
    	HashMap<String,String> details = new HashMap<>();
    	
    	details.put("name",name.getText());
    	details.put("phone number",phoneNumber.getText());
    	details.put("adhar", adhaar_number.getText());
    	details.put("email", email.getText());
    	details.put("number of adults",(String) nAdults.getSelectedItem());
    	details.put("number of children", (String)nChildren.getSelectedItem());
    	details.put("gender", GroupButtonUtils.getSelectedButtonText(gender));
    	details.put("age", age.getText());   	
    	details.put("address", address.getText());
    	
    	return details;
    }
    
    public static void main(String args[]){
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
    	
    	HashMap<String, String> trainDetails = new HashMap<>();
    	trainDetails.put("train name", "");
    	trainDetails.put("train class", "");
    	trainDetails.put("day of booking", "1");
    	trainDetails.put("month of booking", "Jan");
    	trainDetails.put("year of booking", "2021");
    	trainDetails.put("from", "");
    	trainDetails.put("to", "");
    	
        //new PassengerDetailsFrame(passengerDetails,trainDetails, new frameManager());
    }
}