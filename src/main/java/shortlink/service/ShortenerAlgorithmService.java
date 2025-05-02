package shortlink.service;

import org.springframework.stereotype.Service;

@Service
public class ShortenerAlgorithmService {
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE62_SIZE = 62;

    public String idToShortURL(int n) {
        if (n == 0) return "";

        /*        
            StringBuilder instead of StringBuffer since it would perform better for consistent string permutations.
            While StringBuffer is thread-safe, this class wouldn't be a problem for threading, since you would have to instatiate it 
            per call, and not trying to change the String on the fly on the same thread.
        */
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % BASE62_SIZE;
            sb.append(BASE62_CHARS.charAt(remainder));
            // Appending characters in reverse order by parsing the remainder to remove some unnecessary reverse() calls
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