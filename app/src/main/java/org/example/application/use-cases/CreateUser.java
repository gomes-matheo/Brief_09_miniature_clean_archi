import org.example.Core.entities.User;
import org.example.Core.exceptions.EntityAlreadyExistsException;
import org.example.Core.repository.IUserRepository;

public class CreateUser {
    private IUserRepository userRepo;

    private boolean isEmailAvailable(String email) {
        return userRepo.getUsers().stream().anyMatch(u -> u.getUsername().equals(email));
    }

    public void createUserSuccess (String username, String email, String password) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("The registering form isn't complete !");
        } else if (!isEmailAvailable(email)) {
            throw new EntityAlreadyExistsException("This email already exists !");
        } else {
            new User(username, email, password);
        }
    }
}