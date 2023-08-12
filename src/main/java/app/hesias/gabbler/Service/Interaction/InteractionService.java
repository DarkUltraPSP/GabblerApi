package app.hesias.gabbler.Service.Interaction;

import app.hesias.gabbler.Model.Entity.Interaction;

import java.util.List;

public interface InteractionService {
    List<Interaction> getAllInteractions();
    Interaction getInteractionById(int id);
    Interaction createInteraction(Interaction interaction);
    Interaction updateInteraction(int id, Interaction interaction);
    Interaction deleteInteraction(int id);
}
