package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import app.hesias.gabbler.Model.Result.UserRelationshipResult;
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
    public ResponseEntity<UserRelationship> getUser1And2Relationship (@RequestParam int idUser1, @RequestParam int idUser2) {
        UserRelationshipResult userRelationship = userRelationshipService.getByUser1AndUser2(idUser1, idUser2);
        return userRelationship.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(userRelationship.getRequestStatus().getValue()).body(userRelationship.getUserRelationship())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserRelationship> createUserRelationship(@RequestBody UserRelationship userRelationship) {
        UserRelationshipResult toCreate = userRelationshipService.createUserRelationship(userRelationship);
        return toCreate.getRequestStatus() == RequestStatus.CREATED ?
                ResponseEntity.status(toCreate.getRequestStatus().getValue()).body(toCreate.getUserRelationship())
                : ResponseEntity.status(toCreate.getRequestStatus().getValue()).build();
    }

    @PutMapping
    public ResponseEntity<UserRelationship> updateUserRelationship(@RequestBody UserRelationship userRelationship) {
        UserRelationshipResult toUpdate = userRelationshipService.updateUserRelationship(userRelationship);
        return toUpdate.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(toUpdate.getRequestStatus().getValue()).body(toUpdate.getUserRelationship())
                : ResponseEntity.status(toUpdate.getRequestStatus().getValue()).build();
    }

     @DeleteMapping
    public ResponseEntity<UserRelationship> deleteUserRelationship(@RequestBody UserRelationship userRelationship) {
        UserRelationshipResult userRelationshipResult = userRelationshipService.deleteUserRelationship(userRelationship);
        return userRelationshipResult.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(userRelationshipResult.getRequestStatus().getValue()).body(userRelationshipResult.getUserRelationship())
                : ResponseEntity.status(userRelationshipResult.getRequestStatus().getValue()).build();
    }
}
