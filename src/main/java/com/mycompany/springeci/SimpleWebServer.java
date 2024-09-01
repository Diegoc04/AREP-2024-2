package com.mycompany.springeci;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleWebServer {
    
    private static final int PORT = 8080;
    public static String webRoot = "";
    public static Map<String, Service> services = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(PORT);

        // Define some services
        staticfiles("classes/webroot");
        get("/test", (req, res) -> "Test route is working");
        get("/hi", (req, resp) -> "Hello world");
        get("/pi", (req, resp) -> String.valueOf(Math.PI));
        get("/hello", (req, res) -> "hello " + req.getValues("name", ""));
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new ClientHandler(clientSocket));
        }
    }

    // Método para definir la ubicación de los archivos estáticos
    public static void staticfiles(String folder) {
        webRoot = "target/" + folder;
    }
    
    public static void get(String url, Service s) {
    services.put(url, s);
    }

    // Define the Service interface
    public interface Service {
        String getValues(Request req, String response);
    }

    // Define the ClientHandler class
    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream())) {

                String requestLine = in.readLine();
                if (requestLine == null) return;
                String[] tokens = requestLine.split(" ");
                String method = tokens[0];
                String fileRequested = tokens[1];

                if (method.equals("GET")) {
                    handleGetRequest(fileRequested, out, dataOut);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleGetRequest(String fileRequested, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        // Extrae la ruta base (sin los parámetros de consulta)
        String[] pathAndQuery = fileRequested.split("\\?");
        String path = pathAndQuery[0];
        String query = pathAndQuery.length > 1 ? pathAndQuery[1] : null;

        // Verifica si la ruta base coincide con algún servicio registrado
        if (services.containsKey(path)) {
                // Crea un objeto Request y Response
                Request req = new Request(fileRequested); // Pasa la ruta completa


                // Llama al servicio registrado
                String response = services.get(path).getValues(req, "");

                // Envía la respuesta
                out.println("HTTP/1.1 200 OK");
                out.println("Content-type: text/plain");
                out.println("Content-length: " + response.length());
                out.println();
                out.flush();
                dataOut.write(response.getBytes());
                dataOut.flush();
            } else {
                // Manejo de archivos estáticos
                File file = new File(webRoot, path); // Usa solo la ruta base para buscar el archivo
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
                    out.println("<html><body><h1>File Not Found</h1></body></html>");
                    out.flush();
                }
            }
        }

        private String getContentType(String fileRequested) {
            if (fileRequested.endsWith(".html")) return "text/html";
            else if (fileRequested.endsWith(".css")) return "text/css";
            else if (fileRequested.endsWith(".js")) return "application/javascript";
            else if (fileRequested.endsWith(".png")) return "image/png";
            else if (fileRequested.endsWith(".jpg")) return "image/jpeg";
            else if (fileRequested.endsWith(".json")) return "application/json";
            return "text/plain";
        }

        private byte[] readFileData(File file, int fileLength) throws IOException {
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
    }
}