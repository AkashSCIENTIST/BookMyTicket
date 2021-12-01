package emailConnector;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
//import java.sql.*;
import java.time.LocalDate;
public class emailConnector {

    private static String USER_NAME = "<GMAIL_USER_NAME>";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "<GMAIL_PASSWORD>"; // GMail password
    private static String RECIPIENT = "<RECEIPIANT_TESTING_MAIL>";

    public static void sendEmail(HashMap<String, String> details/*String []*/,String email) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { email }; // list of recipient email addresses
        String subject = "Ticket Reservation Successful";
        LocalDate now = LocalDate.now();
        String body = "The ticket that you have reserved on "+now.getDayOfWeek()+", "+now.getDayOfMonth()+"/"+now.getMonthValue()+"/"+now.getYear()+" has been successfully reserved.\n Please find the ticket details below:";
        System.out.println(body);
        body+="\n\n\nTICKET ID : "+details.get("TICKET_ID")+"\nPhone Number : "+details.get("BOOKING_NUMBER")
        		+"\nTrain Name : "+details.get("TRAIN_NAME")
        		+"\nNumber of Persons : "+details.get("PERSONS")+"\nDate of departure : "+details.get("DATE_OF_BOOKING")
        		+"\nTime of departure : "+details.get("DEPARTURE_TIME")+"\nFrom : "+details.get("FROM")+"\nTo : "+details.get("TO")
        		+"\nSeat Number : "+details.get("SEAT_NUMBER")+"\nSeat Class : "+details.get("SEAT_CLASS");
        
        sendFromGMail(from, pass, to, subject, body);
    }
    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
//            MimeBodyPart text = new MimeBodyPart();
//            text.setText(body);
//            MimeBodyPart attachment = new MimeBodyPart();
//            DataSource src = new FileDataSource(attachments[0]);
//            attachment.setDataHandler(new DataHandler(src));
//            attachment.setFileName(attachments[0]);
//            
//            Multipart m = new MimeMultipart();
//            m.addBodyPart(text);
//            m.addBodyPart(attachment);
            
            
            
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}