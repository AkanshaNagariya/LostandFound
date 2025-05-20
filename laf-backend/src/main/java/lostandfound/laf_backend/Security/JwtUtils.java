package lostandfound.laf_backend.Security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret; // Base64-encoded string from properties

    @Value("${jwt.expiration.ms}")
    private long expirationMs;

    // Decode the Base64 secret to bytes
    private byte[] getSecretKeyBytes() {
        return Base64.getDecoder().decode(secret);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, getSecretKeyBytes()) // Use decoded bytes
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKeyBytes()) // Use decoded bytes
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKeyBytes()) // Use decoded bytes
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}