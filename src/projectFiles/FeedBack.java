package projectFiles;

import javax.swing.*;    
import java.awt.event.*;
import java.awt.*;    
import javax.swing.border.Border;
class FeedBack extends JFrame implements ActionListener{    
JRadioButton rb1,rb2,rb3,rb4,rb5;    
JButton b;    
JTextArea tf1;
FeedBack(){    
super("Feedback Form");    
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Icon _1s = new ImageIcon("src\\worst.jpeg");
rb1=new JRadioButton(_1s);    
//rb1.setPreferredSize(new Dimension(50,50));
Icon _2s = new ImageIcon("src\\bad.JPEG");   
rb2=new JRadioButton(_2s);    
Icon _3s = new ImageIcon("src\\meh.jpeg");
rb3=new JRadioButton(_3s);    
Icon _4s = new ImageIcon("src\\nice.jpeg");
rb4=new JRadioButton(_4s);    
Icon _5s = new ImageIcon("src\\amazing.jpeg");
rb5=new JRadioButton(_5s);    
      
JLabel label = new JLabel("How was your overall experience?");
label.setBounds(520,15,500,150);   
label.setFont(new Font("SanSerif", Font.BOLD, 30));
JPanel panel=new JPanel();  
panel.setBackground(new Color(216, 245, 211));
panel.add(rb1); panel.add(rb2);panel.add(rb3); panel.add(rb4);panel.add(rb5);
panel.setBounds(0,150,1600,250);    

tf1=new JTextArea();  
tf1.setBounds(260,400,1080,250);  
tf1.setFont(new Font("SanSerif", Font.PLAIN, 20));
tf1.setBorder(BorderFactory.createLineBorder(Color.black));


ButtonGroup bg=new ButtonGroup();    
bg.add(rb1);bg.add(rb2);bg.add(rb3);bg.add(rb4); bg.add(rb5);    
add(label); 
add(tf1);
b=new JButton("Submit");     
b.addActionListener(this);
b.setBounds(730,700,150,50);
b.setFont(new Font("SanSerif", Font.BOLD, 25));
add(panel);
add(b);  
this.setExtendedState(JFrame.MAXIMIZED_BOTH);
setLayout(null);
this.getContentPane().setBackground(new Color(216, 245, 211));
setVisible(true);    
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 


}    
public void actionPerformed(ActionEvent e){    
if(rb1.isSelected()){    
JOptionPane.showMessageDialog(this,"Thank you for the feedback");    
}    
if(rb2.isSelected()){    
JOptionPane.showMessageDialog(this,"Thank you for the feedback");    
}    
if(rb3.isSelected()){    
JOptionPane.showMessageDialog(this,"Thank you for the feedback");    
}    
if(rb4.isSelected()){    
JOptionPane.showMessageDialog(this,"Thank you for the feedback");    
}    
if(rb5.isSelected()){    
JOptionPane.showMessageDialog(this,"Thank you for the feedback");    
}    
}   

public static void main(String args[]){    
new FeedBack();    
}}   
