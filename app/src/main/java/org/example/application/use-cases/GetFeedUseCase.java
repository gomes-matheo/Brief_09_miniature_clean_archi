import java.util.List;

import org.example.Core.entities.Post;
import org.example.Core.repository.IPostRepository;

public class GetFeedUseCase {
    private final IPostRepository postRepository;

    public GetFeedUseCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> execute(long userId, String mode) {
        return postRepository.getPosts();
    }

    public List<Post> execute(String mode) {
        List<Post> posts = postRepository.getPosts();
        if ("abonnements".equals(mode)) {
            posts = postRepository.getPosts();
        } else {
            posts = postRepository.getPosts();
        }
        return posts;
    }

}
