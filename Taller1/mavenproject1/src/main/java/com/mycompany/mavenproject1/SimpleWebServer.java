package com.mycompany.mavenproject1;

import java.io.*;
import java.net.*;

public class SimpleWebServer {
    private static final int PORT = 8080;
    public static final String WEB_ROOT = "target/classes/webroot"; // Cambia la ruta si es necesario

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
        }
    }
    

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream())) {

            String requestLine = in.readLine();
            if (requestLine == null) return;

            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String fileRequested = tokens[1];

            // Diferenciar entre REST y archivos
            if (fileRequested.startsWith("/hello") || fileRequested.startsWith("/hellopost")) {
                if (method.equals("GET")) {
                    handleGetRequest(fileRequested, out, dataOut);
                } else if (method.equals("POST")) {
                    handlePostRequest(fileRequested, in, out);
                }
            } else {
                // Si no es REST, trata de servirlo como un archivo estático
                serveStaticFile(fileRequested, out, dataOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleGetRequest(String fileRequested, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        if (fileRequested.startsWith("/hello")) {
            // Manejo de la solicitud GET a /hello
            String[] parts = fileRequested.split("\\?");
            String name = parts.length > 1 ? parts[1].split("=")[1] : "Guest";
            String response = "Hello, " + name + "!";

            // Enviar la respuesta
            out.println("HTTP/1.1 200 OK");
            out.println("Content-type: text/plain");
            out.println();
            out.flush();
            out.println(response);
            out.flush();
        } else if (fileRequested.equals("/")) {
            // Manejo de la solicitud GET para la raíz
            File file = new File(WEB_ROOT, "index.html");
            if (file.exists()) {
                int fileLength = (int) file.length();
                byte[] fileData = readFileData(file, fileLength);
                String content = getContentType(file.getName());

                out.println("HTTP/1.1 200 OK");
                out.println("Content-type: " + content);
                out.println("Content-length: " + fileLength);
                out.println();
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
            } else {
                // Archivo no encontrado
                out.println("HTTP/1.1 404 Not Found");
                out.println("Content-type: text/html");
                out.println();
                out.flush();
                out.println("<html><body><h1>404 File Not Found</h1></body></html>");
                out.flush();
            }
        } else {
            // Manejar archivos estáticos
            File file = new File(WEB_ROOT, fileRequested);
            if (file.exists()) {
                int fileLength = (int) file.length();
                byte[] fileData = readFileData(file, fileLength);
                String content = getContentType(fileRequested);

                out.println("HTTP/1.1 200 OK");
                out.println("Content-type: " + content);
                out.println("Content-length: " + fileLength);
                out.println();
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
            } else {
                // Archivo no encontrado
                out.println("HTTP/1.1 404 Not Found");
                out.println("Content-type: text/html");
                out.println();
                out.flush();
                out.println("<html><body><h1>404 File Not Found</h1></body></html>");
                out.flush();
            }
        }
    }
    

    private static void handlePostRequest(String fileRequested, BufferedReader in, PrintWriter out) throws IOException {
        if (fileRequested.startsWith("/hellopost")) {
            // Manejo de la solicitud POST a /hellopost
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                requestBody.append(line).append("\n");
            }

            // Extraer el parámetro 'name'
            String[] parts = fileRequested.split("\\?");
            String name = parts.length > 1 ? parts[1].split("=")[1] : "Guest";
            String response = "POST recibido: " + name;

            // Enviar la respuesta
            out.println("HTTP/1.1 200 OK");
            out.println("Content-type: text/plain");
            out.println();
            out.flush();
            out.println(response);
            out.flush();
        } else {
            // Lógica de manejo de otras rutas POST
            out.println("HTTP/1.1 404 Not Found");
            out.println("Content-type: text/html");
            out.println();
            out.flush();
            out.println("<html><body><h1>404 File Not Found</h1></body></html>");
            out.flush();
        }
    }
    
    private static void serveStaticFile(String fileRequested, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        if (fileRequested.equals("/")) {
            fileRequested = "/index.html";  // Si no se solicita nada, por defecto, usa index.html
        }

        File file = new File(WEB_ROOT, fileRequested);
        if (file.exists() && !fileRequested.startsWith("/hello") && !fileRequested.startsWith("/hellopost")) {
            int fileLength = (int) file.length();
            byte[] fileData = readFileData(file, fileLength);
            String content = getContentType(fileRequested);

            out.println("HTTP/1.1 200 OK");
            out.println("Content-type: " + content);
            out.println("Content-length: " + fileLength);
            out.println();
            out.flush();

            dataOut.write(fileData, 0, fileLength);
            dataOut.flush();
        } else {
            // Archivo no encontrado
            out.println("HTTP/1.1 404 Not Found");
            out.println("Content-type: text/html");
            out.println();
            out.flush();
            out.println("<html><body><h1>404 File Not Found</h1></body></html>");
            out.flush();
        }
    }

    private static byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null) fileIn.close();
        }
        return fileData;
    }

    private static String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".html")) return "text/html";
        else if (fileRequested.endsWith(".css")) return "text/css";
        else if (fileRequested.endsWith(".js")) return "application/javascript";
        else if (fileRequested.endsWith(".png")) return "image/png";
        else if (fileRequested.endsWith(".jpg")) return "image/jpeg";
        return "text/plain";
    }
}
