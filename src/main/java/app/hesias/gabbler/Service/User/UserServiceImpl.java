package app.hesias.gabbler.Service.User;

import app.hesias.gabbler.Model.User;
import app.hesias.gabbler.Repository.UserRepo;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByUuid(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        userRepo.save(user);
        return user;
    }

    @Override
    public User updateUser(int id, User user) {
        User userToUpdate = userRepo.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setFirstname(user.getFirstname());
            userToUpdate.setLastname(user.getLastname());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setBirthdate(user.getBirthdate());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
            userToUpdate.setBiography(user.getBiography());
            userToUpdate.setProfilePictureUrl(user.getProfilePictureUrl());
            userToUpdate.setBannerPictureUrl(user.getBannerPictureUrl());
            userToUpdate.setActivated(user.isActivated());
            userToUpdate.setBanned(user.isBanned());
            userToUpdate.setPrivate(user.isPrivate());
            userRepo.save(userToUpdate);
        }
        return userToUpdate;
    }

    @Override
    public User deleteUser(int id) {
        User userToDelete = userRepo.findById(id).orElse(null);
        if (userToDelete != null) {
            userRepo.delete(userToDelete);
        }
        return userToDelete;
    }
}