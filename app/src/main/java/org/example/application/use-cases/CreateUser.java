import org.example.model.User;

public class CreateUser {
    public boolean createUserCase (String username, String email, String password) {
        
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        new User(id, username, email, password);
        return true;
    }
}