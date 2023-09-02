package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.InteractionResult;
import app.hesias.gabbler.Model.Result.InteractionResults;
import app.hesias.gabbler.Service.Interaction.InteractionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interaction")
@AllArgsConstructor
public class InteractionController {
    InteractionService interactionService;

    @GetMapping
    public ResponseEntity<InteractionResult> getInteractionById(int id) {
        InteractionResult interactionResult = interactionService.getInteractionById(id);
        if (interactionResult.getRequestStatus() == RequestStatus.OK) {
            return ResponseEntity.ok(interactionResult);
        } else {
            return ResponseEntity.status(interactionResult.getRequestStatus().getValue()).body(interactionResult);
        }
    }

    @GetMapping("/gab/{idGab}")
    public ResponseEntity<List<Interaction>> getInteractionByGab(@PathVariable int idGab) {
        InteractionResults interactionResults = interactionService.getInteractionByGab(idGab);
        return interactionResults.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(interactionResults.getRequestStatus().getValue()).body(interactionResults.getInteractions())
                : ResponseEntity.status(interactionResults.getRequestStatus().getValue()).build();
    }

    @GetMapping("/{interactionType}/{idGab}")
    public ResponseEntity<InteractionResults> getInteractionTypeByIdGab(@PathVariable String interactionType, @PathVariable int idGab) {
        InteractionResults interactionResults = interactionService.getInteractionTypeByIdGab(interactionType, idGab);
        if (interactionResults.getRequestStatus() == RequestStatus.OK) {
            return ResponseEntity.ok(interactionResults);
        } else {
            return ResponseEntity.status(interactionResults.getRequestStatus().getValue()).body(interactionResults);
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<InteractionResults> getInteractionByUser(@PathVariable int idUser) {
        InteractionResults interactionResults = interactionService.getInteractionByUser(idUser);
        if (interactionResults.getRequestStatus() == RequestStatus.OK) {
            return ResponseEntity.ok(interactionResults);
        } else {
            return ResponseEntity.status(interactionResults.getRequestStatus().getValue()).body(interactionResults);
        }
    }

    @PostMapping
    public ResponseEntity<InteractionResult> createInteraction(@RequestBody Interaction interaction) {
        InteractionResult interactionResult = interactionService.createInteraction(interaction);
        if (interactionResult.getRequestStatus() == RequestStatus.OK) {
            return ResponseEntity.ok(interactionResult);
        } else {
            return ResponseEntity.status(interactionResult.getRequestStatus().getValue()).body(interactionResult);
        }
    }

    @PutMapping
    public ResponseEntity<InteractionResult> updateInteraction(@RequestBody Interaction interaction) {
        InteractionResult interactionResult = interactionService.updateInteraction(interaction);
        if (interactionResult.getRequestStatus() == RequestStatus.OK) {
            return ResponseEntity.ok(interactionResult);
        } else {
            return ResponseEntity.status(interactionResult.getRequestStatus().getValue()).body(interactionResult);
        }
    }

    @DeleteMapping
    public ResponseEntity<InteractionResult> deleteInteraction(@RequestBody Interaction interaction) {
        InteractionResult interactionResult = interactionService.deleteInteraction(interaction);
        if (interactionResult.getRequestStatus() == RequestStatus.OK) {
            return ResponseEntity.ok(interactionResult);
        } else {
            return ResponseEntity.status(interactionResult.getRequestStatus().getValue()).body(interactionResult);
        }
    }
}
