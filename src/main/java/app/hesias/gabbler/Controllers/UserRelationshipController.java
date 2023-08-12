package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.UserRelationship;
import app.hesias.gabbler.Service.UserRelationship.UserRelationshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-relationship")
@AllArgsConstructor
public class UserRelationshipController {
    UserRelationshipService userRelationshipService;

    @GetMapping
    public ResponseEntity<UserRelationship> usersRelationship(@RequestParam int idUser1, @RequestParam int idUser2) {
        UserRelationship userRelationship = userRelationshipService.getByUser1AndUser2(idUser1, idUser2);
        return userRelationship != null ? ResponseEntity.ok(userRelationship) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserRelationship> createUserRelationship(@RequestBody UserRelationship userRelationship) {
        if (userRelationshipService.getByUser1AndUser2(userRelationship.getUser1().getIdUser(), userRelationship.getUser2().getIdUser()) != null) return ResponseEntity.status(409).build();

        UserRelationship toCreate = userRelationshipService.createUserRelationship(userRelationship);
        return toCreate != null ? ResponseEntity.created(null).body(toCreate) : ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<UserRelationship> updateUserRelationship(@RequestBody UserRelationship userRelationship) {
        UserRelationship toUpdate = userRelationshipService.updateUserRelationship(userRelationship);
        return toUpdate != null ? ResponseEntity.ok(toUpdate) : ResponseEntity.notFound().build();
    }

     @DeleteMapping
    public ResponseEntity<UserRelationship> deleteUserRelationship(@RequestBody UserRelationship userRelationship) {
         if (userRelationshipService.deleteUserRelationship(userRelationship) == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok().build();
    }
}
