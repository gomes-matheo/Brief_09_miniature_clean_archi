package org.example.application.usecases;

import java.util.List;

import org.example.core.entities.Post;
import org.example.core.repository.IPostRepository;
import org.example.presentation.dto.PostDTO;
import org.example.util.PostMapper;

public class RetrieveFeedCase {
    private final IPostRepository postRepository;

    public RetrieveFeedCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> execute(long userId, String mode) {
        List<Post> posts = "abonnements".equals(mode)
            ? postRepository.findByFollowed(userId)
            : postRepository.findAll();

        return posts.stream()
            .map(PostMapper::toDTO)
            .toList();
    }
}