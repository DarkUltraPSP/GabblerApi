package app.hesias.gabbler.Service.User;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Result.UserResult;
import app.hesias.gabbler.Repository.UserRepo;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
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
    public UserResult getUserByUuid(int id) {
        User user = userRepo.findById(id).orElse(null);
        return UserResult.builder()
                .user(user)
                .requestStatus(user != null ? RequestStatus.OK : RequestStatus.NOT_FOUND)
                .build();
    }

    @Override
    public UserResult createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        if (userRepo.findByUsername(user.getUsername()) != null) return UserResult.builder().requestStatus(RequestStatus.CONFLICT).build();
        userRepo.save(user);
        return UserResult.builder()
                .user(user)
                .requestStatus(RequestStatus.CREATED)
                .build();
    }

    @Override
    public UserResult updateUser(int id, User user) {
        User userToUpdate = userRepo.findById(id).orElse(null);
        if (userToUpdate != null) {
            if (userRepo.findByUsername(user.getUsername()) != null) return UserResult.builder().requestStatus(RequestStatus.CONFLICT).build();
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
        return UserResult.builder()
                .user(userToUpdate)
                .requestStatus(userToUpdate != null ? RequestStatus.OK : RequestStatus.NOT_FOUND)
                .build();
    }

    @Override
    public UserResult deleteUser(int id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            userRepo.delete(user);
        }
        return UserResult.builder()
                .user(user)
                .requestStatus(user != null ? RequestStatus.OK : RequestStatus.NOT_FOUND)
                .build();
    }
}