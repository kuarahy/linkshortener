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
            System.out.println("Solventum URL Shortener CLI (type 'exit' to quit) - by Lucas Perez 🪽✨");
            
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("exit")) break;
                
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2) continue;
                
                String command = parts[0];
                String url = parts[1];
                
                try {
                    if (command.equalsIgnoreCase("shorten")) {
                        System.out.println("Short URL: " + shortenerService.shorten(url));
                    } else if (command.equalsIgnoreCase("expand")) {
                        System.out.println("Original URL: " + shortenerService.expand(url));
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            scanner.close();
        };
    }
}