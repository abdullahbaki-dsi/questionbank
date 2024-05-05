package org.dsi.com.userService.service.Implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.dto.response.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;

    private Key key;

    @PostConstruct
    public void initKey() {
        if(secret == null || secret.isEmpty())
            throw new IllegalArgumentException("Secret cannot be null or empty");
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    private String generate(String userId, String userName, String tokenType) {
        Map<String, String> claims = Map.of("id", userId, "userName",userName);
        long expMillis = "ACCESS".equalsIgnoreCase(tokenType)
                ? Long.parseLong(expiration) * 1000
                : Long.parseLong(expiration) * 1000 * 5;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    private boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public Token generateResponseToken(UserResponseDto user) {
       String accessToken= generate(user.getUserID().toString(), user.getUserName(), "ACCESS");
       String refreshToken= generate(user.getUserID().toString(), user.getUserName(), "REFRESH");
       Token.TokenBuilder builder = Token.builder();
       builder.accessToken(accessToken)
              .refreshToken(refreshToken)
               .type("Authorization");
       return builder.build();
    }
}