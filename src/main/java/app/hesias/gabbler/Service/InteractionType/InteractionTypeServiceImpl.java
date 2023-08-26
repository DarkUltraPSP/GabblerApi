package app.hesias.gabbler.Service.InteractionType;

import app.hesias.gabbler.Model.Entity.InteractionType;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.InteractionTypeResult;
import app.hesias.gabbler.Repository.InteractionTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InteractionTypeServiceImpl implements InteractionTypeService{
    private final InteractionTypeRepo interactionTypeRepository;
    @Override
    public InteractionTypeResult createInteractionType(InteractionType interactionType) {
        try {
            interactionTypeRepository.save(interactionType);
            return new InteractionTypeResult(interactionType, RequestStatus.CREATED);
        } catch (Exception e) {
            return new InteractionTypeResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InteractionTypeResult getInteractionTypeByLibelle(String libelle) {
        try {
            InteractionType interactionType = interactionTypeRepository.findByLibelle(libelle).orElseThrow(Exception::new);
            return new InteractionTypeResult(interactionType, RequestStatus.OK);
        } catch (Exception e) {
            return new InteractionTypeResult(null, RequestStatus.NOT_FOUND);
        }
    }
}
