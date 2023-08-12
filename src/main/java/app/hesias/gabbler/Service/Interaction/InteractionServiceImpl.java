package app.hesias.gabbler.Service.Interaction;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Repository.InteractionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    private final InteractionRepo interactionRepo;

    @Override
    public List<Interaction> getAllInteractions() {
        return interactionRepo.findAll();
    }

    @Override
    public Interaction getInteractionById(int id) {
        return interactionRepo.findById(id).orElse(null);
    }

    @Override
    public Interaction createInteraction(Interaction interaction) {
        interactionRepo.save(interaction);
        return interaction;
    }

    @Override
    public Interaction updateInteraction(int id, Interaction interaction) {
        Interaction interactionToUpdate = interactionRepo.findById(id).orElse(null);
        if (interactionToUpdate != null) {
            interactionToUpdate.setUser(interaction.getUser());
            interactionToUpdate.setGab(interaction.getGab());
            interactionToUpdate.setInteractionType(interaction.getInteractionType());
            interactionRepo.save(interactionToUpdate);
        }
        return interactionToUpdate;
    }

    @Override
    public Interaction deleteInteraction(int id) {
        Interaction interactionToDelete = interactionRepo.findById(id).orElse(null);
        if (interactionToDelete != null) {
            interactionRepo.delete(interactionToDelete);
        }
        return interactionToDelete;
    }
}
