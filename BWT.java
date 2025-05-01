// Java program to generate short url from integer id and 
// integer id back from short url. 
import java.util.*;
import java.lang.*;
import java.io.*;

public class BWT
{
    // Comment for nerdy purposes: https://en.wikipedia.org/wiki/Burrows%E2%80%93Wheeler_transform

    // Function to generate a short url from integer ID 
    public String idToShortURL(int n) 
    { 
        // Map to store 62 possible characters 
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); 
    
        StringBuffer shorturl = new StringBuffer(); 
    
        // Convert given integer id to a base 62 number 
        while (n > 0) 
        { 
            // use above map to store actual character 
            // in short url 
            shorturl.append(map[n % 62]);
            n = n / 62; 
        } 
    
        // Reverse shortURL to complete base conversion 
        return shorturl.reverse().toString(); 
    } 
    
    // Function to get integer ID back from a short url 
    public int shortURLtoID(String shortURL) 
    { 
        int id = 0; // initialize result 
    
        // A simple base conversion logic 
        for (int i = 0; i < shortURL.length(); i++) 
        { 
            if ('a' <= shortURL.charAt(i) && 
                       shortURL.charAt(i) <= 'z') 
            id = id * 62 + shortURL.charAt(i) - 'a'; 
            if ('A' <= shortURL.charAt(i) && 
                       shortURL.charAt(i) <= 'Z') 
            id = id * 62 + shortURL.charAt(i) - 'A' + 26; 
            if ('0' <= shortURL.charAt(i) && 
                       shortURL.charAt(i) <= '9') 
            id = id * 62 + shortURL.charAt(i) - '0' + 52; 
        } 
        return id; 
    } 
}

// This code is contributed by shubham96301