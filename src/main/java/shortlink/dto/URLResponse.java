package shortlink.dto;

public class UrlResponse {
    private String url;

    public UrlResponse(String url) {
        this.url = url;
    }

    // Getter
    public String getUrl() {
        return url;
    }
}