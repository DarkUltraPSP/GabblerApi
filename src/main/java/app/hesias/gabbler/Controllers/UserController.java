package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.User;
import app.hesias.gabbler.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByUuid(@PathVariable int id) {
        return userService.getUserByUuid(id) != null ? ResponseEntity.ok(userService.getUserByUuid(id)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User toCreate = userService.createUser(user);
        return toCreate != null ? ResponseEntity.created(URI.create(toCreate.toString())).build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User toUpdate = userService.updateUser(id, user);
        return toUpdate != null ? ResponseEntity.ok(toUpdate) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
