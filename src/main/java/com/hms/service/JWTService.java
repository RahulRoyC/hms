package com.hms.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hms.repo.AppUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int duration;

    private Algorithm algorithm;
    private final AppUserRepository appUserRepository;

    public JWTService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withClaim("name", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token) {
        DecodedJWT verify = JWT.
                require(algorithm).
                withIssuer(issuer).
                build().
                verify(token);
        return verify.getClaim("name").asString();
    }
}