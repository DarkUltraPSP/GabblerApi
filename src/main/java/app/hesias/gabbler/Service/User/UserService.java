package app.hesias.gabbler.Service.User;


import app.hesias.gabbler.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserByUuid(int id);
    User createUser(User user);
    User updateUser(int id, User user);
    User deleteUser(int id);
}
