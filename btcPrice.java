import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class BitcoinPrice {

    public static void main(String[] args) {
        
        // Specify the URL of the API endpoint that provides the bitcoin price
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        
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
            
            // Print the bitcoin price to the console
            System.out.println("Current Bitcoin Price: " + price);
            
            // Close the scanner
            scanner.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}