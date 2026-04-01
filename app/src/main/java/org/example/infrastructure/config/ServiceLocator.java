package org.example.infrastructure.config;

import org.example.application.usecases.*;
import org.example.infrastructure.persistence.*;
import org.example.core.repository.*;

public class ServiceLocator {

    private static ServiceLocator instance;

    private final ICommentRepository COMMENT_REPO;
    private final IFollowerRepository FOLLOWER_REPO;
    private final ILikeRepository LIKE_REPO;
    private final IPostRepository POST_REPO;
    private final IUserRepository USER_REPO;

    private final AuthenticateUserCase AUTH_USER;
    private final CreatePostCase CREATE_POST;
    private final CreateUserCase CREATE_USER;
    private final RetrieveFeedCase RETRIEVE_FEED;
    private final LikeCase TOGGLE_LIKE;
    
    private ServiceLocator() {
        this.COMMENT_REPO = new CommentRepository();
        this.FOLLOWER_REPO = new FollowerRepository();
        this.LIKE_REPO = new LikeRepository();
        this.POST_REPO = new PostRepository(FOLLOWER_REPO);
        this.USER_REPO = new UserRepository();

        this.AUTH_USER = new AuthenticateUserCase(USER_REPO);
        this.CREATE_POST = new CreatePostCase(POST_REPO);
        this.CREATE_USER = new CreateUserCase(USER_REPO);
        this.RETRIEVE_FEED = new RetrieveFeedCase(POST_REPO);
        this.TOGGLE_LIKE = new LikeCase(LIKE_REPO);
    }

    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public AuthenticateUserCase getAuthenticateUserCase() {
        return AUTH_USER;
    }

    public CreatePostCase getCreatePostCase() {
        return CREATE_POST;
    }

    public CreateUserCase getCreateUserCase() {
        return CREATE_USER;
    }

    public RetrieveFeedCase getRetrieveFeedCase() {
        return RETRIEVE_FEED;
    }

    public LikeCase getToggleLikeCase() {
        return TOGGLE_LIKE;
    }
}