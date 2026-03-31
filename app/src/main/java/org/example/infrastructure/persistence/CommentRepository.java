package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.Core.entities.Comment;

public class CommentRepository {
    private List<Comment> comments = new CopyOnWriteArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}
