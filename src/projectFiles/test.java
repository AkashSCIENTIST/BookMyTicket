package projectFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import databaseTools.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class test extends JFrame {
	private static final Color bg = new Color(216, 245, 211);
	private class customLabel extends JPanel {
		JLabel x;
		customLabel(String s, int fontSize) {
			x= new JLabel(s);
			x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
			setBackground(bg);
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
	
	
	JRadioButton[] trains;
	ButtonGroup selectedTrain;
	customLabel title;
	JPanel detailsPanel;
	JPanel mainPanel;
	test() throws SQLException  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,250);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Penalty for Violations");
		setLayout(new BorderLayout(0,0));
		GridBagConstraints g = new GridBagConstraints();

		trains = new JRadioButton[100];
		title = new customLabel("Trains",30);
		databaseManager dbManager = new databaseManager();
		
		selectedTrain = new ButtonGroup();
		
		ResultSet rs = dbManager.getAllTrainInfo();
		int i=0;
		JPanel trainsPanel = new JPanel(new GridLayout(20,5,10,10));
		while(rs.next()&&i<100) {
//			System.out.println(rs.getString("name"));
			trains[i] = new JRadioButton(rs.getString("id"));
			trains[i].setBackground(bg);
			trains[i].setFont(new Font(Font.SANS_SERIF,Font.BOLD,13));
//			System.out.println(trains[i]);
			selectedTrain.add(trains[i]);
			
			trainsPanel.add(trains[i]);
			i++;
			
		}
		
		trainsPanel.setPreferredSize(new Dimension(1400,500));
		trainsPanel.setBackground(bg);
		JScrollPane sc = new JScrollPane(trainsPanel);
		
		JPanel scr = new  JPanel(new BorderLayout(0,0));
		
		scr.add(sc,BorderLayout.CENTER);
		
		JButton submit = new JButton("Submit");
		
		mainPanel = new JPanel(new GridBagLayout());
		
		g.gridx = 1;
		g.gridy = 0;
		g.ipadx =250;
		g.ipady =50;
		g.gridwidth = 5;
		
		mainPanel.add(title,g);
		
		g.gridx = 0;
		g.gridy = 2;
		g.ipadx =0;
		g.ipady =0;
		g.gridwidth = 3;

		
		mainPanel.add(scr,g);
		
		JPanel filler = new JPanel();
		
		g.gridx = 2;
		g.gridy = 5;
		g.ipadx =50;
		g.ipady =20;
		g.gridwidth = 1;

		filler.setBackground(bg);
		mainPanel.add(filler,g);

		g.gridx = 2;
		g.gridy = 6;
		g.ipadx =50;
		g.ipady =10;
		g.gridwidth = 1;
		
		
		mainPanel.add(submit,g);
		mainPanel.setBackground(bg);
		mainPanel.setBorder(BorderFactory.createLineBorder(new Color(213,195,117),5));

		add(mainPanel,BorderLayout.CENTER);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				String trainID = GroupButtonUtils.getSelectedButtonText(selectedTrain);
				try {
					DisplayDatabaseTable.display(dbManager.getSeatInfoForTrain(trainID));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		setVisible(true);
	}
	public static void main(String args[]) throws SQLException {
		new test();
	}
}
