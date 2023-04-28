import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;

public class btcEmail {
    
    // Email settings
    static final String FROM_EMAIL = "your_email_address@example.com";
    static final String FROM_PASSWORD = "your_email_password";
    static final String TO_EMAIL = "recipient_email_address@example.com";
    static final String SUBJECT = "Bitcoin Price Alert";
    static final String MESSAGE = "Bitcoin price has dropped below your desired price.";

    public static void main(String[] args) {
        
        // Specify the URL of the API endpoint that provides the bitcoin price
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        
        // Specify the desired price
        double desiredPrice = 40000.00;
        
        try {
            // Open a connection to the API endpoint URL
            URL api = new URL(url);
            Scanner scanner = new Scanner(api.openStream());
            
            // Read the response from the API endpoint
            String response = scanner.useDelimiter("\\Z").next();
            
            // Extract the bitcoin price from the response
            String[] lines = response.split("\n");
            String priceLine = lines[5];
            String price = priceLine.split(": ")[1].replace(",", "");
            double currentPrice = Double.parseDouble(price);
            
            // Print the bitcoin price to the console
            System.out.println("Current Bitcoin Price: " + price);
            
            // Close the scanner
            scanner.close();
            
            // Check if the current price is below the desired price
            if (currentPrice < desiredPrice) {
                // Send an email to the recipient
                sendEmail();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to send an email
    private static void sendEmail() {
        // Set the email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        // Create a session with the email server
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });
        
        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
            message.setSubject(SUBJECT);
            message.setText(MESSAGE);
            
            // Send the email
            Transport.send(message);
            
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}