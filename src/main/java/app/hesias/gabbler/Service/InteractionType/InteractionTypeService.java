package app.hesias.gabbler.Service.InteractionType;

import app.hesias.gabbler.Model.Entity.InteractionType;
import app.hesias.gabbler.Model.Result.InteractionTypeResult;

public interface InteractionTypeService {
    InteractionTypeResult createInteractionType(InteractionType interactionType);
    InteractionTypeResult getInteractionTypeByLibelle(String libelle);
}
