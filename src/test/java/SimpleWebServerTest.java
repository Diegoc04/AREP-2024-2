import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import org.example.model.Request;
import org.example.model.SimpleWebServer;
import static org.example.model.SimpleWebServer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleWebServerTest {

    @BeforeEach
    public void setUp() {
        SimpleWebServer.services.clear();
    }

    @Test
    public void testStaticFiles() {
        SimpleWebServer.staticfiles("classes/webroot");
        assertEquals("target/classes/webroot", SimpleWebServer.webRoot);
    }

    @Test
    public void testStaticFilesChangeLocation() {
        SimpleWebServer.staticfiles("classes/webroot");
        assertEquals("target/classes/webroot", SimpleWebServer.webRoot);

        SimpleWebServer.staticfiles("another/location");
        assertEquals("target/another/location", SimpleWebServer.webRoot);
    }

    @Test
    public void testStaticFilesEmptyPath() {
        SimpleWebServer.staticfiles("");
        assertEquals("target/", SimpleWebServer.webRoot);
    }
    
    @Test
    public void testStaticFilesAbsolutePath() {
        String absolutePath = "/absolute/path/to/webroot";
        SimpleWebServer.staticfiles(absolutePath);
        assertEquals("target/" + absolutePath, SimpleWebServer.webRoot);
    }
    
    @Test
    public void testStaticFilesPathWithSpecialCharacters() {
        SimpleWebServer.staticfiles("webroot@123");
        assertEquals("target/webroot@123", SimpleWebServer.webRoot);
    }
    
    @Test
    public void testGetService() {
        SimpleWebServer.get("/test", (req, res) -> "Test response");
        assertTrue(SimpleWebServer.services.containsKey("/test"));
    }

    @Test
    public void testNonExistentService() {
        assertFalse(SimpleWebServer.services.containsKey("/nonExistent"));
    }  
}