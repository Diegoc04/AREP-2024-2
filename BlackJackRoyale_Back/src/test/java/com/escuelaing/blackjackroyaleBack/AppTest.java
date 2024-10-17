package com.escuelaing.blackjackroyaleBack;


import com.escuelaing.controller.UserController;
import com.escuelaing.model.User;
import com.escuelaing.repository.UserRepository;
import com.escuelaing.service.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class AppTest {

    @InjectMocks
    private UserController userController; // Controlador a probar, inyectando dependencias simuladas.

    @Mock
    private UserService userService; // Servicio simulado que será utilizado por el controlador.

    private MockMvc mockMvc; // Para realizar pruebas de controladores.

    @BeforeEach
    public void setUp() {
        // Inicializa los mocks y configura MockMvc antes de cada prueba.
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    // Pruebas para UserController

    @Test
    public void testGetAllUsers() throws Exception {
        // Crea usuarios de prueba y define el comportamiento simulado del servicio.
        User user1 = new User("test1@example.com", "Test User 1", "password1");
        User user2 = new User("test2@example.com", "Test User 2", "password2");
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        // Realiza la petición GET a la ruta "/blackjack/Users" y verifica la respuesta.
        mockMvc.perform(get("/blackjack/Users"))
                .andExpect(status().isOk()) // Verifica que el estado HTTP es 200 (OK).
                .andExpect(content().contentType(APPLICATION_JSON)) // Verifica que el tipo de contenido es JSON.
                .andExpect(jsonPath("$[0].email").value("test1@example.com")) // Verifica el email del primer usuario.
                .andExpect(jsonPath("$[1].email").value("test2@example.com")); // Verifica el email del segundo usuario.

        verify(userService, times(1)).getAllUsers(); // Verifica que el método getAllUsers fue llamado una vez.
    }

    @Test
    public void testAddUser() throws Exception {
        // Crea un usuario de prueba para la prueba de adición.
        User user = new User("test@example.com", "Test User", "password");

        // Realiza la petición POST para registrar un nuevo usuario y verifica la respuesta.
        mockMvc.perform(post("/blackjack/register")
                .contentType(APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"name\":\"Test User\", \"password\":\"password\"}"))
                .andExpect(status().isOk()) // Verifica que el estado HTTP es 200 (OK).
                .andExpect(content().string("Nuevo usuario añadido")); // Verifica el mensaje de éxito.

        verify(userService, times(1)).saveUser(any(User.class)); // Verifica que el método saveUser fue llamado una vez.
    }

    @Test
    public void testLoginSuccessful() throws Exception {
        // Crea un usuario de prueba y define el comportamiento simulado para un inicio de sesión exitoso.
        User user = new User("test@example.com", "Test User", "password");
        when(userService.findUserByEmail(user.getEmail())).thenReturn(user);

        // Realiza la petición POST para el inicio de sesión y verifica la respuesta.
        mockMvc.perform(post("/blackjack/login")
                .contentType(APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isOk()) // Verifica que el estado HTTP es 200 (OK).
                .andExpect(content().string("Login successful")); // Verifica el mensaje de éxito en el inicio de sesión.

        verify(userService, times(1)).findUserByEmail(user.getEmail()); // Verifica que el método findUserByEmail fue llamado una vez.
    }

    @Test
    public void testLoginInvalid() throws Exception {
        // Crea un usuario de prueba y simula que no existe en el sistema.
        User user = new User("test@example.com", "Test User", "password");
        when(userService.findUserByEmail(user.getEmail())).thenReturn(null);

        // Realiza la petición POST para el inicio de sesión con credenciales incorrectas y verifica la respuesta.
        mockMvc.perform(post("/blackjack/login")
                .contentType(APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized()) // Verifica que el estado HTTP es 401 (Unauthorized).
                .andExpect(content().string("Invalid email or password")); // Verifica el mensaje de error.

        verify(userService, times(1)).findUserByEmail(user.getEmail()); // Verifica que el método findUserByEmail fue llamado una vez.
    }

    // Pruebas para UserService

    @Test
    public void testUserServiceGetAllUsers() {
        // Crea usuarios de prueba y define el comportamiento simulado del servicio.
        User user1 = new User("test1@example.com", "Test User 1", "password1");
        User user2 = new User("test2@example.com", "Test User 2", "password2");
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        // Llama al método getAllUsers y verifica el resultado.
        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size()); // Verifica que el tamaño de la lista es 2.
        verify(userService, times(1)).getAllUsers(); // Verifica que el método getAllUsers fue llamado una vez.
    }

    @Test
    public void testUserServiceSaveUser() {
        // Crea un usuario de prueba para la prueba de guardado.
        User user = new User("test@example.com", "Test User", "password");

        // Simula el comportamiento del método saveUser.
        when(userService.saveUser(user)).thenReturn(user);

        // Llama al método saveUser y verifica el resultado.
        User result = userService.saveUser(user);
        assertEquals(user, result); // Verifica que el resultado es el usuario guardado.
        verify(userService, times(1)).saveUser(user); // Verifica que el método saveUser fue llamado una vez.
    }

    @Test
    public void testUserServiceFindUserByEmail() {
        // Crea un usuario de prueba y simula su búsqueda por correo.
        User user = new User("test@example.com", "Test User", "password");
        when(userService.findUserByEmail(user.getEmail())).thenReturn(user);

        // Llama al método findUserByEmail y verifica el resultado.
        User result = userService.findUserByEmail(user.getEmail());
        assertEquals(user, result); // Verifica que el resultado es el usuario encontrado.
        verify(userService, times(1)).findUserByEmail(user.getEmail()); // Verifica que el método findUserByEmail fue llamado una vez.
    }
}
