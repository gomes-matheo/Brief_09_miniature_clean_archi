package org.example.Core.usecase;

import org.example.Core.repository.IntPostRepository;
import org.example.model.User;

public class CreatePostUseCase {
    private final IntPostRepository postRepository;

    public CreatePostUseCase(IntPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(User author, String content) throws IllegalArgumentException {

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Le contenu du post ne peut pas être vide.");
        }

        postRepository.save(author, content);
    }

}
