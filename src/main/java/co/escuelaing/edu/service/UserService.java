package co.escuelaing.edu.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import co.escuelaing.edu.model.User;

@Service
public class UserService {

    @Autowired
    private MongoClient mongoClient;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @jakarta.annotation.PostConstruct
    public void init() {
        addUser(new User("diego", "diejoto"));
        addUser(new User("daniel", "RulosS"));
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setUsername(document.getString("username"));
                user.setPassword("************");
                users.add(user);
            }
        } finally {
            cursor.close();
        }
        LOGGER.info("Getting users...");
        return users;
    }

    public Document addUser(User user) {
        Document document = new Document()
                .append("username", user.getUsername())
                .append("password", hashOfPassword(user.getPassword()));
        LOGGER.info("Adding user: {}", document);
        getCollection().insertOne(document);
        return document;
    }

    public boolean verifyPassword(String username, String password) {
        Document document = getCollection().find(new Document("username", username)).first();
        if (document != null) {
            String hashedPassword = document.getString("password");
            return hashedPassword.equals(hashOfPassword(password));
        }
        return false;
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("Twitter").getCollection("User");
    }

    private String hashOfPassword(String password) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-256");
            byte[] hash = mda.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Error hashing password", e);
        }
        return null;
    }

}
