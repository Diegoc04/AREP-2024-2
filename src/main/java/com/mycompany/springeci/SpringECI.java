package com.mycompany.springeci;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author diego.castellanos-a
 */
public class SpringECI {
   
    private Map<String, Method> services = new HashMap<>();
    private Object controllerInstance;

    public static void main(String[] args) {
        
        SpringECI server = new SpringECI();
        
        try {
            server.init(args[0]);
            server.startServer(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(String controllerClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> controllerClass = Class.forName(controllerClassName);
        if (controllerClass.isAnnotationPresent(RestController.class)) {
            controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            Method[] methods = controllerClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    String path = "/App" + method.getAnnotation(GetMapping.class).value();
                    services.put(path, method);
                }
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    String path = "/App" + method.getAnnotation(RequestMapping.class).value();
                    services.put(path, method);
                }
            }
        } else {
            System.out.println("La clase especificada no está anotada con @RestController.");
        }
    }

    public void startServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor escuchando en el puerto: " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
            clientSocket.close(); // Manejo no concurrente
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            System.out.println("Solicitud recibida: " + requestLine);
            String[] tokens = requestLine.split(" ");
            if (tokens.length < 2) {
                sendResponse(out, "400 Bad Request", "Bad Request");
                return;
            }

            String method = tokens[0];
            String path = tokens[1];

            if (!method.equals("GET")) {
                sendResponse(out, "405 Method Not Allowed", "Method Not Allowed");
                return;
            }

            String[] pathParts = path.split("\\?");
            String basePath = pathParts[0];
            Map<String, String> queryParams = new HashMap<>();

            if (pathParts.length > 1) {
                String queryString = pathParts[1];
                String[] params = queryString.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        queryParams.put(keyValue[0], keyValue[1]);
                    }
                }
            }

            // Añadir manejo del prefijo
            Method serviceMethod = services.get(basePath);
            if (serviceMethod != null) {
                Object[] methodArgs = prepareMethodArguments(serviceMethod, queryParams);
                Object result = serviceMethod.invoke(controllerInstance, methodArgs);

                if (result instanceof String) {
                    sendResponse(out, "200 OK", (String) result);
                } else if (result instanceof byte[]) {
                    String contentType = basePath.endsWith(".png") ? "image/png" : "text/html";
                    sendBinaryResponse(out, "200 OK", (byte[]) result, contentType);
                }
            } else {
                sendResponse(out, "404 Not Found", "Servicio no encontrado");
            }

        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    private void sendBinaryResponse(BufferedOutputStream out, String status, byte[] body, String contentType) throws IOException {
        out.write(("HTTP/1.1 " + status + "\r\n").getBytes());
        out.write(("Content-Type: " + contentType + "\r\n").getBytes());
        out.write(("Content-Length: " + body.length + "\r\n").getBytes());
        out.write("\r\n".getBytes()); // Línea en blanco separando cabeceras del cuerpo
        out.write(body);
        out.flush();
    }
    
    
    

    private void sendResponse(BufferedOutputStream out, String status, String body) throws IOException {
        out.write(("HTTP/1.1 " + status + "\r\n").getBytes());
        out.write("Content-Type: text/plain\r\n".getBytes());
        out.write(("Content-Length: " + body.length() + "\r\n").getBytes());
        out.write("\r\n".getBytes());
        out.write(body.getBytes());
        out.flush();
    }

    private Object[] prepareMethodArguments(Method method, Map<String, String> queryParams) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestParam.class)) {
                RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
                String paramName = requestParam.value();
                args[i] = queryParams.get(paramName);
            }
        }

        return args;
    }
}



