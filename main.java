public class Main {
     public static void Main (String[] args) throws IOException {
        int n = 12345; 
        String shorturl = idToShortURL(n); 
        System.out.println("Generated short url is " + shorturl); 
        System.out.println("Id from url is " + shortURLtoID(shorturl)); 
    }
}