import org.example.Core.repository.ILikeRepository;

public class ToggleLikeUseCase {
    private final ILikeRepository likeRepository;

    public ToggleLikeUseCase(ILikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void execute(long userId, long postId) {
        likeRepository.toggleLike(userId, postId);
    }
}
