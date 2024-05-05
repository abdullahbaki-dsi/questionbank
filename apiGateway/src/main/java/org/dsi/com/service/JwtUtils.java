package org.dsi.com.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Service
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public boolean isExpired(String token) {
        log.info("get isExpired called with token: " + token);
        try {

            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}
