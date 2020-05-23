package br.com.api.movies.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtilSecurity {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * generateToken
     *
     * @param value
     * @return
     */
    public String generateToken(String value) {
        return Jwts.builder().setSubject(value)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    /**
     * isValidToken
     *
     * @param token
     * @return
     */
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            String userLogin = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date localDate = new Date(System.currentTimeMillis());

            if (userLogin != null && expiration != null && localDate.before(expiration)) {
                return true;
            }
        }

        return false;
    }

    /**
     * getUserLogin
     *
     * @param token
     * @return
     */
    public String getUserLogin(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

    /**
     * getClaims
     *
     * @param token
     * @return
     */
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }

        catch (Exception e) {
            return null;
        }
    }
}