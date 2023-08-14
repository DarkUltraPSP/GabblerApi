package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class InteractionResult {
    private Interaction interaction;
    private RequestStatus requestStatus;
}
