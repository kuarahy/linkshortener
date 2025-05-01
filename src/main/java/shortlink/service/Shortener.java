package shortlink.service;

import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class Shortener {
    // Comment for nerdy purposes: https://en.wikipedia.org/wiki/Burrows%E2%80%93Wheeler_transform

    /* 
        Putting these in finals because I don't like magic numbers personally, but also
        the big final string would perform better as a final if called consistently for 
        an app that would be doing big N calls for it
    */
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE62_SIZE = 62;

    // I like small names for functions, easier to read and remember
    public String idToShortURL(int n) 
    {   
        // Empty check
        if (n == 0) {
            return "";
        }

        /*        
            StringBuilder instead of StringBuffer since it would perform better for consistent string permutations.
            While StringBuffer is thread-safe, this class wouldn't be a problem for threading, since you would have to instatiate it 
            per call, and not trying to change the String on the fly on the same thread.
        */

        StringBuilder sb = new StringBuilder(); 
    
        // Convert given integer id to a base 62 number 
        while (n > 0) {
            int remainder = n % BASE62_SIZE;
            sb.append(BASE62_CHARS.charAt(remainder));
            // Appending characters in reverse order by parsing the remainder to remove some unnecessary reverse() calls
            n /= BASE62_SIZE;
        }
        return sb.reverse().toString();
    } 
    
    // Function to get integer ID back from a short url 
    public int shortURLToID(String sb) {
        if (sb == null || sb.isEmpty()) {
            return 0;
        }

        int id = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int value = -1;

            // Find the index of the character in BASE62_CHARS
            if (c >= 'a' && c <= 'z') {
                value = c - 'a';
            } else if (c >= 'A' && c <= 'Z') {
                value = 26 + (c - 'A');
            } else if (c >= '0' && c <= '9') {
                value = 52 + (c - '0');
            }

            if (value == -1) { // Invalid character
                return -1; // Return error, for the sake of this exercise, I'll just leave it as a -1
            }

            id = id * BASE62_SIZE + value;
        }
        return id;
    }

    public String publishErrors() {
        int statusCode = shortURLToID("");
        
        switch (statusCode) {
            case 0:
                return "This link has no relation in our database. You could make one right now. <3 ";
                
            case -1:
                return "A character in your link is invalid. For a list of valid characters, click here.";
                
            default:
                // Handle other status codes if necessary, for future proofing (that will never be used, but you know)
                return "An unexpected error occurred with status code: " + statusCode;
        }
    }

    public void reference() {
        System.out.println ("See ya, Space Cowboy");
    }
}
