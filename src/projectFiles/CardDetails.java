package projectFiles;
import javax.swing.*;
import emailConnector.emailConnector;
import java.util.*;
import javax.swing.border.Border;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;

class validCardNumber{
	public static boolean isValid(String input) {
		return Pattern.matches("\\d{16}", input);
	}
}

public class CardDetails extends JFrame {
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
	private class password extends JPasswordField {
		password() {
			this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
			setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
		}
	}
	private class customPanel extends JLabel {
	    customPanel(String s,int fontSize) {
	    	super(s);
	    	Font font1 = new Font("Open Sans Medium",  Font.BOLD, fontSize);
	        setFont(font1);
	        setBackground(bg);
	    }
	}
	private class customRadioButton extends JRadioButton {
	    customRadioButton(String s) {
	    	super(s);
	        setFont(font2);
	        setBackground(bg);
	    }
	}
	customPanel cardNumberLabel, cvvLabel, cardTypeLabel, cardNameLabel, headingLabel, cardBrand;
	JButton pay;
	text cardNumberText, cardNameText, cvvText;
	customRadioButton creditCard, debitCard, mastercard, visaCard, masteroCard, ruPay;
	password cvvPassword;
	ButtonGroup creditDebit, cardCompany;
	JPanel componentPanel, cardTypePanel, cardCompanyPanel, headingPanel, buttonPanel;
    static Font font1 = new Font("Open Sans Medium",  Font.BOLD, 30);
    static Font font2 = new Font("Open Sans Medium", Font.BOLD, 15);
    static Font font3 = new Font("Open Sans Medium", Font.BOLD, 19);
	CardDetails(frameManager manager,HashMap<String,String> personalDetails,HashMap<String,String> trainDetails){
		super("Pay Using Card");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		cvvPassword = new password();
		manager.setFrameIcon(this);
		getContentPane().setBackground(bg);
		cardNumberLabel = new customPanel("Card Number : ",17);
		cvvLabel = new customPanel("CVV : ",17);
		cardTypeLabel = new customPanel("Card Type : ",17);
		cardNameLabel = new customPanel("Card Holder name : ",17);
		headingLabel = new customPanel("Pay by card",30);
		cardBrand = new customPanel("Card Company : ",17);

//        cardNumberLabel.setFont(font2);
//        cvvLabel.setFont(font2);
//        cardTypeLabel.setFont(font2);
//        cardNameLabel.setFont(font2);
//        headingLabel.setFont(font2);
//        cardBrand.setFont(font2);
		
		pay = new JButton("Pay");
        pay.setFont(font3);
        pay.setPreferredSize(new Dimension(200, 40));
		pay.setBorder(BorderFactory.createLineBorder(new Color(108,93,24),2));
		creditCard = new customRadioButton("Credit card");
		debitCard = new customRadioButton("Debit card");
		mastercard = new customRadioButton("MasterCard");
		visaCard = new customRadioButton("VISA");
		masteroCard = new customRadioButton("Mastero");
		ruPay = new customRadioButton("RuPay");

        creditCard.setFont(font2);
        debitCard.setFont(font2);
        visaCard.setFont(font2);
        mastercard.setFont(font2);
        masteroCard.setFont(font2);
        ruPay.setFont(font2);
		
		cardNumberText = new text("");
		cardNameText = new text("");
		cvvText = new text("");

        cardNumberText.setFont(font2);
        cardNameText.setFont(font2);
        cvvText.setFont(font2);
		
		creditDebit =  new ButtonGroup();
		creditDebit.add(creditCard);
		creditDebit.add(debitCard);
		
		cardTypePanel = new JPanel();
		cardTypePanel.add(creditCard);
		cardTypePanel.add(debitCard);
		cardTypePanel.setBackground(bg);
		cardCompany = new ButtonGroup();
		cardCompany.add(mastercard);
		cardCompany.add(masteroCard);
		cardCompany.add(ruPay);
		cardCompany.add(visaCard);

		cardCompanyPanel = new JPanel();
		cardCompanyPanel.add(mastercard);
		cardCompanyPanel.add(masteroCard);
		cardCompanyPanel.add(ruPay);
		cardCompanyPanel.add(visaCard);
		cardCompanyPanel.setBackground(bg);
        headingPanel = new JPanel();
        headingPanel.add(headingLabel);
        headingLabel.setFont(font1);

        buttonPanel = new JPanel();
        buttonPanel.add(pay);
		
		componentPanel = new JPanel();
		componentPanel.add(cardNumberLabel);
		componentPanel.add(cardNumberText);
		componentPanel.add(cvvLabel);
		//componentPanel.add(cvvText);
		componentPanel.add(cvvPassword);
		componentPanel.add(cardNameLabel);
		componentPanel.add(cardNameText);
		componentPanel.add(cardTypeLabel);
		componentPanel.add(cardTypePanel);
		componentPanel.add(cardBrand);
		componentPanel.add(cardCompanyPanel);
		componentPanel.setLayout(new GridLayout(5, 3,3,8));
		
		//componentPanel.setPreferredSize(new Dimension(600,200));
		//componentPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		setVisible(true);
		//setSize(860, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints gb = new GridBagConstraints();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.ipadx = 90;
        gb.ipady = 120;
        
        gb.gridx = 1;
        gb.gridy = 1;
        gb.gridwidth = 3;
        //gb.gridheight=2;
        headingPanel.setBackground(bg);
        mainPanel.add(headingPanel, gb);

        
        //filler.setPreferredSize(new Dimension(100,20));
        gb.gridx = 1;
        gb.gridy = 3;
        gb.gridwidth = 3;
        gb.ipady = 30;
        gb.ipadx=0;
        componentPanel.setBackground(bg);
        mainPanel.add(componentPanel, gb);

        gb.gridx = 1;
        gb.gridy = 4;
        gb.ipadx=0;
        gb.ipady = 0;
        gb.gridwidth = 3;
        buttonPanel.setBackground(bg);
        mainPanel.add(buttonPanel, gb);
        setLayout(new BorderLayout(0,0));
        mainPanel.setBackground(bg);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));
        add(mainPanel,BorderLayout.CENTER);
        CardDetails t = this;
//        addWindowListener(new WindowAdapter() {
//        	public void windowClosing(WindowEvent w) {
//                try {
//                	manager.init(personalDetails, trainDetails);
//                	manager.pushToDB();
//                	manager.printValues();
//                }
//                catch(SQLException e1) {
//                	e1.printStackTrace();
//                }
//
//        	}
//        	
//        });
        trainDetails.put("status", "unpaid");
        pay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String cardNumber = cardNumberText.getText();
                    //String cardCVV = "" + Integer.parseInt(cvvText.getText());
                    String cardCVV = "" + Integer.parseInt(new String(cvvPassword.getPassword()));
                    String cardName = cardNameText.getText();
                    String cardType;
                    String cardBrand;

                    if(mastercard.isSelected()) cardBrand = "Master Card";
                    else if(masteroCard.isSelected()) cardBrand = "Mastero";
                    else if(ruPay.isSelected()) cardBrand = "RuPay";
                    else if(visaCard.isSelected()) cardBrand = "Visa";
                    else cardBrand = "";

                    if(creditCard.isSelected()) cardType = "Credit Card";
                    else if(debitCard.isSelected()) cardType = "Debit Card";
                    else cardType = "";

                    if(cardBrand.equals("") || cardType.equals("") || cardName.equals("") || cardCVV.equals("") || validCardNumber.isValid(cardNumberText.getText()) == false){
                        throw new Exception("Select card brand / type ... ");
                    }

                }
                catch(Exception ex){
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Please enter valid card details", "Error", JOptionPane.WARNING_MESSAGE);
                    
                    return;
                }
                try {
                	trainDetails.put("status","paid");
                	manager.init(personalDetails, trainDetails);
                	manager.pushToDB();
                	manager.printValues();
//                	manager.makeFrameVisible();
                	manager.initProcess(personalDetails.get("email"));
                	t.dispose();
                }
                catch(Exception e1) {
                	e1.printStackTrace();
                }
            }
        });
	
	}
	
//	public static void main(String args[]) {
//		new CardDetails();
//	}
}