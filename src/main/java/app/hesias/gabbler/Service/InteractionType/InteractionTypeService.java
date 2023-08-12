package app.hesias.gabbler.Service.InteractionType;

import app.hesias.gabbler.Model.Entity.InteractionType;

public interface InteractionTypeService {
    InteractionType createInteractionType(InteractionType interactionType);
    InteractionType getInteractionTypeByLibelle(String libelle);
}
