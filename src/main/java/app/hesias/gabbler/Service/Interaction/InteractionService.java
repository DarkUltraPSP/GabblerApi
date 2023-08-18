package app.hesias.gabbler.Service.Interaction;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Result.InteractionResult;
import app.hesias.gabbler.Model.Result.InteractionResults;

import java.util.List;

public interface InteractionService {
    InteractionResults getInteractionByGab(int idGab);
    InteractionResults getInteractionByUser(int idUser);
    InteractionResults getInteractionTypeByIdGab(String type, int idGab);
    InteractionResult getInteractionById(int id);
    InteractionResult getInteractionByGabByUser(int idGab, int idUser);
    InteractionResult createInteraction(Interaction interaction);
    InteractionResult updateInteraction(Interaction interaction);
    InteractionResult deleteInteraction(Interaction interaction);
}
