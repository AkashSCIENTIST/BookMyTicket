package projectFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class fareDetailsFrame extends JFrame {
	private static final Color bg = new Color(216, 245, 211);
	private class customLabel extends JPanel {
		JLabel x;
		customLabel(String s, int fontSize) {
			x= new JLabel(s);
			x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
			add(x);
		}
	}
	
	private class customPanel extends JPanel {
		JLabel l;
		JTextField x;
		customPanel(String name, String value, boolean editable) {
			this(name,value);
			x.setEditable(editable);
		}
		customPanel(String name,String value) {
			super(new GridLayout(1,2,10,10));
			setBackground(Color.white);
			setBackground(bg);
			l = new JLabel(name);
			x = new JTextField(value);
			l.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
			x.setHorizontalAlignment(JTextField.CENTER);
			x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
			add(l);
			add(x);
		}
		public String getText() {
			return x.getText();
		}
	}
	
	
	customLabel title;
	customPanel adult;
	customPanel children;
	
	JPanel detailsPanel;
	JPanel mainPanel;
	fareDetailsFrame() {
		setSize(400,250);
		setTitle("Penalty for Violations");
		setLayout(new BorderLayout(0,0));
		GridBagConstraints g = new GridBagConstraints();

		title = new customLabel("Fare Details",20);
		adult  = new customPanel("Adult : ","Rs. 250",false);
		children = new customPanel("Children : ","Rs. 250",false);
		title.setBackground(bg);
		getContentPane().setBackground(bg);
		
		detailsPanel = new JPanel(new GridLayout(3,1,10,10));
		detailsPanel.add(adult);
		detailsPanel.add(children);
//		detailsPanel.add(helmet);
		detailsPanel.setBackground(bg);
		mainPanel = new JPanel(new GridBagLayout());
		
		g.gridx = 1;
		g.gridy = 0;
		g.ipadx =250;
		g.ipady =50;
//		g.gridwidth = 10;
		
		mainPanel.add(title,g);
		
		g.gridx = 0;
		g.gridy = 2;
		g.ipadx =0;
		g.ipady =0;
		g.gridwidth = 3;

		mainPanel.add(detailsPanel,g);
		mainPanel.setBackground(bg);
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));

		add(mainPanel,BorderLayout.CENTER);
		
		setVisible(true);
	}
	public static void main(String args[]) {
		new fareDetailsFrame();
	}
}
