package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.entity.Customer;
import com.canngos.shoppingbasketservice.exception.BusinessException;
import com.canngos.shoppingbasketservice.exception.TransactionCode;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    private final String secret;
    private static final long TOKEN_VALIDITY = (long)60*24*14; // Token valid for 14 days

    private final JwtParser jwtParser;

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public JwtService(@Value("${jwt.secret}") String secret){
        this.secret = secret;
        this.jwtParser = Jwts.parser().setSigningKey(secret);
    }

    public String generateToken(Customer customer) {
        Claims claims = Jwts.claims().setSubject(customer.getEmail());
        claims.put("firstName",customer.getName());
        claims.put("lastName",customer.getLastName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(TOKEN_VALIDITY));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Optional<Claims> resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return Optional.ofNullable(parseJwtClaims(token));
            }
            return Optional.empty();
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw new BusinessException(TransactionCode.TOKEN_EXPIRED);
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw new BusinessException(TransactionCode.TOKEN_INVALID);
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public Date extractExpiration(String token) {
        Claims claims = parseJwtClaims(token);
        return claims.getExpiration();
    }

    public String extractUsername(String token) {
        Claims claims = parseJwtClaims(token);
        return claims.getSubject();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
