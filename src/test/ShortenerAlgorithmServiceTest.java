package shortlink.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShortenerAlgorithmServiceTest {
    private final ShortenerAlgorithmService service = new ShortenerAlgorithmService();

    @Test
    void idToShortURL_shouldReturnValidString() {
        assertEquals("a", service.idToShortURL(0));
        assertEquals("b", service.idToShortURL(1));
        assertEquals("A", service.idToShortURL(26));
        assertEquals("0", service.idToShortURL(52));
        assertEquals("ba", service.idToShortURL(62));
    }

    @Test
    void shortURLToID_shouldReturnOriginalNumber() {
        assertEquals(0, service.shortURLToID("a"));
        assertEquals(1, service.shortURLToID("b"));
        assertEquals(26, service.shortURLToID("A"));
        assertEquals(52, service.shortURLToID("0"));
        assertEquals(62, service.shortURLToID("ba"));
    }

    @Test
    void shortURLToID_shouldHandleInvalidChars() {
        assertEquals(-1, service.shortURLToID("!"));
        assertEquals(-1, service.shortURLToID("ab!c"));
    }
}