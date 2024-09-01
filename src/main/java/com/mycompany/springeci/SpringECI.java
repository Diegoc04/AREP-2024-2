package com.mycompany.springeci;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

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
    private static final int PORT = 8080;
    public static final String WEB_ROOT = "target/classes/webroot";
    
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, IOException {
        // Verificar si se proporcionaron argumentos
        if (args.length == 0) {
            System.out.println("Error: Debes proporcionar el nombre de la clase controlador como argumento.");
            return;
        }
        Class c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap();
        

        
        //Cargar componentes
        if(c.isAnnotationPresent(RestController.class)){
            Method[] methods = c.getDeclaredMethods();
            for(Method m: methods){
                if(m.isAnnotationPresent(GetMapping.class)){
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }
        
        // Escuchar solicitudes HTTP
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor escuchando en puerto 8080...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Leer solicitud HTTP
            String requestLine = in.readLine();
            if (requestLine != null) {
                System.out.println("Solicitud recibida: " + requestLine);
                String[] requestParts = requestLine.split(" ");
                String path = requestParts[1]; // Obtén el path de la solicitud
                String servicename = path.substring(4); // Asumiendo que el prefijo es "/App/"
                System.out.println("Service name: " + servicename);

                Method ms = services.get(servicename);
                if (ms != null) {
                    String response = (String) ms.invoke(null); // Invocar método
                    // Enviar respuesta HTTP
                    out.write("HTTP/1.1 200 OK\r\n");
                    out.write("Content-Type: text/plain\r\n");
                    out.write("\r\n");
                    out.write(response);
                } else {
                    // Responder con 404 si el servicio no existe
                    out.write("HTTP/1.1 404 Not Found\r\n");
                    out.write("Content-Type: text/plain\r\n");
                    out.write("\r\n");
                    out.write("Servicio no encontrado");
                }
                out.flush();
            }
            clientSocket.close();
        }
    }
    
    private static void serveStaticFile(String path, OutputStream out) throws IOException {
        File file = new File(WEB_ROOT, path);
        if (file.exists() && !file.isDirectory()) {
            String contentType = URLConnection.guessContentTypeFromName(file.getName());

            if (contentType == null) {
                contentType = "application/octet-stream"; // Tipo por defecto
            }

            // Enviar cabeceras HTTP
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Content-Type: " + contentType + "\r\n");
            writer.write("Content-Length: " + file.length() + "\r\n");
            writer.write("\r\n");
            writer.flush();

            // Enviar contenido del archivo
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            fis.close();
        } else {
            // Archivo no encontrado
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("HTTP/1.1 404 Not Found\r\n");
            writer.write("Content-Type: text/plain\r\n");
            writer.write("\r\n");
            writer.write("Archivo no encontrado");
            writer.flush();
        }
    }
 
}
