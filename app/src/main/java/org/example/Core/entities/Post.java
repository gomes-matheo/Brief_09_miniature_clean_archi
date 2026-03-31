package org.example.core.entities;

import java.time.LocalDateTime;

public class Post {

    private long id;
    private User owner;
    private Post parent;
    private String content;
    private LocalDateTime createdAt;
    private boolean isDraft;
    private int likeCount;

    public Post() {
    }

    public long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public Post getParent() {
        return parent;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
