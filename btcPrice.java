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

The program uses the Coindesk API to retrieve the current bitcoin price in USD. 
It connects to the API endpoint using a URL object, reads the response using a Scanner object, 
extracts the price from the response, and prints it to the console.

Note that in order for this program to work, you must have an active internet connection 
and the Coindesk API must be functioning properly. Additionally, you will need to import the necessary 
Java libraries for this program to compile and run