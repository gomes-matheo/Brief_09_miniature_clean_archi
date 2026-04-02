package org.example.core.repository;

import java.util.List;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;

public interface ICommentRepository {

    void addComment(Post fromPost, Comment comment);

    void removeComment(Post fromPost, Comment comment);

    List<Comment> getCommentsFromPost(Post fromPost);


}
