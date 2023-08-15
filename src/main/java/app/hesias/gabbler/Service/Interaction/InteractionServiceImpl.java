package app.hesias.gabbler.Service.Interaction;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.InteractionResult;
import app.hesias.gabbler.Model.Result.InteractionResults;
import app.hesias.gabbler.Repository.GabRepo;
import app.hesias.gabbler.Repository.InteractionRepo;
import app.hesias.gabbler.Repository.UserRepo;
import app.hesias.gabbler.Service.Gab.GabService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InteractionServiceImpl implements InteractionService {
    private final InteractionRepo interactionRepo;
    private final ModelMapper modelMapper;
    private final GabRepo gabRepo;
    private final UserRepo userRepo;

    @Override
    public InteractionResults getInteractionByGab(int idGab) {
        try {
            gabRepo.findById(idGab).orElseThrow(EntityNotFoundException::new);
            List<Interaction> interactions = interactionRepo.findAllByGabId(idGab).orElseThrow(EntityNotFoundException::new);
            return InteractionResults.builder()
                    .interactions(interactions)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return InteractionResults.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return InteractionResults.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @Override
    public InteractionResults getInteractionByUser(int idUser) {
        try {
            userRepo.findById(idUser).orElseThrow(EntityNotFoundException::new);
            List<Interaction> interactions = interactionRepo.findAllByUserId(idUser).orElseThrow(EntityNotFoundException::new);
            return InteractionResults.builder()
                    .interactions(interactions)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return InteractionResults.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return InteractionResults.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @Override
    public InteractionResult getInteractionById(int id) {
        try {
            Interaction interaction = interactionRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            return InteractionResult.builder()
                    .interaction(interaction)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return InteractionResult.builder()
                    .requestStatus(RequestStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    public InteractionResult createInteraction(Interaction interaction) {
        try {
            interactionRepo.save(interaction);
            return InteractionResult.builder()
                    .interaction(interaction)
                    .requestStatus(RequestStatus.CREATED)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return InteractionResult.builder()
                    .requestStatus(RequestStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    public InteractionResult updateInteraction(int id, Interaction interaction) {
        try {
            Interaction interactionToUpdate = interactionRepo.findById(id).orElseThrow(EntityNotFoundException::new);

            modelMapper.map(interaction, interactionToUpdate);
            interactionRepo.save(interactionToUpdate);
            return InteractionResult.builder()
                    .interaction(interactionToUpdate)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return InteractionResult.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return InteractionResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @Override
    public InteractionResult deleteInteraction(int id) {
        try {
            Interaction interactionToDelete = interactionRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            interactionRepo.delete(interactionToDelete);
            return InteractionResult.builder()
                    .interaction(interactionToDelete)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return InteractionResult.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return InteractionResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }
}
