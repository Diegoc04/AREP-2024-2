package co.edu.escuelaing;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

@RestController
public class LogServiceController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/log")
    public ResponseEntity<Void> logMessage(@RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        System.out.println("Received message: " + content);

        try {
            messageService.saveMessage(content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getLastMessages();
    }
}
