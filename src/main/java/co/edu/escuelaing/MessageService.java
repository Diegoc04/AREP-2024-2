package co.edu.escuelaing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setDate(new Date()); // Asegúrate de que el campo `date` se asigna correctamente
        try {
            messageRepository.save(message);
            System.out.println("Message saved successfully.");
        } catch (Exception e) {
            System.err.println("Failed to save message: " + e.getMessage());
        }
    }

    public List<Message> getLastMessages() {
        return messageRepository.findTop10ByOrderByDateDesc();
    }
}






