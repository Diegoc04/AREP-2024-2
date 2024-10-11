package com.escuelaing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.escuelaing.model.User;
import com.escuelaing.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/blackjack")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/Users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    
    @PostMapping("/register")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "Nuevo usuario añadido";

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Buscar al usuario por email en la base de datos
        User existingUser = userService.findUserByEmail(user.getEmail());

        // Verificar si el usuario existe y la contraseña coincide
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}






    // Otros métodos (actualizar, eliminar) pueden ser añadidos aquí


