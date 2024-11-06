package co.escuelaing.edu.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import co.escuelaing.edu.model.User;
import co.escuelaing.edu.service.UserService;

import java.util.Date;
import java.util.UUID;

@Service
public class GenerateToken {

    @Autowired
    private UserService userService;

    private static final String SECRET_KEY = "mySecretKey";

    public TokenDto createToken(User user) {
        if (userService.verifyPassword(user.getUsername(), user.getPassword())) {
            String token = Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setSubject(user.getUsername())
                    .setIssuer("https://server.example.com")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                    .claim("groups", new HashSet<>(Arrays.asList("User", "Admin")))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
            return new TokenDto(token);
        }
        return null;
    }

    public class TokenDto {

        private String token;

        public TokenDto(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
