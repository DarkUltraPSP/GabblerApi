package app.hesias.gabbler.Service.InteractionType;

import app.hesias.gabbler.Model.InteractionType;
import app.hesias.gabbler.Repository.InteractionTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InteractionTypeServiceImpl implements InteractionTypeService{
    private final InteractionTypeRepo interactionTypeRepository;
    @Override
    public InteractionType createInteractionType(InteractionType interactionType) {
        return interactionTypeRepository.save(interactionType);
    }

    @Override
    public InteractionType getInteractionTypeByLibelle(String libelle) {
        return interactionTypeRepository.findByLibelle(libelle);
    }
}
