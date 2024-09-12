import co.edu.escuelaing.LoadBalancerService;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoadBalancerServiceTest {

    @Test
    public void testGetNextLogServiceUrl() {
        LoadBalancerService loadBalancerService = new LoadBalancerService();

        // Test initial state
        assertEquals("http://localhost:35001/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35002/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35003/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35001/log", loadBalancerService.getNextLogServiceUrl()); // Rota de vuelta al inicio
    }
    
    @Test
    public void testMultipleCalls() {
        LoadBalancerService loadBalancerService = new LoadBalancerService();

        // Call multiple times and ensure it cycles through URLs correctly
        assertEquals("http://localhost:35001/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35002/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35003/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35001/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35002/log", loadBalancerService.getNextLogServiceUrl());
        assertEquals("http://localhost:35003/log", loadBalancerService.getNextLogServiceUrl());
    }

    @Test
    public void testInitializationState() {
        LoadBalancerService loadBalancerService = new LoadBalancerService();
        // Get the URL at initial state
        assertEquals("http://localhost:35001/log", loadBalancerService.getNextLogServiceUrl());

        // Simulate some use and then test again
        loadBalancerService.getNextLogServiceUrl(); // Moves to http://localhost:35002/log
        loadBalancerService.getNextLogServiceUrl(); // Moves to http://localhost:35003/log
        
        // Ensure it starts at the beginning of the cycle again
        assertEquals("http://localhost:35001/log", loadBalancerService.getNextLogServiceUrl());
    }

    @Test
    public void testCycleCount() {
        LoadBalancerService loadBalancerService = new LoadBalancerService();

        // Call the method enough times to complete multiple cycles
        for (int i = 0; i < 10; i++) {
            String expectedUrl = "http://localhost:35001/log";
            if (i % 3 == 1) {
                expectedUrl = "http://localhost:35002/log";
            } else if (i % 3 == 2) {
                expectedUrl = "http://localhost:35003/log";
            }
            assertEquals(expectedUrl, loadBalancerService.getNextLogServiceUrl());
        }
    }
}