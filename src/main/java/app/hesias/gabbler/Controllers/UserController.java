package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Result.JwtResult;
import app.hesias.gabbler.Model.Result.UserResult;
import app.hesias.gabbler.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByUuid(@PathVariable int id) {
        UserResult userResult = userService.getUserByUuid(id);
        return userResult.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(userResult.getRequestStatus().getValue()).body(userResult.getUser())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        UserResult toCreate = userService.createUser(user);
        return toCreate.getRequestStatus() == RequestStatus.CREATED ?
                ResponseEntity.status(toCreate.getRequestStatus().getValue()).body(toCreate.getUser())
                : ResponseEntity.status(toCreate.getRequestStatus().getValue()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        UserResult toUpdate = userService.updateUser(id, user);
        return toUpdate.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(toUpdate.getRequestStatus().getValue()).body(toUpdate.getUser())
                : ResponseEntity.status(toUpdate.getRequestStatus().getValue()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        UserResult toDelete = userService.deleteUser(id);
        return toDelete.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(toDelete.getRequestStatus().getValue()).body(toDelete.getUser())
                : ResponseEntity.status(toDelete.getRequestStatus().getValue()).build();
    }
}
