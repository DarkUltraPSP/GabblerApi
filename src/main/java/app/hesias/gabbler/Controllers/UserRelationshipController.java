package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.User;
import app.hesias.gabbler.Model.UserRelationship;
import app.hesias.gabbler.Service.UserRelationship.UserRelationshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user-relationship")
@AllArgsConstructor
public class UserRelationshipController {
    UserRelationshipService userRelationshipService;

    @PostMapping
    public ResponseEntity<UserRelationship> userRelationship(@RequestBody Map<String, User> usersMap) {
        User user1 = usersMap.get("user1");
        User user2 = usersMap.get("user2");

        UserRelationship userRelationship = userRelationshipService.getByUser1AndUser2(user1, user2);
        return userRelationship != null ? ResponseEntity.ok(userRelationship) : ResponseEntity.notFound().build();
    }

}
