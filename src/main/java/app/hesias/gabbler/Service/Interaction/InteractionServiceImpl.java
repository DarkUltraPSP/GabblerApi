package app.hesias.gabbler.Service.Interaction;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.InteractionResult;
import app.hesias.gabbler.Model.Result.InteractionResults;
import app.hesias.gabbler.Repository.GabRepo;
import app.hesias.gabbler.Repository.InteractionRepo;
import app.hesias.gabbler.Repository.UserRepo;
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
            return new InteractionResults(interactions, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new InteractionResults(null, RequestStatus.NOT_FOUND);
            } else {
                return new InteractionResults(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public InteractionResults getInteractionByUser(int idUser) {
        try {
            userRepo.findById(idUser).orElseThrow(EntityNotFoundException::new);
            List<Interaction> interactions = interactionRepo.findAllByUserId(idUser).orElseThrow(EntityNotFoundException::new);
            return new InteractionResults(interactions, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new InteractionResults(null, RequestStatus.NOT_FOUND);
            } else {
                return new InteractionResults(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public InteractionResults getInteractionTypeByIdGab(String type, int idGab) {
        try {
            gabRepo.findById(idGab).orElseThrow(EntityNotFoundException::new);
            List<Interaction> interactions = interactionRepo.findByTypeByGabs(type, idGab).orElseThrow(EntityNotFoundException::new);
            return new InteractionResults(interactions, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new InteractionResults(null, RequestStatus.NOT_FOUND);
            } else {
                return new InteractionResults(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public InteractionResult getInteractionById(int id) {
        try {
            Interaction interaction = interactionRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            return new InteractionResult(interaction, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InteractionResult(null, RequestStatus.NOT_FOUND);
        }
    }

    @Override
    public InteractionResult getInteractionByGabByUser(int idGab, int idUser) {
        try {
            gabRepo.findById(idGab).orElseThrow(EntityNotFoundException::new);
            userRepo.findById(idUser).orElseThrow(EntityNotFoundException::new);
            Interaction interaction = interactionRepo.findByGabByUser(idGab, idUser).orElseThrow(EntityNotFoundException::new);
            return new InteractionResult(interaction, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new InteractionResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new InteractionResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public InteractionResult createInteraction(Interaction interaction) {
        try {
            interactionRepo.save(interaction);
            return new InteractionResult(interaction, RequestStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new InteractionResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InteractionResult updateInteraction(Interaction interaction) {
        try {
            Interaction interactionToUpdate = interactionRepo.findByGabByUser(interaction.getGab().getIdGab(), interaction.getUser().getIdUser()).orElseThrow(EntityNotFoundException::new);

            modelMapper.map(interaction, interactionToUpdate);
            interactionRepo.save(interactionToUpdate);
            return new InteractionResult(interactionToUpdate, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new InteractionResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new InteractionResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public InteractionResult deleteInteraction(Interaction interaction) {
        try {
            Interaction interactionToDelete = interactionRepo.findByGabByUser(interaction.getGab().getIdGab(), interaction.getUser().getIdUser()).orElseThrow(EntityNotFoundException::new);
            interactionRepo.delete(interactionToDelete);
            return new InteractionResult(interactionToDelete, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new InteractionResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new InteractionResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
