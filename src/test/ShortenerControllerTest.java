package shortlink.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import shortlink.service.ShortenerService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShortenerController.class)
class ShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortenerService shortenerService;

    @Test
    void encode_shouldReturnShortUrl() throws Exception {
        when(shortenerService.shorten(anyString())).thenReturn("http://short.est/abc123");

        mockMvc.perform(post("/api/encode")
                .contentType("application/json")
                .content("{\"url\":\"https://example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("http://short.est/abc123"));
    }

    @Test
    void decode_shouldReturnOriginalUrl() throws Exception {
        when(shortenerService.expand(anyString())).thenReturn("https://example.com");

        mockMvc.perform(post("/api/decode")
                .contentType("application/json")
                .content("{\"url\":\"http://short.est/abc123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("https://example.com"));
    }
}