package shortlink.dto;

public class URLResponse {
    private String url;

    public URLResponse(String url) {
        this.url = url;
    }

    // Getter
    public String getUrl() {
        return url;
    }
}