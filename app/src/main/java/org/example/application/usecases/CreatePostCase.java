package org.example.application.usecases;

import org.example.core.entities.User;
import org.example.core.repository.IPostRepository;

public class CreatePostCase {
    private final IPostRepository postRepository;

    public CreatePostCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void sendPost(User author, String content) throws IllegalArgumentException {

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Le contenu du post ne peut pas être vide.");
        }

        postRepository.save(author, content);
    }

}
