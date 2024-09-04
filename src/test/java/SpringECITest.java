/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diego
 */
import com.mycompany.springeci.HelloService;
import com.mycompany.springeci.SpringECI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpringECITest {

    private SpringECI server;
    private Socket mockSocket;
    private ByteArrayOutputStream outStream;

    /**
     * Configura el entorno de prueba antes de cada prueba.
     * - Crea una instancia del servidor SpringECI.
     * - Inicializa el servidor con el controlador HelloService.
     * - Crea un socket simulado (mock) para evitar conexiones de red reales.
     * - Configura la solicitud simulada para realizar una petición GET a /App/hello.
     */
    @BeforeEach
    public void setUp() throws Exception {
        server = new SpringECI();
        server.init("com.mycompany.springeci.HelloService");

        mockSocket = mock(Socket.class);
        outStream = new ByteArrayOutputStream();

        // Simula la entrada y salida del socket para la prueba
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("GET /App/hello HTTP/1.1\r\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(outStream);
    }

    /**
     * Prueba que el servidor maneje correctamente una solicitud a /App/hello.
     * - Verifica que la respuesta del servidor contiene "200 OK" y "Hello World".
     */
    @Test
    public void testHandleClient_Hello() throws Exception {
        server.handleClient(mockSocket);

        String response = outStream.toString();
        assertTrue(response.contains("200 OK"));
        assertTrue(response.contains("Hello World"));
    }

    /**
     * Prueba que el servidor maneje correctamente una solicitud a /App/pi.
     * - Simula una solicitud GET a /App/pi.
     * - Verifica que la respuesta del servidor contiene "200 OK" y "3.1416".
     */
    @Test
    public void testHandleClient_Pi() throws Exception {
        // Cambia la solicitud simulada para probar la ruta /App/pi
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("GET /App/pi HTTP/1.1\r\n".getBytes()));
        server.handleClient(mockSocket);

        String response = outStream.toString();
        assertTrue(response.contains("200 OK"));
        assertTrue(response.contains("3.1416"));
    }

    /**
     * Prueba que el servidor maneje correctamente una solicitud para obtener una imagen.
     * - Lee el contenido del archivo imagen.png.
     * - Simula una solicitud GET a /App/imagen.
     * - Verifica que la respuesta contiene "200 OK" y que el cuerpo de la respuesta es igual al contenido del archivo.
     */
    @Test
    public void testHandleClient_Imagen() throws Exception {
        Path path = Paths.get("src/main/java/resources/webroot/imagen.png");
        byte[] fileContent = Files.readAllBytes(path);

        // Simula la solicitud GET a /App/imagen
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("GET /App/imagen HTTP/1.1\r\n".getBytes()));
        server.handleClient(mockSocket);

        byte[] response = outStream.toByteArray();
        assertTrue(new String(response).contains("200 OK"));
        assertArrayEquals(fileContent, extractResponseBody(response));
    }

    /**
     * Prueba que se lance una excepción cuando se intenta inicializar el servidor con una clase de controlador inválida.
     * - Verifica que se lanza ClassNotFoundException con el mensaje adecuado.
     */
    @Test
    public void testInit_InvalidControllerClass() {
        Exception exception = assertThrows(ClassNotFoundException.class, () -> {
            server.init("InvalidClassName");
        });
        assertTrue(exception.getMessage().contains("InvalidClassName"));
    }

    /**
     * Prueba la preparación de argumentos para métodos con parámetros de consulta.
     * - Simula un método greet que acepta un parámetro "name".
     * - Verifica que los parámetros de consulta se asignan correctamente a los argumentos del método.
     */
    @Test
    public void testPrepareMethodArguments_WithQueryParams() throws Exception {
        Method greetMethod = HelloService.class.getMethod("greet", String.class);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "John");

        Object[] args = server.prepareMethodArguments(greetMethod, queryParams);
        assertEquals(1, args.length);
        assertEquals("John", args[0]);
    }

    /**
     * Método de utilidad para extraer el cuerpo de la respuesta en formato binario.
     * - Encuentra el final de las cabeceras HTTP y devuelve solo el cuerpo de la respuesta.
     */
    private byte[] extractResponseBody(byte[] response) {
        int headerEnd = findHeaderEnd(response);
        return java.util.Arrays.copyOfRange(response, headerEnd, response.length);
    }

    /**
     * Método de utilidad para encontrar el final de los encabezados HTTP.
     * - Busca la secuencia CRLF CRLF que indica el final de las cabeceras.
     */
    private int findHeaderEnd(byte[] response) {
        for (int i = 0; i < response.length - 3; i++) {
            if (response[i] == '\r' && response[i + 1] == '\n' &&
                response[i + 2] == '\r' && response[i + 3] == '\n') {
                return i + 4;
            }
        }
        return -1;
    }
}
