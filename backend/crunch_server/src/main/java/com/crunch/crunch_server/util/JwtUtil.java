package com.crunch.crunch_server.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    
    private Key key;
    private long validityInMilliseconds;

    public JwtUtil(String secret)
    {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMilliseconds = 60;

    }

    public String createToken(String identity, String salt)
    {
        Date now = new Date();
        Date validity = new Date(now.getTime()
            + validityInMilliseconds);


        String token = Jwts.builder()
            .claim("Identity", identity)
            .claim("salt", salt)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

        return token;
    }
}
