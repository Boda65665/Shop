package ru.shop.com.Conrollers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JWT {
    public static void main(String[] args) {
        String jwt = jwt_create("pasaharpsuk@gmail.com");
        Claims data =  jwt_open(jwt).getBody();
        System.out.println(data.get("email"));

    }
    public static String jwt_create(String email){

        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        long now = new Date().getTime();

        String jwtToken = Jwts.builder()
                .claim("name", "Bogdan Kharashuke")
                .claim("email", email)
                .setSubject("auth")
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.ofEpochSecond(now)))
                .setExpiration(Date.from(Instant.ofEpochSecond(now + 3600000)))
                .signWith(hmacKey)
                .compact();
        System.out.println(jwtToken);
        return jwtToken;
    }
    public static Jws<Claims> jwt_open(String jwtString){
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);

        return jwt;
    }
}


