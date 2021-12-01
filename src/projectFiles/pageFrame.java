package projectFiles;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
public class pageFrame extends JFrame {
	private class customPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JLabel t;
		customPanel(String s,int fontSize) {
			t = new JLabel(s);
			t.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
			add(t);
			setBackground(new Color(216, 245, 211));
		}
	}
	private class text extends JTextField {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		text(Border border) {
			setBorder(border);
		}
	}
	customPanel title;
	customPanel ticketId;
	customPanel ticketNumber;
	customPanel aadharLabel;
	text id;
	text number;
	text aadhar;
	JPanel mainPanel;
	JPanel subPanel;
	JButton submit;
	pageFrame(frameManager manager) {
		manager.setFrameIcon(this);
		title = new customPanel("Ticket Status",30);
		setLayout(new BorderLayout(0,0));
		GridBagConstraints g = new GridBagConstraints();
		setSize(600,450);
		id = new text(BorderFactory.createLineBorder(new Color(213,195,117),2));
		number = new text(BorderFactory.createLineBorder(new Color(213,195,117),2));
		aadhar = new text(BorderFactory.createLineBorder(new Color(213,195,117),2));
		ticketId = new customPanel("Enter Ticket ID",18);
		ticketNumber = new customPanel("Enter Phone Number",18);
		aadharLabel = new customPanel("Enter Adhar Number",18);
		submit = new JButton("Check Status");
		subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(3,2,10,10));
		subPanel.add(ticketId);
		subPanel.add(id);
		subPanel.add(ticketNumber);
		subPanel.add(number);
		subPanel.add(aadharLabel);
		subPanel.add(aadhar);
		subPanel.setBackground(new Color(216, 245, 211));
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		g.fill = GridBagConstraints.HORIZONTAL;
		g.gridx=1;
		g.gridy=0;
		g.gridheight=1;
		g.gridwidth=3;
		g.ipady = 100;
		mainPanel.add(title,g);
		
		g.gridx=1;
		g.gridy=2;
		g.gridheight = 2;
		g.gridwidth=3;
		g.ipady=0;
		mainPanel.add(subPanel,g);
		submit.setPreferredSize(new Dimension(150,35));
		JPanel filler  = new JPanel();
		filler.setPreferredSize(new Dimension(300,40));
		filler.setBackground(new Color(216, 245, 211));
		g.gridx = 3;
		g.gridy = 6;
		
		mainPanel.add(filler,g);
		g.fill = GridBagConstraints.NONE;
		g.gridx = 3;
		g.gridy = 8;
		g.gridheight=3;
		g.gridwidth=1;
		JFrame f = this;
		submit.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent t)  {

				try {
					manager.initReceiptFrame(getFrameDetails());
					System.out.println(getFrameDetails());
					f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(f, "Record Not Found!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		mainPanel.add(submit,g);
		mainPanel.setBackground(new Color(216, 245, 211));
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),3));
		
		add(mainPanel,BorderLayout.CENTER);
		setVisible(true);
	}
	public HashMap<String,String> getFrameDetails() {
		HashMap<String, String> details = new HashMap<>();
		details.put("id", id.getText());
		details.put("phone number", number.getText());
		details.put("adhar", aadhar.getText());
		return details;
	}
	public static void main(String s[]) {
		frameManager t = new frameManager();
		new pageFrame(t);
	}
}