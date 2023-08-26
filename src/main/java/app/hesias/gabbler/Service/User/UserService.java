package app.hesias.gabbler.Service.User;


import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Result.JwtResult;
import app.hesias.gabbler.Model.Result.UserResult;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    UserResult getUserByUuid(int id);
    UserResult createUser(User user);
    JwtResult login(String username, String password);
    UserResult updateUser(int id, User user);
    UserResult deleteUser(int id);
}
