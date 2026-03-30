package org.example.model;

import java.time.LocalDateTime;

public class Comment {

    private long id;
    private long postId;
    private User owner;
    private String content;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public long getPostId() {
        return postId;
    }

    public User getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
