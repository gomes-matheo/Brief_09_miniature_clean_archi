package org.example.core.repository;

import java.util.List;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;

public interface ICommentRepository {

    List<Comment> getCommentsFromPost(Post post);

    void addComment(Comment comment);

    void removeComment(Comment comment);

    List<Comment> getComments();

}
