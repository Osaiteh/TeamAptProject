package com.glory.teamaptproject.security;

import com.glory.teamaptproject.exception.ExpiredTokenException;
import com.glory.teamaptproject.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String tokenSecret;

    public JwtTokenProvider(@Value("${app.jwtSecret}") String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String generateToken(String subject) throws Exception {
        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, tokenSecret)
                    .compact();
        } catch (Exception e) {
            throw new Exception(String.format("Error generating token for %s", subject), e);
        }
    }

    public String generateTokenValidForPeriod(String subject, int jwtExpirationInMs) throws Exception {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS256, tokenSecret)
                    .compact();
        } catch (Exception e) {
            throw new Exception("Error generating token", e);
        }
    }

    public Long extractIdFromToken(String token) throws InvalidTokenException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            throw new InvalidTokenException("Unable to extract id from token", e);
        }
    }

    public void validateToken(String authToken) throws ExpiredTokenException, InvalidTokenException {
        try {
            Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(authToken);
        } catch (ExpiredJwtException ex) {
            throw new ExpiredTokenException(ex);
        } catch (Exception ex) {
            throw new InvalidTokenException(ex);
        }
    }
}