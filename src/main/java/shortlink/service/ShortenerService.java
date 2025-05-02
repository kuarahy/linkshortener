package shortlink.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShortenerService {
    private static final String BASE_URL = "http://short.est/";
    private final Map<String, String> urlToKey = new HashMap<>();
    private final Map<String, String> keyToUrl = new HashMap<>();
    private final ShortenerAlgorithmService algorithmService;

    public ShortenerService(ShortenerAlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    public String shorten(String originalUrl) {
        return urlToKey.computeIfAbsent(originalUrl, url -> {
            String key = algorithmService.idToShortURL(url.hashCode());
            keyToUrl.put(key, url);
            return BASE_URL + key;
        });
    }

    public String expand(String shortUrl) {
        String key = shortUrl.replace(BASE_URL, "");
        return keyToUrl.getOrDefault(key, "URL not found");
    }
}
