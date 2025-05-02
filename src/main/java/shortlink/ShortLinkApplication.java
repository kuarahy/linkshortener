package shortlink;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shortlink.service.ShortenerService;
import java.util.Scanner;

@SpringBootApplication
public class ShortLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);
    }

    @Bean
    public CommandLineRunner cliRunner(ShortenerService shortenerService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            printWelcomeMessage();

            while (true) {
                System.out.print("\n> ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                
                if (input.equalsIgnoreCase("easter egg")) {
                    System.out.println("See ya, Space Cowboy");
                    printWelcomeMessage();
                    continue;
                }
                
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2) {
                    System.out.println("Invalid command. Please use one of the following:");
                    printCommands();
                    continue;
                }
                
                String command = parts[0].toLowerCase();
                String url = parts[1];
                
                try {
                    switch (command) {
                        case "shorten":
                            System.out.println("Short URL: " + shortenerService.shorten(url));
                            break;
                        case "expand":
                            System.out.println("Original URL: " + shortenerService.expand(url));
                            break;
                        default:
                            System.out.println("Unknown command. Available commands:");
                            printCommands();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            scanner.close();
        };
    }

    private static void printWelcomeMessage() {
        System.out.println("\n=== URL Shortener CLI ===");
        System.out.println("Available commands:");
        printCommands();
        System.out.println("Type 'exit' to quit");
        System.out.println("Try 'easter egg' for a surprise!");
        System.out.println("========================");
    }

    private static void printCommands() {
        System.out.println("  shorten <url>  - Create short URL");
        System.out.println("  expand <url>   - Get original URL");
        System.out.println("  easter egg     - Special surprise");
    }
}