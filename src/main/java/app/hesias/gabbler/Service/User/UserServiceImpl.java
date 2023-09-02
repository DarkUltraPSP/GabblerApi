package app.hesias.gabbler.Service.User;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Result.JwtResult;
import app.hesias.gabbler.Model.Result.UserResult;
import app.hesias.gabbler.Repository.UserRepo;
import app.hesias.gabbler.utils.Security.JwtUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserResult getUserByUuid(int id) {
        try {
            User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            return new UserResult(user, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new UserResult(null, RequestStatus.NOT_FOUND);
        }
    }

    @Override
    public UserResult createUser(User user) {
        try {
            Optional<User> existingUser = userRepo.findByUsername(user.getUsername());
            Optional<User> existingEmail = userRepo.findByEmail(user.getEmail());
            if (existingUser.isPresent() || existingEmail.isPresent()) {
                throw new EntityExistsException("User with the same username already exists");
            }
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return new UserResult(user, RequestStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityExistsException) {
                return new UserResult(null, RequestStatus.CONFLICT);
            } else {
                return new UserResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public JwtResult login(String username, String password) {
        try {
            User user = userRepo.findByUsernameAndPassword(username, password).orElseThrow(EntityNotFoundException::new);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            return new JwtResult(jwt, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException) {
                return new JwtResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new JwtResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public UserResult signup(User user) {
        try {
            userRepo.findByUsername(user.getUsername()).orElseThrow(EntityExistsException::new);
            userRepo.findByEmail(user.getEmail()).orElseThrow(EntityExistsException::new);
            userRepo.save(user);
            return new UserResult(user, RequestStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityExistsException) {
                return new UserResult(null, RequestStatus.CONFLICT);
            } else {
                return new UserResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
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

            return new UserResult(userToUpdate, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new UserResult(null, RequestStatus.NOT_FOUND);
            } else if (e instanceof EntityExistsException) {
                return new UserResult(null, RequestStatus.CONFLICT);
            } else {
                return new UserResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public UserResult deleteUser(int id) {
        try {
            User userToDelete = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            userRepo.delete(userToDelete);
            return new UserResult(userToDelete, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new UserResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new UserResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}