package co.escuelaing.edu.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import co.escuelaing.edu.model.Post;

@Service
public class StreamService {

    @Autowired
    private MongoClient mongoClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamService.class);

    public List<Post> getStream() {
        List<Post> posts = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Post post = new Post();
                post.setId(document.getString("id"));
                post.setUsername(document.getString("username"));
                post.setCreationDate(
                        document.getDate("creationDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                post.setContent(document.getString("content"));
                posts.add(post);
            }
        } finally {
            cursor.close();
        }
        LOGGER.info("Getting posts...");
        return posts;
    }

    public Document addPost(Post post) {
        String id = UUID.randomUUID().toString();
        post.setId(id);
        Document document = new Document()
                .append("id", post.getId())
                .append("username", post.getUsername())
                .append("creationDate", LocalDate.now())
                .append("content", post.getContent());
        LOGGER.info("Adding post to stream: {} -- {}", post.getId(), post.getUsername());
        getCollection().insertOne(document);
        return document;
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("springtwitter").getCollection("stream");
    }

}
