package edu.eci.arep.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import edu.eci.arep.model.User;
import edu.eci.arep.services.UserService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GenerateToken {

    @Inject
    UserService userService;

    public TokenDto createToken(User user) {
        if (userService.verifyPassword(user.getUsername(), user.getPassword())) {
            String token = Jwt.issuer("http://localhost:8080/issuer")
            .upn(user.getUsername())
            .groups(new HashSet<>(Arrays.asList("User", "Admin")))
            .claim(Claims.birthdate.name(), "2001-07-13")
            .sign();    
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
