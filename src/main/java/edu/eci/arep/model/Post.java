package edu.eci.arep.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

public class Post {

    private String id;
    private String username;
    private LocalDate creationDate;
    @Size(max = 140)
    private String content;

    public Post() {
    }

    public Post(String username, LocalDate creationDate, String content) {
        this.username = username;
        this.creationDate = creationDate;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
