package org.example.infrastructure.persistence;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.IFollowerRepository;
import org.example.core.repository.IPostRepository;

public class PostRepository implements IPostRepository{
    
    private List<Post> posts = new CopyOnWriteArrayList<>();
    private final IFollowerRepository followerRepository;

    public PostRepository(IFollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public void save(User author, String content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Post> findPostById(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Post> getPostsFromUser() {
        return posts.stream()
                .filter(p -> !p.isDraft())
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostsFromFollowed(User subscribedUser) {
        Set<User> followed = followerRepository.getFollows(subscribedUser);
        return posts.stream().filter(p -> followed.contains(p.getOwner().equals(followed))).toList();
    }
}
