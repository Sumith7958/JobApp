package com.JobAppCom.JobAppCom.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;

    public JwtService() {
        this.secretKey = generateSecretKey();
    }

    // Generate the secret key
    public String generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSha256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key: " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    // Generate JWT token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3)) // 3 minutes
                .signWith(getKey())
                .compact();
    }

    // Decode base64 secret key
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract username (subject) from token
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Generic method to extract a claim using a resolver function
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Parse the token and extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Validate token against user details and expiration
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
