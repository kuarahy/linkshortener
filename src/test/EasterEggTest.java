package shortlink;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EasterEggTest {

    @Test
    void shouldDisplayEasterEgg() {
        // Redirect System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Simulate user input
        String input = "easter egg\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the CLI
        ShortLinkApplication.main(new String[]{});

        // Verify output
        assertTrue(outContent.toString().contains("See ya, Space Cowboy"));
    }
}