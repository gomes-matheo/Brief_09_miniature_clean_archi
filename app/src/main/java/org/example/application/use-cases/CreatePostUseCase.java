import org.example.Core.entities.User;
import org.example.Core.repository.IPostRepository;

public class CreatePostUseCase {
    private final IPostRepository postRepository;

    public CreatePostUseCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(User author, String content) throws IllegalArgumentException {

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Le contenu du post ne peut pas être vide.");
        }

        postRepository.save(author, content);
    }

}
