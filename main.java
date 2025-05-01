public static void main (String[] args) throws IOException {
    int n = 12345; 
    String shorturl = idToShortURL(n); 
    System.out.println("Generated short url is " + shorturl); 
    System.out.println("Id from url is " + 
                        shortURLtoID(shorturl)); 
}