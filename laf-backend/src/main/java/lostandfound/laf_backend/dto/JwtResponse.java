package lostandfound.laf_backend.dto;


public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() { return token; }
}
