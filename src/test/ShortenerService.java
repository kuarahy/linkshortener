package shortlink.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShortenerServiceTest {
    private ShortenerService service;
    private final ShortenerAlgorithmService algorithmService = new ShortenerAlgorithmService();

    @BeforeEach
    void setUp() {
        service = new ShortenerService(algorithmService);
    }

    @Test
    void shorten_shouldReturnSameShortUrlForSameInput() {
        String url = "https://example.com";
        String first = service.shorten(url);
        String second = service.shorten(url);
        assertEquals(first, second);
    }

    @Test
    void expand_shouldReturnOriginalUrl() {
        String original = "https://example.com";
        String shortUrl = service.shorten(original);
        assertEquals(original, service.expand(shortUrl));
    }

    @Test
    void expand_shouldHandleUnknownUrls() {
        assertEquals("URL not found", service.expand("http://short.est/unknown"));
    }
}