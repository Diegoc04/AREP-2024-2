package co.escuelaing.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.escuelaing.edu.model.User;
import co.escuelaing.edu.security.jwt.GenerateToken;
import co.escuelaing.edu.security.jwt.GenerateToken.TokenDto;

@RestController
@RequestMapping("/secured")
public class TokenSecuredResource {

    @Autowired
    private GenerateToken tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDto> login(@RequestBody User user) {
        TokenDto token = tokenService.createToken(user);
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

