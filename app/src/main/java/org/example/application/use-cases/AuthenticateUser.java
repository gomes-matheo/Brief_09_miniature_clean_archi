import java.util.Optional;

import org.example.Core.entities.User;
import org.example.Core.repository.IUserRepository;

public class AuthenticateUser {
    private final IUserRepository repository;

    public AuthenticateUser(IUserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> execute(String username, String password) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
