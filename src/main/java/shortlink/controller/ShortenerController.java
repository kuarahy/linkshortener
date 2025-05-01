package shortlink.service;

import org.springframework.stereotype.Service;

@Service
public class ShortenerController {
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE62_SIZE = 62;

    public String idToShortURL(int n) {
        if (n == 0) return "";
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % BASE62_SIZE;
            sb.append(BASE62_CHARS.charAt(remainder));
            n /= BASE62_SIZE;
        }
        return sb.reverse().toString();
    }

    public int shortURLToID(String shortURL) {
        if (shortURL == null || shortURL.isEmpty()) return 0;
        int id = 0;
        for (int i = 0; i < shortURL.length(); i++) {
            char c = shortURL.charAt(i);
            int value = -1;
            if (c >= 'a' && c <= 'z') value = c - 'a';
            else if (c >= 'A' && c <= 'Z') value = 26 + (c - 'A');
            else if (c >= '0' && c <= '9') value = 52 + (c - '0');
            if (value == -1) return -1;
            id = id * BASE62_SIZE + value;
        }
        return id;
    }
}