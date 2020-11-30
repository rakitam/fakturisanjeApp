package sf.posinf.fakturisanje.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenHelper {

    private String secret = "Fakture";
    private Long expiration = 8L;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        //Dodatni podaci iz tokena
        final Claims claims = getClaimsFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, generateExpirationDate());
    }

    private String generateToken(UserDetails userDetails, Date expiredOn) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date(System.currentTimeMillis()));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiredOn)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(getCurrentTimeMillis() + this.expiration * 60 * 60 * 1000);
    }

    private long getCurrentTimeMillis() {
        return new Date().getTime();
    }
}
