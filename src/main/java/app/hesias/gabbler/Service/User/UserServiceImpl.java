package app.hesias.gabbler.Service.User;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Result.UserResult;
import app.hesias.gabbler.Repository.UserRepo;
import com.google.common.hash.Hashing;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserResult getUserByUuid(int id) {
        try {
            User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            return UserResult.builder()
                    .user(user)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return UserResult.builder()
                    .requestStatus(RequestStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    public UserResult createUser(User user) {
        try {
            userRepo.findByUsername(user.getUsername()).orElseThrow(EntityExistsException::new);
            userRepo.save(user);
            return UserResult.builder()
                    .user(user)
                    .requestStatus(RequestStatus.CREATED)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityExistsException) {
                return UserResult.builder()
                        .requestStatus(RequestStatus.CONFLICT)
                        .build();
            } else {
                return UserResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @Override
    public UserResult updateUser(int id, User user) {
        try {
            User userToUpdate = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            User userWithSameUsername = userRepo.findByUsername(user.getUsername()).orElse(null);
            if (userWithSameUsername != null) {
                throw new EntityExistsException("User with the same username already exists");
            }

            modelMapper.map(user, userToUpdate);
            userRepo.save(userToUpdate);

            return UserResult.builder()
                    .user(userToUpdate)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return UserResult.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else if (e instanceof EntityExistsException) {
                return UserResult.builder()
                        .requestStatus(RequestStatus.CONFLICT)
                        .build();
            } else {
                return UserResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @Override
    public UserResult deleteUser(int id) {
        try {
            User userToDelete = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            userRepo.delete(userToDelete);
            return UserResult.builder()
                    .user(userToDelete)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return UserResult.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return UserResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }
}