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
            return InteractionTypeResult.builder()
                    .interactionType(interactionType)
                    .build();
        } catch (Exception e) {
            return InteractionTypeResult.builder()
                    .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @Override
    public InteractionTypeResult getInteractionTypeByLibelle(String libelle) {
        try {
            InteractionType interactionType = interactionTypeRepository.findByLibelle(libelle).orElseThrow(Exception::new);
            return InteractionTypeResult.builder()
                    .interactionType(interactionType)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            return InteractionTypeResult.builder()
                    .requestStatus(RequestStatus.NOT_FOUND)
                    .build();
        }
    }
}
