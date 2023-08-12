package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.InteractionType;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class InteractionTypeResult {
    private InteractionType interactionType;
    private RequestStatus requestStatus;
}
