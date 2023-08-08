package app.hesias.gabbler.Service.InteractionType;

import app.hesias.gabbler.Model.InteractionType;

import java.util.List;

public interface InteractionTypeService {
    InteractionType createInteractionType(InteractionType interactionType);
    InteractionType getInteractionTypeByLibelle(String libelle);
}
