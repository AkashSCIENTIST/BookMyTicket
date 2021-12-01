package printTools;
import java.io.*;
import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.table.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.net.*;
import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Image;

//import projectFiles.displayDetailsFrame.customLabel;
//import projectFiles.displayDetailsFrame.text;

import com.lowagie.text.Image;
 class customLabel extends JPanel {
	JLabel x;
	customLabel(String s, int fontSize) {
		x= new JLabel(s);
		x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
		setBackground(new Color(245, 223, 223));
		add(x);
	}
}
class customPanel extends JPanel {
	customLabel t;
	text x;
	customPanel(String s,String value, int fontSize) {
		setPreferredSize(new Dimension(500,50));
		t = new customLabel(s,fontSize);
//		t.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
		x=new text(value);
		x.setHorizontalAlignment(JTextField.CENTER);
		x.setFont(new Font(Font.SANS_SERIF,Font.BOLD,fontSize));
		setLayout(new GridLayout(1,2,20,10));
		add(t);
		add(x);
		setBackground(new Color(245, 223, 223));
	}
}
class text extends JTextField {
	text(String s) {
		this.setText(s);
		this.setBorder(BorderFactory.createLineBorder(new Color(43, 40, 28),1));
		this.setEditable(false);
	}		
}

public class PrintTool{
	
	
	public static BufferedImage createImage(JPanel panel) {
	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    g.dispose();
	    return bi;
	}
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean JPanelToPdf(JPanel panel, String path, String ticketID) {
		try {
			Document document=new Document();
			JFileChooser fileChooser = new JFileChooser();
			Rectangle sizeVar = new Rectangle(panel.getWidth(), panel.getHeight());
			document.setPageSize(sizeVar);
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			JFrame frame = new JFrame("Save Ticket");
			int option = fileChooser.showOpenDialog(frame);
			File file = fileChooser.getSelectedFile();
			PdfWriter.getInstance(document,new FileOutputStream(file.getAbsolutePath() + "\\" + ticketID + ".pdf"));
			document.open();
			File outputfile = new File(file.getAbsolutePath() + "\\" +  ticketID + ".jpg");
			BufferedImage bufferedImage = createImage(panel);
			ImageIO.write(bufferedImage, "jpg", outputfile);
			Image image1 = Image.getInstance(file.getAbsolutePath() + "\\" +  ticketID + ".jpg");
			image1.setAlignment(Element.ALIGN_CENTER);
			image1.scaleAbsolute(panel.getWidth(), panel.getHeight());
			document.add(image1);
			document.close();
			openWebpage("file:///" + file.getAbsolutePath() + "\\" + ticketID + ".pdf");
			return true;
		}
		catch(Exception ex) {
			return false;
		}
		
	}
	public static void main(String args[]) {
		JFrame f = new JFrame();
		f.setSize(500,500);
		customPanel t = new customPanel("123","123",20);
		JButton y = new JButton("submit");
		f.setLayout(new BorderLayout(0,0));
		f.add(t,BorderLayout.CENTER);
		f.add(y,BorderLayout.SOUTH);
		f.setVisible(true);
		y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintTool.JPanelToPdf(t, "D:\\project downloads", "dihb");
			}
		});
	}
}
