package org.example.util;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.example.model.Comment;
import org.example.model.Post;
import org.example.model.User;

public class DataStore {

    

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public User createUser(String username, String email, String password) {
        User u = new User();
        u.setId(userIdSeq.getAndIncrement());
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setCreatedAt(LocalDateTime.now());
        users.add(u);
        return u;
    }

    public Optional<User> findUserByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public boolean existsUserByUsernameOrEmail(String username, String email) {
        return users.stream().anyMatch(u -> u.getUsername().equals(username) || u.getEmail().equals(email));
    }

    public Post createPost(User owner, String content) {
        Post p = new Post();
        p.setId(postIdSeq.getAndIncrement());
        p.setOwner(owner);
        p.setContent(content);
        p.setCreatedAt(LocalDateTime.now());
        posts.add(p);
        return p;
    }

    public Optional<Post> findPostById(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Post> getAllPosts() {
        return posts.stream()
                .filter(p -> !p.isDraft())
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public List<Post> getPostsFromFollowed(long followerId) {
        Set<Long> followedIds = follows.stream()
                .filter(f -> f.startsWith(followerId + ":"))
                .map(f -> Long.parseLong(f.split(":")[1]))
                .collect(Collectors.toSet());
        return posts.stream()
                .filter(p -> !p.isDraft() && followedIds.contains(p.getOwner().getId()))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public int likeCount(long postId) {
        return (int) likes.stream().filter(l -> l.endsWith(":" + postId)).count();
    }

    public Comment createComment(long postId, User author, String content) {
        Comment c = new Comment();
        c.setId(commentIdSeq.getAndIncrement());
        c.setPostId(postId);
        c.setOwner(author);
        c.setContent(content);
        c.setCreatedAt(LocalDateTime.now());
        comments.add(c);
        return c;
    }

    public List<Comment> getCommentsForPost(long postId) {
        return comments.stream()
                .filter(c -> c.getPostId() == postId)
                .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public void toggleLike(long userId, long postId) {
        String key = userId + ":" + postId;
        if (!likes.remove(key)) {
            likes.add(key);
        }
    }

    public boolean hasLiked(long userId, long postId) {
        return likes.contains(userId + ":" + postId);
    }

    public boolean isFollowing(long followerId, long followingId) {
        return follows.contains(followerId + ":" + followingId);
    }
}
