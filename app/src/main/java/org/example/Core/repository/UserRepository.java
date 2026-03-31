package org.example.Core.repository;
import java.util.Optional;

import org.example.model.User;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
