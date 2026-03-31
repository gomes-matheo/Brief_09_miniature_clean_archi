package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.Core.entities.Comment;

public class CommentRepository {
    private List<Comment> comments = new CopyOnWriteArrayList<>();
    private static final CommentRepository COMMENT_REPO_INSTANCE = new CommentRepository();

    private CommentRepository() {}

    public static CommentRepository getInstance() {
        return COMMENT_REPO_INSTANCE;
    }

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
