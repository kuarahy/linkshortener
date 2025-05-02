package shortlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import shortlink.service.Shortener;

import java.util.Scanner;

@SpringBootApplication
public class ShortLinkApplication {
    public static void main(String[] args) {
        // Start Spring application
        ConfigurableApplicationContext context = SpringApplication.run(ShortLinkApplication.class, args);
        
        // Get the Shortener bean
        Shortener shortener = context.getBean(Shortener.class);
        
        // If no arguments passed, start CLI
        if (args.length == 0) {
            startCLI(shortener);
        }
    }

    private static void startCLI(Shortener shortener) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Solventum's URL Shortener CLI by Lucas Perez, welcome ===");
        System.out.println("Commands:");
        System.out.println("  shorten <URL>  - Create short URL");
        System.out.println("  expand <URL>   - Get original URL");
        System.out.println("  exit           - Quit the program");
        System.out.println("=========================");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split("\\s+", 2);
            if (parts.length < 2) {
                System.out.println("Invalid command. Usage: [shorten|expand] <URL>");
                continue;
            }

            String command = parts[0].toLowerCase();
            String url = parts[1];

            try {
                switch (command) {
                    case "shorten":
                        String shortUrl = "http://short.est/" + shortener.idToShortURL(url.hashCode());
                        System.out.println("Short URL: " + shortUrl);
                        break;
                    case "expand":
                        String code = url.replace("http://short.est/", "");
                        int id = shortener.shortURLToID(code);
                        System.out.println("Original URL: https://example.com/original-" + id);
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }
}