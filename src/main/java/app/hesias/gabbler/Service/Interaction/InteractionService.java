package app.hesias.gabbler.Service.Interaction;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Result.InteractionResult;
import app.hesias.gabbler.Model.Result.InteractionResults;

import java.util.List;

public interface InteractionService {
    InteractionResults getInteractionByGab(int idGab);
    InteractionResults getInteractionByUser(int idUser);
    InteractionResult getInteractionById(int id);
    InteractionResult createInteraction(Interaction interaction);
    InteractionResult updateInteraction(int id, Interaction interaction);
    InteractionResult deleteInteraction(int id);
}
