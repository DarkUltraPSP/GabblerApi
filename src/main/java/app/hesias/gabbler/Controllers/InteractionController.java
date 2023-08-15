package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.InteractionResult;
import app.hesias.gabbler.Service.Interaction.InteractionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interaction")
@AllArgsConstructor
public class InteractionController {
    InteractionService interactionService;


}
