package org.example.infrastructure.persistence;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;
import org.example.core.repository.ICommentRepository;

public class CommentRepository implements ICommentRepository {
    private Map<Post, Set<Comment>> comments = new HashMap<>();

    public CommentRepository() {}

    @Override
    public void addComment(Post fromPost, Comment comment) {
        comments.get(fromPost).add(comment);
    }

    @Override
    public void removeComment(Post fromPost, Comment comment) {
        comments.get(fromPost).remove(comment);
    }

    @Override
    public List<Comment> getCommentsFromPost(Post fromPost) {
        return comments.get(fromPost).stream()
        .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
        .toList();
    }
}
