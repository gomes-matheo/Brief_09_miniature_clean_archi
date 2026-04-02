package org.example.application.usecases;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.ICommentRepository;

public class CreateCommentCase {
    private final ICommentRepository commentRepo;

    public CreateCommentCase(ICommentRepository commentRepository) {
        this.commentRepo = commentRepository;
    }

    public void sendComment(Post parent, User author, String content) throws IllegalArgumentException {

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Le contenu du post ne peut pas être vide.");
        }
    }
}
