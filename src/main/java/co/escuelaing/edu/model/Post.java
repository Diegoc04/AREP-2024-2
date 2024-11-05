package co.escuelaing.edu.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

/**
 * Represents a post made by a user.
 */
public class Post {

    private String id;
    private String username;
    private LocalDate creationDate;
    
    @Length(max = 140)
    private String content;

    public Post() {
    }

    public Post(String username, LocalDate creationDate, String content) {
        this.username = username;
        this.creationDate = creationDate;
        this.content = content;
    }

    // Getter and Setter for 'id'
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for 'username'
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for 'creationDate'
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    // Getter and Setter for 'content'
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null && content.length() > 140) {
            throw new IllegalArgumentException("Content length exceeds the maximum limit of 140 characters");
        }
        this.content = content;
    }
}
