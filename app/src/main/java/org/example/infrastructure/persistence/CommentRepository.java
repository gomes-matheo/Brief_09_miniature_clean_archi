package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.core.entities.Comment;
import org.example.core.repository.ICommentRepository;

public class CommentRepository implements ICommentRepository {
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
