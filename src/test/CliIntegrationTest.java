package shortlink;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import shortlink.service.ShortenerService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = "rate.limit.max-concurrent=1")
class CliIntegrationTest {

    @Autowired
    private ShortLinkApplication application;

    @MockBean
    private ShortenerService shortenerService;

    @Test
    void cliShouldProcessCommands() {
        // Mock service responses
        when(shortenerService.shorten("https://example.com")).thenReturn("http://short.est/abc");
        when(shortenerService.expand("http://short.est/abc")).thenReturn("https://example.com");

        // Simulate user input
        String input = "shorten https://example.com\nexpand http://short.est/abc\neaster egg\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Run the CLI
        application.main(new String[]{});

        // Verify interactions
        verify(shortenerService).shorten("https://example.com");
        verify(shortenerService).expand("http://short.est/abc");
    }
}