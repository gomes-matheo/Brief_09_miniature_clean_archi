package org.example.infrastructure.persistence;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;
import org.example.core.repository.ICommentRepository;

public class CommentRepository implements ICommentRepository {
    private List<Comment> comments = new CopyOnWriteArrayList<>();

    public CommentRepository() {}

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public List<Comment> getCommentsFromPost(Post post) {
        return comments.stream()
                .filter(c -> c.getPostParent() == post)
                .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
                .toList();
    }
}
